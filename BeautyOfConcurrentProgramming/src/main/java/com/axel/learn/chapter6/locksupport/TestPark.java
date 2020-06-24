package com.axel.learn.chapter6.locksupport;

import java.util.concurrent.locks.LockSupport;

/**
 * des
 *
 * @author chenzhaohui
 * @date 2020/6/24
 */
public class TestPark {

	public void testPark() {
		LockSupport.park(this);
	}
	public static void main(String[] args) {
		TestPark testPark = new TestPark();
		testPark.testPark();
	}
}
