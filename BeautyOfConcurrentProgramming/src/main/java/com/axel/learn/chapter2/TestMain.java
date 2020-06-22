package com.axel.learn.chapter2;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * des
 *
 * @author chenzhaohui
 * @date 2020/6/22
 */
public class TestMain {
	private static volatile Bean bean;

	public static void main(String[] args) throws InterruptedException {
		bean = new Bean(0);
		Thread threadA = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 100; i++) {
					bean.inc();
					System.out.println(Thread.currentThread().getName() + "\tcount:" + bean.getResult());
				}
			}
		});
		Thread threadB = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 100; i++) {
					bean.inc();
					System.out.println(Thread.currentThread().getName() + "\tcount:" + bean.getResult());
				}
			}
		});
		Thread threadC = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Field f = Unsafe.class.getDeclaredField("theUnsafe");
					f.setAccessible(true);
					Unsafe unsafe = (Unsafe) f.get(null);
					long l = unsafe.objectFieldOffset(bean.getClass().getDeclaredField("result"));
					System.out.println(l);
				} catch (NoSuchFieldException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		});
		threadA.start();
		threadB.start();
		threadC.start();
		threadA.join();
		threadB.join();
		threadC.join();
		System.out.println(Thread.currentThread().getName() + "\tcount:" + bean.getResult());
		System.exit(0);
	}
}
