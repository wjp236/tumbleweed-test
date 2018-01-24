package com.tumbleweed.test.base.hashmap.consistent;
import java.util.HashMap;
import java.util.Map;
/**
 * Author :极客慧
 * Date :2018-01-09 12:39.
 */
public class Server {
    private String name;
    private Map<String ,String> cacheMap = new HashMap<>();
    public Server(){};
    public Server(String name) {
        this.name = name;
    }
    public void put(String key, String value){
        cacheMap.put(key,value);
    }
    public String get(String key){
        return cacheMap.get(key);
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public int hashCode() {
        int h;
//这里的hashcode的求解：仿照java8中的hash值的算法实现，详情查看另一篇文章
        return (name == null) ? 0 : (h = name.hashCode()) ^ (h >>> 16);
//另一种hash求解，保持hashcode都为正值，目的都是为了是hashcode值的分布更均匀
//return (name == null) ? 0 : (h = name.hashCode()) & 0x7FFFFFFF
    }
}