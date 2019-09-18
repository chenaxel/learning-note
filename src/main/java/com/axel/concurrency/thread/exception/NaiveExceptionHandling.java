package com.axel.concurrency.thread.exception;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * des
 *
 * @author chenzhaohui
 * @date 2019/9/18
 */
public class NaiveExceptionHandling {

	public static void main(String[] args) {
		try {
			ExecutorService service = Executors.newCachedThreadPool();
			service.execute(new ExceptionThread());
		} catch (RuntimeException e) {
			System.out.println("exception has been handling");
		}
	}
}
