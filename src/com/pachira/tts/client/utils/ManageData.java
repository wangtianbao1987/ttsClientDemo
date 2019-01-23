package com.pachira.tts.client.utils;

public interface ManageData {
	/**
	 * 获取响应数据前执行的回调
	 */
	public void preManage();
	/**
	 * 处理数据的回调
	 * @param buff		缓冲数据
	 * @param buffSize	缓冲数据长度
	 * @throws Exception
	 */
	public void manage(byte[] buff,int buffSize) throws Exception;
	/**
	 * 正常获取数据后执行的回调
	 */
	public void endManage();
	/**
	 * 请求过程出错的回调
	 * @param error
	 */
	public void reqErr(String error);
}
