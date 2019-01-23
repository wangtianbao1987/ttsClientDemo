package com.pachira.tts.http.client;

import com.pachira.tts.http.client.utils.ManageData;
import com.pachira.tts.http.client.utils.RequestTTS;
import com.pachira.tts.http.client.utils.common.Utils;
import com.pachira.tts.http.client.utils.reqType.RequestTTSByHttpClient;
import com.pachira.tts.http.client.utils.reqType.RequestTTSBySocket;
import com.pachira.tts.http.client.utils.voice.DownloadManager;
import com.pachira.tts.http.client.utils.voice.PcmManager;
import com.pachira.tts.http.client.utils.voice.WavManager;
/**
 * 
 * @author	王添宝
 * @date	2018年11月6日
 * 
 * 注：
 * 1、HTTP请求使用HttpClient工具类，需要引入:
 * 		commons-logging-1.2.jar
 * 		httpclient-4.5.3.jar
 * 		httpcore-4.4.6.jar
 * 2、MP3播放需要引入：
 * 		jl1.0.1.jar
 * 
 */
public class Main {
	
	public static void main(String[] args) {
		// 演示的 voice项目 版本号
		String version = "1.2.1";
		// HTTP请求方式：POST/GET
		String method = "POST";
		// 测试使用。 play:播放；    download:下载
		String playOrDownload = "play";
		//测试使用。httpClient:使用httpClient框架的方式调用TTS接口； socket:使用socket拼报文的形式调用TTS接口。 若选择socket方式，程序将打印HTTP报文
		// 1.2.1版本后，使用socket可以将合成过程中出现的错误信息打印出来；使用httpClient暂不支持打印错误信息。
		String demoType = "socket";
		
		RequestTTS reqTTS = null;
		ManageData manage = null;
		
		// 确定使用的数据处理的类(manageData)
		if("play".equals(playOrDownload)) {
			if ("pcm".equals(ReqParam.format)) {
				manage = new PcmManager();
			}else if("wav".equals(ReqParam.format)) {
				manage = new WavManager();
			}
		}else if("download".equals(playOrDownload)) {
			manage = new DownloadManager();
		}
		
		// 确定请求HTTP使用的方式(reqTTS)
		if("socket".equals(demoType)) {
			int versionVal = Utils.getVersionVal(version);
			reqTTS = new RequestTTSBySocket(versionVal, method, manage);
		}else if("httpClient".equals(demoType)) {
			reqTTS = new RequestTTSByHttpClient(method, manage);
		}
		
		//调用connection请求http并处理吧
		reqTTS.connection();
		
		
	}
}
