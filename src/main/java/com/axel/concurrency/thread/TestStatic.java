package com.axel.concurrency.thread;


/**
 * des
 *
 * @author chenzhaohui
 * @date 2019/9/11
 */
public class TestStatic {

	public static void main(String[] args) {
		for (int i = 0; i < 50; i++) {
			new Thread(new ThreadStatic()).start();
		}
	}
}