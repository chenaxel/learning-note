package com.axel.jvm.gc;

/**
 * des
 *
 * @author chenzhaohui
 * @date 2020/3/23
 */
public class FinalizeEscapeGC {

	public static FinalizeEscapeGC SAVE_HOOK = null;

	public void isAlive() {
		System.out.println("yes, I am alive");
	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		System.out.println("finalize method executed.");
		SAVE_HOOK = this;
	}

	public static void main(String[] args) throws Throwable {
		SAVE_HOOK = new FinalizeEscapeGC();
		SAVE_HOOK = null;
		System.gc();
		Thread.sleep(500);
		if (SAVE_HOOK != null) {
			SAVE_HOOK.isAlive();
		} else {
			System.out.println("no, I am dead");
		}

		SAVE_HOOK = null;
		System.gc();
		Thread.sleep(500);
		if (SAVE_HOOK != null) {
			SAVE_HOOK.isAlive();
		} else {
			System.out.println("no, I am dead");
		}
	}
}
