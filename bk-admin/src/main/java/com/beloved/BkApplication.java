package com.beloved;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.beloved.**.mapper")
@SpringBootApplication
public class BkApplication {

    public static void main(String[] args) {
        SpringApplication.run(BkApplication.class, args);
    }

}
