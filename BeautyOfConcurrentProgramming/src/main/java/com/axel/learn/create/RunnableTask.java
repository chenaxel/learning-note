package com.axel.learn.create;

/**
 * des
 *
 * @author chenzhaohui
 * @date 2020/6/18
 */
public class RunnableTask implements Runnable {

	@Override
	public void run() {
		System.out.println("runnable task ->" + Thread.currentThread().getName());
	}
}
