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

public class Main {
	private static VoiceInterface face;
	private static void init() throws Exception {
		VoiceInterfaceService vis = new VoiceInterfaceService(new URL("http://172.22.144.127:8080/voice/ws/wstts?wsdl"));
		face = vis.getVoiceInterfacePort();
	}
	
	public static void main(String[] args) throws Exception {
		System.out.println("start");
		init();
		tts();
		getVersion();
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
		MyResp resp = face.deleteWav(wavName, "putonghua");
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
		MyResp resp = face.uploadWav(datas, wavName, language, isOverwrite);
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
		MyResp resp = face.uploadWavByMybyte(mybytes, wavName, language, isOverwrite);
		System.out.println(toMyRespStr(resp));
	}
	
	/**
	 * 检查人声音频是否存在
	 */
	public static void checkName() {
		MyResp resp = face.checkWavName("123/2222.wav", "putonghua");
		System.out.println(toMyRespStr(resp));
	}
	/**
	 * 获取已上传人声音频列表
	 */
	public static void getWavNames() {
		MyResp resp = face.getWavNames("putonghua");
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
		ttsParam.setText("你好,很高兴见到你。请问你叫什么名字？");
		ttsParam.setFormat(Param.format.wav);
		ttsParam.setVoiceName(Param.voiceName.xiaochang);
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
		return "MyResp [code=" + resp.getCode() + ", data=" + getData(resp) + ", list=" + resp.getList() + ", msg=" + resp.getMsg() + ", sn=" + resp.getSn() + "]";
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
