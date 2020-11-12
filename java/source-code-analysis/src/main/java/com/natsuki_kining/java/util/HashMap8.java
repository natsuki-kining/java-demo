package com.natsuki_kining.java.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Objects;

/**
 * HashMap Java 1.8 实现
 *
 *  面试题：
 *      1.put如何实现
 *          1.调用putVal方法
 *          2.判断桶是否为空，如果空就调用resize进行初始化
 *          3.进行与运算，确定桶的下标，如果当前桶是空的，则直接放入元素。
 *          4.否则就判断key值是否一样，一直则直接替换
 *          5.如果不一样，则进行加到链表里面，如果链表长度大于等于8则转成红黑树
 *          6.如果找到key值相同的，则返回旧值，没有则返回空
 *      2.get如何实现
 *          1.调用getNode方法
 *          2.先判断桶是否为空，如果为空，则直接返回空
 *          3.获取桶里的第一个元素，如果hash值跟key一样，则直接返回
 *          4.如果不是则进入循环查找
 *      3.hashMap中什么时候需要进行扩容，扩容resize()又是如何实现的
 *          1.获取原本数组的长度，如果为空，则设置为默认值
 *          2.判断是否超过最大值，如果超过就不扩充。最大值2的三十次方
 *          3.没有超过最大值，则扩充容量为原来的2倍
 *          4.创建新桶
 *          5.重新计算hash值，将数据移到数组里，旧数组对应的下标设置为空
 *      4.hash函数是怎么实现的？还有哪些hash函数的实现方式？
 *          对key的hashCode做hash操作，与高16位做异或运算
 *          还有平方取中法，除留余数法，伪随机数法
 *      5.为什么不直接将key作为哈希值而是与高16位做异或运算？
 *          因为数组位置的确定用的是与运算，仅仅最后四位有效，设计者将key的哈希值与高16为做异或运算使得在做&运算确定数组的插入位置时，
 *          此时的低位实际是高位与低位的结合，增加了随机性，减少了哈希碰撞的次数。
 *      6.为什么必须是2的幂？如果输入值不是2的幂比如10会怎么样？
 *          为了数据的均匀分布，减少哈希碰撞。因为确定数组位置是用的位运算，若数据不是2的次幂则会增加哈希碰撞的次数和浪费数组空间。(PS:其实若不考虑效率，求余也可以就不用位运算了也不用长度必需为2的幂次)
 *          输入数据若不是2的幂，HashMap通过一通位移运算和或运算得到的肯定是2的幂次数，并且是离那个数最近的数字
 *      7.当两个对象的hashCode相等时会怎么样？
 *          会产生哈希碰撞，若key值相同则替换旧值，不然链接到链表后面，链表长度超过阙值8就转为红黑树存储
 *      8.如果两个键的hashcode相同，如何获取值对象？
 *          HashCode相同，通过equals比较内容获取值对象
 *      9.HashMap和HashTable的区别
 *
 *
 * @Author natsuki_kining
 * @Date 2020/11/19 22:51
 **/
public class HashMap8<K,V> extends AbstractMap<K,V> implements Map<K,V> {

    //默认初始化容量 16。容量必须为2的幂。默认的hashmap大小为16.
    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; // aka 16
    //最大的容量大小2^30
    static final int MAXIMUM_CAPACITY = 1 << 30;
    //默认resize的因子。0.75，即实际数量超过总数DEFAULT_LOAD_FACTOR的数量即会发生resize动作。
    static final float DEFAULT_LOAD_FACTOR = 0.75f;
    //树化阈值。当单个segment的容量超过阈值时，将链表转化为红黑树。
    static final int TREEIFY_THRESHOLD = 8;
    //链表化阈值。当resize后或者删除操作后单个segment的容量低于阈值时，将红黑树转化为链表。
    static final int UNTREEIFY_THRESHOLD = 6;
    //最小树化容量。当桶中的bin被树化时最小的hash表容量，低于该容量时不会树化。
    static final int MIN_TREEIFY_CAPACITY = 64;
    //修改次数
    transient int modCount;
    //当HashMap的size大于threshold时会执行resize操作
    //threshold = capacity*loadFactor
    //DEFAULT_INITIAL_CAPACITY * DEFAULT_LOAD_FACTOR = 12 (一开始)
    int threshold;
    //译为装载因子。装载因子用来衡量HashMap满的程度。loadFactor的默认值为0.75f。
    //计算HashMap的实时装载因子的方法为：size/capacity，而不是占用桶的数量去除以capacity。
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

