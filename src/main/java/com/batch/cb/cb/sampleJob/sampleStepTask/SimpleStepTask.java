package com.batch.cb.cb.sampleJob.sampleStepTask;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SimpleStepTask implements Tasklet {

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        log.info(">>> param: {}", chunkContext.getStepContext().getJobParameters());
        log.info(">>> param: {}", stepContribution.getStepExecution().getJobExecution().getJobParameters());
        log.info(">>>> this is class step1");
        return RepeatStatus.FINISHED;
    }

}
