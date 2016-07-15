package com.img;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;

import com.lowagie.text.BadElementException;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;
//import com.infosoft.web.extend.picmanager.Utils.IsChineseOrEnglish;

public class PDFWaterMark {
	/*************************************************
	 * 实例化PdfStamper
	 * @author Amaryllis
	 * @param _pdfReader
	 * @param _savePath
	 * @return
	 * @throws FileNotFoundException
	 * @throws DocumentException
	 * @throws IOException
	 *************************************************/
	public PdfStamper getPdfStamper(PdfReader _pdfReader, String _savePath) throws FileNotFoundException, DocumentException, IOException{
		return new PdfStamper(_pdfReader, new FileOutputStream(_savePath));
	}
	/*************************************************
	 * 实例化PdfReader
	 * @author Amaryllis
	 * @param _filePath
	 * @return
	 * @throws IOException
	 ************************************************/
	public PdfReader getPdfReader(String _filePath) throws IOException{
		return new PdfReader(_filePath); 
	}
	/**
	 * 实例化图片（水印）
	 * @author Amaryllis
	 * @param _waterMark
	 * @return
	 * @throws BadElementException
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	public Image getImage(String _waterMark) throws BadElementException, MalformedURLException, IOException{
		return Image.getInstance(_waterMark); 
	}
	public void printSlopesEffects(PdfStamper pdfStamper , Image image, String _waterMarkName, int _pageCount) throws DocumentException, IOException{
		PdfContentByte under; 
		/*BaseFont base = BaseFont.createFont("c://windows/fonts/SIMHEI.TTF", BaseFont.IDENTITY_H, 
                BaseFont.NOT_EMBEDDED);  //设置字体样式  
*/        for (int i = 1; i < _pageCount; i++) { 
            under = pdfStamper.getOverContent(i);
            // 添加图片 
            under.addImage(image); 
           /* under.beginText(); 
            under.setColorFill(Color.GRAY); 
//            under.setFontAndSize(base, 18); 
            // 字体设置结束 
//            under.endText();
*/            } 
	}
	/*************************************************
	 * 打印水印方式一:带图片、文字形式(PDF)
	 * @author Amaryllis
	 * @param _filePath
	 * @param _savePath
	 * @param _waterMark
	 * @param waterMarkName
	 * @return
	 * @throws IOException
	 * @throws DocumentException
	 *************************************************/
	
	public boolean createPDFWaterMark(String _filePath, String _savePath, String _waterMark) throws IOException, DocumentException{
		   PdfReader pdfReader = new PdfReader(_filePath);
		   int pageCount = pdfReader.getNumberOfPages()+1;//获取pdf页码
		   PdfStamper pdfStamper = new PdfStamper(pdfReader, new FileOutputStream(_savePath));; 
		   Image image = Image.getInstance(_waterMark); 
//		   image.scalePercent(100); 
		   PdfContentByte content;
		   //给每页增加水印 
		   for (int i = 1; i < pageCount; i++) { 
			   content = pdfStamper.getOverContent(i);
			   System.out.println(content.getXTLM());
			   System.out.println();
	            // 添加图片 
			   image.setAbsolutePosition(0, 500);
              content.addImage(image);
              image.setAbsolutePosition(300, 500);
              content.addImage(image);
	           } 
           pdfStamper.close();
		   return true; 
		} 
	 
	
	public static void main(String[] args) throws IOException, DocumentException{
		/*File file = new File("F://test/original.pdf");
		File[] dd = file.listFiles();
		for (int i = 0; i < dd.length; i++) {
			File _file = new File(String.valueOf(dd[i]));
			if (_file.isFile()) {
				int lastlength = Integer.parseInt(String.valueOf(_file.getName().length()));
				String dds = _file.getName().substring(_file.getName().lastIndexOf(".")+1, lastlength);
				int length_v = Integer.parseInt(String.valueOf(_file.getPath().length()));
				String ddss = _file.getPath().substring(_file.getName().lastIndexOf("/")+1, length_v);
				
				if(StringUtils.equalsIgnoreCase(dds.toLowerCase(), "pdf")){
					try {*/
						new PDFWaterMark().createPDFWaterMark("D://test/original.pdf", "D://test/test.pdf", "D://test/yyyy.png");
//					    System.out.println("第"+(i+1)+"个PDF文件"+_file.getName()+"加载水印成功");
					/*} catch (IOException e) {
						e.printStackTrace();
					} catch (DocumentException e) {
						e.printStackTrace();
					}
				}
				
			}
		}*/
		
	}
}
