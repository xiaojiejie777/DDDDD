package com.pdd;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.pdd.mapper")
@EnableScheduling
public class HrServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(HrServerApplication.class, args);
    }

}
