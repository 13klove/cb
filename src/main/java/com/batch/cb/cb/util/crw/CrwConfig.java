package com.batch.cb.cb.util.crw;

import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class CrwConfig {

	@Bean(name="redirectHttpClient")
	public CloseableHttpClient getRedirectHttpClient(){
		return HttpClientFactory.redirectHttpClient(50, 3000);
	}
	
	@Bean(name="httpClient")
	public CloseableHttpClient getHttpClient(){
		return HttpClientFactory.httpClient(50, 3000);
	}	
	
}
