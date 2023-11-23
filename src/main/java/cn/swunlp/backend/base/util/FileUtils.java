package cn.swunlp.backend.base.util;

/**
 * 功能描述：
 *  文件工具类
 * @author TangXi
 * @date 2023/11/23 10:34
 */
public class FileUtils {

    /**
     *  分割文件名
     * @param filename 待处理的文件名
     * @return 返回原始文件名与文件后缀，如 111.pdf => 111 与 pdf
     */
    public static String[] splitFilename(String filename) {
        filename = filterFilename(filename);
        int dotIndex = filename.lastIndexOf(".");
        if (dotIndex != -1 && dotIndex < filename.length() - 1) {
            String name = filename.substring(0, dotIndex);
            String extension = filename.substring(dotIndex + 1);
            return new String[]{name, extension};
        } else {
            return new String[]{filename, ""};
        }
    }

    /**
     * 对文件名进行非中文的特俗字符进行过滤
     * 过滤特殊字符和空格，只保留允许的字符，并限制长度小于100
     * @param filename 待处理的文件名
     * @return 处理之后的文件名
     */
    public static String filterFilename(String filename) {
        String filteredFilename = filename.replaceAll("[^\\u4E00-\\u9FA5a-zA-Z0-9.-]", "");
        if (filteredFilename.length() > 100) {
            filteredFilename = filteredFilename.substring(0, 100);
        }
        return filteredFilename;
    }

}
