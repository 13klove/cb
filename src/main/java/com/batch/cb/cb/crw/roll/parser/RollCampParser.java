package com.batch.cb.cb.crw.roll.parser;

import com.batch.cb.cb.util.crw.CrwParser;
import com.batch.cb.cb.util.crw.CrwVo;
import lombok.Data;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

@Data
public class RollCampParser implements CrwParser {

    private String host = "https://www.op.gg/champion/statistics";

    @Override
    public void read(CrwVo vo) throws IOException {
        CloseableHttpClient client = vo.getClient();
        HttpGet hg = new HttpGet(host);
        hg.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
        hg.setHeader("Accept-Encoding", "gzip, deflate, br");
        hg.setHeader("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7");
        hg.setHeader("Cache-Control", "max-age=0");
        hg.setHeader("Connection", "keep-alive");
        hg.setHeader("Host", "www.op.gg");
        hg.setHeader("Referer", "https://www.op.gg/");
        hg.setHeader("Sec-Fetch-Dest", "document");
        hg.setHeader("Sec-Fetch-Mode", "navigate");
        hg.setHeader("Sec-Fetch-Site", "same-origin");
        hg.setHeader("Sec-Fetch-User", "?1");
        hg.setHeader("Upgrade-Insecure-Requests", "1");
        hg.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/85.0.4183.121 Safari/537.36");

        HttpResponse rs = client.execute(hg);
        String text = EntityUtils.toString(rs.getEntity());
        vo.setOutput(text);
    }

}
