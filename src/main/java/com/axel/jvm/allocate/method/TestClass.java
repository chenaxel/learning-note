package com.axel.jvm.allocate.method;

/**
 * des
 *
 * @author chenzhaohui
 * @date 2020/4/7
 */
public class TestClass {

	public static void main(String[] args) {
		{
			byte[] placeholder = new byte[64 * 1024 * 1024];
		}
		int a = 0;
		System.gc();
	}

}
