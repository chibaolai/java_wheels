package com.bolly.springboot.interceptor;

import com.google.common.collect.Sets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.Set;

/**
 * @author bolly
 */
@Component
public class LoggingInterceptor implements HandlerInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger("ACCESS-LOGGER");

    private static final Set<String> IGNORE_LOG_METHODS = Sets.newHashSet();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        MDC.put(MDCKeys.REQUEST_URI, request.getRequestURI());
        MDC.put(MDCKeys.REMOTE_ADDR, request.getRemoteAddr());
        MDC.put(MDCKeys.USER_ID, String.valueOf(request.getHeader("userId")));
        MDC.put(MDCKeys.TRACE_ID, request.getHeader("nonce"));

        if (IGNORE_LOG_METHODS.contains(request.getMethod())) {
            LOGGER.info(request.getMethod());
        } else {
            ContentCachingRequestWrapper contentCachingRequestWrapper = new ContentCachingRequestWrapper(request);
            ContentCachingRequestWrapper wrapper = WebUtils.getNativeRequest(contentCachingRequestWrapper, ContentCachingRequestWrapper.class);
            if (wrapper != null) {
                byte[] buf = wrapper.getContentAsByteArray();
                if (buf.length > 0) {
                    String payload;
                    try {
                        payload = new String(buf, 0, buf.length, wrapper.getCharacterEncoding());
                    }
                    catch (UnsupportedEncodingException ex) {
                        payload = "[unknown]";
                    }

                    LOGGER.info(payload);
                }
            }
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                @Nullable Exception ex) {
        if (ex != null) {
            LOGGER.info("msg: {}", ex.getMessage());
        } else {
            LOGGER.info("status: 0");
        }
        MDC.clear();
    }

}
