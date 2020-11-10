package com.natsuki_kining.java.util;

/**
 * TODO
 *
 * @Author natsuki_kining
 * @Date 2020/11/19 22:46
 **/
public class LinkedList<E> extends AbstractList<E> implements List<E> {

    private static class Node<E> {
        E item;
        com.natsuki_kining.java.util.LinkedList.Node<E> next;
        com.natsuki_kining.java.util.LinkedList.Node<E> prev;

        Node(com.natsuki_kining.java.util.LinkedList.Node<E> prev, E element, com.natsuki_kining.java.util.LinkedList.Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }
}
