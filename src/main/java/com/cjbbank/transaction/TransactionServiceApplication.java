package com.cjbbank.transaction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication

@ComponentScan({ "com.cjbbank.transaction.**" })
@EnableCaching
public class TransactionServiceApplication {

    public static void main(String[] args) {

        SpringApplication.run(TransactionServiceApplication.class, args);
    }

}
