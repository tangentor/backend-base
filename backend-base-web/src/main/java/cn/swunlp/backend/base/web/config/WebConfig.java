package cn.swunlp.backend.base.web.config;

import cn.swunlp.backend.base.web.interceptor.JsonResultInterceptor;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author TangXi
 * @since 2024/1/31
 */

@Configuration
@ConditionalOnClass(HttpServletRequest.class)
public class WebConfig implements WebMvcConfigurer {

    @Bean
    @ConditionalOnClass(HttpServletRequest.class)
    public JsonResultInterceptor responseResultInterceptor(){
        return new JsonResultInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(responseResultInterceptor());
    }
}

