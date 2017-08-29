package com.tumbleweed.test.yuntongxun.test.ecfile;

import it.sauronsoftware.jave.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

/**
 * Created by mylover on 11/2/15.
 */
public class FileTest implements Runnable {

    public Logger logger = LogManager.getLogger(FileTest.class);

    @Test
    public void fileChange() {

        String filePathAmr = "src/yun/tong/xun/file/1343b.amr";
        String filePathMp3 = "src/yun/tong/xun/file/1343b.mp3";

        this.changeToMp3(filePathAmr, filePathMp3);

    }


    /**
     * 获取图片后缀名
     *
     * @param fileName
     *            文件名
     * @return
     */
    public static String getImageSuffix(String fileName) {
        String suffix = "jpg";
        if (fileName != null && fileName.trim().length() > 0) {
            char dot = '.';
            int beginIndex = fileName.lastIndexOf(dot) + 1;
            suffix = fileName.substring(beginIndex);
        }
        return suffix;
    }

    public static void changeToMp3(String sourcePath, String targetPath) {
        File source = new File(sourcePath);
        File target = new File(targetPath);
        AudioAttributes audio = new AudioAttributes();
        Encoder encoder = new Encoder();

        audio.setCodec("libmp3lame");
        EncodingAttributes attrs = new EncodingAttributes();
        attrs.setFormat("mp3");
        attrs.setAudioAttributes(audio);

        try {
            encoder.encode(source, target, attrs);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InputFormatException e) {
            e.printStackTrace();
        } catch (EncoderException e) {
            e.printStackTrace();
        }
    }

    private static BufferedImage getImage(String srcImgPath) {
        long t1 = System.currentTimeMillis();
//        Image image = new ImageIcon(srcImgPath).getImage();
        Image image = Toolkit.getDefaultToolkit().getImage(srcImgPath);
        if (image instanceof BufferedImage) {
            return (BufferedImage)image;
        }
        image = new ImageIcon(image).getImage();
        BufferedImage bimage = null;
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        try {
            int transparency = Transparency.OPAQUE;
            GraphicsDevice gs = ge.getDefaultScreenDevice();
            GraphicsConfiguration gc = gs.getDefaultConfiguration();
            bimage = gc.createCompatibleImage(image.getWidth(null), image.getHeight(null), transparency);
        } catch (HeadlessException e) {
            System.out.print(e);
        }
        if (bimage == null) {
            int type = BufferedImage.TYPE_INT_RGB;
            bimage = new BufferedImage(image.getWidth(null), image.getHeight(null), type);
        }
        Graphics g = bimage.createGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();
        System.out.println(System.currentTimeMillis() - t1);
        return null;
    }

    public void resize(int scalePix, String srcImgPath, String dstImgPath) {
        try {
            String imgSuffix = getImageSuffix(srcImgPath);
            File dstImgFile = new File(dstImgPath); // 缩略图文件
            BufferedImage srcImgBuf = getImage(srcImgPath);
            int width = srcImgBuf.getWidth();// 原图宽度
            int height = srcImgBuf.getHeight();// 原图高度
            // 是否需要缩放
            if (width > scalePix || height > scalePix) {
                int scaleWidth = scalePix;// 缩略图宽度
                int scaleHeight = (int) (height * scaleWidth / width);// 缩略图高度，以宽度为基准，等比例缩放图片
                if (scaleHeight > scalePix) {
                    scaleHeight = scalePix;
                    scaleWidth = (int) (width * scaleHeight / height);// 以高度为基准，等比例缩放图片
                }
                double sx = (double) scaleWidth / width;
                double sy = (double) scaleHeight / height;
                BufferedImage dstImgBuf = null;
                int type = srcImgBuf.getType();
                if (type == BufferedImage.TYPE_CUSTOM) {
                    ColorModel cm = srcImgBuf.getColorModel();
                    WritableRaster raster = cm.createCompatibleWritableRaster(
                            scaleWidth, scaleHeight);
                    boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
                    dstImgBuf = new BufferedImage(cm, raster,
                            isAlphaPremultiplied, null);
                } else {
                    dstImgBuf = new BufferedImage(scaleWidth, scaleHeight, type);
                    Graphics2D g = dstImgBuf.createGraphics();
                    g.setRenderingHint(RenderingHints.KEY_RENDERING,
                            RenderingHints.VALUE_RENDER_QUALITY);
                    g.drawRenderedImage(srcImgBuf, AffineTransform
                            .getScaleInstance(sx, sy));
                    g.dispose();
                }
                String imgType = "jpeg";
                if ("png".equalsIgnoreCase(imgSuffix)) {
                    imgType = "png";
                }
                ImageIO.write(dstImgBuf, imgType, dstImgFile);
            }
        } catch (IOException e) {
            logger.info(e);
        }
    }

    public void run() {
        this.resize(100, "src/yun/tong/xun/file/69DA63EA82EF0F2DB8BEAE67D2C07394.jpg", "src/yun/tong/xun/file/69DA63EA82EF0F2DB8BEAE67D2C07394.png");
    }

    @Test
    public void testRun () {
        for (int i = 0; i < 1; i++) {
            run();
        }
    }
}
