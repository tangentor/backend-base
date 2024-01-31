package cn.swunlp.backend.base.security;

import cn.swunlp.backend.base.security.strategy.AuthStrategy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author TangXi
 */
@SpringBootApplication
@EnableFeignClients
public class Main {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(Main.class, args);
//        AuthStrategy bean = run.getBean(AuthStrategy.class);
//        System.out.println(bean);
    }
}