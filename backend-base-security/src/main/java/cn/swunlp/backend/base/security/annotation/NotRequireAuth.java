package cn.swunlp.backend.base.security.annotation;

import java.lang.annotation.*;

/**
 * 标记该注解后所有的方法所需要的权限为@link{ AppAuthenticate.NOT_REQUIRED }
 * @author TangXi
 * @since 2024/1/31
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NotRequireAuth {
}
