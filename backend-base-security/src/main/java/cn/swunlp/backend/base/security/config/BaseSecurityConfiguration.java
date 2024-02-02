package cn.swunlp.backend.base.security.config;

import cn.swunlp.backend.base.security.interceptor.AccessControlInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author TangXi
 * @since 2024/1/30
 */
@Configuration
@EnableScheduling
@ComponentScan(basePackages = "cn.swunlp.backend.base.security")
public class BaseSecurityConfiguration {
    @Bean
    public AccessControlInterceptor accessControlInterceptor(AccessControlProperties accessControlProperties) {
        return new AccessControlInterceptor(accessControlProperties);
    }
}
