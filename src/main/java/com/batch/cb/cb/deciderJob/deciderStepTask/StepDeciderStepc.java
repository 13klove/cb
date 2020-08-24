package com.batch.cb.cb.deciderJob.deciderStepTask;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class StepDeciderStepc implements Tasklet {


    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        log.info(">>>> 3step: {}", chunkContext.getStepContext().getJobParameters());
        return RepeatStatus.FINISHED;
    }
}
