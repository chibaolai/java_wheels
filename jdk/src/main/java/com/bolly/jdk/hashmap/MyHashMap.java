package com.bolly.jdk.hashmap;

import java.io.Serializable;
import java.util.*;

/**
 * @author bolly
 * @see HashMap
 */
public class MyHashMap<K,V> extends AbstractMap<K,V> implements Map<K,V>, Cloneable, Serializable {


    private static final long serialVersionUID = 7941846930596033510L;

    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; // aka 16

    static final float DEFAULT_LOAD_FACTOR = 0.75f;

    static final int TREEIFY_THRESHOLD = 8;

    transient MyHashMap.Node<K,V>[] table;

    final float loadFactor;

    public MyHashMap() {
        loadFactor = DEFAULT_LOAD_FACTOR;
    }

    public V put(K key, V value) {
        return putVal(hash(key), key, value, false, true);
    }

    final V putVal(int hash, K key, V value, boolean onlyIfAbsent,
                   boolean evict) {
//        MyHashMap.Node<K,V>[] tab; MyHashMap.Node<K,V> p; int n, i;
//        if ((tab = table) == null || (n = tab.length) == 0)
//            n = (tab = resize()).length;
//        if ((p = tab[i = (n - 1) & hash]) == null)
//            tab[i] = newNode(hash, key, value, null);
//        else {
//            MyHashMap.Node<K,V> e; K k;
//            if (p.hash == hash &&
//                    ((k = p.key) == key || (key != null && key.equals(k))))
//                e = p;
//            else if (p instanceof MyHashMap.TreeNode)
//                e = ((MyHashMap.TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
//            else {
//                for (int binCount = 0; ; ++binCount) {
//                    if ((e = p.next) == null) {
//                        p.next = newNode(hash, key, value, null);
//                        if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
//                            treeifyBin(tab, hash);
//                        break;
//                    }
//                    if (e.hash == hash &&
//                            ((k = e.key) == key || (key != null && key.equals(k))))
//                        break;
//                    p = e;
//                }
//            }
//            if (e != null) { // existing mapping for key
//                V oldValue = e.value;
//                if (!onlyIfAbsent || oldValue == null)
//                    e.value = value;
//                afterNodeAccess(e);
//                return oldValue;
//            }
//        }
//        ++modCount;
//        if (++size > threshold)
//            resize();
//        afterNodeInsertion(evict);
        return null;
    }

    static class Node<K,V> implements Map.Entry<K,V> {
        final int hash;
        final K key;
        V value;
        MyHashMap.Node<K,V> next;

        Node(int hash, K key, V value, MyHashMap.Node<K,V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        @Override
        public final K getKey()        { return key; }
        @Override
        public final V getValue()      { return value; }
        @Override
        public final String toString() { return key + "=" + value; }

        @Override
        public final int hashCode() {
            return Objects.hashCode(key) ^ Objects.hashCode(value);
        }

        @Override
        public final V setValue(V newValue) {
            V oldValue = value;
            value = newValue;
            return oldValue;
        }

        @Override
        public final boolean equals(Object o) {
            if (o == this)
                return true;
            if (o instanceof Map.Entry) {
                Map.Entry<?,?> e = (Map.Entry<?,?>)o;
                if (Objects.equals(key, e.getKey()) &&
                        Objects.equals(value, e.getValue()))
                    return true;
            }
            return false;
        }
    }

    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }


    @Override
    public Set<Entry<K, V>> entrySet() {
        return null;
    }


    public static void main(String[] args) {

//        MyHashMap<String,String> hashMap = new MyHashMap<>();
        String key = "1";
        int h;
        int result = (h = key.hashCode()) ^ (h >>> 16);
        System.out.println("hashCode:" + key.hashCode() + "======" + "h>>> 16:" + (h >>> 16) + "======" + "result:" + result);
    }
}
