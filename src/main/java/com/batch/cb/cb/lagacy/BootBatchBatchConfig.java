package com.batch.cb.cb.lagacy;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration
public class BootBatchBatchConfig extends DefaultBatchConfigurer{

//	@Autowired
//	private DataSource dataSource;
//
//	@Autowired
//	private JobExplorer jobExplorer;
//
//	@Autowired
//	private JobSchedulerVoRepository jobSchedulerVoRepository;
//
//	private static final int THREAD_COUNT = 10;
//
//	//이 구현체는 실제로 Quartz SimpleThreadPool의 하위클래스로 스프링의 생명주기 콜백을 받는다.
//	//이는 Quartz와 Quartz가 아닌 컴포넌트 간에 공유해야 하는 스레드 품이 있는 경우 사용
//	@Bean
//	public SimpleThreadPoolTaskExecutor simpleThreadPoolTaskExecutor() {
//		SimpleThreadPoolTaskExecutor threadPool = new SimpleThreadPoolTaskExecutor();
//		threadPool.setThreadCount(THREAD_COUNT);
//		return threadPool;
//	}
//
//	//jobRepository는 다른 트렌젝션이 읽거나 할때 수정이 안되는 ISOLATION_SERIALIZABLE 상태라 에러가 난다. 햐여
//	//DefaultBatchConfigurer를 상속 받고 ISOLATION_READ_COMMITTED로 수정
//	//추가로 상속을 안받고 bean으로 재정의 한 경우.... 똑같이 에러가 난다.
//	@Override
//	protected JobRepository createJobRepository() throws Exception {
//		JobRepositoryFactoryBean factory = new JobRepositoryFactoryBean();
//		factory.setDataSource(dataSource);
//		factory.setTransactionManager(new DataSourceTransactionManager(dataSource));
//		factory.setIsolationLevelForCreate("ISOLATION_READ_COMMITTED");
//		factory.setTablePrefix("BATCH_");
//		factory.afterPropertiesSet();
//		return factory.getObject();
//	}
//
//	/** job 실행시키는 런쳐 */
//	@Bean
//	public JobLauncher jobLauncher(JobRepository jobRepository) throws Exception {
//		SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
//	    jobLauncher.setJobRepository(jobRepository);
//	    jobLauncher.setTaskExecutor(simpleThreadPoolTaskExecutor());
//	    jobLauncher.afterPropertiesSet();
//	    return jobLauncher;
//	}
//
//	/** 배치 잡목록 스케줄테이블에 등록 */
//	@PostConstruct
//	public void jobListSetting() {
//		List<String> jobNames = jobExplorer.getJobNames();
//		List<String> dbJobs = jobSchedulerVoRepository.selectSrch("jobSchedulerVoMapper.findJobListCheck", null)
//				.stream().map(a->a.getJobName()).collect(Collectors.toList());
//		List<JobSchedulerVo> jobs = jobNames.stream().filter(a -> !dbJobs.contains(a)).map(a->JobSchedulerVo.builder().jobName(a).jobLock(false).build()).collect(Collectors.toList());
//		jobSchedulerVoRepository.insert("jobSchedulerVoMapper.insert", jobs);
//	}

}
