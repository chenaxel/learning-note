package com.axel.learn.chapter1.threadlocal;

/**
 * des
 *
 * @author chenzhaohui
 * @date 2020/6/22
 */
public class TestThreadLocal {
//	private static final ThreadLocal<String> local = new InheritableThreadLocal<>();
	private static final ThreadLocal<String> local = new ThreadLocal<>();

	public static void main(String[] args) {
		local.set("hello");

		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName() + "\tlocal:" + local.get());
			}
		}).start();
		System.out.println(Thread.currentThread().getName() + "\tlocal:" + local.get());
	}
}
