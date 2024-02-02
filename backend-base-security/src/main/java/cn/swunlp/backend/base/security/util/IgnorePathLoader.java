package cn.swunlp.backend.base.security.util;

import cn.swunlp.backend.base.security.exception.IgnoreFileParseException;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * 用于加载忽略权限验证的路径的加载器
 * @author TangXi
 * @since 2024/1/30
 */

public class IgnorePathLoader {

    private static Set<String> ignorePaths;

    private static final String IGNORE_PATHS_FILE = "uri.ignore";

    public static Set<String> load() {
        if(ignorePaths != null) {
            return ignorePaths;
        }
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try(InputStream inputStream = classLoader.getResourceAsStream(IGNORE_PATHS_FILE)){
            if(inputStream == null) {
                throw new IgnoreFileParseException("uri.ignore file not found");
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            ignorePaths = new HashSet<>();
            String line = reader.readLine();
            while(line != null) {
                ignorePaths.add(line);
                line = reader.readLine();
            }
        } catch (Exception e) {
            throw new IgnoreFileParseException(e.getMessage());
        }
        return ignorePaths;
    }

}
