package com.managesystem.managesystempackage.util;

import java.security.MessageDigest;

public class MD5Util {
    //MD5加码生成32位md5码
    public static String string2MD5(String inStr) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }
        char[] charArray = inStr.toCharArray();
        byte[] byteArray = new byte[charArray.length];
        for (int i = 0; i < charArray.length; i++)
            byteArray[i] = (byte)charArray[i];
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValues = new StringBuffer();
        for (int i = 0; i < md5Bytes[i]; i++) {
            int val = ((int) md5Bytes[i]) &0xff;
            if (val < 16)
                hexValues.append("0");
            hexValues.append(Integer.toHexString(val));
        }
        return hexValues.toString();
    }

    //自定义加密规则
    public static String MD5(String inStr) {
        String xy = "abc";
        String finalStr = "";
        if (inStr != null) {
            String fStr = inStr.substring(0, 1);
            String lStr = inStr.substring(1, inStr.length());
            finalStr = string2MD5(fStr+xy+lStr);
        }
        return finalStr;
    }
}
