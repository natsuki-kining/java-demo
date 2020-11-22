package com.natsuki_kining.java.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Objects;

/**
 * HashMap Java 1.8 实现
 *
 * 数组+链表转红黑树结构
 *
 *
 *
 * @Author natsuki_kining
 * @Date 2020/11/19 22:51
 **/
public class HashMap8<K,V> extends AbstractMap<K,V> implements Map<K,V> {


    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; // aka 16
    static final int MAXIMUM_CAPACITY = 1 << 30;
    static final float DEFAULT_LOAD_FACTOR = 0.75f;
    static final int TREEIFY_THRESHOLD = 8;
    static final int UNTREEIFY_THRESHOLD = 6;
    static final int MIN_TREEIFY_CAPACITY = 64;

    transient int modCount;
    int threshold;
    final float loadFactor;
    transient HashMap8.Node<K,V>[] table;
    transient int size;

    public HashMap8(int initialCapacity, float loadFactor) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Illegal initial capacity: " + initialCapacity);
        }
        if (initialCapacity > MAXIMUM_CAPACITY) {
            initialCapacity = MAXIMUM_CAPACITY;
        }
        if (loadFactor <= 0 || Float.isNaN(loadFactor)){
            throw new IllegalArgumentException("Illegal load factor: " + loadFactor);
        }
        this.loadFactor = loadFactor;
        this.threshold = tableSizeFor(initialCapacity);
    }

    public HashMap8(int initialCapacity) {
        this(initialCapacity, DEFAULT_LOAD_FACTOR);
    }

    public HashMap8() {
        this.loadFactor = DEFAULT_LOAD_FACTOR;
    }

    static class Node<K,V> implements java.util.Map.Entry<K,V> {
        final int hash;
        final K key;
        V value;
        HashMap8.Node<K,V> next;

        Node(int hash, K key, V value, HashMap8.Node<K,V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        @Override
        public final K getKey(){
            return key;
        }
        @Override
        public final V getValue(){
            return value;
        }
        @Override
        public final String toString() {
            return key + "=" + value;
        }
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
            if (o == this) {
                return true;
            }
            if (o instanceof java.util.Map.Entry) {
                java.util.Map.Entry<?,?> e = (java.util.Map.Entry<?,?>)o;
                if (Objects.equals(key, e.getKey()) && Objects.equals(value, e.getValue())) {
                    return true;
                }
            }
            return false;
        }
    }

    static final class TreeNode<K,V> extends LinkedHashMap.Entry<K,V> {
        TreeNode<K,V> parent;  //父节点
        TreeNode<K,V> left;//左子节点
        TreeNode<K,V> right;//右子节点
        TreeNode<K,V> prev;    // needed to unlink next upon deletion
        boolean red; //节点颜色

        TreeNode(int hash, K key, V val, HashMap8.Node<K,V> next) {
            super(hash, key, val, next);
        }

        final HashMap8.TreeNode<K,V> root() {
            for (HashMap8.TreeNode<K,V> r = this, p;;) {
                if ((p = r.parent) == null) {
                    return r;
                }
                r = p;
            }
        }

        final HashMap8.TreeNode<K,V> getTreeNode(int h, Object k) {
            //从根节点开始查找
            return ((parent != null) ? root() : this).find(h, k, null);
        }

        final HashMap8.TreeNode<K,V> find(int h, Object k, Class<?> kc) {
            //root方法中把this设置为了根节点
            HashMap8.TreeNode<K,V> p = this;
            do {
                int ph, dir;//当前节点的hash值，dir
                K pk;
                HashMap8.TreeNode<K,V> pl = p.left, pr = p.right, q;
                if ((ph = p.hash) > h) {
                    //当前的hash值大于查找节点的hash值，则说明要找的在左节点
                    p = pl;
                }else if (ph < h) {
                    //当前的hash值小于查找节点的hash值，则说明要找的在右节点
                    p = pr;
                }else if ((pk = p.key) == k || (k != null && k.equals(pk))) {
                    //如果key相同，则直接返回当前节点
                    return p;
                }else if (pl == null) {
                    //左树为空，就去查右树
                    p = pr;
                }else if (pr == null) {
                    //右树为空，就去查左树
                    p = pl;
                }else if ((kc != null ||  //getTreeNode方法传过来的是null
                        //如果key实现了Comparable接口
                        (kc = comparableClassFor(k)) != null) &&
                        //
                        (dir = compareComparables(kc, k, pk)) != 0) {
                    p = (dir < 0) ? pl : pr;
                }else if ((q = pr.find(h, k, kc)) != null) {
                    return q;
                }else {
                    p = pl;
                }
            } while (p != null);
            return null;
        }

        /**
         * 新增一个树形类型元素
         * @param map
         * @param tab
         * @param h
         * @param k
         * @param v
         * @return
         */
        final HashMap8.TreeNode<K,V> putTreeVal(HashMap8<K,V> map, HashMap8.Node<K,V>[] tab,int h, K k, V v) {
            return null;
        }
    }

    static Class<?> comparableClassFor(Object x) {
        //如果实现了Comparable接口
        if (x instanceof Comparable) {
            Class<?> c; Type[] ts, as; Type t; ParameterizedType p;
            if ((c = x.getClass()) == String.class) {
                return c;
            }
            if ((ts = c.getGenericInterfaces()) != null) {
                for (int i = 0; i < ts.length; ++i) {
                    if (((t = ts[i]) instanceof ParameterizedType) &&
                            ((p = (ParameterizedType)t).getRawType() ==
                                    Comparable.class) &&
                            (as = p.getActualTypeArguments()) != null &&
                            as.length == 1 && as[0] == c) {
                        return c;
                    }
                }
            }
        }
        return null;
    }

    @SuppressWarnings({"rawtypes","unchecked"})
    static int compareComparables(Class<?> kc, Object k, Object x) {
        return (x == null || x.getClass() != kc ? 0 : ((Comparable)k).compareTo(x));
    }

    /**
     * 变为2的n次方
     * @param cap
     * @return
     */
    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }

    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    /**
     * 扩容
     * @return
     */
    final HashMap8.Node<K,V>[] resize() {
        return null;
    }

    /**
     * 把容器里的元素变成树结构
     * @param tab HashMap 数组
     * @param hash hash值（要增加的键值对的key的hash值）
     */
    final void treeifyBin(HashMap8.Node<K,V>[] tab, int hash) {
    }

    /**
     * 创建一个新节点
     * @param hash
     * @param key
     * @param value
     * @param next
     * @return
     */
    HashMap8.Node<K,V> newNode(int hash, K key, V value, HashMap8.Node<K,V> next) {
        return new HashMap8.Node<>(hash, key, value, next);
    }

    /**
     * 为LinkedHashMap做铺垫
     * @param p
     */
    void afterNodeAccess(HashMap8.Node<K,V> p) { }
    void afterNodeInsertion(boolean evict) { }
    void afterNodeRemoval(HashMap8.Node<K,V> p) { }


    /**
     * 
     * @param key
     * @param value
     * @return
     */
    @Override
    public V put(K key, V value) {
        return putVal(hash(key), key, value, false, true);
    }

    final V putVal(int hash, K key, V value, boolean onlyIfAbsent, boolean evict) {
        HashMap8.Node<K,V>[] tab;//数组
        HashMap8.Node<K,V> p;//数组下标i tab[i]对应的元素
        //n数组大小，i数组下标
        int n, i;
        //如果桶是空的，则进行初始化，调用resize方法。
        if ((tab = table) == null || (n = tab.length) == 0) {
            n = (tab = resize()).length;
        }
        //与运算。A&(B-1) = A%B
        // (n - 1) & hash 数组的长度跟hash进行与运算，得到数组的下标i
        // 如果数组对应的元素为空则直接放入
        if ((p = tab[i = (n - 1) & hash]) == null) {
            tab[i] = newNode(hash, key, value, null);
        }else {
            HashMap8.Node<K, V> e;//新节点
            K k;
            //如果key值一样，则直接替换
            if (p.hash == hash && ((k = p.key) == key || (key != null && key.equals(k)))){
                e = p;
            }
            //如果是树形节点，则进入树形节点操作，
            else if (p instanceof HashMap8.TreeNode) {
                e = ((HashMap8.TreeNode<K, V>) p).putTreeVal(this, tab, hash, key, value);
            }else {
                //进入循环
                for (int binCount = 0; ; ++binCount) {
                    if ((e = p.next) == null) {
                        //如果next为空，则直接创建一个挂到next上，就是挂到链表的末尾
                        p.next = newNode(hash, key, value, null);
                        //如果元素达到TREEIFY_THRESHOLD - 1，也就是8个，则进入treeifyBin方法把链表转成红黑树
                        if (binCount >= TREEIFY_THRESHOLD - 1) {
                            treeifyBin(tab, hash);
                        }
                        break;
                    }
                    //如果key值相同，则跳出循环
                    if (e.hash == hash && ((k = e.key) == key || (key != null && key.equals(k)))) {
                        break;
                    }
                    //指向下个节点
                    p = e;
                }
            }
            //如果找到key值相同，则用新值覆盖旧值，并返回旧值
            if (e != null) {
                V oldValue = e.value;
                //onlyIfAbsent:是否更改现有的值。 put方法传进来的是false
                if (!onlyIfAbsent || oldValue == null) {
                    e.value = value;
                }
                //算是为后续留的扩展方法，新增成功的回调方法
                afterNodeAccess(e);
                return oldValue;
            }
        }
        //修改记录+1
        ++modCount;
        //判断是否需要扩容
        if (++size > threshold){
            resize();
        }
        //evict 如果为false，则表处于创建模式。put方法传进来的是true
        afterNodeInsertion(evict);
        return null;
    }

    @Override
    public V get(Object key) {
        HashMap8.Node<K,V> e;
        return (e = getNode(hash(key), key)) == null ? null : e.value;
    }

    final HashMap8.Node<K,V> getNode(int hash, Object key) {
        HashMap8.Node<K,V>[] tab; //数组
        HashMap8.Node<K,V> first, e;
        int n; //数组长度
        K k;
        //如果数组不为空，并且根据hash指向的数组元素也不为空
        if ((tab = table) != null && (n = tab.length) > 0 && (first = tab[(n - 1) & hash]) != null) {
            //如果是数组的第一个元素，则直接返回
            if (first.hash == hash && ((k = first.key) == key || (key != null && key.equals(k)))) {
                return first;
            }
            //判断是否还有下级
            if ((e = first.next) != null) {
                //如果是树节点，则进入树节点查找算法
                if (first instanceof HashMap8.TreeNode) {
                    return ((HashMap8.TreeNode<K, V>) first).getTreeNode(hash, key);
                }
                //循环链表，找到则直接返回
                do {
                    if (e.hash == hash && ((k = e.key) == key || (key != null && key.equals(k)))) {
                        return e;
                    }
                } while ((e = e.next) != null);
            }
        }
        return null;
    }


}
