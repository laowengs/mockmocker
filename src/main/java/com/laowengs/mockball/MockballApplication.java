package com.laowengs.mockball;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@ServletComponentScan
@RestController
public class MockballApplication {

    public static void main(String[] args) {
        SpringApplication.run(MockballApplication.class, args);
    }


}
