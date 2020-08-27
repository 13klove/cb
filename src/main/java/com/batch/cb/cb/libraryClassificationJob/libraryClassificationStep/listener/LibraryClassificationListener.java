package com.batch.cb.cb.libraryClassificationJob.libraryClassificationStep.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
//https://freedeveloper.tistory.com/26
public class LibraryClassificationListener implements JobExecutionListener {

    @Override
    public void beforeJob(JobExecution jobExecution) {

    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if(jobExecution.getExitStatus().getExitCode().equals(ExitStatus.FAILED.getExitCode())){
            log.info("여기서 잡실패 메시지를 보낸다(어떤거든) jobInfo: {}", jobExecution.getJobInstance());
        }
    }

}
