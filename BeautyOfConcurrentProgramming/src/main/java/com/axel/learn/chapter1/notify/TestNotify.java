package com.axel.learn.chapter1.notify;

/**
 * des
 *
 * @author chenzhaohui
 * @date 2020/6/19
 */
public class TestNotify {
	static Object resource = new Object();

	public static void main(String[] args) throws InterruptedException {
		Thread threadA = new Thread(new Runnable() {
			@Override
			public void run() {
				synchronized (resource) {
					System.out.println("threadA get lock");
					try {
						resource.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("threadA end");
				}
			}
		});

		Thread threadB = new Thread(new Runnable() {
			@Override
			public void run() {
				synchronized (resource) {
					System.out.println("threadB get lock");
					try {
						resource.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("threadB end");
				}
			}
		});

		Thread threadC = new Thread(new Runnable() {
			@Override
			public void run() {
				synchronized (resource) {
					System.out.println("threadC notify...");
					resource.notifyAll();
				}
			}
		});

		threadA.start();
		threadB.start();
		threadC.start();

		threadA.join();
		threadB.join();
		threadC.join();
		System.out.println("main over");
	}
}