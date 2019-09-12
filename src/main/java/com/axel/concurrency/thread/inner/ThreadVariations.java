package com.axel.concurrency.thread.inner;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

/**
 * des
 *
 * @author chenzhaohui
 * @date 2019/9/12
 */
public class ThreadVariations {

	public static void main(String[] args) {
		new InnerThread1("InnerThread1");
		new InnerThread2("InnerThread2");
		new InnerRunnable1("InnerRunnable1");
		new InnerRunnable2("InnerRunnable2");
		new ThreadMethod("ThreadMethod").runTask();
	}
}

class InnerThread1 {
	private int countDown = 5;
	private Inner thread;

	private class Inner extends Thread {
		Inner(String name) {
			super(name);
			start();
		}

		@Override
		public void run() {
			try {
				while (true) {
					System.out.println(this);
					if (--countDown == 0) {
						return;
					}
					sleep(10);
				}
			} catch (InterruptedException e) {
				System.out.println("interrupted");
			}
		}
	}

	public InnerThread1(String name) {
		thread = new Inner(name);
	}
}

class InnerThread2 {
	private int countDown = 5;
	private Thread thread;

	public InnerThread2(String name) {
		thread = new Thread(name) {
			@Override
			public void run() {
				try {
					while (true) {
						System.out.println(this);
						if (--countDown == 0) {
							return;
						}
						sleep(10);
					}
				} catch (InterruptedException e) {
					System.out.println("interrupted");
				}
			}

			@Override
			public String toString() {
				return getName() + ": " + countDown;
			}
		};
		thread.start();
	}
}

class InnerRunnable1 {
	private int countDown = 5;
	private Inner inner;

	private class Inner implements Runnable {

		Thread t;

		Inner(String name) {
			t = new Thread(name);
			t.start();
		}

		@Override
		public void run() {
			try {
				while (true) {
					System.out.println(this);
					if (--countDown == 0) {
						return;
					}
					sleep(10);
				}
			} catch (InterruptedException e) {
				System.out.println("interrupted");
			}
		}
	}

	public InnerRunnable1(String name) {
		inner = new Inner(name);
	}
}

class InnerRunnable2 {
	private int countDown = 5;
	private Thread inner;

	public InnerRunnable2(String name) {
		inner = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					while (true) {
						System.out.println(this);
						if (--countDown == 0) {
							return;
						}
						TimeUnit.MILLISECONDS.sleep(10);
					}
				} catch (InterruptedException e) {
					System.out.println("sleep() interrupted");
				}
			}

			@Override
			public String toString() {
				return Thread.currentThread().getName() +
						": " + countDown;
			}
		}, name);
		inner.start();
	}
}

class ThreadMethod {
	private int countDown = 5;
	private Thread t;
	private String name;

	public ThreadMethod(String name) {
		this.name = name;
	}

	public void runTask() {
		if (t == null) {
			t = new Thread(name) {
				@Override
				public void run() {
					try {
						while (true) {
							System.out.println(this);
							if (--countDown == 0) {
								return;
							}
							TimeUnit.MILLISECONDS.sleep(10);
						}
					} catch (InterruptedException e) {
						System.out.println("sleep() interrupted");
					}
				}

				@Override
				public String toString() {
					return Thread.currentThread().getName() +
							": " + countDown;
				}
			};
			t.start();
		}
	}
}
