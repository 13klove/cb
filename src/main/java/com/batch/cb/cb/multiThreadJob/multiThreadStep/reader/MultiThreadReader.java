package com.batch.cb.cb.multiThreadJob.multiThreadStep.reader;


import com.batch.cb.cb.domain.tempLibrary.repository.TempLibraryJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

@RequiredArgsConstructor
public class MultiThreadReader implements ItemReader {

    private final TempLibraryJpaRepository tempLibraryJpaRepository;

    @Override
    public Object read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {

        return null;
    }

}
