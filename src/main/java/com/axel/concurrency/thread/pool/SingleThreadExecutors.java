package com.axel.concurrency.thread.pool;

import com.axel.concurrency.thread.LiftOff;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * des
 *
 * @author chenzhaohui
 * @date 2019/9/11
 */
public class SingleThreadExecutors {

	public static void main(String[] args) {
		ExecutorService service = Executors.newSingleThreadExecutor();
		for (int i = 0; i < 5; i++) {
			service.execute(new LiftOff());
		}
		service.shutdown();
	}
}
