package com.axel.concurrency.thread.exception;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * des
 *
 * @author chenzhaohui
 * @date 2019/9/12
 */
public class ThreadException implements Runnable {

	@Override
	public void run() {
		throw new RuntimeException();
	}

	public static void main(String[] args) {
		ExecutorService service = Executors.newCachedThreadPool();
		try {
			service.execute(new ThreadException());
		} catch (Exception e) {
			e.printStackTrace();
		}
		service.shutdown();
	}
}
