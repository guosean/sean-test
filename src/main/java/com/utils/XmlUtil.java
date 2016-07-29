package com.utils;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.InputStream;

/**
 * Created by guozhenbin on 16/7/29.
 */
public class XmlUtil {

    public static boolean isValid(InputStream xsdInput,InputStream xmlInput){
        SchemaFactory schemaFactory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
        try {
            Schema schema = schemaFactory.newSchema(new StreamSource(xsdInput));
            Validator validator = schema.newValidator();
            Source source = new StreamSource(xmlInput);
            validator.validate(source);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }


}
