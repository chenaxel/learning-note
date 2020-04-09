package com.axel.jvm.invoke;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodType;

import static java.lang.invoke.MethodHandles.lookup;

/**
 * des
 *
 * @author chenzhaohui
 * @date 2020/4/9
 */
public class MethodHandlerTest {
	static class ClassA {
		public void println(String s) {
			System.out.println(s);
		}
	}

	public static void main(String[] args) throws Throwable {
		Object object = System.currentTimeMillis() % 2 == 0 ? System.out : new ClassA();
		getPrintln(object).invokeWithArguments("test method handler");
	}

	private static MethodHandle getPrintln(Object receiver) throws NoSuchMethodException, IllegalAccessException {
		MethodType methodType = MethodType.methodType(void.class, String.class);
		return lookup().findVirtual(receiver.getClass(), "println", methodType).bindTo(receiver);
	}
}
