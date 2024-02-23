package cn.swunlp.backend.base.security.access;

/**
 * 访问控制值持有类
 * @author TangXi
 */
public class AccessControlValueHolder {

    // 访问控制值
    private static String value;

    // 上一次访问控制值
    private static String lastValue;

    public static String getLastValue() {
        return AccessControlValueHolder.lastValue;
    }

    /**
     * 获取访问控制值
     * @return 访问控制值
     */
    public static String getValue() {
        return AccessControlValueHolder.value;
    }

    /**
     * 设置访问控制值
     * @param value 访问控制值
     */
    public static void setValue(String value) {
        AccessControlValueHolder.lastValue = AccessControlValueHolder.value;
        AccessControlValueHolder.value = value;
    }
}

