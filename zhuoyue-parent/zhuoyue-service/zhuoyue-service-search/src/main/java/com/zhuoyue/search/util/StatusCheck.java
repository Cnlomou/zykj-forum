package com.zhuoyue.search.util;

import entity.StatusCode;

/**
 * @author Linmo
 * @create 2020/4/19 11:47
 */
public class StatusCheck {
    public static void checkOk(int status, String msg) {
        if (status != StatusCode.OK)
            throw new IllegalStateException(msg);
    }
}
