package cn.swunlp.backend.base.base.util;

import java.io.InputStream;

/**
 * 功能描述：
 *  文件流工具类
 * @author TangXi
 * @since 2024/1/7
 */

public class FileStreamUtil {

    public InputStream getFileAsStream(String path) {
        return FileStreamUtil.class.getClassLoader().getResourceAsStream(path);
    }
}
