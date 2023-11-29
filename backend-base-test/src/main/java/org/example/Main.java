package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
    public static void main(String[] args) throws NoSuchMethodException {
        SpringApplication.run(Main.class, args);
    }



//    @Bean
//    public JsonResultHandler responseResultHandler(){
//        return new JsonResultHandler();
//    }
//
//    @Bean
//    public ResponseResultInterceptor responseResultInterceptor(){
//        return new ResponseResultInterceptor();
//    }
}