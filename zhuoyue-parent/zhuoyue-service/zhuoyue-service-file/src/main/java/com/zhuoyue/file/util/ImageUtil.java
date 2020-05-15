package com.zhuoyue.file.util;

import org.csource.common.Base64;

import java.io.IOException;

public class ImageUtil {
    private static final String PRE = "data:image/";
    private static final String SUF = ";base64,";

    public static String base64(byte[] bytes, String ext) throws IOException {
        if (ext.startsWith(".")) {
            ext = ext.substring(ext.indexOf('.') + 1);
        }
        Base64 base64 = new Base64();
        String encode = base64.encode(bytes);
        return PRE + ext + SUF + encode;
    }
}
