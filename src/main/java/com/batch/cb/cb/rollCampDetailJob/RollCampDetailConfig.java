package com.batch.cb.cb.rollCampDetailJob;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Configuration
@RequiredArgsConstructor
public class RollCampDetailConfig {

    private static final int rollCampDetailStepChunk = 50;

    private JobBuilderFactory jobBuilderFactory;

    @Bean
    public Job rollCampDetailJob(){

        return null;
        //return jobBuilderFactory.get("rollCampDetailSaveJob")

    }

}
