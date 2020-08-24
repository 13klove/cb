package com.batch.cb.cb.lagacy;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.core.job.flow.JobExecutionDecider;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//여러개의 job을 처리
//job이 여러개인 경우 tasklet에서 모두 호출
//다음 step에 job을 하나씩 던져 처리
//batch flow, tasklet, reader, processor, writer 사용
//포인트 여러개의 job들을 어떻게 다음 step으로 1개씩 넘길수 있을까!
public class MemberJobReqConfig {

//	@Autowired
//	private JobBuilderFactory jobBuilderFactory;
//	@Autowired
//	private StepBuilderFactory stepBuilderFactory;
//	@Autowired
//	private JobReqVoRepository jobReqVoRepository;
//	@Autowired
//	private MemberVoRepository memberVoRepository;
//	@Autowired
//	private DataSource dataSource;
//	@Autowired
//	private JobReqListener jobReqListener;
//
//	@Bean
//	public Job memberJobReqJob() {
//		return jobBuilderFactory.get("memberJobReqJob")
//				.start(findJobsByJobDiv())
//				.next(memberJobReqDecider())
//				.from(memberJobReqDecider())
//					.on("MEMBER_JOB_REQ").to(findJobRunStep()).next(memberJobReqDecider())
//					.on("COM").end()
//				.end()
//				.incrementer(new RunIdIncrementer())
//				.build();
//	}
//
//	@Bean
//	public Step findJobsByJobDiv() {
//		return stepBuilderFactory.get("findJobsByJobDivStep")
//				.tasklet(findJobsByJobDivTasklet())
//				.build();
//	}
//
//	@Bean
//	public Step findJobRunStep() {
//		return stepBuilderFactory.get("findJobRunStep")
//				.<MemberVo, MemberVo>chunk(15)
//				.reader(memberJobReqReader())
//				.processor(memberJobReqProcessor())
//				.writer(memberJobReqWriter())
//				.listener(jobReqListener)
//				.build();
//	}
//
//	@Bean
//	public JobExecutionDecider memberJobReqDecider() {
//		return (JobExecution jobExecution, StepExecution stepExecution) -> {
//			List<JobReqVo> jobReqs = (List<JobReqVo>) jobExecution.getExecutionContext().get("jobReqs");
//			if(jobReqs.isEmpty()) return new FlowExecutionStatus("COM");
//			JobReqVo runJob = jobReqs.remove(0);
//			jobExecution.getExecutionContext().put("jobReq", runJob);
//			return new FlowExecutionStatus("MEMBER_JOB_REQ");
//		};
//	}
//
//	@Bean
//	public Tasklet findJobsByJobDivTasklet() {
//		return (StepContribution contribution, ChunkContext chunkContext) -> {
//			Optional<List<JobReqVo>> aa = Optional.ofNullable(jobReqVoRepository.selectAll("jobReqVoMapper.selectAll"));
//			//루프를 돌리기 위해 즉 decider가 루프를 돌 수 있게 넘긴다.
//			chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext().put("jobReqs", aa.orElse(new ArrayList<JobReqVo>()));
//			return RepeatStatus.FINISHED;
//		};
//	}
//
//	@Bean
//	@StepScope
//	public ListItemReader<MemberVo> memberJobReqReader(){
//		List<MemberVo> reqs = memberVoRepository.selectAll("memberVoMapper.selectAll");
//		return new ListItemReader<>(reqs);
//	}
//
//	@Bean
//	public ItemProcessor<MemberVo, MemberVo> memberJobReqProcessor(){
//		return a-> {a.setRoleGroup("ROLE_DEV"); return a;};
//	}
//
//	@Bean
//	public JdbcBatchItemWriter<MemberVo> memberJobReqWriter(){
//		return new JdbcBatchItemWriterBuilder<MemberVo>()
//        .dataSource(dataSource)
//        .sql("UPDATE MEMBER SET ROLE_GROUP = :roleGroup WHERE ID = :id")
//        .beanMapped()
//        .build();
//	}

}
