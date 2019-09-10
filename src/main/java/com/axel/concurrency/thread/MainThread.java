package com.axel.concurrency.thread;

/**
 * des
 *
 * @author chenzhaohui
 * @date 2019/9/10
 */
public class MainThread {

	public static void main(String[] args) {
		LiftOff launch = new LiftOff();
		launch.run();
	}
}
