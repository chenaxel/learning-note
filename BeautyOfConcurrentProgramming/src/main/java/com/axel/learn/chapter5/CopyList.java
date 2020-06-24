package com.axel.learn.chapter5;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * des
 *
 * @author chenzhaohui
 * @date 2020/6/23
 */
public class CopyList {
	private static volatile CopyOnWriteArrayList<String> arrayList = new CopyOnWriteArrayList<>();

	public static void main(String[] args) throws InterruptedException {
		arrayList.add("hello");
		arrayList.add("alibaba");
		arrayList.add("welcome");
		arrayList.add("to");
		arrayList.add("hangzhou");

		Thread threadOne = new Thread(new Runnable() {
			@Override
			public void run() {
				arrayList.set(1, "babe");
				arrayList.remove(2);
				arrayList.remove(3);
			}
		});

		Iterator<String> iterator = arrayList.iterator();
		threadOne.start();
		threadOne.join();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
	}
}
