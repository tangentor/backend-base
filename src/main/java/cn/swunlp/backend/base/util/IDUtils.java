package cn.swunlp.backend.base.util;

import java.util.UUID;

/**
 * 功能描述：
 *  生成ID工具类
 * @author TangXi
 * @version 1.0
 */
public class IDUtils {

    /**
     * 获取UUID
     * @param withLine 是否带横线
     */
    public static String getUuid(boolean withLine){
        String uuid = UUID.nameUUIDFromBytes(DateTimeUtils.getCurrentDateStr().getBytes()).toString();
        return withLine ? uuid : uuid.replaceAll("-","");
    }
}
