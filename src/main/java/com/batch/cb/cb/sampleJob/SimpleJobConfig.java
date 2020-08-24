package com.batch.cb.cb.sampleJob;

import com.batch.cb.cb.sampleJob.sampleStepTask.SimpleStepTask;
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
@Configuration//스프링 부트 배치 잡은 configuration으로 object 생성
@RequiredArgsConstructor
public class SimpleJobConfig {

    //job을 생성하는 obj/ 내부적으로 JobBuilder와 jobRepository를 이용해서 job을 생성한다.
    //추가로 jobRepository는 배치 처리 정보를 담고 있고, job 실행주기를 관리, job이 실행이 되면 jobExecution, stepExecution을 생성 관리 한다.
    //jobExecution, stepExecution은 더 나아가 설명한다.
    private final JobBuilderFactory jobBuilderFactory;

    //job 안에 step을 생하하는 obj/ 내부적으로 StepBuilder와 jobRepository, PlatformTransactionManager를 이용해서 step을 생성
    //PlatformTransactionManager를 통해 step 작업 간에 transaction을 관리(추측)
    private final StepBuilderFactory stepBuilderFactory;

    private final SimpleStepTask simpleStepTask;

    @Bean
    public Job simpleJob(){
        return jobBuilderFactory.get("simpleJob")
                .start(simpleStep())
                .build();
    }

//    @Bean
//    @JobScope
//    public Step simpleStep(@Value("#{jobParameters[requestDate]}") String requestDate){
//        return stepBuilderFactory.get("simpleStep")
//                .tasklet((stepContribution, chunkContext) -> {
//                    log.info(">>>>> this is step1");
//                    log.info(">>>>> param: {}", requestDate);
//                    return RepeatStatus.FINISHED;
//                })
//                .build();
//    }

    @Bean
    @JobScope
    public Step simpleStep(){
        return stepBuilderFactory.get("simpleStep")
                .tasklet(simpleStepTask)
                .build();
    }

}
