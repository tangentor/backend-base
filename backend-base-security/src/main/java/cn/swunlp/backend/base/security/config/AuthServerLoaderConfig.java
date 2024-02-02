package cn.swunlp.backend.base.security.config;

import cn.swunlp.backend.base.security.loader.AuthServerLoader;
import cn.swunlp.backend.base.security.loader.NacosAuthServerLoader;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *  鉴权服务加载配置
 * @author TangXi
 * @since 2024/2/2
 */

@Configuration
public class AuthServerLoaderConfig {

    @Bean
    @ConditionalOnMissingBean(AuthServerLoader.class)
    public AuthServerLoader authServerLoader(DiscoveryClient discoveryClient) {
        return new NacosAuthServerLoader(discoveryClient);
    }
}
