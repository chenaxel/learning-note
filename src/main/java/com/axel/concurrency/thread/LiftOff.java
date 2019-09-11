package com.axel.concurrency.thread;

/**
 * des
 *
 * @author chenzhaohui
 * @date 2019/9/10
 */
public class LiftOff implements Runnable {

	protected int countDown = 10;
	private static int taskCount = 0;
	private final int id = taskCount++;

	public LiftOff(int countDown) {
		this.countDown = countDown;
	}

	public LiftOff() {
	}

	public String status() {
		return "#" + id + "(" + (countDown > 0 ? countDown : "Liftoff!") + "), ";
	}

	@Override
	public void run() {
		while (countDown-- > 0) {
			System.out.println(status());
			Thread.yield();
		}
	}
}