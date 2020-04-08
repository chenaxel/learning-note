package com.axel.jvm.invoke;

/**
 * des
 *
 * @author chenzhaohui
 * @date 2020/4/8
 */
public class StaticResolution {

	public static void sayHello() {
		System.out.println("hello");
	}

	public static void main(String[] args) {
		StaticResolution.sayHello();
	}
}