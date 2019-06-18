package com.nevercome.icuriosity;

import javafx.application.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
public class ICuriosityApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(ICuriosityApplication.class, args);
    }

    // 当SpringBoot项目打成war包发布时,需要继承SpringBootServletInitializer接口实现该方法
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(Application.class);
    }
}
