package com.axel.learn.sleep;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * des
 *
 * @author chenzhaohui
 * @date 2020/6/19
 */
public class TestSleep {
	private static final Lock lock = new ReentrantLock();

	public static void main(String[] args) throws InterruptedException {
		Thread threadA = new Thread(new Runnable() {
			@Override
			public void run() {
				lock.lock();
				System.out.println(" threadA get lock");
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					System.out.println("threadA release lock");
					lock.unlock();
				}
			}
		});

		Thread threadB = new Thread(new Runnable() {
			@Override
			public void run() {
				lock.lock();
				System.out.println(" threadB get lock");
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					System.out.println("threadB release lock");
					lock.unlock();
				}
			}
		});

		threadA.start();
		threadB.start();
//		threadA.interrupt();

		threadA.join();
		threadB.join();
	}
}