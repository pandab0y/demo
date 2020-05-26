package com.example.demo.common.utils;

import org.apache.http.Header;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

public class HttpUtil {
	

	/**
	 * http请求
	 * @param url 请求链接
	 * @param httpMethod 请求方式
	 * @param body 请求参数字符串
	 * @return 请求结果
	 */
	public static String httpRequest(String url,HttpMethod httpMethod,String body, String cookie){
		RestTemplate restTemplate =new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		/* 这个对象有add()方法，可往请求头存入信息 */       
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		headers.add(HttpHeaders.COOKIE, cookie);
		/* 解决中文乱码的关键 , 还有更深层次的问题 关系到 StringHttpMessageConverter，先占位，以后补全*/ 
		HttpEntity<String> entity = new HttpEntity<String>(body, headers);
		ResponseEntity<String> result = restTemplate.exchange(url, httpMethod, entity,String.class);
		body = result.getBody();
		return body;
	}
	
	
	public static String requestSogouWeixin(String url) {
		RestTemplate restTemplate =new RestTemplate();
		HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
		HttpClient httpClient = HttpClientBuilder.create()
                .setRedirectStrategy(new LaxRedirectStrategy())
                .build();
		factory.setHttpClient(httpClient);
		restTemplate.setRequestFactory(factory);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/x-javascript; charset=utf-8");
		headers.add("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 11_0 like Mac OS X) AppleWebKit/604.1.38 (KHTML, like Gecko) Version/11.0 Mobile/15A372 Safari/604.1");
		HttpEntity<String> entity = new HttpEntity<>(headers);
		ResponseEntity<String> result = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
		System.out.println(result.getBody());
		result.getHeaders().get("Set-Cookie").stream().forEach(System.out::println);
		return "";
		
	}
	
	public static void main(String[] args) {
		requestSogouWeixin("https://weixin.sogou.com/weixinwap?query=%E6%AD%A6%E6%B1%89&type=2&ie=utf8&_sug_=n&_sug_type_=-1&s_from=input");
	}
}
