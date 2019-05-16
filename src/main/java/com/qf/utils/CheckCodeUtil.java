package com.qf.utils;

import java.util.Random;

/** 
*
* @author xhc 
* @version May 13, 2019 9:15:33 PM 
*/
public class CheckCodeUtil {
	
	public static String getRandomCode(){
		Random random = new Random();
		StringBuilder builder = new StringBuilder();
		for(int i=0;i<4;i++){
			int code = random.nextInt(10);
			builder.append(code+"");
		}
		return builder.toString();
	}
}
