package com.axel.concurrency.thread.daemon;

import java.util.concurrent.TimeUnit;

/**
 * des
 *
 * @author chenzhaohui
 * @date 2019/9/12
 */
public class DaemonDontRunFinally {

	public static void main(String[] args) throws InterruptedException {
		Thread thread = new Thread(new ADaemon());
		thread.setDaemon(true);
		thread.start();
		TimeUnit.SECONDS.sleep(1);
	}
}

class ADaemon implements Runnable {
	@Override
	public void run() {
		try {
			System.out.println("starting daemon thread");
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			System.out.println("still run?");
		}
	}
}
