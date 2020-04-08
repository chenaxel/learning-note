package com.axel.jvm.invoke;

/**
 * des
 *
 * @author chenzhaohui
 * @date 2020/4/8
 */
public class DynamicDispatch {
	static abstract class Human {
		public void sayHello() {
			System.out.println("hello guy");
		}
	}

	static class Man extends Human {
		@Override
		public void sayHello() {
			System.out.println("hello man");
		}
	}

	static class Woman extends Human {
		@Override
		public void sayHello() {
			System.out.println("hello woman");
		}
	}

	public static void main(String[] args) {
		Human human = new Human() {
			@Override
			public void sayHello() {
				super.sayHello();
			}
		};
		human.sayHello();
		Human man = new Man();
		man.sayHello();
		Human woman = new Woman();
		woman.sayHello();
	}
}