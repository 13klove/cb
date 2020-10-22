package com.batch.cb.cb.rollCampDetailJob.task;

import com.batch.cb.cb.crw.roll.output.RollCampOutput;
import com.batch.cb.cb.crw.roll.parser.RollCampParser;
import com.batch.cb.cb.util.crw.CrwVo;
import lombok.RequiredArgsConstructor;
import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import java.util.List;

@RequiredArgsConstructor
public class RollCampSearchTask implements Tasklet {

    private final CloseableHttpClient httpClient;

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        CrwVo vo = CrwVo.builder().client(httpClient).build();
        new RollCampParser().read(vo);

        List<String> output = new RollCampOutput().getOutput((String) vo.getOutput());

        chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext().put("campDetailUrl", output);
        return RepeatStatus.FINISHED;
    }
}
