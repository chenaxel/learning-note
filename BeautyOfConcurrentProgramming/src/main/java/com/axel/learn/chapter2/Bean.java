package com.axel.learn.chapter2;

/**
 * des
 *
 * @author chenzhaohui
 * @date 2020/6/22
 */
public class Bean {
	private int result;

	public Bean(int result) {
		this.result = result;
	}

	public synchronized int getResult() {
		return result;
	}

	public synchronized void setResult(int result) {
		this.result = result;
	}

	public synchronized void inc() {
		result++;
	}
}
