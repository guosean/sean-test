package com.sean.rpc;

import com.alibaba.dubbo.common.compiler.Compiler;
import com.alibaba.dubbo.common.compiler.support.JavassistCompiler;

/**
 * Created by guozhenbin on 16/7/29.
 */
public class TestCompile {

    public static void main(String[] args) throws Exception{
        Compiler compiler = new JavassistCompiler();
        StringBuilder sb = new StringBuilder("package com.sean.rpc;\n");
        sb.append("import com.sean.rpc.Inter;\n");
        sb.append("public class MyInter implements Inter{\n");
        sb.append("public void test(){\n");
        sb.append("System.out.println(\"i am sean\");\n");
        sb.append("}}");
        String code = sb.toString();
        Class<Inter> inter = (Class<Inter>) compiler.compile(code, TestCompile.class.getClassLoader());
        inter.newInstance().test();
    }

}
