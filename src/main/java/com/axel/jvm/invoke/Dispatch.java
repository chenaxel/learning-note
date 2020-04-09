package com.axel.jvm.invoke;

/**
 * des
 *
 * @author chenzhaohui
 * @date 2020/4/9
 */
public class Dispatch {

	public static class QQ {
	}

	public static class _360 {
	}

	public static class Father {
		public void hardChoice (QQ qq) {
			System.out.println("father qq");
		}

		public void hardChoice(_360 l) {
			System.out.println("father 360");
		}
	}

	public static class Son extends Father {
		@Override
		public void hardChoice(QQ qq) {
			System.out.println("son qq");
		}

		@Override
		public void hardChoice(_360 l) {
			System.out.println("son 360");
		}
	}

	public static void main(String[] args) {
		Father father = new Father();
		Father son = new Son();
		father.hardChoice(new QQ());
		son.hardChoice(new _360());
	}
}