package com.example.cms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
/**
 * @author xzy
 * @date 2018-12-25
 */
@SpringBootApplication
@MapperScan("com.example.cms.mapper")
@ImportResource(value = {"classpath:application-mybatis.xml"})
public class CmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(CmsApplication.class, args);
    }
}
