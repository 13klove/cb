package com.batch.cb.cb.rollCampDetailJob.processor;

import com.batch.cb.cb.crw.roll.characterDetail.output.RollCampDetailOutPut;
import com.batch.cb.cb.crw.roll.characterDetail.parser.RollCampDetailParser;
import com.batch.cb.cb.domain.roll.character.entity.RollCharacter;
import com.batch.cb.cb.util.crw.CrwVo;
import lombok.RequiredArgsConstructor;
import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.batch.item.ItemProcessor;

@RequiredArgsConstructor
public class RollCampDetailProcessor implements ItemProcessor<String, RollCharacter> {

    private final CloseableHttpClient httpClient;

    @Override
    public RollCharacter process(String s) throws Exception {
        CrwVo crwVo = CrwVo.builder().client(httpClient).input(s).build();

        new RollCampDetailParser().read(crwVo);

        return new RollCampDetailOutPut().getOutput((String) crwVo.getOutput());
    }

}
