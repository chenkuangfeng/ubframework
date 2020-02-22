package com.ubsoft.framework.core.util;

import java.io.*;

public class JedisUtil {

	public static byte[] serialize(Object object) throws IOException {
		ObjectOutputStream oos = null;
		ByteArrayOutputStream baos = null;
		try {
			// 序列化
			baos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(baos);
			oos.writeObject(object);
			byte[] bytes = baos.toByteArray();

			return bytes;
		} catch (Exception e) {

		} finally {
			baos.close();
			oos.close();
		}
		return null;
	}

	public static <T> T unserialize(byte[] bytes) throws IOException {
		ByteArrayInputStream bais = null;
		ObjectInputStream ois=null;
		try {
			// 反序列化
			bais = new ByteArrayInputStream(bytes);
			ois = new ObjectInputStream(bais);
			return (T) ois.readObject();
		} catch (Exception e) {

		}finally{
			bais.close();
			ois.close();
		}
		return null;
	}

}
