package com.axel.jvm.load;

/**
 * des
 *
 * @author chenzhaohui
 * @date 2020/4/3
 */
public class ConstClass {
	static {
		System.out.println("const class init.");
	}
	public static final String hello = "hello world";
}
