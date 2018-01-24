package com.tumbleweed.test.base.hashmap.consistent;
import java.util.SortedMap;
import java.util.TreeMap;
/**
 * Author :极客慧
 * Date :2018-01-09 12:39.
 */
public class Cluster {
    private static final int SERVER_SIZE_MAX = 10;
    private int size = 0;
    private SortedMap<Integer, Server> servers = new TreeMap<>();
    public boolean addServer(Server s){
        if (size > SERVER_SIZE_MAX) {
            return false;
        }
        servers.put(s.hashCode(),s);
        size++;
        return true;
    }
    public void put(String key,String value){
        Server s = routServer(key);
        s.put(key, value);
    }
    public String get(String key){
        Server s = routServer(key);
        return s.get(key);
    }
    public Server routServer(String key){
        if (servers.isEmpty()){
            return null;
        }
        int hash = 0;
        if(!servers.containsKey(getHashCode(key))){
            SortedMap<Integer, Server> sortedMap = servers.tailMap(getHashCode(key));
            hash = sortedMap.isEmpty()?servers.firstKey():sortedMap.firstKey();
        }
        return servers.get(hash);
    }
    private static int getHashCode(String key){
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }
}