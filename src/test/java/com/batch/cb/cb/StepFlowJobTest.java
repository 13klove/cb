package com.batch.cb.cb;

import com.batch.cb.cb.stepFlowJob.StepFlowJobConfig;
import com.batch.cb.cb.stepFlowJob.stepFlowStepTask.StepFlowStepa;
import com.batch.cb.cb.stepFlowJob.stepFlowStepTask.StepFlowStepb;
import com.batch.cb.cb.stepFlowJob.stepFlowStepTask.StepFlowStepc;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@Slf4j
@SpringBootTest(classes = {StepFlowJobConfig.class, StepFlowStepa.class, StepFlowStepb.class, StepFlowStepc.class, BatchTestConfig.class})
public class StepFlowJobTest {

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    @Test
    public void stepFlowJobTest() throws Exception{
        JobParameters jobParameters = new JobParametersBuilder()
                .addString("requestDate", "20200824")
                .addDate("basicDate", new Date())
                .toJobParameters();

        JobExecution jobExecution = jobLauncherTestUtils.launchJob(jobParameters);

        log.info("batchJob: {}", jobExecution);
    }

}
