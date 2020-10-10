package com.batch.cb.cb.lagacy;

import com.batch.cb.cb.util.crw.CrwParser;
import com.batch.cb.cb.util.crw.CrwVo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.Data;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
public class NvSpParser implements CrwParser {

	private String url = "https://castbox.shopping.naver.com/shopbox/product.nhn";
	private String encode = "UTF-8";
	
	@Override
	public void read(CrwVo vo) throws ClientProtocolException, IOException {
		Map<String, String> pv = (Map<String, String>) vo.getInput();
		String order = pv.get("order");
		String exposePage = pv.get("exposePage");
		CloseableHttpClient httpClient = vo.getClient();
		List<NameValuePair> param = Lists.newArrayList();
		param.add(new BasicNameValuePair("type", "next"));
		param.add(new BasicNameValuePair("order", order));
		param.add(new BasicNameValuePair("exposePage", exposePage));
		param.add(new BasicNameValuePair("bannerType", "next"));  
		param.add(new BasicNameValuePair("bannerOrder", "1"));
		param.add(new BasicNameValuePair("bannerExposePage", "4"));
		HttpGet hg = new HttpGet(url+"?"+URLEncodedUtils.format(param, encode));
		hg.setHeader("charset", "utf-8");
		hg.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
		hg.setHeader("Referer", "https://castbox.shopping.naver.com/");
		hg.setHeader("Sec-Fetch-Mode", "cors");
		hg.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/76.0.3809.100 Safari/537.36");
		hg.setHeader("X-Requested-With", "XMLHttpRequest");
		
		HttpResponse rs = httpClient.execute(hg);
		String text = EntityUtils.toString(rs.getEntity());
		//System.out.println(text);
		Document doc = Jsoup.parse(text);
		Elements els = doc.select("div.shop_t1 div#contents_productAd div.lst_gift ul.giftlst_u li.giftlst_l");
		List<Map<String, String>> resultUrl = els.stream().map((a) -> {
			Map<String, String> result = Maps.newHashMap();
			result.put("redirectUrl", a.select("a.giftlst_a").attr("href"));
			result.put("desc1", a.select("p.giftlst_words span.giftlst_s1").text());
			result.put("desc2", a.select("p.giftlst_words span.giftlst_s2").text());
			return result;
		}).collect(Collectors.toList());
		vo.setOutput(resultUrl);
	}

}
