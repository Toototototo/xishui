package com.tooto.xishui.config.exception;

import com.tooto.xishui.utils.log.LogUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

/**
 * @author Tooto
 * @className GlobalExceptionHandler
 * @Description 捕获所有的异常
 * @since 2019/4/14 15:42
 */
public class GlobalExceptionHandler implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
        Object o, Exception e) {
        System.out.println(Arrays.toString(e.getStackTrace()));
        LogUtil.error(e.getMessage());
        if (StringUtils.isNotBlank(e.getMessage())) {
            writeContent(e.getMessage());
        } else {
            writeContent("系统异常！");
        }
        return null;
    }

    /**
     * 将内容输出到浏览器
     * 
     * @param content
     *            输出内容
     */
    private void writeContent(String content) {
        HttpServletResponse response =
            ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getResponse();
        assert response != null;
        response.reset();
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Type", "application/json");
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert writer != null;
        writer.print(content);
        writer.flush();
        writer.close();
    }
}
