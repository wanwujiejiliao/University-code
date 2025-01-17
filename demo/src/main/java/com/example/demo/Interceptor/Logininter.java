package com.example.demo.Interceptor;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.pojo.Result;
import com.example.demo.utools.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
@Slf4j
@Component
public class Logininter implements HandlerInterceptor {




     @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("前");
        String url = request.getRequestURI().toString();
        log.info(url);
//        if(url.contains("login")){
//            log.info("登陆操作，直接放行");
//            return true;
//        }
        String jwt = request.getHeader("Authorization");
        if(!StringUtils.hasText(jwt)){
            log.info("请求头为空，返回未返回的信息");
            Result error = Result.error("NOT_LOGIN");
            String jsonString = JSONObject.toJSONString(error);
            response.getWriter().write(jsonString);
            return false  ;
        }
        try {
            JwtUtils.parseJWT(jwt);
        } catch (Exception e) {
            log.info("解析令牌失败，返回未返回的信息");
            Result error = Result.error("NOT_LOGIN");
            String jsonString = JSONObject.toJSONString(error);
            response.getWriter().write(jsonString);
            return false;
        }
        log.info("放行");
        return true;

    }


/*    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("中");
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("后");
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }*/
}
