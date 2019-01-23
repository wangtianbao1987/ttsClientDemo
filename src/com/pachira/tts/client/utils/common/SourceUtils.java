package com.pachira.tts.client.utils.common;

import java.io.Closeable;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;

/**
 * 数据流操作类封装
 * @author	王添宝
 * @date	2018年11月6日
 */
public class SourceUtils {
	public static void runMethod(String methodName,Object... objs){
		if(objs == null){
			return;
		}
		for(Object obj:objs){
			if(obj == null){
				return;
			}
			try {
				Method m = obj.getClass().getMethod(methodName);
				m.invoke(obj);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void write(InputStream in,OutputStream out){
		try {
			byte[] buff = new byte[1024*5];
			int len = 0;
			while((len=in.read(buff)) != -1){
				try {
					out.write(buff,0,len);
					out.flush();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void close(Object... objs){
		if(objs == null){
			return;
		}
		for(Object obj:objs){
			if(obj == null){
				return;
			}
			try {
				if(obj instanceof Closeable){
					((Closeable) obj).close();
				}else if(obj instanceof AutoCloseable){
					((AutoCloseable) obj).close();
				}else {
					runMethod("close", obj);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void flush(Object... objs){
		if(objs == null){
			return;
		}
		for(Object obj:objs){
			if(obj == null){
				return;
			}
			try {
				if(obj instanceof OutputStream){
					((OutputStream) obj).flush();
				}else{
					runMethod("flush", obj);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}
