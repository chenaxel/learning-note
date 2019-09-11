package com.axel.concurrency.thread;

/**
 * des
 *
 * @author chenzhaohui
 * @date 2019/9/11
 */
public class ThreadStatic implements Runnable {

	public static int num;

	public void run() {

		num = 3;
		System.out.println("thread name :" + Thread.currentThread().getName() + " num: " + num);
		num = 5;
		System.out.println("thread name :" + Thread.currentThread().getName() + " num: " + num * 2);
	}
}
