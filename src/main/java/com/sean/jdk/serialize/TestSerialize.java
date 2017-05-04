package com.sean.jdk.serialize;

import com.alibaba.com.caucho.hessian.io.Hessian2Input;
import com.alibaba.com.caucho.hessian.io.Hessian2Output;

import java.io.*;

/**
 * Created by guozhenbin on 16/8/18.
 */
public class TestSerialize {

    private static final String FILE_NAME = "enum.out";

    public static void main(String[] args) throws IOException {
//        TestObj obj = new TestObj("name","desc");

        /*bytes = hout.toByteArray();
        System.out.println(bytes.length);*/
//        ByteArrayInputStream input = new ByteArrayInputStream(bytes);
        FileInputStream input = new FileInputStream(new File(FILE_NAME));
        Hessian2Input hiput = new Hessian2Input(input);
        State testObj = (State) hiput.readObject();
        System.out.println(testObj.getCode());

    }

    private static void serialize() throws Exception{
        State obj = State.OK;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(obj);
        byte[] bytes  = outputStream.toByteArray();
        System.out.println(bytes.length);
//        ByteArrayOutputStream hout = new ByteArrayOutputStream();
        FileOutputStream hout = new FileOutputStream(new File(FILE_NAME));
        Hessian2Output hessian2Output = new Hessian2Output(hout);
        hessian2Output.writeObject(obj);
        hessian2Output.flush();
    }

    enum State {
        OK(3,"ok"),
        FAIL(2,"fail");

        int code;
        String desc;

        State(int code,String desc){
            this.code = code;
            this.desc = desc;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }
    }

    static class TestObj implements Serializable{
        String name;
        String desc;
        TestObj(String name,String desc){
            this.name = name;
            this.desc = desc;
        }

        public String getDesc() {
            return desc;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }

}
