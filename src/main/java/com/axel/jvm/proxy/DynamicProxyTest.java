package com.axel.jvm.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * des
 *
 * @author chenzhaohui
 * @date 2020/4/10
 */
public class DynamicProxyTest {
	interface IHello {
		void sayHello();
	}

	static class Hello implements IHello {
		@Override
		public void sayHello() {
			System.out.println("hello");
		}
	}

	static class DynamicClass implements InvocationHandler {
		Object original;

		Object bind(Object original) {
			this.original = original;
			return Proxy.newProxyInstance(original.getClass().getClassLoader(), original.getClass().getInterfaces(), this);
		}

		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			System.out.println("welcome");
			return method.invoke(original, args);
		}
	}

	public static void main(String[] args) {
		IHello hello = (IHello) new DynamicClass().bind(new Hello());
		hello.sayHello();
	}
}
