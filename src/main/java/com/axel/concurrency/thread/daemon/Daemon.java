package com.axel.concurrency.thread.daemon;

/**
 * des
 *
 * @author chenzhaohui
 * @date 2019/9/12
 */
public class Daemon implements Runnable {
	private Thread[] t = new Thread[10];

	@Override
	public void run() {

		for (int i = 0; i < 10; i++) {
			t[i] = new Thread(new DaemonSpawn());
			t[i].start();
			System.out.println("DaemonSpawn " + i + ":started");
		}
		for (int i = 0; i < t.length; i++) {
			System.out.println("DaemonSpawn " + i + ":" + t[i].isDaemon());
		}
		while (true) {
			Thread.yield();
		}
	}
}

class DaemonSpawn implements Runnable {
	@Override
	public void run() {
		while (true) {
			Thread.yield();
		}
	}
}
