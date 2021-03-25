package com.example.demo.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 没有在平台登录的用户 所有请求都会失败
 */
public class LoginHandlerInterceptor implements HandlerInterceptor {
    //在目标方法执行之前运行此方法
    @Override
     public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取请求参数
        System.out.println(request.getRequestURI());
        String queryString = request.getQueryString();
        System.out.println("queryString请求参数:{}"+ queryString);
        //获取请求body
        //byte[] bodyBytes = StreamUtils.copyToByteArray(request.getInputStream());
        //String body = new String(bodyBytes, request.getCharacterEncoding());
        //System.out.println("bodyBytes请求体：{}"+body);

        String userId = request.getParameter("userId");
        /**
         *
         *从session中取登录用户是否可行fgn
         */
        System.out.println(userId);
        if (userId!=null) {
            if ( userId.equals("1000"))return true;
            return false;
        }
        return true;
    }
}
