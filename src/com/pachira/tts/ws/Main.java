package com.pachira.tts.ws;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pachira.tts.ws.client.MyByte;
import com.pachira.tts.ws.client.MyResp;
import com.pachira.tts.ws.client.MyResp.Data;
import com.pachira.tts.ws.client.MyResp.Data.Entry;
import com.pachira.tts.ws.client.TtsParam;
import com.pachira.tts.ws.client.TtsResponse;
import com.pachira.tts.ws.client.VoiceInterface;
import com.pachira.tts.ws.client.VoiceInterfaceService;
import com.pachira.tts.ws.utils.Param;

/**
 * WebService 客户端调用实例
 * @author	王添宝
 * @date	2019年1月23日
 * 
 * 注：客户端生成命令：
 * wsimport -encoding UTF-8 -s F:\git\ttsClientDemo\src -p com.pachira.tts.ws.client -keep http://172.22.144.127:8080/voice/ws/wstts?wsdl
 */
public class Main {
	private static VoiceInterface face;
	private static void init() throws Exception {
		VoiceInterfaceService vis = new VoiceInterfaceService(new URL("http://172.22.144.127:8080/voice/ws/wstts?wsdl"));
		face = vis.getVoiceInterfacePort();
	}
	
	public static void main(String[] args) throws Exception {
		System.out.println("start");
		init();
		getVersion();
		tts();
		getWavNames();
		checkName();
		uploadWav();
		getWavNames();
		deleteWav("webservice测试.wav");
		getWavNames();
		uploadWavByMybyte();
		getWavNames();
		deleteWav("aaa/bbb/ccc/webservice测试.wav");
		getWavNames();
		System.out.println("end");
	}
	
	/**
	 * 删除人声音频
	 */
	public static void deleteWav(String wavName) {
		MyResp resp = face.deleteWav(wavName, "putonghua"); // <=> face.deleteWav(wavName, null);
		System.out.println(toMyRespStr(resp));
	}
	
	/**
	 * 通过byte[]上传人声音频
	 */
	public static void uploadWav() throws Exception {
		List<byte[]> datas = new ArrayList<byte[]>();
		FileInputStream fis = new FileInputStream(new File("C:\\Users\\Administrator\\Desktop\\test.wav"));
		byte[] buff = new byte[1024];
		int len = 0;
		while((len=fis.read(buff)) != -1) {
			byte[] bb = new byte[len];
			System.arraycopy(buff, 0, bb, 0, len);
			datas.add(bb);
		}
		fis.close();
		String wavName = "webservice测试.wav";;
		String language = "putonghua";
		boolean isOverwrite = true;
		MyResp resp = face.uploadWav(datas, wavName, language, isOverwrite); // <=> face.uploadWav(datas, wavName, null, isOverwrite);
		System.out.println(toMyRespStr(resp));
	}
	
	/**
	 * 通过MyByte对象上传人声音频
	 */
	public static void uploadWavByMybyte() throws Exception {
		String wavName = "aaa/bbb/ccc/webservice测试.wav";
		String language = "putonghua";
		boolean isOverwrite = true;
		List<MyByte> mybytes = new ArrayList<MyByte>();
		FileInputStream fis = new FileInputStream(new File("C:\\Users\\Administrator\\Desktop\\test.wav"));
		
		byte[] buff = new byte[1024];
		int len = 0;
		while((len = fis.read(buff)) != -1) {
			MyByte mybyte = new MyByte();
			mybyte.setBuff(buff);
			mybyte.setStart(0);
			mybyte.setEnd(len);
			mybytes.add(mybyte);
		}
		fis.close();
		MyResp resp = face.uploadWavByMybyte(mybytes, wavName, language, isOverwrite); // <=> face.uploadWavByMybyte(mybytes, wavName, null, isOverwrite);
		System.out.println(toMyRespStr(resp));
	}
	
	/**
	 * 检查人声音频是否存在
	 */
	public static void checkName() {
		MyResp resp = face.checkWavName("123/2222.wav", "putonghua"); // <=> face.checkWavName("123/2222.wav", null);
		System.out.println(toMyRespStr(resp));
	}
	/**
	 * 获取已上传人声音频列表
	 */
	public static void getWavNames() {
		MyResp resp = face.getWavNames("putonghua"); // <=> face.getWavNames(null);
		System.out.println(toMyRespStr(resp));
	}
	/**
	 * 获取当前系统版本号
	 */
	public static void getVersion() {
		MyResp resp = face.getVersion();
		System.out.println(toMyRespStr(resp));
	}
	/**
	 * 语音合成
	 */
	public static void tts() throws Exception {
		TtsParam ttsParam = new TtsParam();
		// TTS文本
		ttsParam.setText("你好,很高兴见到你。请问你叫什么名字？");
		// 音量（默认：1，取值范围0.5-2）
		ttsParam.setVolume(1D);
		// 语速（默认：1，取值范围0.5-2）
		ttsParam.setSpeed(1D);
		// 音调（默认：1，取值范围0.5-2）
		ttsParam.setPitch(1D);
		// 发音人（默认：xiaochang）
		ttsParam.setVoiceName(Param.voiceName.xiaochang);
		// 音频格式（默认：pcm）
		ttsParam.setFormat(Param.format.wav);
		// 采样率（默认：16000）
		ttsParam.setSampleRate(Param.sampleRate._16K);
		// 使用使用SSML标记（默认：使用）
		ttsParam.setTagMode(Param.tagMode.ssml);
		// 英文读法（默认：自动判断）
		ttsParam.setEngMode(Param.engMode.auto);
		// 语言标志（默认：普通话）
		ttsParam.setLanguage(Param.language.zhCmn);
		TtsResponse res = face.synthesizeText(ttsParam);
		if("0000".equals(res.getErrNo())) {
			// TTS请求成功
			List<byte[]> datas = res.getDatas();
			File file = new File("C:\\Users\\Administrator\\Desktop","test."+(ttsParam.getFormat()==null?"pcm":ttsParam.getFormat()));
			FileOutputStream fos = null;
			try {
				fos = new FileOutputStream(file);
				for(byte[] buff: datas) {
					fos.write(buff,0,buff.length);
				}
				System.out.println("音频保存文件成功："+file.getAbsolutePath());
			} catch (Exception e) {
				throw e;
			} finally {
				if(fos != null) {try{fos.close();}catch(Exception e) {}}
			}
		}else {
			System.out.println(res.getErrNo()+":"+res.getErrMsg());
		}
	}
	
	
	/*************************************************************/
	public static String toMyRespStr(MyResp resp) {
		return ("0000".equals(resp.getCode())?"请求成功：":"请求失败：")+" {code=" + resp.getCode() + ", data=" + getData(resp) + ", list=" + resp.getList() + ", msg=" + resp.getMsg() + ", sn=" + resp.getSn() + "}";
	}
	public static Map<String,Object> getData(MyResp resp){
		Map<String,Object> map = new HashMap<String,Object>();
		Data data = resp.getData();
		if(data == null) {
			return map;
		}
		List<Entry> list = data.getEntry();
		if(list==null || list.isEmpty()) {
			return map;
		}
		for(Entry entry:list) {
			map.put(entry.getKey(), entry.getValue());
		}
		return map;
	}
}
