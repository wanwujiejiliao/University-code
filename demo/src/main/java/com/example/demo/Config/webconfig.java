package com.example.demo.Config;

import com.example.demo.Interceptor.Logininter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class webconfig implements WebMvcConfigurer {

    @Autowired
    private Logininter interceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptor).addPathPatterns("/**").excludePathPatterns("/login").excludePathPatterns("/image/**")
                .excludePathPatterns("/teacher/**");
    }
@Override
public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**")//项目中的所有接口都支持跨域
            .allowedOriginPatterns("*")//所有地址都可以访问，也可以配置具体地址
            .allowCredentials(true)
            .allowedMethods("*")//"GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS"
            .maxAge(3600);// 跨域允许时间
}
    //设置文件虚拟路径映射
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/image/**").addResourceLocations("file:C:\\Users\\27954\\Desktop\\projects\\IdeaProjects\\demo\\");
    }
}
