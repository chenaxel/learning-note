package com.axel.jvm.allocate;

/**
 * des
 *
 * @author chenzhaohui
 * @date 2020/3/25
 */
public class Allocation {

	private static final int _1MB = 1024 * 1024;

	public static void testAllocation() {

		byte[] allocation1, allocation2, allocation3, allocation4;
		allocation1 = new byte[2 * _1MB];
		allocation2 = new byte[2 * _1MB];
		allocation3 = new byte[2 * _1MB];
		allocation4 = new byte[4 * _1MB];
	}

	public static void testPretenureSizeThreshold() {
		byte[] allocation;
		allocation = new byte[4 * _1MB];
	}

	public static void main(String[] args) {

		testTenuringThreshold();
	}

	public static void testTenuringThreshold() {
		byte[] allocate1, allocate2, allocate3;
		allocate1 = new byte[_1MB / 4];
		allocate2 = new byte[_1MB * 4];
		allocate3 = new byte[_1MB * 4];
		allocate3 = null;
		allocate3 = new byte[_1MB * 4];
	}
}
