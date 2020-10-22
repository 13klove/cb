package com.batch.cb.cb.crw.roll.characterDetail.output;

import com.batch.cb.cb.crw.roll.characterDetail.parser.RollCampDetailParser;
import com.batch.cb.cb.util.crw.CrwVo;
import com.batch.cb.cb.util.crw.HttpClientFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.junit.jupiter.api.Test;

public class RollCampDetailOutPutTest {

    @Test
    public void RollCampDetailOutPutTest()throws Exception{
        CloseableHttpClient closeableHttpClient = HttpClientFactory.httpClient(1, 1000);
        String url = "champion/garen/statistics/";
        CrwVo crwVo = CrwVo.builder().client(closeableHttpClient).input(url).build();

        RollCampDetailParser rollCampDetailParser = new RollCampDetailParser();
        rollCampDetailParser.read(crwVo);

        RollCampDetailOutPut rollCampDetailOutPut = new RollCampDetailOutPut();
        rollCampDetailOutPut.getOutput((String) crwVo.getOutput());
    }

}
