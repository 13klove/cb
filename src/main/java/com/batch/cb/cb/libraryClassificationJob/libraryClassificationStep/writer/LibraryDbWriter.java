
package com.batch.cb.cb.libraryClassificationJob.libraryClassificationStep.writer;

import com.batch.cb.cb.domain.library.entity.Library;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.core.io.FileSystemResource;

//https://howtodoinjava.com/spring-batch/flatfileitemwriter-write-to-csv-file/
public class LibraryDbWriter  {

    public FlatFileItemWriter<Library> libraryFlatFileItemWriter(){
        FlatFileItemWriter<Library> flatFileItemWriter = new FlatFileItemWriter<>();
        flatFileItemWriter.setResource(new FileSystemResource("C:\\Users\\hbjang\\Desktop\\result.csv"));
        flatFileItemWriter.setAppendAllowed(true);

        //File f = new File("C:\\Users\\hbjang\\Desktop\\result.csv");
        return flatFileItemWriter;
    }

}
