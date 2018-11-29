package com.pachira.tts.client;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class ReqParam {
	public static String url = "http://172.22.144.138:88/voice/tts";
	public static String downloadDir = "/root/桌面/789";
	public static Map<String,String> param = new HashMap<String,String>();
	public static String param_json = "";
	public static String param_form = "";
	
	public static String text = "Hello,你好";
	// 请求参数：音量(取值范围1~2默认为1)
	public static String volume = "1";
	// 请求参数：语速(取值范围1~2默认为1)
	public static String speed = "1";
	// 请求参数：音调(取值范围1~2默认为1)
	public static String pitch = "1";
	// 请求参数：采样率:16000(默认)/8000
	public static String sample_rate = "16000";
	// 请求参数：是否支持SSML标记-> 0:不支持；1:支持(默认)
	public static String tag_mode = "1";
	// 请求参数：英文读法-> 0:自动判断(默认)；1:字母读法
	public static String eng_mode = "0";
	// 请求参数：音频格式（pcm(默认)/wav）。注：pcm支持流式播放；wav不支持
	public static String format = "pcm";
	// 请求参数：发音人->xiaochang(默认)
	public static String voice_name = "xiaochang";
	// 请求参数：语言标志，目前支持zh-cmn(默认)
	public static String language = "zh-cmn";
	
	static {
		System.out.println("TTS文本字数："+text.length());
		
		// 封装请求参数
		param.put("text", text);
		param.put("volume", volume);
		param.put("speed", speed);
		param.put("pitch", pitch);
		param.put("sample_rate", sample_rate);
		param.put("tag_mode", tag_mode);
		param.put("eng_mode", eng_mode);
		param.put("format", format);
		param.put("voice_name", voice_name);
		param.put("language", language);
		
		StringBuffer sb = new StringBuffer();
		sb.append(",\"text\":\""+text.replaceAll("\"", "\\\\\"")+"\"");
		sb.append(",\"volume\":\""+volume+"\"");
		sb.append(",\"speed\":\""+speed+"\"");
		sb.append(",\"pitch\":\""+pitch+"\"");
		sb.append(",\"sample_rate\":\""+sample_rate+"\"");
		sb.append(",\"tag_mode\":\""+tag_mode+"\"");
		sb.append(",\"eng_mode\":\""+eng_mode+"\"");
		sb.append(",\"format\":\""+format+"\"");
		sb.append(",\"voice_name\":\""+voice_name+"\"");
		sb.append(",\"language\":\""+language+"\"");
		param_json = "{"+sb.substring(1)+"}";
		
		sb = new StringBuffer();
		try {
			sb.append("&text="+URLEncoder.encode(text, "UTF-8"));
			sb.append("&volume="+volume);
			sb.append("&speed="+speed);
			sb.append("&pitch="+pitch);
			sb.append("&sample_rate="+sample_rate);
			sb.append("&tag_mode="+tag_mode);
			sb.append("&eng_mode="+eng_mode);
			sb.append("&format="+format);
			sb.append("&voice_name="+voice_name);
			sb.append("&language="+language);
			param_form = sb.substring(1);
		} catch (Exception e) {
			
		}
	}
	
}
