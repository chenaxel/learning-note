package com.axel.concurrency.thread.share;

/**
 * des
 *
 * @author chenzhaohui
 * @date 2019/9/18
 */
public abstract class BaseIntGenerator {

	private volatile boolean cancel = false;

	public abstract int next();

	public void cancel() {
		this.cancel = true;
	}

	public boolean isCancel() {
		return cancel;
	}
}
