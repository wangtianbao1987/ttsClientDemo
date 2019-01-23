package com.pachira.tts.uploadwav.httpclient;

import java.io.File;
import java.net.URLEncoder;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
/**
 * 使用httpclient工具类上传人声音频实例
 * @author	王添宝
 * @date	2019年1月23日
 * 
 * 注：程序运行所需jar文件：
 * commons-logging-1.2.jar
 * httpclient-4.5.3.jar
 * httpcore-4.4.6.jar
 * httpmime-4.5.3.jar
 * 
 */
public class Main {
	
	public static void main(String[] args) {
		upload();
	}
	
	public static void upload() {
		CloseableHttpClient httpclient = null;
		CloseableHttpResponse response = null;
		try {
			httpclient = HttpClients.createDefault();
			HttpPost httppost = new HttpPost("http://172.22.144.129:88/voice/uploadWav?language=putonghua&checkok=1&wavName="+URLEncoder.encode("中文", "UTF-8"));
			FileBody wavFile = new FileBody(new File("C:\\Users\\WTB\\Desktop\\tts.wav"));
			
			RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(200000).setSocketTimeout(200000).build();
			httppost.setConfig(requestConfig);
			HttpEntity reqEntity = MultipartEntityBuilder.create().addPart("wavFile", wavFile).build();
			httppost.setEntity(reqEntity);
			
			response = httpclient.execute(httppost);
			
			HttpEntity resEntity = response.getEntity();
			if (resEntity != null) {
				String responseEntityStr = EntityUtils.toString(response.getEntity());
				System.out.println(responseEntityStr);
			}
			EntityUtils.consume(resEntity);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MyUtils.close(response,httpclient);
		}
	}
	
}
