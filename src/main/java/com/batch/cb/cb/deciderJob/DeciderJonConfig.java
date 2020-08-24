package com.batch.cb.cb.deciderJob;

import com.batch.cb.cb.deciderJob.deciderStepTask.StepDeciderStepa;
import com.batch.cb.cb.deciderJob.deciderStepTask.StepDeciderStepb;
import com.batch.cb.cb.deciderJob.deciderStepTask.StepDeciderStepc;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class DeciderJonConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final StepDeciderStepa stepDeciderStepa;
    private final StepDeciderStepb stepDeciderStepb;
    private final StepDeciderStepc stepDeciderStepc;

    @Bean
    public Job deciderJob(){
        return jobBuilderFactory.get("deciderJob")
                .start(step1())
                .build();
    }

    @Bean
    public Step step1(){
        return stepBuilderFactory.get("deciderStep1")
                .tasklet(stepDeciderStepa)
                .build();
    }

    @Bean
    public Step step2(){
        return stepBuilderFactory.get("deciderStep2")
                .tasklet(stepDeciderStepb)
                .build();
    }

    @Bean
    public Step step3(){
        return stepBuilderFactory.get("deciderStep3")
                .tasklet(stepDeciderStepc)
                .build();
    }

}
