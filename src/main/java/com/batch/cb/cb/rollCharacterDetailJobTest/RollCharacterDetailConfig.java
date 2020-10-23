package com.batch.cb.cb.rollCharacterDetailJobTest;

import com.batch.cb.cb.domain.roll.character.entity.RollCharacter;
import com.batch.cb.cb.domain.roll.character.repository.RollCharacterRepository;
import com.batch.cb.cb.domain.roll.position.repository.RollPositionRepository;
import com.batch.cb.cb.rollCharacterDetailJobTest.processor.RollCampDetailTestProcessor;
import com.batch.cb.cb.rollCharacterDetailJobTest.reader.RollCampDetailTestReader;
import com.batch.cb.cb.rollCharacterDetailJobTest.writer.RollCampDetailTestWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManagerFactory;

@Transactional
@Configuration
@RequiredArgsConstructor
public class RollCharacterDetailConfig {

    private static final int rollCampDetailStepChunk = 100;

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final EntityManagerFactory entityManagerFactory;
    private final RollCharacterRepository rollCharacterRepository;
    private final RollPositionRepository rollPositionRepository;

    @Bean
    public Job getTestJob(){
        return jobBuilderFactory.get("testJobRoll")
                .start(getTestStep())
                .incrementer(new RunIdIncrementer())
                .build();
    }

    @Bean
    public Step getTestStep(){
        return stepBuilderFactory.get("testStepRoll")
                .<RollCharacter, RollCharacter>chunk(rollCampDetailStepChunk)
                .reader(new RollCampDetailTestReader())
                .processor(new RollCampDetailTestProcessor())
                .writer(new RollCampDetailTestWriter(rollCharacterRepository, rollPositionRepository, entityManagerFactory))
                .build();
    }


}
