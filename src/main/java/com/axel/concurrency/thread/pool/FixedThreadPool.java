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
public class FixedThreadPool {

	public static void main(String[] args) {
		ExecutorService executorService = Executors.newFixedThreadPool(1);
		for (int i = 0; i < 5; i++) {
			executorService.execute(new LiftOff());
		}
		executorService.shutdown();
	}
}