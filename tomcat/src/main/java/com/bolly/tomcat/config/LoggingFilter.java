package com.bolly.tomcat.config;

import com.bolly.support.utils.DateUtils;
import com.bolly.support.utils.JacksonUtils;
import com.bolly.support.utils.ObjectUtils;
import com.bolly.tomcat.controller.WebapiController;
import com.bolly.tomcat.core.MDCKeys;
import com.bolly.tomcat.core.RedisKeys;
import com.bolly.tomcat.core.SecurityContext;
import com.bolly.tomcat.servlet.ServletUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.UUID;


/**
 * 日志过滤器-记录请求log
 */
@Component
public class LoggingFilter implements Filter {
	private static final Logger LOGGER = LoggerFactory.getLogger("API1-LOGGER");
	private static final String PARAM_USER_ID = "userId";
    private static final String HEADER_X_HOSP_ID = "x_hospId";
    private static final String HEADER_X_FROM_ID = "x_fromId";

    private StringRedisTemplate redisTemplate;
    
    @Override
	public void init(FilterConfig filterConfig) throws ServletException {
    	ServletContext servletContext = filterConfig.getServletContext();
		ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		redisTemplate = ctx.getBean(StringRedisTemplate.class);
		WebapiController webapiController = ctx.getBean(WebapiController.class);
		System.out.println(webapiController);
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
			ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletRequestParseBodyWrapper requestWrapper = null;
		String queryString = httpServletRequest.getQueryString();
		String method = httpServletRequest.getMethod();
		if ("POST".equalsIgnoreCase(method)) {
			if (httpServletRequest.getContentType().contains("json")) {
				if (requestWrapper == null) {
					requestWrapper = new HttpServletRequestParseBodyWrapper(httpServletRequest);
				}

				StringBuffer stringBuffer = new StringBuffer();
				stringBuffer.append("Header:").append(requestWrapper.getHeader(httpServletRequest));
				stringBuffer.append("Body:").append(requestWrapper.getBody());
				queryString = stringBuffer.toString();
			} else {
				Map<String, String[]> params = httpServletRequest.getParameterMap();
				if(params != null && params.size() > 0) {
					queryString = JacksonUtils.marshal(params);
				}
			}
		} else {
			queryString = StringUtils.isBlank(queryString) ? "" : "?" + queryString;
		}

		String remoteAddr = ServletUtils.getRemoteAddr(httpServletRequest);
		SecurityContext.bindRemoteAddr(remoteAddr);
		MDC.put(MDCKeys.TRACE_ID, UUID.randomUUID().toString());
		MDC.put(MDCKeys.REMOTE_ADDR, remoteAddr);
		MDC.put(MDCKeys.REQUEST_URI, httpServletRequest.getRequestURI() + queryString);
		MDC.put(MDCKeys.USER_ID, httpServletRequest.getParameter(PARAM_USER_ID));
		LOGGER.info(method);
		// 记录接口请求次数
		if (StringUtils.isNotEmpty(httpServletRequest.getHeader(HEADER_X_HOSP_ID))
				&& StringUtils.isNotEmpty(httpServletRequest.getHeader(HEADER_X_FROM_ID))) {
			String date = DateUtils.format(new Date(), DateUtils.FMT_DATE_CMPRS);
			String stisticsKey = RedisKeys.statisticsCount(httpServletRequest.getHeader(HEADER_X_HOSP_ID), date,
					httpServletRequest.getRequestURI(), httpServletRequest.getHeader(HEADER_X_FROM_ID));
			ValueOperations<String, String> opsForValue = redisTemplate.opsForValue();
			String count = opsForValue.get(stisticsKey);
			if (StringUtils.isEmpty(count)) {
				count = "0";
			}
			Integer statisticsCount = Integer.valueOf(count) + 1;
			opsForValue.set(stisticsKey, ObjectUtils.toStr(statisticsCount));
		}
		chain.doFilter((requestWrapper != null ? requestWrapper : request), response);
	}

	@Override
	public void destroy() {

	}
}
