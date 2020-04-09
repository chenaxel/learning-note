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
public class SuperMethodTest {
	class GrandFather {
		void think() {
			System.out.println("I am grandfather");
		}
	}

	class Father extends GrandFather{
		@Override
		void think() {
			System.out.println("I am father");
		}
	}

	class Son extends Father {
		@Override
		void think() {
			try {
				MethodType methodType = MethodType.methodType(void.class);
				MethodHandle think = lookup().findSpecial(GrandFather.class, "think", methodType, getClass());
				think.invoke(this);
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		new SuperMethodTest().new Son().think();
	}
}
