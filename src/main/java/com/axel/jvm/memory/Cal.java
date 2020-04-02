package com.axel.jvm.memory;

import org.openjdk.jol.info.ClassLayout;

/**
 * des
 *
 * @author chenzhaohui
 * @date 2020/4/1
 */
public class Cal {
	public static void main(String[] args) {
		System.out.println(ClassLayout.parseClass(FieldTest.class).toPrintable());
	}
}
class FieldTest {
	byte a;
	int c;
	boolean d;
	long e;
	Object f;
}
