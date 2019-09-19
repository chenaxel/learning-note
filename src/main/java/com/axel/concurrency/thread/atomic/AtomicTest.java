package com.axel.concurrency.thread.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * des
 *
 * @author chenzhaohui
 * @date 2019/9/19
 */
public class AtomicTest implements Runnable {

	private int value = 0;

	public int getValue() {
		return value;
	}

	private synchronized void increment() {
		value++;
		value++;
	}

	@Override
	public void run() {
		while (true) {
			increment();
		}
	}

	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		AtomicTest at = new AtomicTest();
		exec.execute(at);
		while (true) {
			int val = at.getValue();
			if (val % 2 != 0) {
				System.out.println(val);
				System.exit(0);
			}
		}
	}
}
