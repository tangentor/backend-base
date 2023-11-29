package cn.swunlp.backend.base.web.util;


import org.springframework.web.method.HandlerMethod;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @author a2576
 */
public class ReflectUtils {

    /**
     * 判断处理器对象是否被指定注解所标注。
     *
     * @param handler           处理器对象
     * @param annotationClass   指定的注解类型
     * @return 如果处理器对象被指定注解所标注则返回true，否则返回false
     */
    public static boolean isAnnotated(Object handler, Class<? extends Annotation> annotationClass) {
        if (handler instanceof HandlerMethod) {
            final HandlerMethod handlerMethod = (HandlerMethod) handler;
            final Class<?> beanType = handlerMethod.getBeanType();
            final Method method = handlerMethod.getMethod();

            // 判断处理器类是否标注了指定注解
            if (beanType.isAnnotationPresent(annotationClass)) {
                return true;
            } else {
                return method.isAnnotationPresent(annotationClass);
            }
        }
        return false;
    }


}
