package com.pachira.tts.ws.utils;

public final class Param {
	/**
	 * 文件格式
	 */
	public final static class format{
		public static final String pcm = "pcm";
		public static final String wav = "wav";
	}
	/**
	 * 语言包
	 */
	public final static class language{
		/**
		 * 普通话
		 */
		public static final String zhCmn = "zh-cmn";
	}
	/**
	 * 采样率
	 */
	public final static class sampleRate{
		/**
		 * 采样率：8K
		 */
		public static final int _8K = 8000;
		/**
		 * 采样率：16K
		 */
		public static final int _16K = 16000;
	}
	/**
	 * SSML标记语言
	 */
	public final static class tagMode{
		/**
		 * 不使用SSML标记语言
		 */
		public static final int none = 0;
		/**
		 * 使用SSML标记语言
		 */
		public static final int ssml = 1;
	}
	/**
	 * 英文读法
	 */
	public final static class engMode{
		/**
		 * 自动判断
		 */
		public static final int auto = 0;
		/**
		 * 字母读法
		 */
		public static final int letter = 1;
	}
	/**
	 * 发音人
	 */
	public final static class voiceName{
		/**
		 * 小畅 · 女声 · 中英混合
		 */
		public static final String xiaochang = "xiaochang";
		/**
		 * 小红 · 女声 · 中英混合
		 */
		public static final String xiaohong = "xiaohong";
		/**
		 * 小艳 · 女声 · 中英混合
		 */
		public static final String xiaoyan = "xiaoyan";
		/**
		 * 小梅 · 女声 · 中英混合
		 */
		public static final String xiaomei = "xiaomei";
		/**
		 * Mary · 女声 · 英文
		 */
		public static final String Mary = "Mary";
		/**
		 * 小伟 · 男声 · 中英混合
		 */
		public static final String xiaowei = "xiaowei";
		/**
		 * 小强 · 男声 · 中英混合
		 */
		public static final String xiaoqiang = "xiaoqiang";
		/**
		 * 小兵 · 男声 · 中英混合
		 */
		public static final String xiaobing = "xiaobing";
		/**
		 * Mark · 男声 · 英文
		 */
		public static final String Mark = "Mark";
		/**
		 * 宝宝 · 女童声 · 中英混合
		 */
		public static final String baobao = "baobao";
		/**
		 * 贝贝 · 男童声 · 中英混合
		 */
		public static final String beibei = "beibei";
	}
	
}
