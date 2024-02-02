package cn.swunlp.backend.base.security.entity;

import lombok.Builder;
import lombok.Setter;

/**
 * 认证用户信息
 * @author TangXi
 * @since 2024/1/29
 */

@Setter
@Builder
public class AuthenticatedUserProfile {

    /**
     * 用户基本信息部分
     */
    private String username;

    private String nickname;

    /**
     * 权限部分和角色部分
     */
    private String[] permissions;

    private String[] roles;

    public AuthenticatedUserProfile(String username, String nickname, String[] permissions) {
        this.username = username;
        this.nickname = nickname;
        this.permissions = permissions;
    }

    public AuthenticatedUserProfile(String username, String nickname, String[] permissions, String[] roles) {
        this(username, nickname, permissions);
        this.roles = roles;
    }


    public boolean hasPermission(String permission) {
        for(String p : permissions) {
            if(p.equals(permission)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasRole(String role) {
        for(String r : roles) {
            if(r.equals(role)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasAllPermission(String[] permissions) {
        for(String p : permissions) {
            if(!hasPermission(p)) {
                return false;
            }
        }
        return true;
    }

    public boolean hasAllRole(String[] roles) {
        for(String r : roles) {
            if(!hasRole(r)) {
                return false;
            }
        }
        return true;
    }

    public boolean hasAnyPermission(String[] permissions) {
        for(String p : permissions) {
            if(hasPermission(p)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasAnyRole(String[] roles) {
        for(String r : roles) {
            if(hasRole(r)) {
                return true;
            }
        }
        return false;
    }
}
