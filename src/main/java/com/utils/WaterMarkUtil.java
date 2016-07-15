/*package com.utils;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.security.auth.login.Configuration;

import org.apache.avalon.framework.configuration.DefaultConfiguration;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.krysalis.barcode4j.BarcodeGenerator;
import org.krysalis.barcode4j.BarcodeUtil;
import org.krysalis.barcode4j.tools.MimeTypes;

import com.img.RGBmapCanvasProvider;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;

public class WaterMarkUtil {
	static Logger log = Logger.getLogger(WaterMarkUtil.class);
	*//**
	 * 
	 * @Title: createWaterMark2PDF
	 * @Description: 针对PDF添加水印图片
	 * @param srcFilePath 源文件
	 * @param savePath 保存文件
	 * @param waterMarkPath 水印图片
	 * @return
	 * @throws IOException
	 * @throws DocumentException
	 *//*
	public static void createWaterMark2PDF(String srcFilePath, String savePath, String waterMarkPath) throws IOException, DocumentException{
		createWaterMarkSpread2PDF(srcFilePath, savePath, waterMarkPath,Float.NaN,Float.NaN);
		} 
	*//**
	 * 
	 * @Title: createWaterMarkSpread2PDF
	 * @Description: 针对PDF添加水印图片，并根据间隙计算，平铺
	 * @param srcFilePath 源文件
	 * @param savePath 保存文件
	 * @param waterMarkPath 水印图片
	 * @param spaceX 横向间隙
	 * @param spaceY 纵向间隙
	 * @return
	 * @throws IOException
	 * @throws DocumentException
	 *//*
	public static void createWaterMarkSpread2PDF(String srcFilePath, String savePath, String waterMarkPath,float spaceX,float spaceY) throws IOException, DocumentException{
		PdfStamper pdfStamper = null;  
		try{ 
		  PdfReader pdfReader = new PdfReader(srcFilePath);
		   int pageCount = pdfReader.getNumberOfPages()+1;//获取pdf页码
		   pdfStamper = new PdfStamper(pdfReader, new FileOutputStream(savePath));; 
		   Image image = Image.getInstance(waterMarkPath); 
		   Rectangle size = pdfReader.getPageSize(1);
		   float pdfY = size.top();
		   float pdfX = size.right();
		   Dimension dim = ImageUtil.getDim4Image(waterMarkPath);
		PageSpreadSize pss = getPageSpreadSize(size, dim, spaceX, spaceY);
		   PdfContentByte content;
		   //给每页增加水印 
		   for (int i = 1; i < pageCount; i++) { 
			   content = pdfStamper.getOverContent(i);
			   float posY = pdfY;//Y坐标绝对路径
			   //若为单行、单列，则居中显示
			   if(pss.getSizeX()==PageSpreadSize.Default && pss.getSizeY()==PageSpreadSize.Default){
				   image.setAbsolutePosition((float)(pdfX/2-dim.getWidth()/2), (float)(pdfY/2-dim.getHeight()/2));
				   content.addImage(image);
			   }
			   else{
			   //行
			   for(int pi=1; pi<=pss.getSizeY(); pi++){
				   posY = pdfY- pi*((float)dim.getHeight()+(spaceY<=0?0:spaceY/2));
				   float posX=0f; //X坐标绝对路径
				   //列
				   for(int py=1; py<=pss.getSizeX(); py++){
					   posX = (py-1)*((float)dim.getWidth()+(spaceX<=0?0:spaceX/2));
					   image.setAbsolutePosition(posX, posY);
					   content.addImage(image); 
				   }
			   }
			   }
	           } 
		  }finally{
			  if(pdfStamper!=null){
				  pdfStamper.close();
			  }
		  }
		}
	*//**
	 * 获取图片平铺的行、列数信息
	 * @param pdfRec
	 * @param imgDim
	 * @param spaceX
	 * @param spaceY
	 * @return
	 *//*
	public static PageSpreadSize getPageSpreadSize(Rectangle pdfRec, Dimension imgDim,float spaceX, float spaceY){
		PageSpreadSize pss = PageSpreadSize.getInstance();
		 if(pdfRec!=null && imgDim!=null && !Float.isNaN(spaceX) && !Float.isNaN(spaceY)){
			 float pdfY = pdfRec.top();
			 float pdfX = pdfRec.right();
			 float imgY = (float) (imgDim.getHeight()+(spaceY<=0?0:spaceY/2));
			 float imgX = (float) (imgDim.getWidth()+(spaceX<=0?0:spaceX/2));
			 if(pdfY>imgY && pdfX>imgX && (imgY>0 && imgX>0)){
				 pss.setSizeX((int) (pdfX/imgX));
				 pss.setSizeY((int)(pdfY/imgY));
			 }
		 }
		 return pss;
	}
	
	*//**
	 * 
	 * @Title: createWaterMarkImg
	 * @Description: 根据指定条形码生成水印图片
	 * @param barCode 条形码
	 * @param transparent 透明度
	 * @throws Throwable 
	 *//*
	public static void createWaterMarkImg(final String barCode,final int transparent,final String imgName) throws Throwable{
		ByteArrayOutputStream bout = new ByteArrayOutputStream(4096);
		try {
			if(StringUtils.isEmpty(barCode)){
				throw new Exception("条形码信息不能为空");
			}
			String format = MimeTypes.MIME_PNG;
			float transparentF = !Float.isNaN(transparent)?transparent:WaterMarkConstants.BARCODE_DEFAULT_TRANSPARENT;
			Configuration cfg = buildCfg();
			BarcodeGenerator gen = BarcodeUtil.getInstance().createBarcodeGenerator(cfg);
			try {
				int resolution =WaterMarkConstants.BARCODE_DEFAULT_RESOLUTION;
				RGBmapCanvasProvider bitmap = (new RGBmapCanvasProvider(bout,
						format, resolution, BufferedImage.TYPE_INT_RGB, false,
						0,transparentF));
				gen.generateBarcode(bitmap, barCode);
				bitmap.finish();
				ImageUtil.transparentImage(bitmap.getBufferedImage(), imgName, transparent, ImageUtil.FORMAT_PNG);
			} finally {
				bout.close();
			}
		} catch (Exception e) {
			log.error("create code error", e);
			throw e;
		} catch (Throwable t) {
			log.error("create code error", t);
			throw t;
		}
	}
	private static Configuration buildCfg() {
		DefaultConfiguration cfg = new DefaultConfiguration("barcode");
		DefaultConfiguration child = new DefaultConfiguration(
				WaterMarkConstants.BARCODE_TYPE_CODE128);
		cfg.addChild(child);
		return cfg;
	}
	public static void main(String[] args) throws Throwable{
		String genBarCode = BarCodeGenerator.genBarCode();
		String imgName = "D://test/"+genBarCode+".png";
		createWaterMarkImg(genBarCode, 1, imgName);
		createWaterMark2PDF("D://test/original.pdf", "D://test/abc.pdf", "D://test/yyyy.png");//,Float.NaN,Float.NaN
		File file = new File("D://test/abc.pdf");
		File f2 = new File("D://test/original.pdf");
		if(f2.exists()){
			f2.delete();
		}
		System.out.println(file.renameTo(f2));
	}

}
*//**
 * 页面平铺信息 包含行、列数
 * @author guozb
 *
 *//*
class PageSpreadSize{
	public final static int Default = 1;
	*//**
	 * X方向平铺列数
	 *//*
	private int sizeX;
	*//**
	 * Y方向平铺行数
	 *//*
	private int sizeY;
	*//**
	 * 默认值为1
	 *//*
	public PageSpreadSize() {
		this.sizeX = Default;
		this.sizeY = Default;
	}
	
	public PageSpreadSize(int sizeX,int sizeY){
		this.sizeX = sizeX;
		this.sizeY = sizeY;
	}
	
	public int getSizeX() {
		return sizeX;
	}
	public void setSizeX(int sizeX) {
		this.sizeX = sizeX;
	}
	public int getSizeY() {
		return sizeY;
	}
	public void setSizeY(int sizeY) {
		this.sizeY = sizeY;
	}
	*//**
	 * 获取尺寸对象，默认值为1
	 * @return
	 *//*
	public static PageSpreadSize getInstance(){
		return new PageSpreadSize();
	}
}

*/