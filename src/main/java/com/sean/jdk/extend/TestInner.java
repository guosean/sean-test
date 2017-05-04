package com.sean.jdk.extend;

/**
 * Created by guozhenbin on 16/8/1.
 */
public class TestInner {

    public static void main(String[] args) {
        InnerMain innerMain = new InnerMain();
        Counter c1 = innerMain.getCounter("local"),
        c2 = innerMain.getCounter2("any");
        for (int i=0; i<5; i++){
            System.out.print(c1.next());
        }
        for(int i=0; i<5; i++){
            System.out.print(c2.next());
        }
    }

}

interface Counter{
    int next();
}

class InnerMain{

    private int count = 0;
    Counter getCounter(final String name){
        class localCounter implements Counter{

            private String name ;

            public localCounter(){
                System.out.println("localCounter()");
            }

            public localCounter(String name){
                System.out.println("local name:"+name);
                this.name = name;
            }

            @Override
            public int next() {
                System.out.println(name);
                return count++;
            }
        }

        return new localCounter(name);
    }

    Counter getCounter2(final String name){
        return new Counter() {
            {
                System.out.println("counter");
            }
            @Override
            public int next() {
                System.out.println(name);
                return count++;
            }
        };
    }

}