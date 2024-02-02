package cn.swunlp.backend.base.base.util;



import java.lang.annotation.*;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author a2576
 */
public class ReflectUtils {

    private static final String SPRING_CGLIB_FLAG = "$$SpringCGLIB$$";

    public static boolean isAnnotated(Object object, Class<? extends Annotation> annotationClass) {
        Class<?> clazz = object.getClass();
        if(clazz.getName().contains(SPRING_CGLIB_FLAG)){
            clazz = clazz.getSuperclass();
        }
        if(clazz.isAnnotationPresent(annotationClass)) {
            return true;
        }
        Annotation[] annotations = clazz.getAnnotations();
        return Arrays.stream(annotations)
                .anyMatch(annotation -> isAnnotated(annotation, annotationClass));
    }

    /**
     * 判断一个方法是否被指定注解所标注
     * 这个注解可能是方法上的，也可能是类上的
     * 还有可能是注解里面的注解
     */
    public static boolean isAnnotated(Method method, Class<? extends Annotation> annotationClass) {
        //检查当前方法是否被指定注解标注
        if (method.isAnnotationPresent(annotationClass)) {
            return true;
        }
        //检查当前方法所在的类是否被指定注解标注
        if (method.getDeclaringClass().isAnnotationPresent(annotationClass)) {
            return true;
        }
        //递归方法注解的注解
        for (Annotation annotation : method.getAnnotations()) {
            if (isAnnotated(annotation, annotationClass)) {
                return true;
            }
        }
        //递归类注解的注解
        for (Annotation annotation : method.getDeclaringClass().getAnnotations()) {
            if (isAnnotated(annotation, annotationClass)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isAnnotated(Annotation annotation, Class<? extends Annotation> annotationClass) {
        //检查当前注解是否被指定注解标注
        if (annotation.annotationType().isAnnotationPresent(annotationClass)) {
            return true;
        }
        //递归注解的注解，排除元注解与重复注解
        for (Annotation anno : Arrays.stream(annotation.annotationType().getAnnotations())
                .dropWhile(ReflectUtils::isMetaAnnotation).toList()) {
            if (isAnnotated(anno, annotationClass)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isMetaAnnotation(Annotation annotation) {
        Class<? extends Annotation> annotationType = annotation.annotationType();
        return annotationType.equals(Retention.class) ||
                annotationType.equals(Target.class) ||
                annotationType.equals(Documented.class) ||
                annotationType.equals(Inherited.class);
    }

    public static <A extends Annotation> A findAnnotation(Object object, Class<A> annotationClass) {
        Class<?> clazz = object.getClass();
        if(clazz.getName().contains(SPRING_CGLIB_FLAG)){
            clazz = clazz.getSuperclass();
        }
        A annotation = clazz.getAnnotation(annotationClass);
        if (annotation != null) {
            return annotation;
        }
        for (Annotation anno : clazz.getAnnotations()) {
            A target = findAnnotation(anno, annotationClass);
            if(target != null){
                return target;
            }
        }
        return null;
    }

    public static <A extends Annotation> A findAnnotation(Method method, Class<A> annotationClass) {
        //方法上查找
        A annotation = method.getAnnotation(annotationClass);
        if (annotation != null) {
            return annotation;
        }
        //类上查找
        annotation = method.getDeclaringClass().getAnnotation(annotationClass);
        if (annotation != null) {
            return annotation;
        }
        //方法上注解查找
        for (Annotation anno : method.getAnnotations()) {
            A target = findAnnotation(anno, annotationClass);
            if(target != null){
                return target;
            }
        }
        //类上注解查找
        for (Annotation anno : method.getDeclaringClass().getAnnotations()) {
            A target = findAnnotation(anno, annotationClass);
            if(target != null){
                return target;
            }
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public static <A extends Annotation> A findAnnotation(Annotation annotation, Class<A> target) {
        if (!isAnnotated(annotation, target)) {
            return null;
        }
        //递归查找
        Annotation[] annotations = annotation.annotationType().getAnnotations();
        for (Annotation anno : annotations) {
            if(anno.getClass().equals(target)){
                return (A) anno;
            }
            if(!isMetaAnnotation(anno)){
                findAnnotation(annotation, anno.getClass());
            }
        }
        return null;
    }
}
