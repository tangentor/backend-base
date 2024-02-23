package cn.swunlp.backend.base.security.config;

import cn.swunlp.backend.base.security.annotation.EnableAuthConfiguration;
import cn.swunlp.backend.base.security.interceptor.AccessControlInterceptor;
import cn.swunlp.backend.base.security.interceptor.UserInfoInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author TangXi
 * @since 2024/2/2
 */

@Configuration
@Conditional(EnableAuth.class)
@RequiredArgsConstructor
public class SecurityConfig implements WebMvcConfigurer {

    private final AccessControlInterceptor accessControlInterceptor;

    private final UserInfoInterceptor userInfoInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userInfoInterceptor);
        registry.addInterceptor(accessControlInterceptor);
    }

}
