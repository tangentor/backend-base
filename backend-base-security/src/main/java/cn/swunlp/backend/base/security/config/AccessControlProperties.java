package cn.swunlp.backend.base.security.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author TangXi
 * @since 2024/1/29
 */

@Data
@Component
@ConfigurationProperties(prefix = "security.access-control")
public class AccessControlProperties {

    private boolean enable = true;

    private String header = "X-Access-Control";

    private String validValue;
}
