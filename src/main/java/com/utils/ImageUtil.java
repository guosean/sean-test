package com.utils;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.security.auth.login.Configuration;

import org.apache.avalon.framework.configuration.DefaultConfiguration;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.krysalis.barcode4j.BarcodeGenerator;
import org.krysalis.barcode4j.BarcodeUtil;
import org.krysalis.barcode4j.tools.MimeTypes;

import com.img.RGBmapCanvasProvider;

public class ImageUtil {
	/**
	 * png格式
	 */
	public final static String FORMAT_PNG = "PNG";
	/**
	 * 获取图片尺寸信息
	 * @param imageName 图片名称
	 * @return 图片尺寸信息
	 * @throws IOException 
	 */
	public static Dimension getDim4Image(final String imageName) throws IOException{
		Dimension dim = new Dimension();
		Image img = ImageIO.read(new File(imageName));
		if(img!=null){
			dim.setSize(img.getWidth(null),img.getHeight(null));
		}
		return dim;
	}
	
	private static Logger log=Logger.getLogger(ImageUtil.class);
	/**
	 * 
	 * @Title: createWaterMarkImg
	 * @Description: 根据指定条形码生成水印图片
	 * @param barCode 条形码
	 * @param transparent 透明度
	 * @throws Throwable 
	 */
	public static void createWaterMarkImg(final String barCode,final int transparent,final String imgName) throws Throwable{
		ByteArrayOutputStream bout = new ByteArrayOutputStream(4096);
		try {
			if(StringUtils.isEmpty(barCode)){
				throw new Exception("条形码信息不能为空");
			}
			String format = MimeTypes.MIME_PNG;
			float transparentF = !Float.isNaN(transparent)?transparent:WaterMarkConstants.BARCODE_DEFAULT_TRANSPARENT;
//			Configuration cfg = buildCfg();
//			BarcodeGenerator gen = BarcodeUtil.getInstance().createBarcodeGenerator(cfg);
			try {
				int resolution =WaterMarkConstants.BARCODE_DEFAULT_RESOLUTION;
				RGBmapCanvasProvider bitmap = (new RGBmapCanvasProvider(bout,
						format, resolution, BufferedImage.TYPE_INT_RGB, false,
						0,transparentF));
//				gen.generateBarcode(bitmap, barCode);
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
	/**
	 * 设置源图片为背景透明，并设置透明度
	 * @param srcFile 源图片
	 * @param desFile 目标文件
	 * @param alpha 透明度
	 * @param formatName 文件格式
	 * @throws IOException
	 */
	public static void transparentImage(String srcFile,String desFile,int alpha,String formatName) throws IOException{
    	BufferedImage temp =  ImageIO.read(new File(srcFile));//取得图片
        transparentImage(temp, desFile, alpha, formatName);
    }
	/**
	 * 设置源图片为背景透明，并设置透明度
	 * @param srcImage 源图片
	 * @param desFile 目标文件
	 * @param alpha 透明度
	 * @param formatName 文件格式
	 * @throws IOException
	 */
	public static void transparentImage(BufferedImage srcImage,
			String desFile, int alpha, String formatName) throws IOException {
		int imgHeight = srcImage.getHeight();//取得图片的长和宽
        int imgWidth = srcImage.getWidth();
        int c = srcImage.getRGB(3, 3);
        //防止越位
        if (alpha < 0) {
            alpha = 0;
         } else if (alpha > 10) {
            alpha = 10;
         }
        BufferedImage bi = new BufferedImage(imgWidth, imgHeight,
                BufferedImage.TYPE_4BYTE_ABGR);//新建一个类型支持透明的BufferedImage
        for(int i = 0; i < imgWidth; ++i)//把原图片的内容复制到新的图片，同时把背景设为透明
        {
            for(int j = 0; j < imgHeight; ++j)
            {
            	//把背景设为透明
                if(srcImage.getRGB(i, j) == c){
                    bi.setRGB(i, j, c & 0x00ffffff);
                }
                //设置透明度
                else{
                	int rgb = bi.getRGB(i, j);
                    rgb = ((alpha * 255 / 10) << 24) | (rgb & 0x00ffffff);
                    bi.setRGB(i, j, rgb);
                }
            }
        }
		ImageIO.write(bi, StringUtils.isEmpty(formatName)?FORMAT_PNG:formatName, new File(desFile));
	}
/*	private static Configuration buildCfg() {
//		DefaultConfiguration cfg = new DefaultConfiguration("barcode");
		DefaultConfiguration child = new DefaultConfiguration(
				WaterMarkConstants.BARCODE_TYPE_CODE128);
		cfg.addChild(child);
		return cfg;
	}
	*/
}
