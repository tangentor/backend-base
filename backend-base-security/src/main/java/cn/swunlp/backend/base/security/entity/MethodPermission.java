package cn.swunlp.backend.base.security.entity;

import lombok.Data;

/**
 * 方法权限信息
 *     封装一个方法的权限信息
 *  Version: 1.0
 * @author TangXi
 */
@Data
public class MethodPermission {

    /**
     * 方法名
     */
    private String methodName;

    /**
     * 映射的URI路径
     */
    private String uri;

    /**
     * 请求方法
     */
//    @JsonSerialize(converter = RequestMethodConverter.class)
//    @JsonDeserialize(converter = RequestMethodConverter.class)
//    private RequestMethod requestMethod;

    private String requestMethod;

    /**
     * 合法请求来源
     */
    private String[] allowedOrigins;

    /**
     * 是否是内部资源
     */
    private boolean isInternalSource;

    /**
     * 方法的句柄
     */
//    private Object handler;

    /**
     * 所需要的权限信息
     */
    private String[] permissions;

    /**
     * 所需要的角色信息
     */
    private String[] roles;

    /**
     * 是否需要满足所有条件
     */
    private boolean requireAll;
}
