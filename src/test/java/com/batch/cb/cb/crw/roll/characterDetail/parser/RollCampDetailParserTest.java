package com.batch.cb.cb.crw.roll.characterDetail.parser;

import com.batch.cb.cb.util.crw.CrwVo;
import com.batch.cb.cb.util.crw.HttpClientFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RollCampDetailParserTest {

    @Test
    public void RollCampDetailParserTest()throws Exception{
        CloseableHttpClient closeableHttpClient = HttpClientFactory.httpClient(1, 1000);
        String url = "champion/garen/statistics/";
        CrwVo crwVo = CrwVo.builder().client(closeableHttpClient).input(url).build();

        RollCampDetailParser rollCampDetailParser = new RollCampDetailParser();
        rollCampDetailParser.read(crwVo);
        System.out.println(crwVo.getOutput());
        Assertions.assertNotNull(crwVo.getOutput());
    }
}
