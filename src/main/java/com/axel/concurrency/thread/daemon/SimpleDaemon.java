package com.axel.concurrency.thread.daemon;

import java.util.concurrent.TimeUnit;

/**
 * des
 *
 * @author chenzhaohui
 * @date 2019/9/11
 */
public class SimpleDaemon implements Runnable {
	@Override
	public void run() {
		try {
			TimeUnit.MICROSECONDS.sleep(100);
			System.out.println(Thread.currentThread() + " " + this);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		for (int i = 0; i < 10; i++) {
			Thread daemon = new Thread(new SimpleDaemon());
			daemon.setDaemon(true);
			daemon.start();
		}
		System.out.println("all daemon threads start");
		try {
			TimeUnit.MICROSECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
