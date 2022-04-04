package com.bjpowernode.crm.web.interceptor;

import com.bjpowernode.crm.commons.constants.Constants;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
       //通过session判断用户是否登录
        HttpSession session = request.getSession();
        if(null==session.getAttribute(Constants.SESSION_USER)){
            //手动重定向要带上项目名称
            String path = request.getContextPath();
            response.sendRedirect(path);
            return false;
        }else
            return true;

         }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
       }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        }
}
