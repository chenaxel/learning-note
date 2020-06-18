package com.axel.learn.create;

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
