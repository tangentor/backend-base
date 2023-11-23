package cn.swunlp.backend.base.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * 日期时间工具类
 * @author TangXi
 */
public class DateTimeUtils {

    /**
     * 获取当前的格式化时间
     * @param format 格式化字符串
     *               例如：
     *               <p>yyMMdd -> 231010</p>
     *               <p>yyyy-MM-dd -> 2023-10-10</p>
     *               <p>yyyy-MM-dd HH:mm:ss -> 2023-10-10 17:47:23</p>
     * @return 格式化后的时间字符串
     */
    public static String getCurrentDateStr(String format){
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(format);
        return currentDate.format(dateFormatter);
    }

    /**
     * 获取当前的格式化时间,默认格式为yyyy-MM-dd
     * @return 格式化后的时间字符串
     */
    public static String getCurrentDateStr(){
        return getCurrentDateStr("yyyy-MM-dd");
    }


}
