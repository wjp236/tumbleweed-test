package com.yuntongxun.util;

import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.List;

public  class XmlUtil {
	private static final Logger log = LogManager.getLogger(XmlUtil.class.getName());
	private Document doc;

	public XmlUtil(String xml) {
		try {
			this.doc = DocumentHelper.parseText(xml);
		} catch (DocumentException e) {
			log.error(e);
			throw new RuntimeException("112613");
		}
	}

	public XmlUtil(InputStream xml) {
		try {
			SAXReader reader = new SAXReader();
			this.doc = reader.read(xml);
		} catch (DocumentException e) {
			log.error(e);
			throw new RuntimeException("112613");
		}
	}

	@SuppressWarnings("unchecked")
	private Element findElement(List<Element> elements, String text) {
		for (Element element : elements) {
			log.debug("Get Element: " + element.getName());
			if (element.elements().size() > 0) {
				Element result = findElement(element.elements(), text);
				if (result != null) {
					return result;
				}
				log.debug("continue find next node.");
			} else {
				String elementText = element.getName();
				if (elementText.equalsIgnoreCase(text)) {
					log.debug("found the samed node [" + text + "].");
					return element;
				}
				log.debug("continue find node [" + text + "].");
			}
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	public Element getChildElement(Element root, String visitedNodeName) {
		try {
			log.debug("RootElement: " + root.getName());
			Element element = findElement(root.elements(), visitedNodeName);
			if (element != null) {
				return element;
			} else {
				return null;
			}
		} catch (Exception e) {
			log.error(e);
			log.warn("Can't find Element: " + visitedNodeName);
			return null;
		}
	}

	public Element getChildElement(String visitedNodeName) {
		return getChildElement(this.doc.getRootElement(), visitedNodeName);
	}

	@SuppressWarnings("unchecked")
	public List<Element> getChildElementList(Element root) {
		return root.elements();
	}

	public List<Element> getChildElementList() {
		return getChildElementList(this.doc.getRootElement());
	}

	public String toString() {

		return this.doc.asXML();
	}

	public void setKeyValue(String key, String value) {
		Element root = this.doc.getRootElement();
		root.addElement(key).addText(value);
	}

	public static String json2XML(String json, String String) {
		log.info("json2Xml..");
		JSONObject jobj = JSONObject.fromObject(json);
		log.info("json to obj..");
		XMLSerializer xmlserializer = new XMLSerializer();
		xmlserializer.setRootName(String);
		log.info("set root..");
		String xml = xmlserializer.write(jobj);
		log.info("json to xml is :");
		log.info(xml);
		return xml;
	}
	
	/**
	 * 删除value为空的key
	 * @param keys
	 */
	public void removekey_NullValue(String[] keys){
		for(String key : keys){
			Element e = this.getChildElement(key);
			if (e != null && "".equals(e.getTextTrim())) {
				log.info("remove the key "+key);
				doc.getRootElement().remove(e);
			}
		}
	}
	
	public void replaceKey(String[] srcKey,String[] destKey){
		if(srcKey==null || srcKey.length==0){
			log.info("the srcKey is null or length==0,return null");
		}
		if(destKey==null || destKey.length==0){
			log.info("the destKey is null or length==0,return null");
		}
		if(srcKey.length!=destKey.length){
			log.info("the srcKey length "+srcKey.length+" and the destKey length "+destKey.length+",return null");
		}
		for(int i=0;i<srcKey.length;i++){
			Element e = this.getChildElement(srcKey[i]);
			if (e != null ) {
				String value=e.getTextTrim();
				log.info("replace "+srcKey[i] +" with "+destKey[i]+" and text is "+value);
				Element root = doc.getRootElement();
				root.remove(e);
				root.addElement(destKey[i]).addText(value);
			}
		}
	}
	
	/**
	 * 如果key不存在或者为null，则为其设值
	 * @param key
	 * @param value
	 */
	public void setkey(String key,String value){
		Element e = this.getChildElement(key);
		if(e==null){
			this.doc.getRootElement().addElement(key).addText(value);
			log.info("add key: "+key+" ,value:"+value);
			return;
		}
		if("".equals(e.getTextTrim())){
			e.addText(value);
			return;
		}
	}
	
	/**
	 * 删除key,并且返回value
	 * @param key
	 * @return
	 */
	public String removeKey(String key){
		Element e = this.getChildElement(key);
		String value=null;
		if(e!=null && !"".equals(e.getTextTrim())){
			value=e.getTextTrim();
			this.doc.getRootElement().remove(e);
		}
		log.info("remove key:"+key+",value:"+value);
		return value;
	}
	
	
	public Element getRootElement(){
		Element root = this.doc.getRootElement();
		return root;
	}
	

}