package cn.swunlp.backend.base;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 后端基础服务
 * <p>
 * 提供了一些基础的功能，例如：
 * 提供一套常用的工具类库，包括但不限于日期处理、字符串处理、文件操作等，以减少开发人员的重复工作。
 * <p>
 *
 * @author TangXi
 */
@SpringBootApplication
public class BackendBaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendBaseApplication.class, args);
    }

}