        /**
         * 将根节点变成头元素，即保存在Node[]数组中的元素
         * @param tab
         * @param root
         * @param <K>
         * @param <V>
         */
        static <K,V> void moveRootToFront(HashMap8.Node<K,V>[] tab, HashMap8.TreeNode<K,V> root) {
            int n;
            if (root != null && tab != null && (n = tab.length) > 0) {
                //计算根元素在数组中的索引位置
                int index = (n - 1) & root.hash;
                //获取头元素
                HashMap8.TreeNode<K,V> first = (HashMap8.TreeNode<K,V>)tab[index];
                if (root != first) {
                    HashMap8.Node<K,V> rn;
                    //将对应索引的头元素设置成根元素
                    tab[index] = root;
                    //将root前后的元素建立连接，即移除root元素
                    HashMap8.TreeNode<K,V> rp = root.prev;
                    if ((rn = root.next) != null) {
                        ((HashMap8.TreeNode<K, V>) rn).prev = rp;
                    }
                    if (rp != null) {
                        rp.next = rn;
                    }
                    //将root和first元素建立连接，即将root元素插入到first前面
                    if (first != null) {
                        first.prev = root;
                    }
                    root.next = first;
                    root.prev = null;
                }
                //检查元素是否符合规则
                assert checkInvariants(root);
            }
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
         * 如果存在key值相等的则返回该节点，否则插入新节点，返回null
         * @param map
         * @param tab
         * @param h
         * @param k
         * @param v
         * @return
         */
        final HashMap8.TreeNode<K,V> putTreeVal(HashMap8<K,V> map, HashMap8.Node<K,V>[] tab, int h, K k, V v) {
            Class<?> kc = null;
            boolean searched = false;
            //获取根节点
            HashMap8.TreeNode<K,V> root = (parent != null) ? root() : this;
            for (HashMap8.TreeNode<K,V> p = root;;) {
                int dir, ph; //dir：插入的位置，小于0插入到左子树，大于则插入到右子树。ph：当前节点的hash值
                K pk;
                if ((ph = p.hash) > h) {
                    dir = -1;
                }
                else if (ph < h) {
                    dir = 1;
                }
                //hash相等且key相等则返回当前节点
                else if ((pk = p.key) == k || (k != null && k.equals(pk))) {
                    return p;
                }
                //hash相等，equals方法不等，看key是否实现Comparable接口，如果没有实现或者实现了还是跟父节点key一样
                else if ((kc == null &&
                        (kc = comparableClassFor(k)) == null) ||
                        (dir = compareComparables(kc, k, pk)) == 0) {
                    if (!searched) {
                        HashMap8.TreeNode<K,V> q, ch;
                        searched = true;
                        //从当前节点的子节点中找到key值相等的节点并返回
                        if (((ch = p.left) != null &&
                                (q = ch.find(h, k, kc)) != null) ||
                                ((ch = p.right) != null &&
                                        (q = ch.find(h, k, kc)) != null)) {
                            return q;
                        }
                    }
                    //如果当前节点的子节点还是没有key值相等的则采用系统默认方法比较key大小
                    dir = tieBreakOrder(k, pk);
                }

                HashMap8.TreeNode<K,V> xp = p;
                //找到待插入的位置
                if ((p = (dir <= 0) ? p.left : p.right) == null) {
                    HashMap8.Node<K,V> xpn = xp.next;
                    //构造新的节点
                    HashMap8.TreeNode<K,V> x = map.newTreeNode(h, k, v, xpn);
                    if (dir <= 0) {
                        xp.left = x;
                    }
                    else {
                        xp.right = x;
                    }
                    //此处维护的链表顺序并不是插入顺序，新插入的节点并不是在最后一个
                    xp.next = x;
                    x.parent = x.prev = xp;
                    if (xpn != null) {
                        ((HashMap8.TreeNode<K, V>) xpn).prev = x;
                    }
                    //插入完成后将根节点作为tab 数组内的第一个节点
                    moveRootToFront(tab, balanceInsertion(root, x));
                    return null;
                }
            }
        }

        /**
         * 红黑树的扩容切分
         * 具体来说就是将一颗红黑树中的元素几乎均匀的分到两个红黑树中，该实现是基于HashMap的容量的，切分时先将原有的链表转换成两个链表，再判断两个链表的长度是否做形态转换。
         * @param map
         * @param tab
         * @param index 数组的索引
         * @param bit 数组长度,即HashMap的容量,是2的幂
         */
        final void split(HashMap8<K,V> map, HashMap8.Node<K,V>[] tab, int index, int bit) {
            HashMap8.TreeNode<K,V> b = this;
            // 切分成lo 和 hi的两个链表
            HashMap8.TreeNode<K,V> loHead = null, loTail = null;
            HashMap8.TreeNode<K,V> hiHead = null, hiTail = null;
            //lc和hc分别表示两个链表的长度
            int lc = 0, hc = 0;
            //从当前节点开始往下遍历
            for (HashMap8.TreeNode<K,V> e = b, next; e != null; e = next) {
                next = (HashMap8.TreeNode<K,V>)e.next;
                e.next = null;
                //根据此条件判断是hi链表还是lo链表
                //只有e的hash值的二进制表示的第9位为1时，该表达式才为1，其他情形都是0
                if ((e.hash & bit) == 0) {
                    //不断将当前元素追加到lo链表后面
                    if ((e.prev = loTail) == null) {
                        loHead = e;
                    }
                    else {
                        loTail.next = e;
                    }
                    loTail = e;
                    ++lc;
                }
                else {
                    //不断将当前元素追加到hi链表后面
                    if ((e.prev = hiTail) == null) {
                        hiHead = e;
                    }
                    else {
                        hiTail.next = e;
                    }
                    hiTail = e;
                    ++hc;
                }
            }

            if (loHead != null) {
                //如果lo链表的长度小于阈值
                if (lc <= UNTREEIFY_THRESHOLD) {
                    tab[index] = loHead.untreeify(map);
                }
                else {
                    tab[index] = loHead;
                    //hiHead为null表明当前tab没有被拆分，依然还是一颗树，不需要重新树化
                    if (hiHead != null) {
                        loHead.treeify(tab);
                    }
                }
            }
            if (hiHead != null) {
                if (hc <= UNTREEIFY_THRESHOLD) {
                    tab[index + bit] = hiHead.untreeify(map);
                }
                else {
                    tab[index + bit] = hiHead;
                    if (loHead != null) {
                        hiHead.treeify(tab);
                    }
                }
            }
        }

        /**
         * 判断两个任意对象大小的通用方法,该方法是正常流程比不出大小时才会使用
         * @param a
         * @param b
         * @return
         */
        static int tieBreakOrder(Object a, Object b) {
            int d;
            if (a == null || b == null || (d = a.getClass().getName().compareTo(b.getClass().getName())) == 0) {
                //identityHashCode是本地方法，忽视对象本身对hashCode()方法的重写
                d = (System.identityHashCode(a) <= System.identityHashCode(b) ? -1 : 1);
            }
            return d;
        }

        /**
         * 将单向链表结构的TreeNode转换成红黑树结构，原有的链表结构基本没有变化，只是将红黑树的根节点作为链表元素的第一个节点而已
         * 在调用此方法前，此元素对应的单向链表的Node已经全部转换成TreeNode了
         * @param tab
         */
        final void treeify(HashMap8.Node<K,V>[] tab) {
            HashMap8.TreeNode<K,V> root = null;
            for (HashMap8.TreeNode<K,V> x = this, next; x != null; x = next) {
                next = (HashMap8.TreeNode<K,V>)x.next;
                x.left = x.right = null;
                //root为空时将当前节点置为根节点，置为黑色
                if (root == null) {
                    x.parent = null;
                    x.red = false;
                    root = x;
                }
                else {
                    K k = x.key;
                    int h = x.hash;
                    Class<?> kc = null;
                    for (HashMap8.TreeNode<K,V> p = root;;) {
                        int dir, ph;
                        K pk = p.key;
                        //比较根节点和目标节点的hash值，如果dir小于等于0则目标节点作为根节点的左节点，否则作为右节点
                        if ((ph = p.hash) > h) {
                            dir = -1;
                        }
                        else if (ph < h) {
                            dir = 1;
                        }
                        else if ((kc == null &&
                                (kc = comparableClassFor(k)) == null) ||
                                (dir = compareComparables(kc, k, pk)) == 0) {
                            dir = tieBreakOrder(k, pk);
                        }
                        HashMap8.TreeNode<K,V> xp = p;
                        //如果当前根节点的左节点或者右节点为空则将目标节点插入对应的左节点或者右节点
                        //否则继续遍历，直到找到对应的插入位置
                        if ((p = (dir <= 0) ? p.left : p.right) == null) {
                            //将当前节点的父节点设置根节点，根据dir将当前节点设置根节点的左节点或者右节点
                            x.parent = xp;
                            if (dir <= 0) {
                                xp.left = x;
                            }
                            else {
                                xp.right = x;
                            }
                            //获取根节点
                            root = balanceInsertion(root, x);
                            break;
                        }
                    }
                }
            }
            //将红黑树的根节点作为链表结构的第一个节点
            moveRootToFront(tab, root);
        }

        /**
         * 将红黑树转换成链表
         * @param map
         * @return
         */
        final HashMap8.Node<K,V> untreeify(HashMap8<K,V> map) {
            HashMap8.Node<K,V> hd = null, tl = null;
            for (HashMap8.Node<K,V> q = this; q != null; q = q.next) {
                //replacementNode是构造一个普通的链表节点
                HashMap8.Node<K,V> p = map.replacementNode(q, null);
                //当前节点是链表第一个元素，
                if (tl == null) {
                    hd = p;
                }
                else {
                    //将当前节点置为上一个节点的下一个节点
                    tl.next = p;
                }
                //tl表示上一个节点
                tl = p;
            }
            return hd;
        }

        /**
         * 左旋
         * 对节点P做红黑树左旋，返回该节点的根节点
         * @param root
         * @param p
         * @param <K>
         * @param <V>
         * @return
         */
        static <K,V> HashMap8.TreeNode<K,V> rotateLeft(HashMap8.TreeNode<K,V> root,HashMap8.TreeNode<K,V> p) {
            HashMap8.TreeNode<K,V> r, pp, rl;
            if (p != null && (r = p.right) != null) {
                //两个等于号，从右往左计算，即先执行p.right = r.left,后执行rl = p.right，最后判断rl!=null
                //将p的右节点的左节点变成p的右节点
                if ((rl = p.right = r.left) != null) {
                    rl.parent = p;
                }
                //将p的父节点变成r的父节点，判断该父节点是否为空
                if ((pp = r.parent = p.parent) == null) {
                    //如果父节点为空，则r是根节点，根节点总是黑色的
                    (root = r).red = false;
                }
                //如果父节点不为空，且p是该父节点的左节点
                else if (pp.left == p) {
                    pp.left = r;
                }else {
                    pp.right = r;
                }
                r.left = p;
                p.parent = r;
            }
            return root;
        }

        /**
         * 右旋
         *
         * 对节点P做红黑树右旋操作，并返回该节点的根节点，是左旋的逆向操作
         * @param root
         * @param p
         * @param <K>
         * @param <V>
         * @return
         */
        static <K,V> HashMap8.TreeNode<K,V> rotateRight(HashMap8.TreeNode<K,V> root, HashMap8.TreeNode<K,V> p) {
            HashMap8.TreeNode<K,V> l, pp, lr;
            if (p != null && (l = p.left) != null) {
                //将p的左节点由l变成l的右节点
                if ((lr = p.left = l.right) != null) {
                    lr.parent = p;
                }
                //将p设置成l的右节点，p的父节点设置成l，l的父节点设置成p原来的父节点
                if ((pp = l.parent = p.parent) == null) {
                    (root = l).red = false;
                }else if (pp.right == p) {
                    pp.right = l;
                }else {
                    pp.left = l;
                }
                l.right = p;
                p.parent = l;
            }
            return root;
        }

        /**
         * root为原来的根节点，因为插入时会旋转，根节点会发生改变，所以该方法返回的是执行平衡插入后的根节点
         * @param root 根节点
         * @param x
         * @param <K>
         * @param <V>
         * @return 平衡插入后的根节点
         */
        static <K,V> HashMap8.TreeNode<K,V> balanceInsertion(HashMap8.TreeNode<K,V> root, HashMap8.TreeNode<K,V> x) {
            //红黑树新增节点一定是红色的，因为这样违反规则的可能性最小
            x.red = true;
            //注意此处是for循环，即由当前节点逐步往上处理
            for (HashMap8.TreeNode<K,V> xp, xpp, xppl, xppr;;) {
                //当前节点的父节点为空，即该节点为根节点，根节点一定是黑色的
                if ((xp = x.parent) == null) {
                    x.red = false;
                    return x;
                }
                //父节点是黑色的或者父节点为根节点
                else if (!xp.red || (xpp = xp.parent) == null) {
                    return root;
                }
                //父节点是祖父节点的左节点，即上述讨论中的2节点是左节点
                if (xp == (xppl = xpp.left)) {
                    //祖父节点的右节点存在且是红色节点，即是情形一
                    if ((xppr = xpp.right) != null && xppr.red) {
                        //将其父节点和父节点的兄弟节点涂成黑色
                        xppr.red = false;
                        xp.red = false;
                        //祖父节点置为红色，将当前节点置为祖父节点
                        xpp.red = true;
                        //注意此处直接跳到祖父节点了，因为祖父节点成红色的
                        x = xpp;
                    }
                    //如果祖父节点的右节点不存在或者是黑色的
                    else {
                        //当前节点是父节点的右节点，即2节点为左节点时的情形二
                        if (x == xp.right) {
                            //将当前节点置为父节点，并对父节点左旋
                            root = rotateLeft(root, x = xp);
                            xpp = (xp = x.parent) == null ? null : xp.parent;
                        }
                        //如果父节点存在，则将父节点涂黑，祖父节点涂红，并将祖父节点右旋
                        //即2节点为左节点时的情形三
                        if (xp != null) {
                            xp.red = false;
                            if (xpp != null) {
                                xpp.red = true;
                                root = rotateRight(root, xpp);
                            }
                        }
                    }
                }
                //父节点是祖父节点的右节点
                else {
                    //如果祖父节点的左节点存在且是红色的，即是情形
                    if (xppl != null && xppl.red) {
                        //祖父节点和祖父节点的兄弟节点置为黑色的
                        xppl.red = false;
                        xp.red = false;
                        //父节点置为红色的，将当前节点置为祖父节点
                        xpp.red = true;
                        x = xpp;
                    }
                    //如果祖父节点的左节点并存在或者是黑色的
                    else {
                        //如果当前节点是父节点的左节点，即2节点为右节点时的情形二
                        if (x == xp.left) {
                            //将当前节点置为父节点，并对父节点右旋
                            root = rotateRight(root, x = xp);
                            xpp = (xp = x.parent) == null ? null : xp.parent;
                        }
                        //如果父节点存在
                        if (xp != null) {
                            //将父节点置为黑色，祖父节点置为红色，对祖父节点做左旋，即2节点为右节点时的情形三
                            xp.red = false;
                            if (xpp != null) {
                                xpp.red = true;
                                root = rotateLeft(root, xpp);
                            }
                        }
                    }
                }
            }
        }

        /**
         * 递归检查当前节点是否符合双向链表以及红黑树规则
         * 该方法在红黑树发生改变时都会执行一次，确保改变后红黑树符合规范
         * @param t
         * @param <K>
         * @param <V>
         * @return
         */
        static <K,V> boolean checkInvariants(HashMap8.TreeNode<K,V> t) {
            HashMap8.TreeNode<K,V> tp = t.parent,
                    tl = t.left,
                    tr = t.right,
                    tb = t.prev,
                    tn = (HashMap8.TreeNode<K,V>)t.next;
            //是否符合双向链表规则
            if (tb != null && tb.next != t) {
                return false;
            }
            if (tn != null && tn.prev != t) {
                return false;
            }
            //是否符合红黑树规则
            if (tp != null && t != tp.left && t != tp.right) {
                return false;
            }
            //左节点的hash值必须小于根节点hash值
            if (tl != null && (tl.parent != t || tl.hash > t.hash)) {
                return false;
            }
            //右节点hash值必须大于根节点hash值
            if (tr != null && (tr.parent != t || tr.hash < t.hash)) {
                return false;
            }
            //红色节点的子节点一定不能是红色
            if (t.red && tl != null && tl.red && tr != null && tr.red) {
                return false;
            }
            //递归检查左节点和右节点
            if (tl != null && !checkInvariants(tl)) {
                return false;
            }
            if (tr != null && !checkInvariants(tr)) {
                return false;
            }
            return true;
        }
    }

