package com.axel.concurrency.thread.synchronize;

import com.axel.concurrency.thread.share.BaseIntGenerator;
import com.axel.concurrency.thread.share.EvenChecker;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * des
 *
 * @author chenzhaohui
 * @date 2019/9/19
 */
public class MutexEvenGenerator extends BaseIntGenerator {

	private int value = 0;
	private Lock lock = new ReentrantLock();

	@Override
	public int next() {
		try {
			lock.lock();
			value++;
			Thread.yield();
			value++;
			return value;
		} finally {
			lock.unlock();
		}
	}

	public static void main(String[] args) {
		EvenChecker.test(new MutexEvenGenerator());
	}
}
