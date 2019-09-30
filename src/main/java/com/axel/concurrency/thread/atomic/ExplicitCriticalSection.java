package com.axel.concurrency.thread.atomic;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * des
 *
 * @author chenzhaohui
 * @date 2019/9/30
 */
public class ExplicitCriticalSection {
	public static void main(String[] args) throws Exception {
		PairManager
				pman1 = new ExplicitPairManager1(),
				pman2 = new ExplicitPairManager2();
		CriticalSections.testApproaches(pman1, pman2);
	}
}

class ExplicitPairManager1 extends PairManager {

	private Lock lock = new ReentrantLock();

	@Override
	public synchronized void increment() {

		lock.lock();
		try {
			pair.incrementX();
			pair.incrementY();
			store(getPair());
		} finally {
			lock.unlock();
		}
	}
}

class ExplicitPairManager2 extends PairManager {

	private Lock lock = new ReentrantLock();

	@Override
	public void increment() {

		Pair temp;
		lock.lock();
		try {
			pair.incrementX();
			pair.incrementY();
			temp = getPair();
		} finally {
			lock.unlock();
		}
		store(temp);
	}
}
