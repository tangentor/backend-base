package cn.swunlp.backend.base.base.util;

import org.springframework.beans.BeanUtils;

/**
 * 功能描述：BeanUtils扩展
 * @author TangXi
 * @version 1.0
 */
public class BeanUtilExt extends BeanUtils {

    /**
     * 拷贝对象属性值到另一个对象并返回
     * @param source 源对象
     * @param target 目标对象
     * @param <K> 目标对象类型
     * @return 目标对象
     */
    public static <K>  K copyBean(Object source, K target){
        copyProperties(source,target);
        return target;
    }
}
