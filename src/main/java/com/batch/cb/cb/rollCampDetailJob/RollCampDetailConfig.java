package com.batch.cb.cb.rollCampDetailJob;

import com.batch.cb.cb.domain.roll.character.entity.RollCharacter;
import com.batch.cb.cb.domain.roll.character.repository.RollCharacterRepository;
import com.batch.cb.cb.domain.roll.position.repository.RollPositionRepository;
import com.batch.cb.cb.rollCampDetailJob.processor.RollCampDetailProcessor;
import com.batch.cb.cb.rollCampDetailJob.reader.RollCampDetailReader;
import com.batch.cb.cb.rollCampDetailJob.task.RollCampSearchTask;
import lombok.RequiredArgsConstructor;
import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManagerFactory;

@Transactional
@Configuration
@RequiredArgsConstructor
public class RollCampDetailConfig {

    private static final int rollCampDetailStepChunk = 50;

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final CloseableHttpClient httpClient;
    private final EntityManagerFactory entityManagerFactory;
    private final RollCharacterRepository rollCharacterRepository;
    private final RollPositionRepository rollPositionRepository;

    @Bean
    public Job rollCampDetailJob(){
        return jobBuilderFactory.get("rollCampDetailSaveJob")
                .start(getRollCampUrlStep())
                .next(saveRollCampDetailStep())
                .incrementer(new RunIdIncrementer())
                .build();
    }

    @Bean
    public Step getRollCampUrlStep(){
        return stepBuilderFactory.get("getRollCampUrlStep")
                .tasklet(new RollCampSearchTask(httpClient))
                .build();
    }

    @Bean
    public Step saveRollCampDetailStep(){
        JpaItemWriter<RollCharacter> writer = new JpaItemWriter<>();
        writer.setEntityManagerFactory(entityManagerFactory);
        return stepBuilderFactory.get("saveRollCampDetailStep")
                .<String, RollCharacter>chunk(rollCampDetailStepChunk)
                .reader(new RollCampDetailReader())
                .processor(new RollCampDetailProcessor(httpClient, rollCharacterRepository, rollPositionRepository))
                .writer(writer)
                .build();
    }

}
