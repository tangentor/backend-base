package cn.swunlp.backend.base.security.strategy;

import cn.swunlp.backend.base.security.constant.AppAuthenticate;
import cn.swunlp.backend.base.security.entity.AuthenticatedUserProfile;
import cn.swunlp.backend.base.security.exception.StrategyParseException;
import lombok.Data;
import lombok.ToString;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cglib.core.ReflectUtils;
import org.springframework.stereotype.Component;

/**
 * @author TangXi
 * @since 2024/1/29
 */

@ToString
@Data
@Component("configurableAuthStrategy")
@ConditionalOnProperty(name = "security.auth.strategy.enable",havingValue = "true")
@ConfigurationProperties(prefix = "security.auth.strategy")
public class ConfigurableAuthStrategy implements AuthStrategy {

    private boolean enable;

    private String[] permissions;

    private String[] roles;

    private boolean requireAll = false;

    private boolean isInternalSource = false;

    private String[] allowedOrigins;

    /**
     * 自定义权限验证方法的实现类的类全限定名
     * 该类需要实现 AuthStrategy 接口，重写 authMethod 方法
     */
    private String authMethodClass;

    @Override
    public String[] permissions() {
        return this.permissions;
    }

    @Override
    public String[] roles() {
        return this.roles;
    }

    @Override
    public String[] allowedOrigins() {
        if(this.allowedOrigins == null || this.allowedOrigins.length == 0){
            return new String[]{ AppAuthenticate.ALLOW_ALL };
        }
        return this.allowedOrigins;
    }

    @Override
    public boolean isInternalSource() {
        return this.isInternalSource;
    }

    @Override
    public boolean requireAll() {
        return this.requireAll;
    }

    @Override
    public boolean authMethod(AuthenticatedUserProfile profile) {
        if(authMethodClass == null){
            return AuthStrategy.super.authMethod(profile);
        }
        authMethodClass = authMethodClass.trim();
        AuthStrategy authStrategy = loadStrategyFromClassName(authMethodClass);
        return authStrategy.authMethod(profile);
    }

    private AuthStrategy loadStrategyFromClassName(String authMethodClass) {
        try {
            Class<?> aClass = Class.forName(authMethodClass);
            Object o = ReflectUtils.newInstance(aClass);
            if(!(o instanceof AuthStrategy)){
                throw new StrategyParseException("自定义权限验证方法的实现类必须实现 AuthStrategy 接口：" + authMethodClass);
            }
            return (AuthStrategy)o;
        } catch (Exception e) {
            throw new StrategyParseException("无法加载自定义权限验证方法的实现类：" + authMethodClass);
        }
    }
}
