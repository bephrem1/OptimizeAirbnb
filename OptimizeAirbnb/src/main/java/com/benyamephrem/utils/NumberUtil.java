package com.benyamephrem.utils;

public class NumberUtil {

    public NumberUtil() {
    }

    public static double roundHundredths(double n){
        return Math.round(n * 100.0) / 100.0;
    }
}
