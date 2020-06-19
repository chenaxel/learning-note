package com.axel.learn.chapter1.yeild;

/**
 * des
 *
 * @author chenzhaohui
 * @date 2020/6/19
 */
public class YieldTest implements Runnable {

	public YieldTest() {
		new Thread(this).start();
	}

	@Override
	public void run() {
		for (int i = 0; i < 5; i++) {
			if (i % 5 == 0) {
				System.out.println(Thread.currentThread().getName() + " yield cpu");
				Thread.yield();
			}
			System.out.println(Thread.currentThread().getName() + " complete");
		}
	}

	public static void main(String[] args) {
		new YieldTest();
		new YieldTest();
		new YieldTest();
	}
}
