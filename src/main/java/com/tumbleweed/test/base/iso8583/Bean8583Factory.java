package com.tumbleweed.test.base.iso8583;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.*;


public class Bean8583Factory {

    private static Map<String,Properties> map = new HashMap<String, Properties>();

    private static Bean8583Factory instance = null;

    private static Log log = LogFactory.getLog(Bean8583Factory.class);

    public static Bean8583Factory getInstance(){
        if(null == instance){
            map.clear();
            instance = new Bean8583Factory();
        }
        return instance;
    }

    private Bean8583Factory(){
        init();
    }

    public void init() {
        System.out.println("加载8583配置开始");

        File f = new File("src/main/com/com.tumbleweel.hui10.base/iso8583/ISO8583medata.xml");
        if ((f.exists()) && (f.isFile())) {
            SAXReader reader = new SAXReader();
            try {
                Iterator<Node> iterator2;
                Document doc = reader.read(f);
                List obj = doc.getRootElement().elements();
                if (obj == null) {
                    return;
                }
                Iterator<Node> iterator = obj.iterator();
                while (iterator.hasNext()) {
                    Node imetadata = iterator.next();
                    Properties pop = new Properties();

                    Node isHeader = imetadata.selectSingleNode("@isHeader");
                    if(null != isHeader){
                        pop.setProperty("isHeader", isHeader.getText());
                    }

                    Node isBCD = imetadata.selectSingleNode("@isBCD");
                    if(null != isBCD){
                        pop.setProperty("isBCD", isBCD.getText());
                    }

                    Node type = imetadata.selectSingleNode("@type");
                    if(null != type){
                        pop.setProperty("type", type.getText());
                    }

                    Node length = imetadata.selectSingleNode("@length");
                    if(null != length){
                        pop.setProperty("length", length.getText());
                    }

                    Node variable_flag = imetadata.selectSingleNode("@variable_flag");
                    if(null != variable_flag){
                        pop.setProperty("variable_flag", variable_flag.getText());
                    }

                    Node field_index = imetadata.selectSingleNode("@field_index");
                    if(null != field_index){
                        pop.setProperty("field_index", field_index.getText());
                    }

                    Node encoding = imetadata.selectSingleNode("@encoding");
                    if(null != encoding){
                        pop.setProperty("encoding", encoding.getText());
                    }

                    pop.setProperty("name", imetadata.getName());
                    map.put(imetadata.getName(),pop);

                }
            } catch (DocumentException e) {
                e.printStackTrace();
                System.out.println("加载8583配置异常");
            }
        }
        System.out.println("加载8583配置完成");
    }

    public Map<String, Properties> getMap() {
        return map;
    }

    public Properties getFieldPropertie(String fieldName) {
        return map.get(fieldName);
    }

    public String getFieldPropertieVal(String fieldName,String propertieName) {
        return map.get(fieldName).getProperty(propertieName);
    }

}