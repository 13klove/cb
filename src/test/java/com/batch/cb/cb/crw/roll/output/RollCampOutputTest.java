package com.batch.cb.cb.crw.roll.output;

import com.batch.cb.cb.crw.roll.parser.RollCampParser;
import com.batch.cb.cb.util.crw.CrwVo;
import com.batch.cb.cb.util.crw.HttpClientFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class RollCampOutputTest {

    @Test
    public void RollCampOutputTest() throws Exception{
        CloseableHttpClient closeableHttpClient = HttpClientFactory.httpClient(1, 5000);
        CrwVo crwVo = CrwVo.builder().client(closeableHttpClient).build();

        RollCampParser parser = new RollCampParser();
        parser.read(crwVo);

        RollCampOutput rollCampOutput = new RollCampOutput();
        List<String> output = rollCampOutput.getOutput((String) crwVo.getOutput());

        Assertions.assertNotNull(output);
    }

}
