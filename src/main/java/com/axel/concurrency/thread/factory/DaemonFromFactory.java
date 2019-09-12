package com.axel.concurrency.thread.factory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * des
 *
 * @author chenzhaohui
 * @date 2019/9/12
 */
public class DaemonFromFactory implements Runnable {
	@Override
	public void run() {
		try {
			while (true) {
				TimeUnit.MILLISECONDS.sleep(100);
				System.out.println(Thread.currentThread() + " " + this);
			}
		} catch (InterruptedException e) {
			System.out.println("Interrupted");
		}
	}

	public static void main(String[] args) throws InterruptedException {
		ExecutorService executorService = Executors.newCachedThreadPool(new DaemonThreadFactory());
		for (int i = 0; i < 10; i++) {
			executorService.execute(new DaemonFromFactory());
		}
		System.out.println("all daemon thread started");
		TimeUnit.MICROSECONDS.sleep(500);
	}
}