    HashMap8.TreeNode<K,V> newTreeNode(int hash, K key, V value, HashMap8.Node<K,V> next) {
        return new HashMap8.TreeNode<>(hash, key, value, next);
    }

    HashMap8.Node<K,V> replacementNode(HashMap8.Node<K,V> p, HashMap8.Node<K,V> next) {
        return new HashMap8.Node<>(p.hash, p.key, p.value, next);
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
     * 变为2的幂
     *
     * 好处：主要是可以使用按位与替代取模来提升hash的效率
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
        HashMap8.Node<K,V>[] oldTab = table;
        int oldCap = (oldTab == null) ? 0 : oldTab.length;
        int oldThr = threshold;
        int newCap, newThr = 0;
        if (oldCap > 0) {
            // 超过最大值就不再扩充了
            if (oldCap >= MAXIMUM_CAPACITY) {
                threshold = Integer.MAX_VALUE;
                return oldTab;
            }
            // 没超过最大值，就扩充为原来的2的幂 newCap = x << 1 扩大为 x的平方
            else if ((newCap = oldCap << 1) < MAXIMUM_CAPACITY && oldCap >= DEFAULT_INITIAL_CAPACITY) {
                newThr = oldThr << 1;
            }
        }
        //oldCap = 0，原数组为空
        else if (oldThr > 0) {
            newCap = oldThr;
        }
        //元数组为空，并且threshold=0，则都设置为默认值
        else {
            newCap = DEFAULT_INITIAL_CAPACITY;
            newThr = (int)(DEFAULT_LOAD_FACTOR * DEFAULT_INITIAL_CAPACITY);
        }
        if (newThr == 0) {
            float ft = (float)newCap * loadFactor;
            newThr = (newCap < MAXIMUM_CAPACITY && ft < (float)MAXIMUM_CAPACITY ? (int)ft : Integer.MAX_VALUE);
        }
        threshold = newThr;
        //创建新数组，将旧桶里的数据移到新桶里
        @SuppressWarnings({"rawtypes","unchecked"})
        HashMap8.Node<K,V>[] newTab = (HashMap8.Node<K,V>[])new HashMap8.Node[newCap];
        table = newTab;
        if (oldTab != null) {
            // 把每个bucket都移动到新的buckets中
            //oldCap久数组的长度
            for (int j = 0; j < oldCap; ++j) {
                //桶里的第一个元素
                HashMap8.Node<K,V> e;
                if ((e = oldTab[j]) != null) {
                    // 清除原来table[i]中的值
                    oldTab[j] = null;
                    //如果只有一个节点，则直接放进去
                    if (e.next == null) {
                        newTab[e.hash & (newCap - 1)] = e;
                    }
                    //如果是红黑树，则进行红黑树的操作
                    else if (e instanceof HashMap8.TreeNode) {
                        ((HashMap8.TreeNode<K, V>) e).split(this, newTab, j, oldCap);
                    }
                    else {
                        HashMap8.Node<K,V> loHead = null, loTail = null;
                        HashMap8.Node<K,V> hiHead = null, hiTail = null;
                        HashMap8.Node<K,V> next;
                        do {
                            next = e.next;
                            //e.hash & oldCap == 0 原来的坐标没有发生变化
                            //e.hash & oldCap != 0 在原来坐标的前提下增加 oldCap
                            //https://www.cnblogs.com/shoshana-kong/p/9633634.html
                            if ((e.hash & oldCap) == 0) {
                                if (loTail == null) {
                                    loHead = e;
                                }
                                else {
                                    loTail.next = e;
                                }
                                loTail = e;
                            }
                            else {
                                if (hiTail == null) {
                                    hiHead = e;
                                }
                                else {
                                    hiTail.next = e;
                                }
                                hiTail = e;
                            }
                        } while ((e = next) != null);
                        if (loTail != null) {
                            loTail.next = null;
                            newTab[j] = loHead;
                        }
                        if (hiTail != null) {
                            hiTail.next = null;
                            //在原来坐标的前提下增加 oldCap
                            newTab[j + oldCap] = hiHead;
                        }
                    }
                }
            }
        }
        return newTab;
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
