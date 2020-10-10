
package com.batch.cb.cb.libraryClassificationJob.libraryClassificationStep.writer;

import com.batch.cb.cb.domain.library.dto.LibraryDto;
import com.sun.deploy.util.StringUtils;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.core.io.FileSystemResource;

import java.util.Arrays;
import java.util.List;

//https://howtodoinjava.com/spring-batch/flatfileitemwriter-write-to-csv-file/
public class LibraryDbWriter  {

    private static final List<String> header = Arrays.asList("도서관명", "시도명", "시군구명", "도서관 타입");

    public FlatFileItemWriter<LibraryDto> libraryFlatFileItemWriter(){
        FlatFileItemWriter<LibraryDto> flatFileItemWriter = new FlatFileItemWriter<>();
        flatFileItemWriter.setResource(new FileSystemResource("C:\\Users\\hbjang\\Desktop\\result.csv"));
        flatFileItemWriter.setAppendAllowed(true);
        flatFileItemWriter.setEncoding("euc-kr");
        flatFileItemWriter.setHeaderCallback(a->{a.write(StringUtils.join(header, ","));});

        DelimitedLineAggregator<LibraryDto> delimitedLineAggregator = new DelimitedLineAggregator<>();
        delimitedLineAggregator.setDelimiter(",");
        flatFileItemWriter.setLineAggregator(delimitedLineAggregator);
        BeanWrapperFieldExtractor<LibraryDto> beanWrapperFieldExtractor = new BeanWrapperFieldExtractor<>();
        beanWrapperFieldExtractor.setNames(new String[]{"libraryNm", "bigLocal", "smallLocal", "libraryType"});
        delimitedLineAggregator.setFieldExtractor(beanWrapperFieldExtractor);

        return flatFileItemWriter;
    }

}
