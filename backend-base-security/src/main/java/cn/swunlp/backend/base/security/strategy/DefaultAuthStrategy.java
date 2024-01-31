package cn.swunlp.backend.base.security.strategy;

import cn.swunlp.backend.base.security.constant.AppAuthenticate;
import cn.swunlp.backend.base.security.entity.AuthenticatedUserProfile;
import lombok.ToString;

/**
 * 默认权限验证策略:
 *  不需要权限验证
 * @author TangXi
 * @since 2024/1/29
 */
@ToString
public class DefaultAuthStrategy implements AuthStrategy {
    @Override
    public String[] permissions() {
        return new String[0];
    }

    @Override
    public String[] roles() {
        return new String[0];
    }

    @Override
    public String[] allowedOrigins() {
        return new String[]{ AppAuthenticate.ALLOW_ALL };
    }

    /**
     * 不需要权限验证
     */
    @Override
    public boolean authMethod(AuthenticatedUserProfile profile) {
        return true;
    }
}
