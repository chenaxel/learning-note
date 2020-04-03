package com.axel.jvm.load;

/**
 * des
 *
 * @author chenzhaohui
 * @date 2020/4/3
 */
public class ChildClass extends SuperClass {
	static {
		System.out.println("child class init.");
	}
}
