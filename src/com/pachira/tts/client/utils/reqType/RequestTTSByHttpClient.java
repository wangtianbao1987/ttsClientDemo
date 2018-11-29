package com.pachira.tts.client.utils.reqType;

import java.io.InputStream;
import java.util.HashMap;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import com.pachira.tts.client.ReqParam;
import com.pachira.tts.client.utils.ManageData;
import com.pachira.tts.client.utils.RequestTTS;
import com.pachira.tts.client.utils.common.HttpUtils;
import com.pachira.tts.client.utils.common.SourceUtils;
import com.pachira.tts.client.utils.common.Utils;
/**
 * 实现使用HttpClient工具类发送HTTP请求.
 */
public class RequestTTSByHttpClient implements RequestTTS {
	private String method;
	private ManageData manage;
	/**
	 * HttpClient方式请求TTS服务
	 */
	public RequestTTSByHttpClient(String method,ManageData manage) {
		this.method = method;
		this.manage = manage;
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
					manage.preManage();
					in = httpEntity.getContent();
					int bufferSize = 512;
					byte[] buff = new byte[bufferSize];
					int len = 0;
					while ((len = in.read(buff)) != -1) {
						manage.manage(buff, len);
					}
					manage.endManage();
				}else {
					manage.reqErr("无返回数据");
				}
			}else {
				byte[] buff = EntityUtils.toByteArray(httpEntity);
				manage.reqErr(new String(buff,"UTF-8"));
			}
		} catch (Exception ee) {
			ee.printStackTrace();
		} finally {
			// 等一秒钟等待音频播放完
			Utils.sleep(1000);
			SourceUtils.close(in);
		}
	}
}
