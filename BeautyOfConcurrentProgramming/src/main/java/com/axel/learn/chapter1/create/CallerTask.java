package com.axel.learn.chapter1.create;

import java.util.concurrent.Callable;

/**
 * des
 *
 * @author chenzhaohui
 * @date 2020/6/18
 */
public class CallerTask implements Callable<String> {
	@Override
	public String call() throws Exception {
		return "hello ->" + Thread.currentThread().getName();
	}
}
