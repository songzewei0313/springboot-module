package com.song.common.utils;

import java.awt.*;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author szw
 * @date 2022/5/2 21:21
 */
public class FontUtil {

    private Font definedFont = null;

    public Font getDefinedFont(int ft, float fs, String fontUrl) {

        if (definedFont == null) {
            InputStream is = null;
            BufferedInputStream bis = null;
            try {
                is = new FileInputStream(fontUrl);
                bis = new BufferedInputStream(is);
                definedFont = Font.createFont(Font.TRUETYPE_FONT, is);
                //设置字体大小，float型
//                definedFont = definedFont.deriveFont(fs);
            } catch (FontFormatException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (null != bis) {
                        bis.close();
                    }
                    if (null != is) {
                        is.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return definedFont;
    }

    public static Font getDefinedFont(int ft, float fs, Font definedFont, String fontUrl) {

        if (definedFont == null) {
            InputStream is = null;
            BufferedInputStream bis = null;
            try {
                is = new FileInputStream(fontUrl);
                bis = new BufferedInputStream(is);
                definedFont = Font.createFont(Font.TRUETYPE_FONT, is);
                //设置字体大小，float型
//                definedFont = definedFont.deriveFont(fs);
            } catch (FontFormatException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (null != bis) {
                        bis.close();
                    }
                    if (null != is) {
                        is.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return definedFont;
    }


}
