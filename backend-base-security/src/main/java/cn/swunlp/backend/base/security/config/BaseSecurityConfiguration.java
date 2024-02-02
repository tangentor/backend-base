package cn.swunlp.backend.base.security.config;

import cn.swunlp.backend.base.security.interceptor.AccessControlInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author TangXi
 * @since 2024/1/30
 */
@Configuration
@ComponentScan(basePackages = "cn.swunlp.backend.base.security")
public class BaseSecurityConfiguration {

    @Bean
    public AccessControlInterceptor accessControlInterceptor() {
        return new AccessControlInterceptor();
    }
}
