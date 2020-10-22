package com.batch.cb.cb.crw.roll.character.parser;

import com.batch.cb.cb.util.crw.CrwVo;
import com.batch.cb.cb.util.crw.HttpClientFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class RollCampParserTest {

    @Test
    public void rollCampParserTest() throws Exception{
        CloseableHttpClient closeableHttpClient = HttpClientFactory.httpClient(1, 5000);
        CrwVo crwVo = CrwVo.builder().client(closeableHttpClient).build();
        RollCampParser parser = new RollCampParser();
        parser.read(crwVo);

        Assertions.assertNotNull(crwVo.getOutput());
    }

}
