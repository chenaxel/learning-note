package com.axel.learn.wait;

/**
 * des
 *
 * @author chenzhaohui
 * @date 2020/6/19
 */
public class WaitNotifyInterrupt {
	static Object resource = new Object();

	public static void main(String[] args) throws InterruptedException {

		Thread threadA = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					System.out.println("---begin---");
					synchronized (resource) {
						resource.wait(100);
					}
					System.out.println("---end---");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});

		threadA.start();
		Thread.sleep(1000);
	}
}
