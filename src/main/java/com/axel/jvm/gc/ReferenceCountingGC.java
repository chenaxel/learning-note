package com.axel.jvm.gc;

/**
 * des
 *
 * @author chenzhaohui
 * @date 2020/3/23
 */
public class ReferenceCountingGC {

	private Object instance = null;
	private static int _1MB = 1024 * 1024;

	private byte[] bigByte = new byte[2 * _1MB];

	public static void main(String[] args) {
		ReferenceCountingGC referenceA = new ReferenceCountingGC();
		ReferenceCountingGC referenceB = new ReferenceCountingGC();
		referenceA.instance = referenceB;
		referenceB.instance = referenceA;
		referenceA = null;
		referenceB = null;
		System.gc();
	}
}
