package com.natsuki_kining.java.util;


/**
 * TODO
 *
 * @Author natsuki_kining
 * @Date 2020/11/19 22:51
 **/
public class LinkedHashMap<K,V> extends HashMap8<K,V> implements Map<K,V> {

    static class Entry<K,V> extends HashMap8.Node<K,V> {
        LinkedHashMap.Entry<K,V> before, after;
        Entry(int hash, K key, V value, HashMap8.Node<K,V> next) {
            super(hash, key, value, next);
        }
    }

}
