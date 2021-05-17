package com.maveProject.mave.util;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;

public class MD5Generator {

    private String result;

    public MD5Generator(String input) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        LocalDateTime now = LocalDateTime.now();
        String fileName = input + now.toString();
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(fileName.getBytes(StandardCharsets.UTF_8));
        byte[] md5Hash = md5.digest();
        StringBuilder hexMD5Hash = new StringBuilder();
        for (byte b : md5Hash) {
            String hexString = String.format("%02x", b);
            hexMD5Hash.append(hexString);
        }
        result = hexMD5Hash.toString();
    }

    public String toString(){
        return result;
    }
}
