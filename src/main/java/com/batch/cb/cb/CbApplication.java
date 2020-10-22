package com.batch.cb.cb;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
@EnableBatchProcessing//배치 기능 활성화(필수)
//https://ahea.wordpress.com/2017/02/20/3-spring-batch-boot-%EC%97%B0%EB%8F%99-%EB%B0%8F-%EC%B2%98%EB%A6%AC-%EA%B3%BC%EC%A0%95/
//https://jojoldu.tistory.com/328?category=902551
//https://heowc.dev/programming-study/repo/spring/spring-batch/configuring-and-running-job.html
//https://marobiana.tistory.com/133
//https://jojoldu.tistory.com/487
//quartz - https://blog.advenoh.pe.kr/spring/Quartz-Job-Scheduler%EB%9E%80/BootBatchBatchConfig.java

//https://bkim.tistory.com/23
public class CbApplication {

    public static void main(String[] args) {
        SpringApplication.run(CbApplication.class, args);
    }

}