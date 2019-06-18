package com.nevercome.icuriosity.common.config;

import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Configuration;

/**
 * @author: sun
 * @date: 2019/6/18
 */
@Configuration
@AutoConfigureAfter(MybatisAutoConfiguration.class)
public class MyBatisConfig {

//    @Bean
//    public MapperScannerConfigurer mapperScannerConfigurer() {
//        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
//        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
//        mapperScannerConfigurer.setBasePackage("com.zhuma.demo.mapper");
//
//        Properties properties = new Properties();
////        properties.setProperty("mappers", CrudMapper.class.getName());
//        properties.setProperty("notEmpty", "false");
//        properties.setProperty("IDENTITY", "MYSQL");
//        properties.setProperty("ORDER", "BEFORE");
//        mapperScannerConfigurer.setProperties(properties);
//        return mapperScannerConfigurer;
//    }

}
