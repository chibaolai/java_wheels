package com.bolly.tomcat.servlet;

import com.bolly.support.utils.UnexpectedException;
import org.apache.commons.lang3.ArrayUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 *
 * URL解码过滤器
 *
 */
public class URLDecodeFilter implements Filter {
    private static final String ENC_TYPE_UTF8 = "UTF-8";

    private static final String ENC_TYPE_ISO8859_1 = "ISO-8859-1";

    private String encoding;

    /**
     * 初始化,读取Filter配置encoding
     *
     * @param filterConfig
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        encoding = filterConfig.getInitParameter("encoding");
        if (encoding == null) {
            encoding = ENC_TYPE_UTF8;
        }

    }

    /**
     * 执行过滤,URL Param Decode
     *
     * @param request
     * @param response
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
        ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        if ("GET".equalsIgnoreCase(req.getMethod())) {
            req = new RequestWrapper(req);
        }
        chain.doFilter(req, response);
    }

    /**
     * destroy
     */
    @Override
    public void destroy() {
        // Do nothing
    }

    /**
     *
     * RequestWrapper
     *
     */
    private class RequestWrapper extends HttpServletRequestWrapper {

        public RequestWrapper(HttpServletRequest request) {
            super(request);
        }

        /**
         * get decoded param Map
         */
        @Override
        public Map<String, String[]> getParameterMap() {
            Map<String, String[]> decodedParamMap = new HashMap<String, String[]>();
            Set<Entry<String, String[]>> paramEntrySet = super.getParameterMap().entrySet();
            for (Entry<String, String[]> entry : paramEntrySet) {
                String[] values = entry.getValue();
                for (int i = 0; i < values.length; i++) {
                    values[i] = decode(values[i]);
                }
                decodedParamMap.put(entry.getKey(), values);
            }
            return decodedParamMap;
        }

        /**
         * get decoded param
         *
         * @param name
         */
        @Override
        public String getParameter(String name) {
            return decode(super.getParameter(name));
        }

        /**
         * get decoded params
         */
        @Override
        public String[] getParameterValues(String name) {
            String[] values = super.getParameterValues(name);
            if (ArrayUtils.isNotEmpty(values)) {
                for (int i = 0; i < values.length; i++) {
                    values[i] = decode(values[i]);
                }
            }
            return values;
        }

        /**
         * decode value
         *
         * @param value
         * @return
         */
        private String decode(String value) {
            try {
                if (value != null) {
                    return new String(value.getBytes(ENC_TYPE_ISO8859_1), encoding);
                }
            } catch (UnsupportedEncodingException e) {
                throw new UnexpectedException(e);
            }
            return null;
        }
    }
}
