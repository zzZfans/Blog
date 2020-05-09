package com.zfans.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Zfans
 * @date 2020/5/6 2:17
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        String user = "user";
        if (request.getSession().getAttribute(user) == null) {
            response.sendRedirect("/admin");
            return false;
        }
        return true;
    }
}
