package com.zhuoyue.web.util;

import entity.StatusCode;

/**
 * @author Linmo
 * @create 2020/4/23 18:13
 */
public class StatusUtil {
    public static void notOk(int code, String message) {
        if (code != StatusCode.OK)
            throw new IllegalStateException(message);
    }
}
