package com.batch.cb.cb.multiThreadJob;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class MultiThreadConfig {

    private final String JOB_NAME = "multiThreadJob";
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job multiThreadJob(){
        return jobBuilderFactory.get(JOB_NAME)
                .start(multiThreadStep())
                .build();
    }

    @Bean
    public Step multiThreadStep(){
        return null;
//        return stepBuilderFactory.get(JOB_NAME+"Step")
//                .<>chunk()
    }

}
