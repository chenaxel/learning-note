package com.axel.concurrency.thread.sleep;

import com.axel.concurrency.thread.LiftOff;

import java.util.concurrent.TimeUnit;

/**
 * des
 *
 * @author chenzhaohui
 * @date 2019/9/11
 */
public class SleepingTask extends LiftOff {

	@Override
	public void run() {
		while (countDown-- > 0) {
			System.out.println(status());
			try {
				TimeUnit.MICROSECONDS.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {

		for (int i = 0; i < 5; i++) {
			new Thread(new SleepingTask()).start();
		}
	}
}
