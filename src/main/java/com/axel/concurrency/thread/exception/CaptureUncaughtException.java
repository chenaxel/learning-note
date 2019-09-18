package com.axel.concurrency.thread.exception;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * des
 *
 * @author chenzhaohui
 * @date 2019/9/18
 */
public class CaptureUncaughtException {

	public static void main(String[] args) {
		ExecutorService service = Executors.newCachedThreadPool(new HandlerThreadFactory());
		service.execute(new ExceptionThread2());
	}
}

class ExceptionThread2 implements Runnable {
	@Override
	public void run() {
		Thread t = Thread.currentThread();
		System.out.println("run() by" + t);
		System.out.println("eh + " + t.getUncaughtExceptionHandler());
		throw new RuntimeException();
	}
}

class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
	@Override
	public void uncaughtException(Thread t, Throwable e) {
		System.out.println("caught + " + e);
	}
}

class HandlerThreadFactory implements ThreadFactory {
	@Override
	public Thread newThread(Runnable r) {
		System.out.println("create new thread");
		Thread t = new Thread(r);
		System.out.println("created" + t);
		t.setUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
		System.out.println("eh" + t.getUncaughtExceptionHandler());
		return t;
	}
}