package com.pachira.tts.client.utils;

import java.util.HashMap;
import java.util.Map;

public abstract class RequestTTS {
	private Map<String,Object> map = new HashMap<String,Object>();
	/**
	 * 存放/获取操作过程中需要用的数据
	 * @return
	 */
	public Map<String,Object> getData(){
		return map;
	}
	/**
	 * 获取响应数据前执行的回调
	 */
	public abstract void preManage();
	/**
	 * 处理数据的回调
	 * @param buff		缓冲数据
	 * @param buffSize	缓冲数据长度
	 * @throws Exception
	 */
	public abstract void manage(byte[] buff,int buffSize) throws Exception;
	/**
	 * 正常获取数据后执行的回调
	 */
	public abstract void endManage();
	/**
	 * 请求过程出错的回调
	 * @param error
	 */
	public abstract void reqErr(String error);
	/**
	 * 请求TTS接口
	 */
	public abstract void connection();
}
