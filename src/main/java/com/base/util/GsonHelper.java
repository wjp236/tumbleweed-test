/**
 * CopyRight 2016 必拓电子商务有限公司
 */
package com.base.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.List;
import java.util.Map;

/**
 * Gson帮助类
 * 
 * @author wupeng<wupengg@enn.com>
 */
public class GsonHelper {
    public static Map<String, String> convertMapFromBean(Object bean) {
        Gson gson = new Gson();

        return gson.fromJson(gson.toJson(bean),
                new TypeToken<Map<String, String>>() {
                }.getType());
    }

    public static Map<String, String> convertMapFromJson(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, new TypeToken<Map<String, String>>() {
        }.getType());
    }

    public static List<Map<String, String>> convertToList(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, new TypeToken<List<Map<String, String>>>() {
        }.getType());
    }

    public static String convertMapToJson(Map<String, String> map) {
        Gson gson = new Gson();
        return gson.toJson(map);
    }

    public static String toJsonExcludeBlank(Object bean) {
        Gson gson = new GsonBuilder().serializeNulls().create();
        return gson.toJson(bean);
    }

    public static String toJson(Object obj) {
        return new Gson().toJson(obj);
    }

    public static Object jsonToJavaBean(String body,Class<?> classType) {
        Gson gson = new Gson();
        return gson.fromJson(body, classType);
    }
}
