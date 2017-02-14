package com.enn.hashMap;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述:hashMap实现
 *
 * @author: mylover
 * @Time: 10/02/2017.
 */
public class WangJPHashMap<K, V> implements WangJPMap<K, V> {

    private static int defaultLength = 16;

    private static float defaultLoader = 0.75f;

    private static int size = 0;

    private Entry<K, V>[] table = null;

    //默认构造函数
    public WangJPHashMap() {
        this(defaultLength, defaultLoader);
    }

    //自定义构造函数
    public WangJPHashMap(int length, float loader) {
        defaultLength = length;
        defaultLoader = loader;

        table = new Entry[defaultLength];
    }

    @Override
    public V put(K k, V v) {

        //判断是否扩容
        if (size >= defaultLength * defaultLoader) {
            upSize();
        }

        //根据hash函数,找到数组位置
        int index = getIndex(k);
        Entry<K, V> entry = table[index];

        if (entry == null) {
            //如果空,放进去
            table[index] = newEntry(k, v, null);
            size ++;
        } else {
            table[index] = newEntry(k, v, entry);
        }

        return table[index].getValue();
    }

    private void upSize() {

        Entry<K, V>[] newTable = new Entry[2 * defaultLength];

        //创建新的数组中,散列原值
        againstHash(newTable);

    }

    /**
     * 重新散列
     * @param newTable
     */
    private void againstHash(Entry<K, V>[] newTable) {
        //创建集合存储老hashmap的对象
        List<Entry<K, V>> list = new ArrayList<>();

        for (int i = 0; i < table.length; i++) {
            if (table[i] == null) {
                continue;
            }
            //找到数组该位置的所有对象
            findEntryByNext(table[i], list);
        }

        if (list.size() > 0) {
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

    private void findEntryByNext(Entry<K, V> kvEntry, List<Entry<K, V>> list) {
        if (kvEntry != null && kvEntry.next != null) {
            list.add(kvEntry);
            findEntryByNext(kvEntry.next, list);
        } else {
            list.add(kvEntry);
        }
    }

    /**
     * 创建entry对象
     * @param k
     * @param v
     * @param entry
     * @return
     */
    private Entry<K, V> newEntry(K k, V v, Entry entry) {
        return new Entry(k, v, entry);
    }

    private int getIndex(K k) {
        int m = defaultLength;
        if (k != null) {
            int index =  k.hashCode() % m;
            return index >= 0 ? index : -index;
        }
        return 0;
    }

    @Override
    public V get(K k) {

        //根据hash函数,找到那个位置
        int index = getIndex(k);
        if (table[index] == null) {
            return null;
        }
        return findEntryByKey(k, table[index]);
    }

    private V findEntryByKey(K k, Entry<K, V> kvEntry) {
        if (k == kvEntry.getKey() || k.equals(kvEntry.getKey())) {
            return kvEntry.getValue();
        } else {
            if (kvEntry.next != null) {
                return findEntryByKey(k, kvEntry.next);
            }
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    class Entry<K, V> implements WangJPMap.Entry<K, V> {
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
