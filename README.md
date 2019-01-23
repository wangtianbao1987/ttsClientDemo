![image](https://raw.githubusercontent.com/wangtianbao1987/ttsClientDemo/master/pachira.png)
# 普强TTS接口调用演示实例说明
## HTTP
### 运行入口
* com.pachira.tts.http.client.Main
### 参数配置类
* com.pachira.tts.http.client.ReqParam
### 使用说明

系统为客户端调用普强TTS服务接口演示实例。

* com.pachira.tts.http.client.Main

提供如下信息的配置：

    请求方式：GET/POST
    播放/下载：play/download
    请求HTTP时的编程方式：socket/httpClient工具类
    
程序如下：

```

// HTTP请求方式：POST/GET
String method = "POST";
// 测试使用。 play:播放；    download:下载
String playOrDownload = "play";
//测试使用。httpClient:使用httpClient框架的方式调用TTS接口； socket:使用socket拼报文的形式调用TTS接口。 
//若选择socket方式，程序将打印HTTP报文
// 1.2.1版本后，使用socket可以将合成过程中出现的错误信息打印出来；使用httpClient暂不支持打印错误信息。
String demoType = "socket";

```
* com.pachira.tts.http.client.ReqParam

提供如下信息的配置，请自行进入文件中查看：

    请求HTTP的路径【url】、下载/临时文件存放位置【downloadDir】、传递参数

* 具体实现过程请自行debug运行查看

### 请求报文说明：
#### 在1.2.0版本之前支持的报文如下：
* GET提交
```
GET /voice/tts?text=Hello%2C%E4%BD%A0%E5%A5%BD&volume=1&speed=1&pitch=1&sample_rate=16000&tag_mode=1&eng_mode=0&format=pcm&voice_name=xiaochang&language=zh-cmn HTTP/1.1
Host: 172.22.144.138:88
Connection: Keep-Alive
Content-Type: application/x-www-form-urlencoded
```
* POST提交
```
POST /voice/tts HTTP/1.1
Host: 172.22.144.138:88
Connection: Keep-Alive
Content-Type: application/x-www-form-urlencoded
Content-Length: 144

text=Hello%2C%E4%BD%A0%E5%A5%BD&volume=1&speed=1&pitch=1&sample_rate=16000&tag_mode=1&eng_mode=0&format=pcm&voice_name=xiaochang&language=zh-cmn
```
#### 在1.2.1版本之后POST提交增加对JSON字符串的支持，同时请求头可以去掉Content-Type属性：
* GET提交
```
GET /voice/tts?text=Hello%2C%E4%BD%A0%E5%A5%BD&volume=1&speed=1&pitch=1&sample_rate=16000&tag_mode=1&eng_mode=0&format=pcm&voice_name=xiaochang&language=zh-cmn HTTP/1.1
Host: 172.22.144.138:88
Connection: Keep-Alive
```
* POST提交
```
POST /voice/tts HTTP/1.1
Host: 172.22.144.138:88
Connection: Keep-Alive
Content-Length: 144

text=Hello%2C%E4%BD%A0%E5%A5%BD&volume=1&speed=1&pitch=1&sample_rate=16000&tag_mode=1&eng_mode=0&format=pcm&voice_name=xiaochang&language=zh-cmn
```
或者
```
POST /voice/tts HTTP/1.1
Host: 172.22.144.138:88
Connection: Keep-Alive
Content-Length: 172

{"text":"Hello,你好","volume":"1","speed":"1","pitch":"1","sample_rate":"16000","tag_mode":"1","eng_mode":"0","format":"pcm","voice_name":"xiaochang","language":"zh-cmn"}
```

### 响应报文说明：
#### 正常的响应报文如下：
```
HTTP/1.1 200
Content-Disposition: attachment;fileName=tts.pcm
Content-Type: audio/pcm;charset=UTF-8
Transfer-Encoding: chunked
Date: Wed, 28 Nov 2018 13:33:55 GMT

2000
<[[长度为8192的二进制数据]]>
2000
<[[长度为8192的二进制数据]]>
2000
<[[长度为8192的二进制数据]]>
1420
<[[长度为5152的二进制数据]]>
2000
<[[长度为8192的二进制数据]]>
680
<[[长度为1664的二进制数据]]>
0
```

#### 系统校验不通过时的响应报文如下：
* 1.2.0版本之前
```
HTTP/1.1 200
Content-Type: text/html;charset=UTF-8
Content-Length: 72
Date: Wed, 28 Nov 2018 13:38:58 GMT

{"err_msg":"语速:参数[speed]取值范围为0.5~2.0","err_no":"7401","sn":"708932ff-aec5-4598-86ee-c0c892f79f74"}
```

* 1.2.1版本以后，Content-Type修改为application/json;charset=UTF-8
```
HTTP/1.1 200
Content-Type: application/json;charset=UTF-8
Content-Length: 72
Date: Wed, 28 Nov 2018 13:38:58 GMT

{"err_msg":"语速:参数[speed]取值范围为0.5~2.0","err_no":"7401","sn":"283e92a8-87d5-4897-9c46-1b2884ec7012"}
```
另：
* 1.2.1版本之后，请求pcm语音时，增加TTS在运行过程中的错误返回，报错信息在最后一个buff中。对应的响应报文如下：
```
HTTP/1.1 200
Content-Disposition: attachment;fileName=tts.pcm
Content-Type: audio/pcm;charset=UTF-8
Transfer-Encoding: chunked
Date: Wed, 28 Nov 2018 13:33:55 GMT

2000
<[[长度为8192的二进制数据]]>
2000
<[[长度为8192的二进制数据]]>
2000
<[[长度为8192的二进制数据]]>
1420
<[[长度为5152的二进制数据]]>
2000
<[[长度为8192的二进制数据]]>
680
<[[长度为1664的二进制数据]]>
40
{"err_msg":"TTS合成语音过大;退出合成","err_no":"7412","sn":"6dab995e-0c25-4546-ae5a-876e64fa9f2c"}
0
```


## WebService
### 运行入口
* com.pachira.tts.ws.Main

### 客户端代码生成过程

#### 客户端代码生成：
```
wsimport -encoding UTF-8 -s F:\git\ttsClientDemo\src -p com.pachira.tts.ws.client -keep http://172.22.144.127:8080/voice/ws/wstts?wsdl
```
* 说明：
```
在cmd命令窗口中执行以上命令会根据 http://172.22.144.127:8080/voice/ws/wstts?wsdl 在F:\git\ttsClientDemo\src目录中生成webservice客户端源码，源码所在包名为：com.pachira.tts.ws.client。

-encoding : 指定webservice客户端源码文件编码
-s : 指定源码存放位置
-p : 指定源码包结构
-keep : 指定wsdl路径
```
#### 参数取值指定类：
* com.pachira.tts.ws.utils.Param
```
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
```
#### WebService客户端Demo：
* com.pachira.tts.ws.Main
```
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

```


