package cn.swunlp.backend.base.web.annotation;

import java.lang.annotation.*;

/**
 * 功能描述：
 *
 * @author TangXi
 * @date 2023/11/27 11:30
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.TYPE})
@Documented
public @interface JsonResult {

}
