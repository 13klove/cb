package com.batch.cb.cb;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing//배치 기능 활성화(필수)
public class CbApplication {

    public static void main(String[] args) {
        SpringApplication.run(CbApplication.class, args);
    }

}
