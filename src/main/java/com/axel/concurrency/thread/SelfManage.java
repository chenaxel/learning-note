package com.axel.concurrency.thread;

/**
 * des
 *
 * @author chenzhaohui
 * @date 2019/9/12
 */
public class SelfManage implements Runnable {

	private int countDown = 5;
	private Thread thread = new Thread(this);

	public SelfManage() {
		thread.start();
	}

	@Override
	public String toString() {
		return Thread.currentThread().getName() +
				"(" + countDown + "), ";
	}

	@Override
	public void run() {

		while (true) {
			System.out.println(this);
			if (--countDown == 0) {
				return;
			}
		}
	}

	public static void main(String[] args) {
		for (int i = 0; i < 5; i++) {
			new SelfManage();
		}
	}
}
