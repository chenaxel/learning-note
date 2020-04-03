package com.axel.jvm.code;

/**
 * des
 *
 * @author chenzhaohui
 * @date 2020/4/2
 */
public class TestClass {
	static class Parent {
		public static int A = 1;

		static {
			A = 2;
		}
	}

	static class Sub extends Parent {
		public static int B = A;
	}

	public static void main(String[] args) {
		Runnable script = () -> {
			System.out.println(Thread.currentThread() + "start");
			DeadLoopClass dlc = new DeadLoopClass();
			System.out.println(Thread.currentThread() + "run over");
		};
		new Thread(script).start();
		new Thread(script).start();
	}

	static class DeadLoopClass {
		static {
			if (true) {
				System.out.println(Thread.currentThread() + "init dead loop class");
				while (true) {

				}
			}
		}
	}
}
