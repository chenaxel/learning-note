package com.axel.jvm.oom;

/**
 * des
 *
 * @author chenzhaohui
 * @date 2020/3/20
 */
public class JavaVMStackOOM {

	private void dontStop() {
		while(true) {

		}
	}

	public void stackLeakByThread() {
		while (true) {
			Thread thread = new Thread(() -> dontStop());
			thread.start();
		}
	}

	public static void main(String[] args) {
		//别跑 系统爆炸
//		new JavaVMStackOOM().stackLeakByThread();
	}
}
