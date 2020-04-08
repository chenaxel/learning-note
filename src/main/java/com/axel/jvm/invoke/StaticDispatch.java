package com.axel.jvm.invoke;

/**
 * des
 *
 * @author chenzhaohui
 * @date 2020/4/8
 */
public class StaticDispatch {

	static abstract class Human {
	}

	static class Man extends Human {
	}

	static class Woman extends Human {
	}

	public void sayHello(Human human) {
		System.out.println("hey guy");
	}

	public void sayHello(Man man) {
		System.out.println("hey gentlemen");
	}

	public void sayHello(Woman woman) {
		System.out.println("hello lady");
	}

	public static void main(String[] args) {
		Human man = new Man();
		Human woman = new Woman();
		StaticDispatch dispatch = new StaticDispatch();
		dispatch.sayHello(man);
		dispatch.sayHello(woman);
	}
}