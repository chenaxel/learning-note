package com.axel.concurrency.thread.share;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * des
 *
 * @author chenzhaohui
 * @date 2019/9/18
 */
public class EvenChecker implements Runnable {

	private BaseIntGenerator intGenerator;
	private final int id;

	public EvenChecker(BaseIntGenerator generator, int id) {
		this.intGenerator = generator;
		this.id = id;
	}

	@Override
	public void run() {

		while (!intGenerator.isCancel()) {
			int next = intGenerator.next();
			if (next % 2 != 0) {
				System.out.println(next + " is not even");
				intGenerator.cancel();
			}
		}
	}

	public static void test(BaseIntGenerator generator, int count) {
		System.out.println("Press Control-C to exit");
		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i = 0; i < count; i++) {
			exec.execute(new EvenChecker(generator, i));
		}
		exec.shutdown();
	}

	public static void test(BaseIntGenerator generator) {
		test(generator, 5);
	}
}
