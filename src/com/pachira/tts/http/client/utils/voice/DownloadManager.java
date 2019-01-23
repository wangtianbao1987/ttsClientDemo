package com.pachira.tts.http.client.utils.voice;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.pachira.tts.http.client.ReqParam;
import com.pachira.tts.http.client.utils.ManageData;
import com.pachira.tts.http.client.utils.common.SourceUtils;
/**
 * 请求的处理方式(下载:download)
 */
public class DownloadManager implements ManageData {
	private File file = new File(ReqParam.downloadDir,new SimpleDateFormat("yyyy-MM-dd HH_mm_ss_SSS").format(new Date())+"."+ReqParam.format);
	private FileOutputStream fos = null;
	
	@Override
	public void preManage() {
		try {
			fos = new FileOutputStream(file);
		}catch (Exception e) {
			throw new RuntimeException("创建文件输出流失败");
		}
	}
	@Override
	public void manage(byte[] buff, int buffSize) throws Exception {
		fos.write(buff, 0, buffSize);
	}
	@Override
	public void endManage() {
		SourceUtils.close(fos);
		System.out.println("文件下载结束:"+file.getAbsolutePath());
	}
	@Override
	public void reqErr(String error) {
		System.err.println("\n\n[ERROR]："+error);
	}
	
}
