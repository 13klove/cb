package com.batch.cb.cb.config;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@RequiredArgsConstructor
public class CbBatchConfig extends DefaultBatchConfigurer {

	private final DataSource dataSource;

	//jobRepository는 다른 트렌젝션이 읽거나 할때 수정이 안되는 ISOLATION_SERIALIZABLE 상태라 에러가 난다. 햐여
	//DefaultBatchConfigurer를 상속 받고 ISOLATION_READ_COMMITTED로 수정
	//추가로 상속을 안받고 bean으로 재정의 한 경우.... 똑같이 에러가 난다.
	@Override
	protected JobRepository createJobRepository() throws Exception {
		JobRepositoryFactoryBean factory = new JobRepositoryFactoryBean();
		factory.setDataSource(dataSource);
		factory.setTransactionManager(new DataSourceTransactionManager(dataSource));
		factory.setIsolationLevelForCreate("ISOLATION_READ_COMMITTED");
		factory.setTablePrefix("BATCH_");
		factory.afterPropertiesSet();
		return factory.getObject();
	}

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
