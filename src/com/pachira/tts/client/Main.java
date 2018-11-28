package com.pachira.tts.client;

import com.pachira.tts.client.utils.voice.DownloadManager;
import com.pachira.tts.client.utils.voice.PcmManager;
import com.pachira.tts.client.utils.voice.WavManager;
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
		// HTTP请求方式：POST/GET
		String method = "POST";
		// 测试使用。 play:播放；    download:下载
		String playOrDownload = "play";
		//测试使用。httpClient:使用httpClient框架的方式调用TTS接口； socket:使用socket拼报文的形式调用TTS接口。 若选择socket方式，程序将打印HTTP报文
		// 1.2.1版本后，使用socket可以将合成过程中出现的错误信息打印出来；使用httpClient暂不支持打印错误信息。
		String demoType = "socket";
		
		if("socket".equals(demoType)) {
			if("play".equals(playOrDownload)) {
				// 播放音频
				if ("pcm".equals(ReqParam.format)) {
					// 播放PCM格式的TTS（流式播放）
					PcmManager.getRequestTTSBySocket(method).connection();
				}else if("wav".equals(ReqParam.format)) {
					// 播放WAV
					WavManager.getRequestTTSBySocket(method).connection();
				}
			}else if("download".equals(playOrDownload)) {
				// 下载PCM/WAV
				DownloadManager.getRequestTTSBySocket(method).connection();
			}
		}else if("httpClient".equals(demoType)) {
			if("play".equals(playOrDownload)) {
				// 播放音频
				if ("pcm".equals(ReqParam.format)) {
					// 播放PCM格式的TTS（流式播放）
					PcmManager.getRequestTTSByHttpClient(method).connection();
				}else if("wav".equals(ReqParam.format)) {
					// 播放WAV
					WavManager.getRequestTTSByHttpClient(method).connection();
				}
			}else if("download".equals(playOrDownload)) {
				// 下载PCM/WAV
				DownloadManager.getRequestTTSByHttpClient(method).connection();
			}
		}
		
	}
}
