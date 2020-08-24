package com.batch.cb.cb.lagacy;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//기본 job -> reader - processor - writer 구조
public class MemberUpdateJobConfig {

//	@Autowired
//	private MemberVoRepository memberVoRepository;
//	@Autowired
//	private DataSource dataSource;
//
//	public static final int CHUNK_SIZE = 1;
//
//	@Bean
//	public Job memberUpdateJob(JobBuilderFactory jobBuilderFactory, Step memberUpdateStep) {
//		return jobBuilderFactory.get("memberUpdateJob")
//				.start(memberUpdateStep)
//				.incrementer(new RunIdIncrementer())
//				.build();
//	}
//
//	@Bean
//	public Step memberUpdateStep(StepBuilderFactory stepBuilderFactory) {
//		return stepBuilderFactory.get("memberUpdateStep")
//				.<MemberVo, MemberVo>chunk(CHUNK_SIZE)
//				.reader(memberUpdateReadQueue())
//				.processor(memberUpdateProcessor())
//				.writer(memberUpdateWriter())
//				.build();
//	}
//
//	@Bean
//	@StepScope
//	public ListItemReader<MemberVo> memberUpdateReadQueue(){
//		List<MemberVo> reqs = memberVoRepository.selectAll("memberVoMapper.selectAll");
//		return new ListItemReader<>(reqs);
//	}
//
//	@Bean
//	public ItemProcessor<MemberVo, MemberVo> memberUpdateProcessor(){
//		return a-> {a.setRoleGroup("ROLE_DEV"); return a;};
//	}
//
//	@Bean
//	public JdbcBatchItemWriter<MemberVo> memberUpdateWriter(){
//		return new JdbcBatchItemWriterBuilder<MemberVo>()
//        .dataSource(dataSource)
//        .sql("UPDATE MEMBER SET ROLE_GROUP = :roleGroup WHERE ID = :id")
//        .beanMapped()
//        .build();
//	}

}
