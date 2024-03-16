package com.laowengs.mocker;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@ServletComponentScan
@MapperScan("com.laowengs")
@EnableCaching
public class MockerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MockerApplication.class, args);


    }


}
