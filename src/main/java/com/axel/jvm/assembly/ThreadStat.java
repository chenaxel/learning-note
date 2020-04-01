package com.axel.jvm.assembly;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * des
 *
 * @author chenzhaohui
 * @date 2020/4/1
 */
@Slf4j
public class ThreadStat {

	public static void createBusyThread() {
		Thread thread = new Thread(() -> {
			while (true) {
			}
		}, "testBusyThread");
		thread.start();
	}

	public static void createLockThread(final Object lock) {
		Thread thread = new Thread(() -> {
			synchronized (lock) {
				try {
					lock.wait();
				} catch (InterruptedException e) {
					log.info("", e);
				}
			}
		}, "testLockThread");
		thread.start();
	}

	static class SynAddRunnable implements Runnable {
		int a, b;

		public SynAddRunnable(int a, int b) {
			this.a = a;
			this.b = b;
		}

		@Override
		public void run() {
			synchronized (Integer.valueOf(a)) {
				synchronized (Integer.valueOf(b)) {
					System.out.println(a + b);
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		br.readLine();
//		createBusyThread();
//		br.readLine();
//		Object obj = new Object();
//		createLockThread(obj);
		for (int i = 0; i < 100; i++) {
			new Thread(new SynAddRunnable(1, 2)).start();
			new Thread(new SynAddRunnable(2, 1)).start();
		}
	}
}
