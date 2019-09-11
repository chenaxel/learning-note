package com.axel.concurrency.thread.result;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * des
 *
 * @author chenzhaohui
 * @date 2019/9/11
 */
public class CallableDemo {

	public static void main(String[] args) {

		ExecutorService executorService = Executors.newCachedThreadPool();
		List<Future<String>> result = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			result.add(executorService.submit(new ThreadWithResult(i)));
		}
		result.stream().map(item -> {
			try {
				return item.get();
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
				return "";
			}
		}).forEach(System.out::println);
	}
}

class ThreadWithResult implements Callable<String> {

	private int id;

	public ThreadWithResult(int id) {
		this.id = id;
	}

	@Override
	public String call() {
		return "result of ThreadWithResult：" + id;
	}
}