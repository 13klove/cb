package com.batch.cb.cb;

import com.batch.cb.cb.stepNextJob.StepNextJobConfig;
import com.batch.cb.cb.stepNextJob.stepNextStepTask.StepNextStepa;
import com.batch.cb.cb.stepNextJob.stepNextStepTask.StepNextStepb;
import com.batch.cb.cb.stepNextJob.stepNextStepTask.StepNextStepc;
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
@SpringBootTest(classes = {StepNextJobConfig.class, StepNextStepa.class, StepNextStepb.class, StepNextStepc.class, BatchTestConfig.class})
public class StepNextJobTest {

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    @Test
    public void stepNextJobTest() throws Exception{
        JobParameters jobParameters = new JobParametersBuilder()
                .addString("requestDate", "20200824")
                .addDate("basicDate", new Date())
                .toJobParameters();

        JobExecution jobExecution = jobLauncherTestUtils.launchJob(jobParameters);

        log.info("batchJob: {}", jobExecution);
    }

}
