package cn.swunlp.backend.base.security.annotation;


import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * 启用权限配置注解
 *
 * @author TangXi
 * @since 2024/1/28
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EnableAuthConfiguration {

    @AliasFor("isEnable")
    String value() default "true";

    String isEnable() default "true";
}
