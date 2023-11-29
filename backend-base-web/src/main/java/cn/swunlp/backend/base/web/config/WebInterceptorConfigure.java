package cn.swunlp.backend.base.web.config;

import cn.swunlp.backend.base.web.advice.JsonResultHandler;
import cn.swunlp.backend.base.web.interceptor.JsonResultInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Description: (Your class description here)
 *
 * @author TangXi
 * @createDate 2023/11/27
 */

@Configuration
public class WebInterceptorConfigure implements WebMvcConfigurer {




    @Bean
    public JsonResultHandler responseResultHandler(){
        return new JsonResultHandler();
    }

    @Bean
    public JsonResultInterceptor responseResultInterceptor(){
        return new JsonResultInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(responseResultInterceptor());
    }
}
