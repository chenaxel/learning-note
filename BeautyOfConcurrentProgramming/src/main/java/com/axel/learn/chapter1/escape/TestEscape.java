package com.axel.learn.chapter1.escape;

/**
 * this逃逸
 * 避免:初始化完成后再启动线程
 *
 * @author chenzhaohui
 * @date 2020/6/18
 */
public class TestEscape {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public TestEscape(String name) throws InterruptedException {
		new Thread(new EscapeRunnable()).start();
		Thread.sleep(1000);
		this.name = name;
	}

	private class EscapeRunnable implements Runnable {
		@Override
		public void run() {
			System.out.println(name);
		}
	}

	public static void main(String[] args) throws InterruptedException {
		new TestEscape("hello");
	}
}
