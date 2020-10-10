package com.batch.cb.cb.util.crw;

import lombok.Builder;
import lombok.Data;
import org.apache.http.impl.client.CloseableHttpClient;

@Data
@Builder
public class CrwVo {

	private Object input;
	private Object output;
	private CloseableHttpClient client;
	
}
