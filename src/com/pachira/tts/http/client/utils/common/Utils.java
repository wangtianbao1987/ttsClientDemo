package com.pachira.tts.http.client.utils.common;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.DecimalFormat;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;

public class Utils {
	
	/**
	 * 启动播放WAV文件
	 * @param bufferSize	播放缓冲大小
	 * @param sampleRate	采样率
	 * @return
	 */
	public static SourceDataLine getSourceDataLine(int bufferSize,float sampleRate) {
		try {
			int sampleSizeInBits = 16;
			int channels = 1;
			boolean signed = true;
			boolean bigEndian = false;
			AudioFormat af = new AudioFormat(sampleRate, sampleSizeInBits, channels, signed, bigEndian);
			SourceDataLine.Info info = new DataLine.Info(SourceDataLine.class, af, bufferSize);
			SourceDataLine sdl = (SourceDataLine) AudioSystem.getLine(info);
			sdl.open(af);
			sdl.start();
			return sdl;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void sleep(long time) {
		try {
			Thread.sleep(time);
		} catch (Exception e) {
			
		}
	}
	/**
	 * 将数据流写入文件中
	 * @param in	要写入的数据
	 * @param file	存放数据的文件
	 */
	public static void writeFile(InputStream in,File file) {
		byte[] buff = new byte[102400];
		int len = 0;
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(file);
			while((len=in.read(buff)) != -1) {
				fos.write(buff, 0, len);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			SourceUtils.close(fos);
		}
	}
	/**
	 * 删除文件
	 * @param file
	 */
	public static void deleteFile(final File file) {
		if(file == null) {
			return;
		}
		new Thread() {
			public void run() {
				int tryTime = 5;
				while(file.exists() && tryTime-->0) {
					System.gc();
					file.delete();
					Utils.sleep(1000L);
				}
			};
		}.start();
	}
	
	public static int getVersionVal(String version) {
		String[] vs = version.split("\\.");
		DecimalFormat df1 = new DecimalFormat("#");
		DecimalFormat df2 = new DecimalFormat("00");
		version = df1.format(Integer.parseInt(vs[0]))+df2.format(Integer.parseInt(vs[1]))+df2.format(Integer.parseInt(vs[2]));
		return Integer.parseInt(version);
	}
}
