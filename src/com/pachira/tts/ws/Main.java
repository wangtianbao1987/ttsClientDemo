package com.pachira.tts.ws;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.List;

import com.pachira.tts.ws.client.TtsParam;
import com.pachira.tts.ws.client.TtsResponse;
import com.pachira.tts.ws.client.VoiceInterface;
import com.pachira.tts.ws.client.VoiceInterfaceService;
import com.pachira.tts.ws.utils.Param;

public class Main {
	public static void main(String[] args) throws Exception {
		VoiceInterfaceService vis = new VoiceInterfaceService(new URL("http://172.22.144.127:8080/voice/ws/wstts?wsdl"));
		VoiceInterface face = vis.getVoiceInterfacePort();
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
			// TTS请求失败
			System.out.println(res.getErrNo()+":"+res.getErrMsg());
		}
	}
}
