package com.axel.concurrency.thread;

/**
 * des
 *
 * @author chenzhaohui
 * @date 2019/9/10
 */
public class BasicThreads {

	public static void main(String[] args) {
		Thread t = new Thread(new LiftOff());
		t.start();
		System.out.println("waiting lift off.");
	}
}