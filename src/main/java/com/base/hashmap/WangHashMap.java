package com.base.hashmap;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述:
 *
 * @author: mylover
 * @Time: 09/02/2017.
 */
public class WangHashMap<K, V> implements WangMap<K, V> {

    private static int defaultLength = 16;

    private static double defaultLoader = 0.75;

    private Entry<K, V>[] table = null;

    private int size = 0;

    public WangHashMap() {
        this(defaultLength, defaultLoader);
    }

    public WangHashMap(int length, double loader) {
        defaultLength = length;
        defaultLoader = loader;

        table = new Entry[defaultLength];
    }

    private int getIndex(K k) {
        int m = defaultLength;
        int index = k.hashCode() % m;
        return index >= 0 ? index : -index;
    }

    @Override
    public V put(K k, V v) {

        //扩容
        if (size >= defaultLength * defaultLoader) {
            up2size();
        }

        //创建一个hash函数,根据key和hash函数算出数组下标
        int index = getIndex(k);
        Entry<K, V> entry = table[index];
        if (entry == null) {
            table[index] = newEntry(k,v,null);
            size++;
        } else {
            table[index] = newEntry(k,v,entry);
        }
        return table[index].getValue();
    }

    private void up2size() {
        Entry<K,V>[] newTable = new Entry[2 * defaultLength];
        //老数据再散列
        aganinHash(newTable);

    }

    private void aganinHash(Entry<K,V>[] newTable) {

        List<Entry<K,V>> list = new ArrayList<>();


        for (int i=0; i < table.length; i++) {
            if (table[i] == null) {
                continue;
            }
            findEntryByNext(table[i], list);
        }
        if (list.size() > 0){
            size = 0;
            defaultLength = defaultLength * 2;
            table = newTable;
            for (Entry<K, V> entry : list) {
                if (entry.next != null) {
                    entry.next = null;
                }
                put(entry.getKey(), entry.getValue());
            }
        }

    }

    private void findEntryByNext(Entry<K,V> entry, List<Entry<K, V>> list) {
        if (entry != null && entry.next != null) {
            list.add(entry);
            findEntryByNext(entry.next, list);
        }
    }

    private Entry<K, V> newEntry(K k, V v, Entry<K, V> next) {
        return new Entry(k,v,next);
    }

    @Override
    public V get(K k) {
        int index = getIndex(k);
        //go
        if (table[index] == null) {
            return null;
        }
        return findValueByEqualKey(k, table[index]);
    }

    public V findValueByEqualKey(K k,Entry<K, V> entry) {
        if (k == entry.getKey() || k.equals(entry.getKey())) {
            return entry.getValue();
        } else {
            if (entry.next != null) {
                return findValueByEqualKey(k, entry.next);
            }
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    class Entry<K, V> implements WangMap.Entry<K, V> {

        K k;
        V v;
        Entry<K, V> next;

        public Entry(K k, V v, Entry<K, V> next) {
            this.k = k;
            this.v = v;
            this.next = next;
        }

        @Override
        public K getKey() {
            return k;
        }

        @Override
        public V getValue() {
            return v;
        }
    }
}
