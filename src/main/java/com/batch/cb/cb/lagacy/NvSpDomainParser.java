package com.batch.cb.cb.lagacy;

import com.batch.cb.cb.util.crw.CrwParser;
import com.batch.cb.cb.util.crw.CrwVo;
import com.google.common.collect.Maps;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class NvSpDomainParser implements CrwParser {

	private CloseableHttpClient redirectHttpClient;
	
	@Override
	public void read(CrwVo vo) throws ClientProtocolException, IOException {
		redirectHttpClient = vo.getClient();
		String url = (String) vo.getInput();
		HttpGet get = new HttpGet(url);
		HttpHost target = URIUtils.extractHost(get.getURI());
		CloseableHttpResponse response = redirectHttpClient.execute(target, get);
		Stream<Header> headers = Stream.of(response.getAllHeaders());
		List<Map<String, String>> result = headers.filter(a -> a.getName().equals("Location")).map(a -> {
			Map<String, String> urlResult = Maps.newHashMap();
			System.out.println(a.getValue());
			String fullDomain = a.getValue();
			urlResult.put("fullDomain", fullDomain);
			String[] domainSplit = fullDomain.split("\\?");
			urlResult.put("url", domainSplit[0]);
			return urlResult;
		}).collect(Collectors.toList());
		vo.setOutput(result);
	}

}