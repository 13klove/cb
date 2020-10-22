package com.batch.cb.cb.rollCampDetailJob.writer;

import org.springframework.batch.item.database.JpaItemWriter;

public class RollCampDetailWriter {

    public JpaItemWriter RollCam(){
        return new JpaItemWriter<>();
    }

}
