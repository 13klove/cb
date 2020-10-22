package com.batch.cb.cb.crw.roll.characterDetail.parser;

import com.batch.cb.cb.util.crw.CrwParser;
import com.batch.cb.cb.util.crw.CrwVo;
import lombok.Data;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

@Data
public class RollCampDetailParser implements CrwParser {

    private String host = "https://www.op.gg/";

    @Override
    public void read(CrwVo vo) throws IOException {
        String url = (String) vo.getInput();
        CloseableHttpClient client = vo.getClient();
        HttpGet get = new HttpGet(host+url);
        get.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
        get.setHeader("Accept-Encoding", "gzip, deflate, br");
        get.setHeader("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7");
        get.setHeader("Cache-Control", "max-age=0");
        get.setHeader("Connection", "keep-alive");
        get.setHeader("Host", "www.op.gg");
        get.setHeader("Sec-Fetch-Dest", "document");
        get.setHeader("Sec-Fetch-Mode", "navigate");
        get.setHeader("Sec-Fetch-Site", "none");
        get.setHeader("Sec-Fetch-User", "?1");
        get.setHeader("Upgrade-Insecure-Requests", "1");
        get.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/85.0.4183.121 Safari/537.36");

        HttpResponse execute = client.execute(get);
        String text = EntityUtils.toString(execute.getEntity());
        vo.setOutput(text);
    }

}
