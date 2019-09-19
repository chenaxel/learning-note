package com.axel.concurrency.thread.synchronize;

import com.axel.concurrency.thread.share.BaseIntGenerator;
import com.axel.concurrency.thread.share.EvenChecker;

/**
 * des
 *
 * @author chenzhaohui
 * @date 2019/9/19
 */
public class SynchronizedEvenGenerator extends BaseIntGenerator {

	private int currentEvenValue = 0;

	@Override
	public synchronized int next() {
		currentEvenValue++;
		currentEvenValue++;
		return currentEvenValue;
	}

	public static void main(String[] args) {
		EvenChecker.test(new SynchronizedEvenGenerator());
	}
}
