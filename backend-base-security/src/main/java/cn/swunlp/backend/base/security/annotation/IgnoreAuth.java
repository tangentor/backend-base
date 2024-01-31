package cn.swunlp.backend.base.security.annotation;

import java.lang.annotation.*;

/**
 * 忽略权限注解
 *     标记注解，用于标记某个类或者方法不需要权限验证
 *     优先级：只要标记该注解，其他所有权限注解都会失效
 * @author TangXi
 * @since 2024/1/29
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IgnoreAuth {
}
