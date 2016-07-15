package com.img;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.image.BufferedImage;

import org.krysalis.barcode4j.BarcodeDimension;
import org.krysalis.barcode4j.BarcodeGenerator;
import org.krysalis.barcode4j.output.java2d.Java2DCanvasProvider;
import org.krysalis.barcode4j.tools.UnitConv;

import com.utils.WaterMarkConstants;

public class RGBmapBuilder {
	
	 private static float transparent=WaterMarkConstants.BARCODE_DEFAULT_TRANSPARENT;

   /**
    * Utility class: Constructor prevents instantiating when subclassed.
    */
   protected RGBmapBuilder() {
       throw new UnsupportedOperationException();
   }
   
   /**
    * (non-javadoc)
    * 准备图像
    * @param dim the barcode dimensions
    * @param resolution the desired image resolution (dots per inch)
    * @param imageType the desired image type (Values: BufferedImage.TYPE_*)
    * @return the requested BufferedImage
    */
   public static BufferedImage prepareImage(BarcodeDimension dim,
                       int resolution, int imageType) {
       return prepareImage(dim, 0, resolution, imageType);
   }
   
   /**
    * (non-javadoc)
    * 准备图像
    * @param dim the barcode dimensions
    * @param orientation the barcode orientation (0, 90, 180, 270)
    * @param resolution the desired image resolution (dots per inch)
    * @param imageType the desired image type (Values: BufferedImage.TYPE_*)
    * @return the requested BufferedImage
    */
   public static BufferedImage prepareImage(BarcodeDimension dim,
                       int orientation,
                       int resolution, int imageType) {
       int bmw = UnitConv.mm2px(dim.getWidthPlusQuiet(orientation), resolution);
       int bmh = UnitConv.mm2px(dim.getHeightPlusQuiet(orientation), resolution);       
       BufferedImage bi = new BufferedImage(
               bmw,
               bmh,
               imageType);
       return bi;
   }

   /**
    * (non-javadoc)
    * 准备2d图像
    * @param image the BufferedImage instance
    * @param dim the barcode dimensions
    * @param orientation the barcode orientation (0, 90, 180, 270)
    * @param antiAlias true enables anti-aliasing
    * @return the Graphics2D object to paint on
    */
   public static Graphics2D prepareGraphics2D(BufferedImage image, 
               BarcodeDimension dim, int orientation,
               boolean antiAlias, float transparent) {
   	RGBmapBuilder.transparent=transparent;
       Graphics2D g2d = image.createGraphics();
       //设置背景透明 test guozb begin
     /*  image = g2d.getDeviceConfiguration().createCompatibleImage(image.getWidth(), image.getHeight(), Transparency.TRANSLUCENT);
       g2d = image.createGraphics();*/
       //设置背景透明 test guozb end
       if (antiAlias) {
           g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
               RenderingHints.VALUE_ANTIALIAS_ON);
           g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, 
                   RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
       }
       g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, 
           RenderingHints.VALUE_FRACTIONALMETRICS_ON);
       //yangh add
       //g2d.setColor(new Color(230,225,225));
//       g2d.fillRect(0, 0, image.getWidth(), image.getHeight());
       g2d.setColor(new Color(0, 0, 0));//
//       g2d.setColor(new Color(255,0,0));
       g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, transparent));
       //yangh end
       g2d.setBackground(Color.white); 
       g2d.clearRect(0, 0, image.getWidth(), image.getHeight());
       g2d.scale(image.getWidth() / dim.getWidthPlusQuiet(orientation), 
               image.getHeight() / dim.getHeightPlusQuiet(orientation));
       return g2d;
   }

   /**
    * (non-javadoc)
    * 得到一个codebar图像
    * @param bargen the BarcodeGenerator to use
    * @param msg the message to encode
    * @param resolution the desired image resolution (dots per inch)
    * @return the requested BufferedImage
    */
   public static BufferedImage getImage(BarcodeGenerator bargen, String msg, int resolution) {
       BarcodeDimension dim = bargen.calcDimensions(msg);
       BufferedImage bi = prepareImage(dim, resolution, BufferedImage.TYPE_BYTE_GRAY);
       int orientation = 0;
       Graphics2D g2d = prepareGraphics2D(bi, dim, orientation, true,transparent);
       Java2DCanvasProvider provider = new Java2DCanvasProvider(g2d, orientation);
       bargen.generateBarcode(provider, msg);
       bi.flush();
       return bi;
   }
   
  
}
