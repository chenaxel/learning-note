package com.axel.concurrency.thread.exception;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * des
 *
 * @author chenzhaohui
 * @date 2019/9/18
 */
public class ExceptionThread implements Runnable {
	@Override
	public void run() {
		throw new RuntimeException("ex");
	}

	public static void main(String[] args) {
		ExecutorService service = Executors.newCachedThreadPool();
		service.execute(new ExceptionThread());
		service.shutdown();
	}
}