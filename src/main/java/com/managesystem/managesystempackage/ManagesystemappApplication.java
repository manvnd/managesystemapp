package com.managesystem.managesystempackage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.managesystem.managesystempackage.repository")
@SpringBootApplication
public class ManagesystemappApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManagesystemappApplication.class, args);
    }

}
