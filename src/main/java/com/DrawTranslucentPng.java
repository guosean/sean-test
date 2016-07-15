package com;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.img.MyImageObserver;
import com.utils.BarCodeGenerator;
import com.utils.ImageUtil;

public class DrawTranslucentPng {  
    public static BufferedImage drawTranslucentStringPic(int width, int height, Integer fontHeight,String drawStr)  
    {  
        try  
        {  
            BufferedImage buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);  
            Graphics2D gd = buffImg.createGraphics();  
            //设置透明  start  
            buffImg = gd.getDeviceConfiguration().createCompatibleImage(width, height, Transparency.TRANSLUCENT);  
            gd=buffImg.createGraphics();  
            //设置透明  end  
//            gd.setFont(new Font("微软雅黑", Font.PLAIN, fontHeight)); //设置字体  
//            gd.fillRect(0, 0, width, height);
            gd.setColor(Color.black); //设置颜色 
            gd.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, 1f));
           
//            gd.drawRect(0, 0, width - 1, height - 1); //画边框  
//            gd.drawString(drawStr, width/2-fontHeight*drawStr.length()/2,fontHeight); //输出文字（中文横向居中）
            BufferedImage img = ImageIO.read(new File("D://test/test1.png"));
            gd.drawImage(img, 0, 0, width, height, new MyImageObserver());
//            gd.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER)); 
            return buffImg;  
        } catch (Exception e) {  
            return null;  
        }  
    } 
    public static void transparentImage(String srcFile,String desFile,int alpha) throws IOException{
    	BufferedImage temp =  ImageIO.read(new File(srcFile));//取得图片
        int imgHeight = temp.getHeight();//取得图片的长和宽
        int imgWidth = temp.getWidth();
        int c = temp.getRGB(3, 3);
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
                if(temp.getRGB(i, j) == c){
                    bi.setRGB(i, j, c & 0x00ffffff);
                }//这里把背景设为透明
                else{
                	int rgb = bi.getRGB(i, j);
                    rgb = ((alpha * 255 / 10) << 24) | (rgb & 0x00ffffff);
                    bi.setRGB(i, j, rgb);
//                    bi.setRGB(i, j, temp.getRGB(i, j));
                }
                
            }
        }
        ImageIO.write(bi, "PNG", new File(desFile));
    }
      
    public static void main(String[] args) throws Throwable  
    {  
     /*   BufferedImage imgMap = drawTranslucentStringPic(400, 80, 36,"欢迎访问我的博客");  
        File imgFile=new File("D://test/ttt.png");  
        try  
        {  
            ImageIO.write(imgMap, "PNG", imgFile);  
        } catch (IOException e)  
        {  
            e.printStackTrace();  
        }  
        System.out.println("生成完成");  */
    	ImageUtil.createWaterMarkImg(BarCodeGenerator.genBarCode(), 1, "D://test/xxxx.png");
    	ImageUtil.transparentImage("D://test/test1.png","D://test/yyyy.png",1,ImageUtil.FORMAT_PNG);
//    	ChangeImageAlpha.setAlpha("D://test/yyyy.png", "D://test/yyyy-al.png", 2);
    }  
  
}  
