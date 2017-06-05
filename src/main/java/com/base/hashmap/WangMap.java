package com.base.hashmap;

/**
 * 描述:
 *
 * @author: mylover
 * @Time: 09/02/2017.
 */
public interface WangMap<K, V> {

    V put(K k, V v);

    V get(K k);

    int size();

    interface Entry<K, V> {
        public K getKey();

        public V getValue();
    }

}
