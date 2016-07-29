package com.sean.jdk.io;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.net.URL;

/**
 * Created by guozhenbin on 16/7/29.
 */
public class TestXml {

    public static void main(String[] args) throws Exception{
        testValidate();
    }

    private static void testValidate() throws Exception{
        SchemaFactory schemaFactory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
        //        File schemeFile = new File(xsdPath);
        URL xsdSource = Thread.currentThread().getContextClassLoader().getResource("access_control.xsd");
        Schema schema = schemaFactory.newSchema(xsdSource);
        //        schemaFactory.newSchema(xsdSource);
        Validator validator = schema.newValidator();
        URL xmlSource = Thread.currentThread().getContextClassLoader().getResource("access_control.xml");
        Source source = new StreamSource(xmlSource.openStream());
        validator.validate(source);
        System.out.println("success");
    }
}
