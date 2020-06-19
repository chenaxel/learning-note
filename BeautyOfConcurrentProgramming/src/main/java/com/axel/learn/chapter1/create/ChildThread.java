package com.axel.learn.chapter1.create;

/**
 * des
 *
 * @author chenzhaohui
 * @date 2020/6/18
 */
public class ChildThread extends Thread {
	@Override
	public void run() {
		System.out.println("I am a child thread");
	}
}
