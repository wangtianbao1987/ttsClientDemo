package com.pachira.tts.client.utils.voice;

import javax.sound.sampled.SourceDataLine;

import com.pachira.tts.client.ReqParam;
import com.pachira.tts.client.utils.ManageData;
import com.pachira.tts.client.utils.common.SourceUtils;
import com.pachira.tts.client.utils.common.Utils;

/**
 * 请求的处理方式(播放pcm)
 */
public class PcmManager implements ManageData {
	private SourceDataLine line;
	private Byte bb;
	@Override
	public void preManage() {
		String sample_rate = ReqParam.param.get("sample_rate");
		if(sample_rate==null || "".equals(sample_rate)) {
			sample_rate = "16000";
		}
		line = Utils.getSourceDataLine(512, Integer.parseInt(sample_rate));
	}
	@Override
	public void manage(byte[] buff, int buffSize) throws Exception {
		if(bb != null) {
			byte[] buff2 = new byte[buff.length + 1];
			buff2[0] = bb;
			System.arraycopy(buff, 0, buff2, 1, buffSize);
			buffSize ++;
			buff = buff2;
		}
		if(buffSize%2==0) {
			line.write(buff, 0, buffSize);
			bb = null;
		}else {
			line.write(buff, 0, buffSize-1);
			bb = buff[buffSize-1];
		}
	}
	@Override
	public void endManage() {
		// 等500毫秒等待音频播放完
		Utils.sleep(500);
		SourceUtils.close(line);
	}
	@Override
	public void reqErr(String error) {
		System.err.println("\n\n[ERROR]："+error);
	}
}
