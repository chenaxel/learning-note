package com.axel.concurrency.thread.mutex;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * des
 *
 * @author chenzhaohui
 * @date 2019/10/16
 */
public class Interrupting2 {

	public static void main(String[] args) throws InterruptedException {
		Thread t = new Thread(new Blocked2());
		t.start();
		TimeUnit.SECONDS.sleep(1);
		System.out.println("Issuing t.interrupt()");
		t.interrupt();
	}
}

class BlockedMutex {

	private Lock lock = new ReentrantLock();

	public BlockedMutex() {
		lock.lock();
	}

	public void f() {
		try {
			lock.lockInterruptibly();
			System.out.println("lock acquired in f()");
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.out.println("Interrupted from lock acquisition in f()");
		}
	}
}


class Blocked2 implements Runnable {

	private BlockedMutex mutex = new BlockedMutex();

	@Override
	public void run() {
		System.out.println("waiting for f() in blocked");
		mutex.f();
		System.out.println("broken of blocked");
	}
}