package com.pachira.tts.uploadwav.socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.util.Map;
import java.util.UUID;

public class UploadFile {
	public static String post(FormFile formFile,String path, Map<String, String> params) throws Exception {
		OutputStream outStream = null;
		Socket socket = null;
		BufferedReader reader = null;
		try {
			final String BOUNDARY = "--------------pachirafileupload_"+UUID.randomUUID(); // 数据分隔线
			final String endline = "--" + BOUNDARY + "--\r\n";// 数据结束标志
			int fileDataLength = 0;
			// 得到文件类型数据的总长度
			StringBuilder fileExplain = new StringBuilder();
			fileExplain.append("--");
			fileExplain.append(BOUNDARY);
			fileExplain.append("\r\n");
			fileExplain.append("Content-Disposition: form-data;name=\"" + formFile.getParameterName() + "\";filename=\"" + formFile.getFilname() + "\"\r\n");
			fileExplain.append("Content-Type: " + formFile.getContentType() + "\r\n\r\n");
			fileExplain.append("\r\n");
			fileDataLength += fileExplain.length();
			if (formFile.getInStream() != null) {
				fileDataLength += formFile.getFile().length();
			} else {
				fileDataLength += formFile.getData().length;
			}

			StringBuilder textEntity = new StringBuilder();
			for (Map.Entry<String, String> entry : params.entrySet()) {// 构造文本类型参数的实体数据
				textEntity.append("--");
				textEntity.append(BOUNDARY);
				textEntity.append("\r\n");
				textEntity.append("Content-Disposition: form-data; name=\"" + entry.getKey() + "\"\r\n\r\n");
				textEntity.append(entry.getValue());
				textEntity.append("\r\n");
			}
			// 计算传输给服务器的实体数据总长度
			int dataLength = textEntity.toString().getBytes().length + fileDataLength + endline.getBytes().length;

			URL url = new URL(path);
			int port = url.getPort() == -1 ? 80 : url.getPort();
			socket = new Socket(InetAddress.getByName(url.getHost()), port);
			outStream = socket.getOutputStream();
			// 下面完成HTTP请求头的发送
			String requestmethod = "POST " + url.getPath() + (formFile.getOpertype()==null?"":("?"+formFile.getOpertype())) + " HTTP/1.1\r\n";
			outStream.write(requestmethod.getBytes());
			String accept = "Accept: image/gif, image/jpeg, image/pjpeg, image/pjpeg, application/x-shockwave-flash, application/xaml+xml, application/vnd.ms-xpsdocument, application/x-ms-xbap, application/x-ms-application, application/vnd.ms-excel, application/vnd.ms-powerpoint, application/msword, */*\r\n";
			outStream.write(accept.getBytes());
			String language = "Accept-Language: zh-CN\r\n";
			outStream.write(language.getBytes());
			String contenttype = "Content-Type: multipart/form-data; boundary=" + BOUNDARY + "\r\n";
			outStream.write(contenttype.getBytes());
			String contentlength = "Content-Length: " + dataLength + "\r\n";
			outStream.write(contentlength.getBytes());
			String alive = "Connection: Keep-Alive\r\n";
			outStream.write(alive.getBytes());
			String host = "Host: " + url.getHost() + ":" + port + "\r\n";
			outStream.write(host.getBytes());
			// 写完HTTP请求头后根据HTTP协议再写一个回车换行
			outStream.write("\r\n".getBytes());
			// 把所有文本类型的实体数据发送出来
			outStream.write(textEntity.toString().getBytes());
			// 把所有文件类型的实体数据发送出来

			StringBuilder fileEntity = new StringBuilder();
			fileEntity.append("--");
			fileEntity.append(BOUNDARY);
			fileEntity.append("\r\n");
			fileEntity.append("Content-Disposition: form-data;name=\"" + formFile.getParameterName() + "\";filename=\"" + formFile.getFilname() + "\"\r\n");
			fileEntity.append("Content-Type: " + formFile.getContentType() + "\r\n\r\n");
			outStream.write(fileEntity.toString().getBytes());
			if (formFile.getInStream() != null) {
				byte[] buffer = new byte[1024];
				int len = 0;
				while ((len = formFile.getInStream().read(buffer, 0, 1024)) != -1) {
					outStream.write(buffer, 0, len);
				}
				formFile.getInStream().close();
			} else {
				outStream.write(formFile.getData(), 0, formFile.getData().length);
			}
			outStream.write("\r\n".getBytes());
			// 下面发送数据结束标志，表示数据已经结束
			outStream.write(endline.getBytes());
			outStream.flush();
			
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			StringBuffer sb = new StringBuffer();
			String len = null;
			while((len=reader.readLine()) != null) {
				sb.append("\n"+len);
			}
			if(sb.length() > 0) {
				return sb.substring(1);
			}else {
				return "";
			}
		} catch (Exception e) {
			throw e;
		} finally {
			MyUtils.close(outStream,reader,socket);
		}
	}
}
