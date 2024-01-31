package cn.swunlp.backend.base.security.strategy;

import cn.swunlp.backend.base.security.entity.AuthenticatedUserProfile;

/**
 * 权限验证策略接口
 *      主要用于处理在没有配置RequireAuth注解时的权限验证策略
 * @author TangXi
 * @since 2024/1/29
 */
public interface AuthStrategy {

    /**
     * 需要的权限列表
     */
    String[] permissions();

    /**
     * 需要的角色列表
     */
    String[] roles();

    /**
     * 合法的来源
     */
    String[] allowedOrigins();

    /**
     * 是否是内部来源
     */
    default boolean isInternalSource() {
        return false;
    }

    /**
     * 是否需要满足所有权限和角色
     */
    default boolean requireAll() {
        return false;
    }

    /**
     * 权限验证方法
     */
    default boolean authMethod(AuthenticatedUserProfile profile) {
        if(profile == null) {
            return false;
        }
        if(requireAll()) {
            return profile.hasAllPermission(permissions()) && profile.hasAllRole(roles());
        } else {
            return profile.hasAnyPermission(permissions()) || profile.hasAnyRole(roles());
        }
    }

}
