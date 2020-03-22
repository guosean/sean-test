package com.sean.jdk.collection;

import java.util.Iterator;

/**
 * TODO description
 *
 * @author guozhenbin
 * @date 2020/3/12
 */
public class TestIterable {

    public static void main(String[] args) {

    }

   static class Node implements Iterable{
        String value;
        Node head;
        Node next;

        Iterator iterator = new Iterator() {

            Node current = head;

            @Override
            public boolean hasNext() {
                return null != next;
            }

            @Override
            public Object next() {
                return next;
            }
        };

        public Node(String value){
            this.value = value;
        }

        public void addNode(String value){

        }

        @Override
        public Iterator iterator() {
            return iterator;
        }

    }
}

