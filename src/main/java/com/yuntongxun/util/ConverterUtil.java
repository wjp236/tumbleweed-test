/**
 * 
 */
package com.yuntongxun.util;

import org.apache.commons.beanutils.BeanUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.zip.GZIPOutputStream;

/**
 * @author chao
 */
public class ConverterUtil {

	@SuppressWarnings("unchecked")
	public static Object populateResult(Map map, Class clazz) {
		Object obj = null;
		try {
			obj = clazz.newInstance();
			BeanUtils.populate(obj, map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}

	public static byte[] compress(byte[] b) throws IOException {
		ByteArrayOutputStream baos = null;
		try {
			baos = new ByteArrayOutputStream();
			GZIPOutputStream gos = new GZIPOutputStream(baos);
			gos.write(b, 0, b.length);
			gos.finish();
			gos.flush();
			gos.close();
			byte[] data = baos.toByteArray();
			return data;
		} finally {
			if (baos != null) {
				baos.close();
			}
		}
	}
}
