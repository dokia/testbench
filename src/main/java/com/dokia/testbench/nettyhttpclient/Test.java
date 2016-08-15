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

public class Test {

	public static void main(String[] args) throws Exception{
		HttpClient client;
		HttpClientConfig config;
		config = HttpClientConfig.builder()
				.withNumConnections(1)
				.withReadTimeout(Duration.standardSeconds(1000))
				.build();
		
		client = HttpClientInit.createClient(config, new Lifecycle());
		ListenableFuture<StatusResponseHolder> future = client.go(
				new Request(HttpMethod.GET, new URL("http://www.baidu.com")),
				new StatusResponseHandler(Charset.defaultCharset()));
		StatusResponseHolder holder = future.get();
		if (holder.getStatus().equals(HttpResponseStatus.OK)) {
			String content = holder.getContent();
			System.out.println(content);
		} else {
			System.out.println(holder.getStatus());
		}
		
		
	}

}
