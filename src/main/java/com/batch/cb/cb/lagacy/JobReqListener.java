package com.batch.cb.cb.lagacy;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.AfterStep;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JobReqListener {

//	@Autowired
//	private JobReqVoRepository jobReqVoRepository;
//
//	@BeforeStep
//	public void beforeStep(StepExecution stepExecution) {
//		JobReqVo jobReqVo = (JobReqVo) stepExecution.getJobExecution().getExecutionContext().get("jobReq");
//		jobReqVo.setJobStatus("ING");
//		jobReqVoRepository.update("jobReqVoMapper.update", jobReqVo);
//	}
//
//	@AfterStep
//	public ExitStatus afterStep(StepExecution stepExecution) {
//		JobReqVo jobReqVo = (JobReqVo) stepExecution.getJobExecution().getExecutionContext().get("jobReq");
//		jobReqVo.setJobStatus("COMPLETE");
//		jobReqVoRepository.update("jobReqVoMapper.update", jobReqVo);
//		return ExitStatus.COMPLETED;
//	}


}
