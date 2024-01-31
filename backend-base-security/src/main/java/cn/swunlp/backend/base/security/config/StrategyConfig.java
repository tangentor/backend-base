package cn.swunlp.backend.base.security.config;

import cn.swunlp.backend.base.security.strategy.AuthStrategy;
import cn.swunlp.backend.base.security.strategy.ConfigurableAuthStrategy;
import cn.swunlp.backend.base.security.strategy.DefaultAuthStrategy;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 策略配置类
 * @author TangXi
 * @since 2024/1/29
 */

@Configuration
public class StrategyConfig {

    @Bean
    @ConditionalOnMissingBean(name = "configurableAuthStrategy")
    public AuthStrategy defaultAuthStrategy() {
        return new DefaultAuthStrategy();
    }
}
