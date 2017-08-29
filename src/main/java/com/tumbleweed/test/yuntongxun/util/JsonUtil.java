package com.tumbleweed.test.yuntongxun.util;

import net.sf.json.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class JsonUtil {
	public static final Logger log = LogManager.getLogger(JsonUtil.class.getName());
	
	private JSONObject jsonObject;
	
	public JsonUtil(String jsonString){
		try{
		jsonObject = JSONObject.fromObject(jsonString);
		}catch(Exception e){
			log.error("String transform JsonObject is fail", e);
		}
	}
	
	/**
	 * 返回jsonObject 的字符串形式
	 * @return
	 */
	public String getJsontoString() {
		return jsonObject.toString();
	}
	/**
	 * 删除value为null or "" 的 key键
	 * @param keys
	 * @return
	 */
	public void removekey_NullValue(String[] keys) {
		for(String key:keys){
			if (jsonObject.containsKey(key)) {
				String value = jsonObject.getString(key);
				if (value == null || "".equals(value)) {
					log.info("remove the key :"+key);
					jsonObject.remove(key);
				}
			}
		}
	}
	
	/**
	 * 更改json的key
	 * @param srcKey
	 * @param destKey
	 * @return
	 */
	public void replaceKey(String[] srcKey,String[] destKey){
		if(srcKey==null || srcKey.length==0){
			log.info("the srcKey is null or length==0");
		}
		if(destKey==null || destKey.length==0){
			log.info("the destKey is null or length==0");
		}
		if(srcKey.length!=destKey.length){
			log.info("the srcKey length "+srcKey.length+" and the destKey length "+destKey.length);
		}
		for(int i=0;i<srcKey.length;i++){
			if (jsonObject.containsKey(srcKey[i])) {
				String srcValue = jsonObject.getString(srcKey[i]);
				jsonObject.remove(srcKey[i]);
				jsonObject.put(destKey[i],srcValue);
				log.info("replace the key "+srcKey[i]+" with "+destKey[i]);
			}
		}
	}
	
	/**
	 * 获取key 的value值
	 * @return
	 */
	public String getValueBykey(String key){
		if (jsonObject.containsKey(key)) {
			String srcValue = jsonObject.getString(key);
			return srcValue;
		}
		log.info("the json not contain the key:"+key+",return null");
		return null;
	}
	/**
	 * 删除key,并且返回value
	 * @param key
	 */
	public String removeKey(String key){
		if (jsonObject.containsKey(key)) {
			String srcValue = jsonObject.getString(key);
			jsonObject.remove(key);
			log.info("the json remove key:"+key);
			return srcValue;
		}
		return null;
	}
	
	/**
	 * 值不为null返回true
	 * @return
	 */
	public boolean isNotNull(String key){
		if (jsonObject.containsKey(key) && jsonObject.getString(key) != null && !"".equals(jsonObject.getString(key))) {
			log.info("jsonObject contains "+key);
			log.info("key:"+key+",value:"+jsonObject.getString(key));
			return true;
		}else{
			log.info("jsonObject not contains "+key+" or value is null");
			return false;
		}
	}
	
	/**
	 * 如果key存在,则设置 key的value
	 * @param key
	 */
	public void setValue(String key,String value){
		if (jsonObject.containsKey(key)){
			log.info("jsonObject contains "+key);
			log.info("set key:"+key+",value:"+value);
			jsonObject.put(key, value);
		}else{
			log.info("jsonObject not contains "+key);
		}
	}
	/**
	 * 如果key不能再，则设置缺省值
	 * @param key
	 * @param value
	 */
	public void setKeyAndValue(String key,String value){
		if (!jsonObject.containsKey(key) 
				|| jsonObject.getString(key) ==null
				|| "".equals(jsonObject.getString(key))){
			log.info("set key:"+key+",value:"+value);
			jsonObject.put(key, value);
		}
	}
	

}
