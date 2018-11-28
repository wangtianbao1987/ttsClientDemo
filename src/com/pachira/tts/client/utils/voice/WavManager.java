package com.pachira.tts.client.utils.voice;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;

import com.pachira.tts.client.ReqParam;
import com.pachira.tts.client.utils.RequestTTS;
import com.pachira.tts.client.utils.SourceUtils;
import com.pachira.tts.client.utils.Utils;
import com.pachira.tts.client.utils.reqType.RequestTTSByHttpClient;
import com.pachira.tts.client.utils.reqType.RequestTTSBySocket;

public class WavManager {
	/**
	 * Socket形式请求TTS并处理WAV数据（播放）
	 */
	public static RequestTTS getRequestTTSBySocket(String method) {
		// Socket方式请求TTS
		return new RequestTTSBySocket(method) {
			/**
			 * 流式播放WAV音频
			 */
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
				FileOutputStream fos = null;
				try {
					File wavFile = new File(ReqParam.downloadDir,new SimpleDateFormat("yyyy-MM-dd HH_mm_ss_SSS").format(new Date())+".wav");
					fos = new FileOutputStream(wavFile);
					getData().put("wavFile", wavFile);
					getData().put("fos", fos);
				} catch (Exception e) {
					SourceUtils.close(fos);
					throw new RuntimeException("准备文件失败");
				}
			}
			@Override
			public void endManage() {
				AudioInputStream ais = null;
				SourceDataLine line = null;
				File wavFile = null;
				FileOutputStream fos = null;
				try {
					fos = (FileOutputStream)getData().get("fos");
					wavFile = (File)getData().get("wavFile");
					ais = AudioSystem.getAudioInputStream(wavFile);// 获得音频输入流
					AudioFormat baseFormat = ais.getFormat();// 指定声音流中特定数据安排
					DataLine.Info info = new DataLine.Info(SourceDataLine.class, baseFormat);
					line = (SourceDataLine) AudioSystem.getLine(info);
					// 从混频器获得源数据行
					line.open(baseFormat);// 打开具有指定格式的行，这样可使行获得所有所需的系统资源并变得可操作。
					line.start();// 允许数据行执行数据 I/O
					int BUFFER_SIZE = 4000 * 4;
					int intBytes = 0;
					byte[] audioData = new byte[BUFFER_SIZE];
					while (intBytes != -1) {
						intBytes = ais.read(audioData, 0, BUFFER_SIZE);// 从音频流读取指定的最大数量的数据字节，并将其放入给定的字节数组中。
						if (intBytes >= 0) {
							line.write(audioData, 0, intBytes);// 通过此源数据行将音频数据写入混频器。
						}
					}
				} catch (Exception e) {
					throw new RuntimeException("播放wav失败");
				}  finally {
					// 等一秒钟等待音频播放完
					Utils.sleep(1000);
					try {line.drain();}catch(Exception e) {}
					SourceUtils.close(fos,line,ais);
					Utils.deleteFile(wavFile);
				}
			}
		};
	}
	
	/**
	 * HttpClient形式请求TTS并处理WAV数据（播放）
	 */
	public static RequestTTS getRequestTTSByHttpClient(String method) {
		return new RequestTTSByHttpClient(method) {
			/**
			 * 流式播放WAV音频
			 */
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
				FileOutputStream fos = null;
				try {
					File wavFile = new File(ReqParam.downloadDir,new SimpleDateFormat("yyyy-MM-dd HH_mm_ss_SSS").format(new Date())+".wav");
					fos = new FileOutputStream(wavFile);
					getData().put("wavFile", wavFile);
					getData().put("fos", fos);
				} catch (Exception e) {
					SourceUtils.close(fos);
					throw new RuntimeException("准备文件失败");
				}
			}
			@Override
			public void endManage() {
				AudioInputStream ais = null;
				SourceDataLine line = null;
				File wavFile = null;
				FileOutputStream fos = null;
				try {
					fos = (FileOutputStream)getData().get("fos");
					wavFile = (File)getData().get("wavFile");
					ais = AudioSystem.getAudioInputStream(wavFile);// 获得音频输入流
					AudioFormat baseFormat = ais.getFormat();// 指定声音流中特定数据安排
					DataLine.Info info = new DataLine.Info(SourceDataLine.class, baseFormat);
					line = (SourceDataLine) AudioSystem.getLine(info);
					// 从混频器获得源数据行
					line.open(baseFormat);// 打开具有指定格式的行，这样可使行获得所有所需的系统资源并变得可操作。
					line.start();// 允许数据行执行数据 I/O
					int BUFFER_SIZE = 4000 * 4;
					int intBytes = 0;
					byte[] audioData = new byte[BUFFER_SIZE];
					while (intBytes != -1) {
						intBytes = ais.read(audioData, 0, BUFFER_SIZE);// 从音频流读取指定的最大数量的数据字节，并将其放入给定的字节数组中。
						if (intBytes >= 0) {
							line.write(audioData, 0, intBytes);// 通过此源数据行将音频数据写入混频器。
						}
					}
				} catch (Exception e) {
					throw new RuntimeException("播放wav失败");
				}  finally {
					// 等一秒钟等待音频播放完
					Utils.sleep(1000);
					try {line.drain();}catch(Exception e) {}
					SourceUtils.close(fos,line, ais);
					Utils.deleteFile(wavFile);
				}
			}
		};
	}
	
}
