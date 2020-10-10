package com.batch.cb.cb.lagacy;

import com.batch.cb.cb.util.crw.CrwParser;
import com.batch.cb.cb.util.crw.CrwVo;
import com.google.common.collect.Lists;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Component
public class ApiParser implements CrwParser {

	private CloseableHttpClient httpClient;
	private String url = "http://127.0.0.1:8081/daRegAdset/regDaRegAdset";
	private String encode = "UTF-8";
	
	@Override
	public void read(CrwVo vo) throws ClientProtocolException, IOException {
		Map<String, String> pv = (Map<String, String>) vo.getInput();
		String order = pv.get("advId");
		String exposePage = pv.get("advName");
		httpClient = vo.getClient();
		List<NameValuePair> param = Lists.newArrayList();
		param.add(new BasicNameValuePair("advId", order));
		param.add(new BasicNameValuePair("advName", exposePage));
		HttpGet hg = new HttpGet(url+"?"+URLEncodedUtils.format(param, encode));
//		hg.setHeader("charset", "utf-8");
//		hg.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
//		hg.setHeader("Referer", "https://castbox.shopping.naver.com/");
//		hg.setHeader("Sec-Fetch-Mode", "cors");
//		hg.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/76.0.3809.100 Safari/537.36");
//		hg.setHeader("X-Requested-With", "XMLHttpRequest");
		
		HttpResponse rs = httpClient.execute(hg);
		String text = EntityUtils.toString(rs.getEntity());
		System.out.println("!@#!@#!@#"+text);
/*		Stream<Header> headers = Stream.of(response.getAllHeaders());
		List<Map<String, String>> result = headers.filter(a -> a.getName().equals("Location")).map(a -> {
			Map<String, String> urlResult = Maps.newHashMap();
			System.out.println(a.getValue());
			String fullDomain = a.getValue();
			urlResult.put("fullDomain", fullDomain);
			String[] domainSplit = fullDomain.split("\\?");
			urlResult.put("url", domainSplit[0]);
			return urlResult;
		}).collect(Collectors.toList());*/
		//vo.setOutput(result);
	}

}