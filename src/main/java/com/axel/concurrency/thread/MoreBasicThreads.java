package com.axel.concurrency.thread;

/**
 * des
 *
 * @author chenzhaohui
 * @date 2019/9/10
 */
public class MoreBasicThreads {

	public static void main(String[] args) {
		for (int i = 0; i < 5; i++) {
			new Thread(new LiftOff()).start();
		}
		System.out.println("waiting for lifting off.");
	}
}
