package com.sean.study;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Created by guozhenbin on 2017/10/11
 */
public class MainTest {

    public static void main(String[] args) {
        Stream stream = Arrays.stream(TestMethod.class.getDeclaredMethods()).filter(method -> method.getName().startsWith("get"));
        long count = stream.count();
        System.out.println(count);
        System.out.println(stream.findFirst().get());
    }

}

class TestMethod{
    public void getA(){}
    public void getB(){}
    public void getC(){}
}