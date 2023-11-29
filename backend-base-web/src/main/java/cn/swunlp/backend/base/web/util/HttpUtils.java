package cn.swunlp.backend.base.web.util;

import cn.swunlp.backend.base.web.constant.ResultCode;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * 描述:
 *  Http工具类
 * @author TangXi
 */

public class HttpUtils {
    public static void writeUtf8Message(HttpServletResponse response, String message,int status) {
        response.setContentType("text/plain; charset=utf-8");
        response.setStatus(status);
        try {
            response.getWriter().write(message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void writeUtf8Message(HttpServletResponse response, String message) {
        response.setContentType("text/plain; charset=utf-8");
        response.setStatus(ResultCode.SUCCESS);
        try {
            response.getWriter().write(message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
