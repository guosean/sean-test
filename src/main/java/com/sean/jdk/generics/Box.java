package com.sean.jdk.generics;

/**
 * Created by guozhenbin on 2017/3/23.
 */
public class Box<T> {

    private T t;

    public T get(){
        return t;
    }

    public void set(T t){
        this.t = t;
    }

    public static void main(String[] args) {
        Box<Integer> box = new Box();

    }
}
