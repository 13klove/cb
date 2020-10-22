package com.batch.cb.cb.rollCampDetailJob.reader;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import java.util.List;

public class RollCampDetailReader implements ItemReader<String>, StepExecutionListener {

    private List<String> list;

    @Override
    public void beforeStep(StepExecution stepExecution) {
        list = (List<String>) stepExecution.getJobExecution().getExecutionContext().get("campDetailUrl");
    }

    @Override
    public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        return !this.list.isEmpty() ? this.list.remove(0) : null;
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        return null;
    }

}
