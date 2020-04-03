package com.axel.jvm.load;

/**
 * des
 *
 * @author chenzhaohui
 * @date 2020/4/3
 */
public class SuperClass {
	static {
		System.out.println("super class init.");
	}

	public static int value = 123;
}
