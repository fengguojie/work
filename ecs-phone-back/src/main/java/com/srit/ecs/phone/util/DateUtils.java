package com.srit.ecs.phone.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    public static String getCurrentTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }
    
    /**
	 * 生成主键，生成结果为日期的“yyyyMMddhh24mmss”+“两位随机数”。
	 * @return
	 */
	public static Long generateNumberKey(){
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String time = formatter.format(currentTime);
		//第一位0-9的随机数
		int frst=(int)(Math.random()*10);
		//第二位0-9的随机数
		int two=(int)(Math.random()*10);
		Long numberkey=Long.parseLong(time + frst + two);
		return numberkey;
	}
}
