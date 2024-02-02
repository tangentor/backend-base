package cn.swunlp.backend.base.security.entity;

import lombok.Data;

/**
 * 应用访问控制
 * @author TangXi
 * @since 2024/2/2
 */

@Data
public class AppAccessControl {

    /**
     * 是否启用应用访问控制
     */
    private boolean enable;

    /**
     * 应用访问控制的Header名称
     */
    private String header;

    /**
     * 应用访问控制的有效值
     */
    private String validValue;
}
