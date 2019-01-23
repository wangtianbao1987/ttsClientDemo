package com.pachira.tts.uploadwav.socket;

import java.io.Closeable;
import java.io.OutputStream;
import java.lang.reflect.Method;

public class MyUtils {
	
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
				}else{
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
