package com;

import java.io.FileOutputStream;
import java.io.IOException;

import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;

public class PdfTest {
  
	public static void main(String[] args) throws IOException, DocumentException {
		 PdfReader pdfReader = new PdfReader("D:\\test\\Hadoop权威指南.pdf"); 
		   System.out.println( pdfReader.isTampered()); 
		   PdfStamper pdfStamper = new PdfStamper(pdfReader, new FileOutputStream("D:\\test\\abc.pdf")); 
		   int pageCount   = pdfReader.getNumberOfPages(); 
		   Image image = Image.getInstance("D:\\test\\test.png"); 
		   image.setAbsolutePosition(200, 200); 
		   PdfContentByte pdfByte; 
		   //给每页增加水印 
		   for( int i = 1 ; i < pageCount; ++i){ 
		   pdfByte = pdfStamper.getUnderContent(i); 
		   pdfByte.addImage(image); 
		   } 
		   pdfStamper.close(); 
		   System.out.println( pdfReader.isTampered() ); 
	}
	
}
