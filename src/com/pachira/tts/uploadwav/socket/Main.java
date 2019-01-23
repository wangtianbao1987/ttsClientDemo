package com.pachira.tts.uploadwav.socket;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Main {
	public static void main(String[] args) {
		File iconFile = new File("C:\\Users\\WTB\\Desktop\\tts.wav");
		String url = "http://172.22.144.129:88/voice/uploadWav";
		Map<String, String> map = new HashMap<String, String>();// 表单内容
		map.put("language", "putonghua");
		map.put("wavName", "javasend2");
		map.put("checkok", "1");

		if (iconFile != null) {
			FormFile uploadfile = new FormFile(iconFile.getName(), iconFile, "wavFile","audio/x-wav");
			try {
				String res = UploadFile.post(uploadfile,url, map);
				System.out.println(res);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
