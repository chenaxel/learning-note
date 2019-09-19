package com.axel.concurrency.thread.atomic;

/**
 * des
 *
 * @author chenzhaohui
 * @date 2019/9/19
 */
public class SerialNumberGenerator {
	private static volatile int serialNumber = 0;

	public static int nextSerialNumber() {
		return serialNumber++;
	}
}
