package cn.swunlp.backend.base.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author TangXi
 */
@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(Main.class, args);
//        AuthStrategy bean = run.getBean(AuthStrategy.class);
//        System.out.println(bean);
    }
}