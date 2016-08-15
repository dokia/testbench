package com.dokia.testbench.nettyhttpclient;

import java.net.URL;
import java.nio.charset.Charset;

import com.google.common.util.concurrent.ListenableFuture;

public interface AsynHttpClient <T>{

	ListenableFuture<T> get(URL url, Charset charset);
}
