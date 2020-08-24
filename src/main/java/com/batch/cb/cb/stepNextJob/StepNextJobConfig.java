package com.batch.cb.cb.stepNextJob;

import com.batch.cb.cb.stepNextJob.stepNextStepTask.StepNextStepa;
import com.batch.cb.cb.stepNextJob.stepNextStepTask.StepNextStepb;
import com.batch.cb.cb.stepNextJob.stepNextStepTask.StepNextStepc;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class StepNextJobConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final StepNextStepa stepNextStepa;
    private final StepNextStepb stepNextStepb;
    private final StepNextStepc stepNextStepc;

    @Bean
    public Job stepNextJob(){
        return jobBuilderFactory.get("stepNextJob")
                .start(step1())
                .next(step2())
                .next(step3())
                .build();
    }

    @Bean
    @JobScope
    public Step step1(){
        return stepBuilderFactory.get("step1")
                .tasklet(stepNextStepa)
                .build();
    }

    @Bean
    @JobScope
    public Step step2(){
        return stepBuilderFactory.get("step2")
                .tasklet(stepNextStepb)
                .build();
    }

    @Bean
    @JobScope
    public Step step3(){
        return stepBuilderFactory.get("step3")
                .tasklet(stepNextStepc)
                .build();
    }

}
