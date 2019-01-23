package com.pachira.tts.http.client.utils.reqType;

import java.io.InputStream;
import java.util.HashMap;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import com.pachira.tts.http.client.ReqParam;
import com.pachira.tts.http.client.utils.HttpUtils;
import com.pachira.tts.http.client.utils.RequestTTS;
import com.pachira.tts.http.client.utils.SourceUtils;
import com.pachira.tts.http.client.utils.Utils;

public abstract class RequestTTSByHttpClient extends RequestTTS {
	private String method;
	/**
	 * HttpClient方式请求TTS服务
	 */
	public RequestTTSByHttpClient(String method) {
		this.method = method;
	}
	/**
	 * 通过HttpClient请求TTS接口
	 */
	public void connection() {
		InputStream in = null;
		try {
			HttpResponse resp = null;
			if("GET".equals(method)) {
				HttpUtils hu = new HttpUtils();
				resp = hu.doGet(ReqParam.url, new HashMap<String, String>(), ReqParam.param);
			}else if("POST".equals(method)) {
				HttpUtils hu = new HttpUtils();
				resp = hu.doPost(ReqParam.url, new HashMap<String, String>(), ReqParam.param, "");
			}
			String contentType = resp.getHeaders("Content-Type")[0].getValue();
			HttpEntity httpEntity = resp.getEntity();
			if(contentType.startsWith("audio")) {
				if (httpEntity != null) {
					preManage();
					in = httpEntity.getContent();
					int bufferSize = 512;
					byte[] buff = new byte[bufferSize];
					int len = 0;
					while ((len = in.read(buff)) != -1) {
						manage(buff, len);
					}
					endManage();
				}else {
					reqErr("无返回数据");
				}
			}else {
				byte[] buff = EntityUtils.toByteArray(httpEntity);
				reqErr(new String(buff,"UTF-8"));
			}
		} catch (Exception ee) {
			ee.printStackTrace();
		} finally {
			// 等一秒钟等待音频播放完
			Utils.sleep(1000);
			SourceUtils.close(in);
		}
	}
	
	/**
	 * 获取响应数据前执行的回调
	 */
	public abstract void preManage();
	/**
	 * 处理数据的回调
	 * @param buff		缓冲数据
	 * @param buffSize	缓冲数据长度
	 * @throws Exception
	 */
	public abstract void manage(byte[] buff,int buffSize) throws Exception;
	/**
	 * 正常获取数据后执行的回调
	 */
	public abstract void endManage();
	/**
	 * 请求过程出错的回调
	 * @param error
	 */
	public abstract void reqErr(String error);
}
