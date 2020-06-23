package com.axel.learn.chapter4;

import java.util.concurrent.atomic.AtomicLong;

/**
 * des
 *
 * @author chenzhaohui
 * @date 2020/6/23
 */
public class Atomic {
	public static void main(String[] args) throws InterruptedException {
		final AtomicLong count = new AtomicLong(0);
		final int[] arrayOne = new int[]{0, 1, 2, 3, 0, 5, 6, 0, 56, 0};
		final int[] arrayTwo = new int[]{10, 1, 2, 3, 0, 5, 6, 0, 56, 0};
		Thread threadA = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i : arrayOne) {
					if (0 == i) {
						count.addAndGet(1);
					}
				}
			}
		});
		Thread threadB = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i : arrayTwo) {
					if (0 == i) {
						count.addAndGet(1);
					}
				}
			}
		});

		threadA.start();
		threadB.start();
		threadA.join();
		threadB.join();
		System.out.println("count->" + count.get());
	}
}
