package com.batch.cb.cb.libraryClassificationJob.libraryClassificationStep.reader;

import com.batch.cb.cb.domain.tempLibrary.dto.TempLibraryDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.FileSystemResource;

import java.awt.*;
import java.io.File;

@Slf4j
public class LibraryCsvReader {

    public FlatFileItemReader<TempLibraryDto> libraryCsvReader(){
        FlatFileItemReader<TempLibraryDto> csvReader = new FlatFileItemReader<>();
        //csvReader.setResource(new FileSystemResource("C:\\Users\\hbjang\\Desktop\\temp.csv"));
        csvReader.setResource(new FileSystemResource("C:\\Users\\hbjang\\Desktop\\temp.csv"));

        csvReader.setLinesToSkip(1);
        csvReader.setEncoding("MS949");

        DefaultLineMapper<TempLibraryDto> defaultLineMapper = new DefaultLineMapper<>();

        DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer();
        delimitedLineTokenizer.setNames("libraryNm", "bigLocal", "smallLocal", "libraryType");
        delimitedLineTokenizer.setStrict(false);//이거를 해야 csv의 컬럼을 정확히 일치 안시켜도 된다. csv에는 컬럼이 4개 이상이여도 된다.

        BeanWrapperFieldSetMapper<TempLibraryDto> beanWrapperFieldSetMapper = new BeanWrapperFieldSetMapper<>();
        beanWrapperFieldSetMapper.setTargetType(TempLibraryDto.class);

        defaultLineMapper.setLineTokenizer(delimitedLineTokenizer);
        defaultLineMapper.setFieldSetMapper(beanWrapperFieldSetMapper);

        csvReader.setLineMapper(defaultLineMapper);

        return csvReader;
    }

    public void libraryCsvReaderTest() throws Exception {
        FlatFileItemReader<TempLibraryDto> csvReader = new FlatFileItemReader<>();
        System.out.println("=========================================");
        File file = new File("C:\\Users\\hbjang\\Desktop\\library.csv");

        //System.out.println(new ClassPathResource("C:\\Users\\hbjang\\Desktop\\library.csv"));
        //csvReader.setResource(new ClassPathResource("/library.csv"));
        csvReader.setResource(new FileSystemResource("C:\\Users\\hbjang\\Desktop\\temp.csv"));
        csvReader.setLinesToSkip(1);

        DefaultLineMapper<TempLibraryDto> defaultLineMapper = new DefaultLineMapper<>();

        DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer();
        delimitedLineTokenizer.setNames("libraryNm", "bigLocal", "smallLocal", "libraryType");

        BeanWrapperFieldSetMapper<TempLibraryDto> beanWrapperFieldSetMapper = new BeanWrapperFieldSetMapper<>();
        beanWrapperFieldSetMapper.setTargetType(TempLibraryDto.class);

        defaultLineMapper.setLineTokenizer(delimitedLineTokenizer);
        defaultLineMapper.setFieldSetMapper(beanWrapperFieldSetMapper);

        csvReader.setLineMapper(defaultLineMapper);
        csvReader.open(new ExecutionContext());

        TempLibraryDto tempLibraryDto;

        while ((tempLibraryDto = csvReader.read()) != null) {
            log.info("{}", tempLibraryDto);
        }

        csvReader.close();
    }

}
