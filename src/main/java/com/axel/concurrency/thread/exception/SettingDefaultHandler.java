package com.axel.concurrency.thread.exception;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * des
 *
 * @author chenzhaohui
 * @date 2019/9/18
 */
public class SettingDefaultHandler {

	public static void main(String[] args) {
		Thread.setDefaultUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
		ExecutorService service = Executors.newCachedThreadPool();
		service.execute(new ExceptionThread2());
		service.shutdown();
	}
}
