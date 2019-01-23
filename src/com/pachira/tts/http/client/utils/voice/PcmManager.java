package com.pachira.tts.http.client.utils.voice;

import javax.sound.sampled.SourceDataLine;

import com.pachira.tts.http.client.ReqParam;
import com.pachira.tts.http.client.utils.RequestTTS;
import com.pachira.tts.http.client.utils.Utils;
import com.pachira.tts.http.client.utils.reqType.RequestTTSByHttpClient;
import com.pachira.tts.http.client.utils.reqType.RequestTTSBySocket;

public class PcmManager {
	/**
	 * Socket形式请求TTS并处理PCM数据（播放）
	 */
	public static RequestTTS getRequestTTSBySocket(String method) {
		return new RequestTTSBySocket(method) {
			/**
			 * 流式播放PCM音频
			 */
			@Override
			public void manage(byte[] buff,int buffSize) throws Exception {
				SourceDataLine sdl = (SourceDataLine)getData().get("sdl");
				Byte bb = (Byte)getData().get("bb");
				if (bb == null) {
					if (buffSize % 2 == 0) {
						sdl.write(buff, 0, buffSize);
					} else {
						sdl.write(buff, 0, buffSize - 1);
						bb = buff[buffSize - 1];
						getData().put("bb", bb);
					}
				} else {
					if (buffSize % 2 == 0) {
						byte[] buff2 = new byte[buff.length + 1];
						buff2[0] = bb;
						System.arraycopy(buff, 0, buff2, 1, buffSize);
						sdl.write(buff2, 0, buffSize);
						bb = buff2[buffSize];
						getData().put("bb", bb);
					} else {
						byte[] buff2 = new byte[buff.length + 1];
						buff2[0] = bb;
						System.arraycopy(buff, 0, buff2, 1, buffSize);
						sdl.write(buff2, 0, buffSize + 1);
						bb = null;
						getData().remove("bb");
					}
				}
			}
			@Override
			public void reqErr(String error) {
				System.out.println("\n\n[ERROR]："+error);
			}
			@Override
			public void preManage() {
				String sample_rate = ReqParam.param.get("sample_rate");
				if(sample_rate==null || "".equals(sample_rate)) {
					sample_rate = "16000";
				}
				SourceDataLine sdl = Utils.getSourceDataLine(512, Integer.parseInt(sample_rate));
				getData().put("sdl", sdl);
			}
			@Override
			public void endManage() {}
		};
	}
	/**
	 * HttpClient形式请求TTS并处理PCM数据（播放）
	 */
	public static RequestTTS getRequestTTSByHttpClient(String method) {
		return new RequestTTSByHttpClient(method) {
			/**
			 * 流式播放PCM音频
			 */
			@Override
			public void manage(byte[] buff,int buffSize) throws Exception {
				SourceDataLine sdl = (SourceDataLine)getData().get("sdl");
				Byte bb = (Byte)getData().get("bb");
				if (bb == null) {
					if (buffSize % 2 == 0) {
						sdl.write(buff, 0, buffSize);
					} else {
						sdl.write(buff, 0, buffSize - 1);
						bb = buff[buffSize - 1];
						getData().put("bb", bb);
					}
				} else {
					if (buffSize % 2 == 0) {
						byte[] buff2 = new byte[buff.length + 1];
						buff2[0] = bb;
						System.arraycopy(buff, 0, buff2, 1, buffSize);
						sdl.write(buff2, 0, buffSize);
						bb = buff2[buffSize];
						getData().put("bb", bb);
					} else {
						byte[] buff2 = new byte[buff.length + 1];
						buff2[0] = bb;
						System.arraycopy(buff, 0, buff2, 1, buffSize);
						sdl.write(buff2, 0, buffSize + 1);
						bb = null;
						getData().remove("bb");
					}
				}
			}
			@Override
			public void reqErr(String error) {
				System.out.println("\n\n[ERROR]："+error);
			}
			@Override
			public void preManage() {
				String sample_rate = ReqParam.param.get("sample_rate");
				if(sample_rate==null || "".equals(sample_rate)) {
					sample_rate = "16000";
				}
				SourceDataLine sdl = Utils.getSourceDataLine(512, Integer.parseInt(sample_rate));
				getData().put("sdl", sdl);
			}
			@Override
			public void endManage() {}
		};
	}
	
}
