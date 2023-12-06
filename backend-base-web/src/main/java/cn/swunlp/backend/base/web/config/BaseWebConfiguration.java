package cn.swunlp.backend.base.web.config;

import cn.swunlp.backend.base.web.interceptor.JsonResultInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
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
@ComponentScan(basePackages = {"cn.swunlp.backend.base.web"})
public class BaseWebConfiguration implements WebMvcConfigurer {

    @Bean
    public JsonResultInterceptor responseResultInterceptor(){
        return new JsonResultInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(responseResultInterceptor());
    }
}
