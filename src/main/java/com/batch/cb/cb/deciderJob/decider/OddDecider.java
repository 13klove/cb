package com.batch.cb.cb.deciderJob.decider;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.core.job.flow.JobExecutionDecider;

import java.util.Random;

@Slf4j
public class OddDecider implements JobExecutionDecider {

    @Override
    public FlowExecutionStatus decide(JobExecution jobExecution, StepExecution stepExecution) {
        Random rand = new Random();

        int ranNum = rand.nextInt(50)+1;
        log.info("ranNum: {}", ranNum);

        return ranNum%2==0?new FlowExecutionStatus("EVEN"):new FlowExecutionStatus("ODD");
    }

}
