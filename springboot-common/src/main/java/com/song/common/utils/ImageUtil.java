package com.song.common.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;

/**
 * 图片工具类
 *
 * @author song
 * @version 1.0
 * @date 2022/4/29 15:07
 */
public class ImageUtil {

    /**
     * @param targetImg
     * @param textColor
     * @param fontSize
     * @param text
     * @param num
     * @param name
     * @param outPath
     */
    public static void addTextWaterMark(BufferedImage targetImg, Color textColor, int fontSize, String text, String num, String name, String outPath) {
        try {
            //图片宽
            int width = targetImg.getWidth();
            //图片高
            int height = targetImg.getHeight();
            BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
            Graphics2D g = bufferedImage.createGraphics();
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.drawImage(targetImg, 0, 0, width, height, null);
            //水印颜色
            g.setColor(textColor);
            //最上面的       NO.
            g.setFont(new Font("微软雅黑", Font.PLAIN, fontSize));
            //获取文字长度
            int x = width - 1112;
            int y = height - 2036;
            myDrawString(text, x, y, 1.4, g);
            //最上面的编号   0001
            g.setFont(new Font("微软雅黑", Font.PLAIN, fontSize));
            x = width - 896;
            y = height - 2036;
            myDrawString(num, x, y, 1.2, g);

            //上面的名字
            fontSize = 200;
            g.setColor(Color.black);
            g.setFont(new Font("微软雅黑", Font.BOLD, fontSize));
            System.out.println("上面的名字为：" + name);
            int length = name.length();
            if (length == 2) {
                x = width - 1116;
                y = height - 1232;
                myDrawString(name, x, y, 1.5, g);
            } else if (length == 3) {
                x = width - 1182;
                y = height - 1232;
                myDrawString(name, x, y, 1.1, g);
            } else if (length == 4) {
                x = width - 1232;
                y = height - 1232;
                myDrawString(name, x, y, 1.0, g);
            }

            //下面名字
            g.setColor(Color.RED);
            g.rotate(Math.toRadians(-13), (double) width / 2, (double) height / 2);
            fontSize = 52;
            System.out.println("下面的名字为：" + name);
            if (length == 2) {
                x = width - 1442;
            } else if (length == 4) {
                fontSize = 49;
                x = width - 1487;
            } else if (length == 3) {
                x = width - 1462;
            }
            y = height - 1096;
            g.setFont(new Font("思源黑体", Font.PLAIN, fontSize));
            g.drawString(name, x, y);
            //下面编号
            String textNum = text + num;
            fontSize = 52;
            g.setFont(new Font("思源黑体", Font.PLAIN, fontSize));
            x = width - 1472;
            y = height - 1024;
            g.drawString(textNum, x, y);
            FileOutputStream outImgStream = new FileOutputStream(outPath);
            ImageIO.write(bufferedImage, "jpg", outImgStream);
            outImgStream.flush();
            outImgStream.close();
            g.dispose();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int len(Graphics2D g, String text) {
        //获取文字长度
        int len = g.getFontMetrics(
                g.getFont()).charsWidth(text.toCharArray(),
                0, text.length());
        return len;
    }

    public static void myDrawString(String str, int x, int y, double rate, Graphics2D g) {
        String tempStr;
        int orgStringWight = g.getFontMetrics().stringWidth(str);
        int orgStringLength = str.length();
        int tempx = x;
        int tempy = y;
        while (str.length() > 0) {
            tempStr = str.substring(0, 1);
            str = str.substring(1);
            g.drawString(tempStr, tempx, tempy);
            tempx = (int) (tempx + (double) orgStringWight / (double) orgStringLength * rate);
        }
    }

    public static void addTextWaterMark(BufferedImage targetImg, Color textColor, String text, String num, String name, String outPath) {
        try {
            //图片宽
            int width = targetImg.getWidth();
            //图片高
            int height = targetImg.getHeight();
            BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
            Graphics2D g = bufferedImage.createGraphics();
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.drawImage(targetImg, 0, 0, width, height, null);
            int fontSize = 110;
            //水印颜色
            g.setColor(textColor);
            //最上面的       NO.
            Font font = null;
            font = FontUtil.getDefinedFont(1, 110.0f, font, Consts.FONT_SHS_B);
            g.setFont(font);
            //获取文字长度
            int x = width - 1112;
            int y = height - 2036;
            myDrawString(text, x, y, 1.4, g);
            //最上面的编号   0001
            x = width - 896;
            y = height - 2036;
            myDrawString(num, x, y, 1.2, g);

            //上面的名字
            fontSize = 200;
            g.setFont(new Font("思源黑体", Font.PLAIN, fontSize));
            g.setColor(Color.black);
            System.out.println("上面的名字为：" + name);
            int length = name.length();
            if (length == 2) {
                x = width - 1116;
                y = height - 1232;
                myDrawString(name, x, y, 1.5, g);
            } else if (length == 3) {
                x = width - 1182;
                y = height - 1232;
                myDrawString(name, x, y, 1.1, g);
            } else if (length == 4) {
                x = width - 1232;
                y = height - 1232;
                myDrawString(name, x, y, 1.0, g);
            }

            //下面名字
            g.setColor(Color.RED);
            g.rotate(Math.toRadians(-13), (double) width / 2, (double) height / 2);
            fontSize = 52;
            System.out.println("下面的名字为：" + name);
            if (length == 2) {
                x = width - 1442;
            } else if (length == 4) {
                fontSize = 49;
                x = width - 1487;
            } else if (length == 3) {
                x = width - 1465;
            }
            y = height - 1095;
            g.setFont(new Font("思源黑体", Font.PLAIN, fontSize));
            g.drawString(name, x, y);
            //下面编号
            String textNum = text + num;
            font = FontUtil.getDefinedFont(1, 52.0f, font, Consts.FONT_SHS_P);
            g.setFont(font);
            x = width - 1475;
            y = height - 1025;
            g.drawString(textNum, x, y);
            FileOutputStream outImgStream = new FileOutputStream(outPath);
            ImageIO.write(bufferedImage, "jpg", outImgStream);
            outImgStream.flush();
            outImgStream.close();
            g.dispose();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addTextWaterMark(BufferedImage targetImg, Color textColor, Font font, Font font1, String text, String num, String name, String outPath) {
        try {
            //图片宽
            int width = targetImg.getWidth();
            //图片高
            int height = targetImg.getHeight();
            BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
            Graphics2D g = bufferedImage.createGraphics();
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.drawImage(targetImg, 0, 0, width, height, null);
            int fontSize = 110;
            //水印颜色
            g.setColor(textColor);
            //最上面的       NO.
            font = font.deriveFont(110.0f);
            g.setFont(font);
            int x = width - 1107;
            int y = height - 2036;
            myDrawString(text, x, y, 1.4, g);
            //最上面的编号   0001
            x = width - 891;
            y = height - 2036;
            myDrawString(num, x, y, 1.2, g);

            //上面的名字
            fontSize = 200;
            g.setFont(new Font("宋体", Font.BOLD, fontSize));
            g.setColor(Color.black);
            System.out.println("上面的名字为：" + name);
            int length = name.length();
            if (length == 2) {
                x = width - 1116;
                y = height - 1232;
                myDrawString(name, x, y, 1.5, g);
            } else if (length == 3) {
                x = width - 1182;
                y = height - 1232;
                myDrawString(name, x, y, 1.1, g);
            } else if (length == 4) {
                x = width - 1232;
                y = height - 1232;
                myDrawString(name, x, y, 1.0, g);
            }

            //下面名字
            g.setColor(Color.RED);
            g.rotate(Math.toRadians(-13), (double) width / 2, (double) height / 2);
            fontSize = 52;
            System.out.println("下面的名字为：" + name);
            if (length == 2) {
                x = width - 1442;
            } else if (length == 4) {
                fontSize = 49;
                x = width - 1487;
            } else if (length == 3) {
                x = width - 1462;
            }
            y = height - 1093;
            g.setFont(new Font("思源黑体", Font.PLAIN, fontSize));
            g.drawString(name, x, y);
            //下面编号
            String textNum = text + num;
            x = width - 1484;
            y = height - 1022;
            font1 = font1.deriveFont(52.0f);
            g.setFont(font1);
            g.drawString(textNum, x, y);
            FileOutputStream outImgStream = new FileOutputStream(outPath);
            ImageIO.write(bufferedImage, "jpg", outImgStream);
            outImgStream.flush();
            outImgStream.close();
            g.dispose();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
