package com.tooto.xishui.config;

import ch.qos.logback.core.util.ContextUtil;
import com.tooto.xishui.config.exception.BusinessException;
import com.tooto.xishui.entity.common.SessionUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import sun.misc.BASE64Decoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Tooto
 * @className RequestHandlderInterceptor
 * @Description RequestHandlderInterceptor Desc
 * @since 2019/4/14 16:45
 */
public class RequestHandlderInterceptor implements HandlerInterceptor {
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    /**
     * @Description 请求不检验session
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
        throws Exception {
        String requestPath = request.getRequestURI();
        if (requestPath.contains(("/swagger-ui.html"))// swagger请求放过
        ) {
            return true;
        }
        if (handler instanceof HandlerMethod) {
            // 获取token
            String token = request.getHeader("Authorization");
            if (StringUtils.isBlank(token)) {
                // 通过localhost访问不禁止
                String ip = request.getRemoteAddr();
                if (ip.equals("localhost") || ip.equals("127.0.0.1") || ip.equals("0:0:0:0:0:0:0:1")) {
                    return true;
                }
                // 根据sessionId 从redis获取token
                Cookie[] cookies = request.getCookies();
                String _s = "";
                if (cookies != null) {
                    for (Cookie cookie : cookies) {
                        if ("_s".equals(cookie.getName())) {
                            BASE64Decoder decoder = new BASE64Decoder();
                            _s = new String(decoder.decodeBuffer(cookie.getValue()));
                            break;
                        }
                    }
                }

                if (StringUtils.isNotEmpty(_s)) {
                    token = stringRedisTemplate.opsForValue().get("jwt:" + _s);
                }
                if (StringUtils.isEmpty(token)) {
                    throw new BusinessException(HttpStatusEnum.AUTHENTICATION_EXCEPTION,
                        HttpStatusEnum.AUTHENTICATION_EXCEPTION.getMessage() + ":" + requestPath);
                }
            }
            try {
                SessionUser sessionUser = ContextUtil.getSessionUser(token);
                if (!sessionUser.isAnonymous()) {
                    ContextUtil.setSessionUser(sessionUser);
                } else {
                    // 匿名用户
                    throw new BusinessException(HttpStatusEnum.UN_AUTHENTICATION_EXCEPTION,
                        HttpStatusEnum.UN_AUTHENTICATION_EXCEPTION.getMessage() + ":" + token);
                }
            } catch (Exception e) {
                // 非法token
                throw new BusinessException(HttpStatusEnum.AUTHENTICATION_EXCEPTION,
                    HttpStatusEnum.AUTHENTICATION_EXCEPTION.getMessage() + ":" + token, e);
            }
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
        ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
        throws Exception {

    }
}
