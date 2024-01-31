package cn.swunlp.backend.base.security.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author TangXi
 * @since 2024/1/29
 */

@Data
@Component
@ConfigurationProperties(prefix = "security.auth.app")
public class AppConfigProperties {

    private String name;

    private String code;
}
