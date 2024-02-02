package cn.swunlp.backend.base.security.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.util.List;

/**
 * 应用权限
 *      封装一个应用的接口以及对应的权限信息
 * @author TangXi
 * @since 2024/1/29
 */
@Data
public class ApplicationPermission {
    /**
     * 应用名称
     */
    private String applicationName;

    /**
     * 应用代码
     */
    private String applicationCode;

    /**
     * 应用接口前缀
     */
    private String  prefix;

    /**
     * 方法权限
     */
    private List<MethodPermission> methodPermissions;

}
