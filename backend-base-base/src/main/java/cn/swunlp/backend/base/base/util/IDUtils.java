package cn.swunlp.backend.base.base.util;

import org.springframework.util.DigestUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;
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
     * @param isUpperCase 是否大写
     */
    public static String getUuid(boolean withLine,boolean isUpperCase){
        String uuid = UUID.randomUUID().toString();
        if(isUpperCase){
            uuid = uuid.toUpperCase();
        }
        if(withLine){
            return uuid;
        }else{
            return uuid.replaceAll("-", "");
        }
    }

    public static String getUuid(){
        return getUuid(false,false);
    }

    public static void main(String[] args) {
        System.out.println(getUuid(false,true));
    }
}
