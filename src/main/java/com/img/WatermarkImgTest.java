package com.img;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.security.auth.login.Configuration;

import org.apache.avalon.framework.configuration.DefaultConfiguration;
import org.apache.log4j.Logger;
import org.krysalis.barcode4j.BarcodeGenerator;
import org.krysalis.barcode4j.BarcodeUtil;
import org.krysalis.barcode4j.tools.MimeTypes;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfGState;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;
import com.lowagie.text.pdf.PdfWriter;
import com.utils.BarCodeGenerator;
import com.utils.ImageUtil;
import com.utils.WaterMarkConstants;

public class WatermarkImgTest {
	Logger log = Logger.getLogger(WatermarkImgTest.class);
	
	public void createBarcodeWaterMarkImg(String codeStr,int transparent,String imgName){
		ByteArrayOutputStream bout = new ByteArrayOutputStream(4096);
		try {
			String format = MimeTypes.MIME_PNG;
			float transparentF = !Float.isNaN(transparent)?transparent:WaterMarkConstants.BARCODE_DEFAULT_TRANSPARENT;
//			Configuration cfg = buildCfg();
//			BarcodeGenerator gen = BarcodeUtil.getInstance().createBarcodeGenerator(cfg);
			try {
				int resolution =WaterMarkConstants.BARCODE_DEFAULT_RESOLUTION;
				RGBmapCanvasProvider bitmap = new RGBmapCanvasProvider(bout,
						format, resolution, BufferedImage.TYPE_INT_RGB, false,
						0,transparentF);
			/*	BitmapCanvasProvider bitmap = new BitmapCanvasProvider(bout,
						imgName, 150, BufferedImage.TYPE_BYTE_GRAY,
						true, 0);*/
//				gen.generateBarcode(bitmap, codeStr);
				bitmap.finish();
//				ImageIO.write(bitmap.getBufferedImage(), "PNG", new File(imgName));
				ImageUtil.transparentImage(bitmap.getBufferedImage(), imgName, transparent, ImageUtil.FORMAT_PNG);
			} finally {
				bout.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("create code error", e);
		} catch (Throwable t) {
			t.printStackTrace();
			log.error("create code error", t);
		}
		
	}
	/*public void createImg(String path){
		FileUtils.writeByteFile(getByteArrayOs().toByteArray(), new File(path));
	}*/
/*	protected Configuration buildCfg() {
//			DefaultConfiguration cfg = new DefaultConfiguration("barcode");
			DefaultConfiguration child = new DefaultConfiguration(
					WaterMarkConstants.BARCODE_TYPE_CODE128);
			cfg.addChild(child);
			return cfg;
	}*/
	public static void main(String[] args) throws Exception{
		WatermarkImgTest test = new WatermarkImgTest();
		String genBarCode = BarCodeGenerator.genBarCode();
		String imgName = "D:\\test\\test1.png";
		test.createBarcodeWaterMarkImg(genBarCode, 1, imgName );
		/*String inFile = "D:\\test\\Hadoop权威指南.pdf";
		String outFile = "D:\\test\\test.pdf";
		test.addPdfMark(inFile, outFile, imgName, 10);*/
//		testAddMark();
	}
	
	public static void testAddMark() throws DocumentException, MalformedURLException, IOException{
		 Document document = new Document();
		        PdfWriter.getInstance(document,
		                new FileOutputStream("D:\\test\\test.pdf"));
		        document.open();
		        Image image1 = Image.getInstance("D:\\test\\test1.png");
		        document.add(image1);
		      /*  Image image2 = Image.getInstance("D:\\test\\test1.png");
		        document.add(image2);*/
		        document.close();
	}
	 
	public void addPdfMark(String inPdfFile,String outPdfFile,String markImagePath, int pageSize) throws Exception{
		PdfReader reader = new PdfReader(inPdfFile);
		PdfStamper stamp = new PdfStamper(reader, new FileOutputStream(outPdfFile));
		Image img = Image.getInstance(markImagePath);
		 /*img.setGrayFill(0.4f);
		 img.setAlignment(Image.ALIGN_TOP); */
		img.setAbsolutePosition(10, 480);
		PdfContentByte under;
		PdfGState gs = new PdfGState();
        gs.setFillOpacity(0.2f);		
		for(int i=1; i<=pageSize; i++){
			under = stamp.getOverContent(i);//stamp.getOverContent(i);
//			under.saveState();
			under.addImage(img);
			under.setGState(gs);
//			under.restoreState();
		}
		stamp.close();
		File tmpFile = new File(inPdfFile);
		if(tmpFile.exists()){
			tmpFile.delete();
		}
	}

}
