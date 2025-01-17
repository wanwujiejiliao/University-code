package com.example.demo.Filter;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.pojo.Result;
import com.example.demo.utools.JwtUtils;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.IOException;

@Slf4j
//@WebFilter(urlPatterns = "/*")
public class Loginfilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String url = request.getRequestURI().toString();
        log.info(url);
        if(url.contains("login")){
            log.info("登陆操作，直接放行");
            filterChain.doFilter(request, response);
            return;
        }
        String jwt = request.getHeader("token");
        if(!StringUtils.hasText(jwt)){
            log.info("请求头为空，返回未返回的信息");
            Result error = Result.error("NOT_LOGIN");
            String jsonString = JSONObject.toJSONString(error);
            response.getWriter().write(jsonString);
            return;
        }
        try {
            JwtUtils.parseJWT(jwt);
        } catch (Exception e) {
            log.info("解析令牌失败，返回未返回的信息");
            Result error = Result.error("NOT_LOGIN");
            String jsonString = JSONObject.toJSONString(error);
            response.getWriter().write(jsonString);
            return;
        }
        log.info("放行");
        filterChain.doFilter(request, response);

    }

}
