package com.pachira.tts.http.client.utils.voice;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.pachira.tts.http.client.ReqParam;
import com.pachira.tts.http.client.utils.RequestTTS;
import com.pachira.tts.http.client.utils.SourceUtils;
import com.pachira.tts.http.client.utils.reqType.RequestTTSByHttpClient;
import com.pachira.tts.http.client.utils.reqType.RequestTTSBySocket;

public class DownloadManager {
	/**
	 * Socket形式请求TTS并处理响应数据（下载）
	 */
	public static RequestTTS getRequestTTSBySocket(String method) {
		return new RequestTTSBySocket(method) {
			@Override
			public void manage(byte[] buff,int buffSize) throws Exception {
				FileOutputStream fos = (FileOutputStream)getData().get("fos");
				fos.write(buff, 0, buffSize);
			}
			@Override
			public void reqErr(String error) {
				System.out.println("\n\n[ERROR]："+error);
			}
			@Override
			public void preManage() {
				try {
					File file = new File(ReqParam.downloadDir,new SimpleDateFormat("yyyy-MM-dd HH_mm_ss_SSS").format(new Date())+"."+ReqParam.format);
					FileOutputStream fos = new FileOutputStream(file);
					getData().put("fos", fos);
				}catch (Exception e) {
					throw new RuntimeException("创建文件输出流失败");
				}
			}
			@Override
			public void endManage() {
				SourceUtils.close(getData().get("fos"));
			}
		};
	}
	
	/**
	 * HttpClient形式请求TTS并处理响应数据（下载）
	 */
	public static RequestTTS getRequestTTSByHttpClient(String method) {
		return new RequestTTSByHttpClient(method) {
			@Override
			public void manage(byte[] buff,int buffSize) throws Exception {
				FileOutputStream fos = (FileOutputStream)getData().get("fos");
				fos.write(buff, 0, buffSize);
			}
			@Override
			public void reqErr(String error) {
				System.out.println("\n\n[ERROR]："+error);
			}
			@Override
			public void preManage() {
				try {
					File file = new File(ReqParam.downloadDir,new SimpleDateFormat("yyyy-MM-dd HH_mm_ss_SSS").format(new Date())+"."+ReqParam.format);
					FileOutputStream fos = new FileOutputStream(file);
					getData().put("fos", fos);
				}catch (Exception e) {
					throw new RuntimeException("创建文件输出流失败");
				}
			}
			@Override
			public void endManage() {
				SourceUtils.close(getData().get("fos"));
			}
		};
	}
}
