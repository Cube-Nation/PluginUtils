package de.cubenation.plugins.utils.chatapi;

import java.io.UnsupportedEncodingException;

public class UTF8Converter {
    public static String convert(String src) {
        try {
            return new String(src.getBytes("ISO-8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
        }
        return src;
    }
}
