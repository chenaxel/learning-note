package com.axel.learn.chapter1.create;

import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * des
 *
 * @author chenzhaohui
 * @date 2020/6/18
 */
public class ThreadCreateTest {

	@Test
	public void testExtend() {
		new ChildThread().start();
	}

	@Test
	public void testRunnable() throws InterruptedException {
		new Thread(new RunnableTask()).start();
		Thread.sleep(1000);
	}

	@Test
	public void testFuture() {
		FutureTask<String> task = new FutureTask<>(new CallerTask());
		new Thread(task).start();
		try {
			String message = task.get();
			System.out.println(message);
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}
}