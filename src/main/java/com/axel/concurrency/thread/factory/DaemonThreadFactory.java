package com.axel.concurrency.thread.factory;

import java.util.concurrent.ThreadFactory;

/**
 * des
 *
 * @author chenzhaohui
 * @date 2019/9/12
 */
public class DaemonThreadFactory implements ThreadFactory {
	@Override
	public Thread newThread(Runnable r) {
		Thread thread = new Thread();
		thread.setDaemon(true);
		return thread;
	}
}