package com.yiyang;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@SpringBootApplication
@MapperScan("com.yiyang.mapper")
public class YiYangApp {
    public static void main(String[] args) {
        SpringApplication.run(YiYangApp.class,args);
    }

    /**
     * 解决跨域问题，把 CORS 过滤器对象交给 Spring 管理
     */
    @Bean
    public CorsFilter corsFilter(){
        CorsConfiguration configuration = new CorsConfiguration();
        // 配置允许的域名
        configuration.addAllowedOrigin("*");
        // 配置允许的请求方法
        configuration.addAllowedMethod("*");
        // 配置允许携带的请求头
        configuration.addAllowedHeader("*");
        // 创建 UrlBasedCorsConfigurationSource 对象，用于基于 URL 路径模式来配置不同的 CORS（跨源资源共享）策略。
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        // 配置完的过滤器需要拦截的路径
        source.registerCorsConfiguration("/**",configuration);
        // 返回 CORS 过滤器对象
        return new CorsFilter(source);
    }
}
