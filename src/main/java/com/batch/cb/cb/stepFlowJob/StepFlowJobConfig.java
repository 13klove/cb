package com.batch.cb.cb.stepFlowJob;

import com.batch.cb.cb.stepFlowJob.stepFlowStepTask.StepFlowStepa;
import com.batch.cb.cb.stepFlowJob.stepFlowStepTask.StepFlowStepb;
import com.batch.cb.cb.stepFlowJob.stepFlowStepTask.StepFlowStepc;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class StepFlowJobConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final StepFlowStepa stepFlowStepa;
    private final StepFlowStepb stepFlowStepc;
    private final StepFlowStepc stepFlowStepb;

    @Bean
    public Job stepFlowJob(){
        return jobBuilderFactory.get("stepFlowJob")
                //on으로 이벤트 캐치 한다. fail인경우
                .start(stepFlow1()).on(ExitStatus.FAILED.getExitCode()).to(stepFlow3()).on("*").end()
                //from은 위에서 발생한 이벤트 캐치가 아닌경우
                .from(stepFlow1()).on("*").to(stepFlow2()).next(stepFlow3()).on("*").end()
                //end는 2가지가 있다. flowBuilder를 반환하는 end, flowBuilder를 종료하는 end
                //flowBuilder를 반환하는 end인 경우 계속해서 flow를 이어갈 수 있다.
                .end()
                .build();
    }

    @Bean
    public Step stepFlow1(){
        return stepBuilderFactory.get("stepFlow1")
                .tasklet(stepFlowStepa)
                .build();
    }

    @Bean
    public Step stepFlow2(){
        return stepBuilderFactory.get("stepFlow2")
                .tasklet(stepFlowStepc)
                .build();
    }

    @Bean
    public Step stepFlow3(){
        return stepBuilderFactory.get("stepFlow3")
                .tasklet(stepFlowStepb)
                .build();
    }

}
