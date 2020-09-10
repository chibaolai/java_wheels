package com.bolly.tomcat.servlet;

import com.bolly.support.utils.JacksonUtils;
import com.bolly.support.utils.UnexpectedException;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * ServletUtils
 *
 * @author Erich
 */
public class ServletUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServletUtils.class);

    // -------------------------------------------------------------------------
    // ContentType Response Header
    // -------------------------------------------------------------------------
    public static final String CT_PLAIN_TEXT = "text/plain;charset=UTF-8";
    public static final String CT_HTML = "text/html;charset=UTF-8";
    public static final String CT_JSON = "application/json;charset=UTF-8";
    public static final String CT_XML = "text/xml;charset=UTF-8";
    public static final String CT_JAVASCRIPT = "text/javascript;charset=UTF-8";
    public static final String CT_STREAM = "application/ocelet-stream";

    private ServletUtils() {
    }

    /**
     * 判断给定的请求对象是否是异步请求
     *
     * @param request
     * @return
     */
    public static boolean isAsync(HttpServletRequest request) {
        return request.getHeader("x-requested-with") != null;
    }

    /**
     * 将给定的字符串内容以contentType "text/plain;charset=UTF-8"响应给客户端
     *
     * @param response
     * @param content
     */
    public static void renderText(HttpServletResponse response, String content) {
        render(response, CT_PLAIN_TEXT, content);
    }

    /**
     * 将给定的字符串内容以contentType "text/html;charset=UTF-8"响应给客户端
     *
     * @param response
     * @param content
     */
    public static void renderHTML(HttpServletResponse response, String content) {
        render(response, CT_HTML, content);
    }

    /**
     * 将给定的XML形式的字符串内容以contentType "text/xml;charset=UTF-8"响应给客户端
     *
     * @param response
     * @param content
     *            XML形式的字符串内容
     */
    public static void renderXML(HttpServletResponse response, String content) {
        render(response, CT_XML, content);
    }

    /**
     * 将给定的json形式的字符串内容以contentType "text/json;charset=UTF-8"响应给客户端
     *
     * @param response
     * @param content
     *            json形式的字符串内容
     */
    public static void renderJSON(HttpServletResponse response, String content) {
        render(response, CT_JSON, content);
    }

    /**
     * 将给定的java对象以contentType "text/json;charset=UTF-8"响应给客户端
     *
     * @param javaObject
     *            List&lt;POJO&gt;, POJO[], POJO or Map
     * @see {@link Jackson2Utils#marshal(Object)}
     */
    public static void renderJSON(HttpServletResponse response, Object javaObject) {
        renderJSON(response, JacksonUtils.marshal(javaObject));
    }

    /**
     * 将给定的java对象以contentType "text/javascript;charset=UTF-8"响应给客户端
     *
     * @param response
     * @param callbackName
     * @param javaObject
     * @see {@link Jackson2Utils#marshalToJSONP(String, Object)}
     */
    public static void renderJSONP(HttpServletResponse response, String callbackName, Object javaObject) {
        render(response, CT_JAVASCRIPT, JacksonUtils.marshalToJSONP(callbackName, javaObject));
    }

    /**
     * 获取客户端的IP地址
     *
     * @param request
     * @return
     */
    public static String getRemoteAddr(HttpServletRequest request) {
        String remoteAddr = request.getHeader("x-forwarded-for");
        if (notExists(remoteAddr)) {
            remoteAddr = request.getHeader("Proxy-Client-IP");
        } else {
            return remoteAddr;
        }
        if (notExists(remoteAddr)) {
            remoteAddr = request.getHeader("WL-Proxy-Client-IP");
        } else {
            return remoteAddr;
        }
        if (notExists(remoteAddr)) {
            remoteAddr = request.getRemoteAddr();
        } else {
            return remoteAddr;
        }
        return remoteAddr;
    }

    private static boolean notExists(String remoteAddr) {
        return remoteAddr == null || remoteAddr.length() == 0 || "unknown".equalsIgnoreCase(remoteAddr);
    }

    /**
     * 从请求中抽取int类型的参数值，如果抽取的结果无法转换为int类型将使用给定的默认值返回
     *
     * @param request
     * @param name
     * @param defaultValue
     *            给定的默认值
     * @return
     */
    public static int getIntParameter(HttpServletRequest request, String name, int defaultValue) {
        int value = defaultValue;
        String strValue = request.getParameter(name);
        if (strValue != null) {
            try {
                value = Integer.parseInt(strValue);
            } catch (NumberFormatException e) {
                // Ignore, so only log debug.
                LOGGER.debug(e.getMessage(), e);
            }
        }
        return value;
    }

    /**
     * 从请求中抽取参数值，如果抽取到的结果为null或""，将返回给定的默认值
     *
     * @param request
     * @param name
     * @param defaultValue
     * @return
     */
    public static String getParameter(HttpServletRequest request, String name, String defaultValue) {
        String value = request.getParameter(name);
        if (value == null || value.length() == 0) {
            value = defaultValue;
        }
        return value;
    }

    /**
     * 设置无缓存响应头
     *
     * @param response
     */
    public static void setNoCacheHeader(HttpServletResponse response) {

        // Set to expire far in the past.
        response.setHeader("Expires", "0");

        // Set standard HTTP/1.1 no-cache headers.
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");

        // Set IE extended HTTP/1.1 no-cache headers (use addHeader).
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");

        // Set standard HTTP/1.0 no-cache header.
        response.setHeader("Pragma", "no-cache");
    }

    /**
     * 设置文件下载响应头
     *
     * @param response
     * @param fileName
     */
    public static void setFileDownloadHeader(HttpServletResponse response, String fileName) {
        try {
            response.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(fileName, "UTF-8")
                + "\"");
        } catch (UnsupportedEncodingException e) {
            throw new UnexpectedException("Unsupported Encoding Exception [" + e.getMessage() + " ].", e);
        }
    }

    /**
     * 将给定的inputStream，以contentType "application/ocelet-stream"响应给客户端，多用于下载
     *
     * @param response
     * @param inputStream
     */
    public static void renderStream(HttpServletResponse response, InputStream inputStream) {
        renderStream(response, inputStream, null);
    }

    /**
     * 将给定的inputStream，以给定的contentType响应给客户端，多用于下载
     *
     * @param response
     * @param inputStream
     * @param contentType
     */
    public static void renderStream(HttpServletResponse response, InputStream inputStream, String contentType) {
        if (contentType == null) {
            response.setContentType(CT_STREAM);
        }
        try {
            IOUtils.copy(inputStream, response.getOutputStream());
        } catch (IOException e) {
            throw new UnexpectedException("I/O Exception" + e.getMessage() + " ].", e);
        }
    }

    /**
     * 将给定的字符串内容以给定的contentType响应给客户端
     *
     * @param response
     * @param contentType
     * @param content
     */
    public static void render(HttpServletResponse response, String contentType, String content) {

        setNoCacheHeader(response);

        response.setContentType(contentType);
        try {
            PrintWriter printWriter = response.getWriter();
            printWriter.write(content);
            printWriter.flush();
        } catch (IOException e) {
            throw new UnexpectedException("IO Exception [" + e.getMessage() + " ].", e);
        }
    }
}
