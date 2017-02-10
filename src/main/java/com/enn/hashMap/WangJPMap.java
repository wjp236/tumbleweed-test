package com.enn.hashMap;

/**
 * 描述:
 *
 * @author: mylover
 * @Time: 10/02/2017.
 */
public interface WangJPMap<K, V> {

    V put(K k, V v);

    V get(K k);

    int size();

    interface Entry<K, V> {
        K getKey();

        V getValue();
    }

}
