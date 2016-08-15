package com.dokia.testbench.nettyhttpclient;

import java.net.URL;
import java.nio.charset.Charset;

import org.jboss.netty.handler.codec.http.HttpMethod;
import org.jboss.netty.handler.codec.http.HttpResponseStatus;
import org.joda.time.Duration;

import com.google.common.util.concurrent.ListenableFuture;
import com.metamx.common.lifecycle.Lifecycle;
import com.metamx.http.client.HttpClient;
import com.metamx.http.client.HttpClientConfig;
import com.metamx.http.client.HttpClientInit;
import com.metamx.http.client.Request;
import com.metamx.http.client.response.StatusResponseHandler;
import com.metamx.http.client.response.StatusResponseHolder;

public class StatusAsynHttpClient implements AsynHttpClient<StatusResponseHolder>{

	HttpClient client;
	
	public StatusAsynHttpClient(int numConnections, int readTimeout) {
		HttpClientConfig config = HttpClientConfig.builder()
				.withNumConnections(numConnections)
				.withReadTimeout(Duration.standardSeconds(readTimeout))
				.build();
		client = HttpClientInit.createClient(config, new Lifecycle());
	}
	
	@Override
	public ListenableFuture<StatusResponseHolder> get(URL url, Charset charset) {
		ListenableFuture<StatusResponseHolder> future = client.go(
				new Request(HttpMethod.GET, url),
				new StatusResponseHandler(charset)
				);
		return future;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args) throws Exception{
		AsynHttpClient httpClient = new StatusAsynHttpClient(5, 100);
		ListenableFuture<StatusResponseHolder> future = 
				httpClient.get(new URL("http://www.baidu.com"), Charset.defaultCharset());
		StatusResponseHolder holder = future.get();
		if (holder.getStatus().equals(HttpResponseStatus.OK)) {
			String content = holder.getContent();
			System.out.println(content);
		} else {
			System.out.println(holder.getStatus());
		}
	}
}
