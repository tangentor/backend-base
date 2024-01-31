package cn.swunlp.backend.base.security.annotation;

import cn.swunlp.backend.base.security.constant.AppAuthenticate;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * 权限注解
 * 值为访问该资源所需要的权限
 * @author TangXi
 * @since 2024/1/28
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequireAuth {
    // value() 方法用于指定需要的权限列表。由于使用了@AliasFor注解，
    // 它可以作为permissions()的别名，因此这两个属性是等效的。
    // 如果没有指定权限，默认为空数组。
    @AliasFor("permissions")
    String[] value() default {};

    // permissions() 方法同样用于指定需要的权限列表。
    // 如果没有指定权限，默认为空数组。
    // 通过@AliasFor注解，这个属性可以与value()互换使用。
    @AliasFor("value")
    String[] permissions() default {};

    // roles() 方法用于指定需要的角色列表。
    // 如果没有指定角色，默认为空数组。
    String[] roles() default {};

    // requireAll() 方法是一个布尔值，用于指定是否需要满足所有权限和角色。
    // 如果设置为true，则必须满足所有的权限和角色才能访问受保护的资源。
    // 如果设置为false，则只需要满足其中一个权限或角色即可。
    // 默认值为false，即满足任一权限或角色即可。
    boolean requireAll() default false;

    String[] allowedOrigins() default { AppAuthenticate.ALLOW_ALL };

    boolean isInternalSource() default false;
}

