package com.axel.concurrency.thread.daemon;

import java.util.concurrent.TimeUnit;

/**
 * des
 *
 * @author chenzhaohui
 * @date 2019/9/12
 */
public class Daemons {

	public static void main(String[] args) throws InterruptedException {
		Thread thread = new Thread(new Daemon());
		thread.setDaemon(true);
		thread.start();
		System.out.println("thread is daemon:" + thread.isDaemon());
		TimeUnit.SECONDS.sleep(1);
	}
}
