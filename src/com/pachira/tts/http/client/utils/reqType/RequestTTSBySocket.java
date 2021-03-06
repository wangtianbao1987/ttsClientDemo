package com.pachira.tts.http.client.utils.reqType;

import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.URI;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

import com.pachira.tts.http.client.ReqParam;
import com.pachira.tts.http.client.utils.ManageData;
import com.pachira.tts.http.client.utils.RequestTTS;
import com.pachira.tts.http.client.utils.common.SourceUtils;
import com.pachira.tts.http.client.utils.common.Utils;
/**
 * 实现使用Socket发送HTTP请求
 */
public class RequestTTSBySocket implements RequestTTS {
	private String method;
	private ManageData manage;
	private int version;
	/**
	 * Socket方式请求TTS服务
	 * @param method	请求方式
	 */
	public RequestTTSBySocket(int version,String method,ManageData manage) {
		this.method = method;
		this.manage = manage;
		this.version = version;
	}
	
	public String paramDec() {
		return ReqParam.param_form;
	}
	
	public String map2Json() {
		return ReqParam.param_json;
	}
	
	/**
	 * 通过Socket请求TTS接口
	 */
	public void connection() {
		OutputStreamWriter osw = null;
		Socket socket = null;
		InputStream in = null;
		try {
			URI uri = new URI(ReqParam.url);
			try {
				if(ReqParam.url.startsWith("http://")) {
					socket = new Socket(uri.getHost(), uri.getPort());
				}else if(ReqParam.url.startsWith("https://")) {
					socket = (SSLSocket)((SSLSocketFactory)SSLSocketFactory.getDefault()).createSocket(uri.getHost(), uri.getPort());
				}else {
					throw new RuntimeException("协议不明："+ReqParam.url);
				}
			}catch(RuntimeException e) {
				e.printStackTrace();
				return;
			} catch (Exception e) {
				System.err.println("连接服务器失败");
				e.printStackTrace();
				return;
			}
			
			osw = new OutputStreamWriter(socket.getOutputStream(),"utf-8");
			String paramStr = null;
			if("GET".equals(method)) {
				paramStr = paramDec();
			}else {
				if(version <= 10200) {
					System.out.println("[注]在1.2.1版本以后, 请求头中可以没有不写 Content-Type: application/x-www-form-urlencoded");
					paramStr = paramDec();
				}else {
					System.out.println("1.2.1版本以后, POST方式传递参数支持json串的形式，body体内容编码为UTF-8");
					paramStr = map2Json();
				}
			}
			System.out.println("==========================HTTP请求报文============================");
			
			StringBuffer sb = new StringBuffer();
			if("POST".equals(method)) {
				sb.append(method+" " + uri.getPath() + " HTTP/1.1\r\n");
			}else {
				sb.append(method+" " + uri.getPath() + "?"+paramStr+" HTTP/1.1\r\n");
			}
			sb.append("Host: " + uri.getHost()+":"+uri.getPort() + "\r\n");
			sb.append("Connection: Keep-Alive\r\n");
			if(version <= 10200) {
				sb.append("Content-Type: application/x-www-form-urlencoded\r\n");
			}
			if("POST".equals(method)) {
				sb.append("Content-Length: "+paramStr.getBytes("UTF-8").length+"\r\n");
			}
			sb.append("\r\n");
			osw.write(sb.toString());
			System.out.print(sb.toString());
			osw.flush();
			if("POST".equals(method)) {
				osw.write(paramStr);
				System.out.print(paramStr);
			}
			osw.flush();
			System.out.println("\n\n==========================HTTP响应报文============================");
			in = socket.getInputStream();
			String len = readLine(in);
			if(!len.contains("200")) {
				manage.reqErr("请求失败："+len);
				return;
			}
			System.out.println(len);
			String contentType = "";
			int contentLength = 0;
			while(!"".equals(len=readLine(in))) {
				if(len.startsWith("Content-Type")) {
					contentType = len;
				}else if(len.startsWith("Content-Length")) {
					contentLength = Integer.parseInt(len.split(":")[1].trim());
				}
				System.out.println(len);
			}
			System.out.println();
			// 头部解析完成，接下在开始解析chunked部分
			if(contentType.contains("audio")) {
				// 正常音频数据
				manage.preManage();
				String errJson = "";
				while(!"0".equals(len = readLine(in))){
					if("".equals(len)) {
						continue;
					}
					System.out.println(len);
					int dataLen = Integer.parseInt(len, 16);
					byte[] buff = new byte[dataLen];
					int pos = 0;
					int readLen = 0;
					while(pos < dataLen && -1 != (readLen=in.read(buff, pos, dataLen-pos))) {
						pos += readLen;
					}
					if(buff[0] == '{' && buff[dataLen-1] == '}') {
						errJson = new String(buff,"UTF-8");
						System.out.println(errJson);
					}else {
						System.out.printf("<[[长度为%d的二进制数据]]>\n",dataLen);
						manage.manage(buff, dataLen);
					}
				}
				System.out.println(len);
				if(!"".equals(errJson)) {
					manage.reqErr(errJson);
				}
				// 流读取结束
				manage.endManage();
			}else {
				// 服务器返回错误信息
				byte[] buff = new byte[contentLength];
				int pos = 0;
				int readLen = 0;
				while(pos < contentLength && -1 != (readLen=in.read(buff, pos, contentLength-pos))) {
					pos += readLen;
				}
				String errStr = new String(buff,"UTF-8").trim();
				System.out.println(errStr);
				manage.reqErr(errStr);
			}
		} catch (Exception ee) {
			ee.printStackTrace();
		} finally {
			// 等一秒钟等待音频播放完
			Utils.sleep(1000);
			SourceUtils.close(in,osw,socket);
		}
	}
	
	public String readLine(InputStream in) throws Exception {
		StringBuffer sb = new StringBuffer("");
		byte b = '0';
		while(true) {
			if((b=(byte)in.read()) == '\r' && in.read()=='\n') {
				break;
			}
			sb.append(new String(new byte[] {b}));
		}
		return sb.toString().trim();
	}
}
