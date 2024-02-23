package cn.swunlp.backend.base.web.util;

/**
 * 功能描述:
 *     Bean工具类扩展Web版
 * @author TangXi
 * @version 1.0
 */
public class BeanUtilsExt extends cn.swunlp.backend.base.base.util.BeanUtilExt{


    /**
     * 带泛型的Bean转化
     * @param source 源对象
     * @param target 目标对象类型
     */
    public static <K> K copyBeanSuper(Object source, Class<K> target){
        String s = JSONUtils.toJson(source);
        return JSONUtils.toObject(s,target);
    }
}
