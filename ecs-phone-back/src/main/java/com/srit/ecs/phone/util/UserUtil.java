package com.srit.ecs.phone.util;

import java.util.Random;

import org.apache.shiro.authc.SimpleAuthenticationInfo;

import com.sun.tools.javac.file.JavacFileManager.MissingArchive;

public class UserUtil {
	
	/*
	 * 生成随机字符串
	 */
    public static String getStringRandom(int length) {
        String val = "";
        Random random = new Random();
        for(int i = 0; i < length; i++) {
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            if( "char".equalsIgnoreCase(charOrNum) ) {
                int temp = 97;
                val += (char)(random.nextInt(26) + temp);
            } else if( "num".equalsIgnoreCase(charOrNum) ) {
                val += String.valueOf(random.nextInt(10));
            }
        }
        return val;
    }
    
    public static void main(String[] args) {
    	System.out.println(getStringRandom(8));
	}

}
