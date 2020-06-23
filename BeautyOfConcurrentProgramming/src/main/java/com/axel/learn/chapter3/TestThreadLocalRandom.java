package com.axel.learn.chapter3;

import java.util.concurrent.ThreadLocalRandom;

/**
 * des
 *
 * @author chenzhaohui
 * @date 2020/6/23
 */
public class TestThreadLocalRandom {
	public static void main(String[] args) {
		ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();
		for (int i = 0; i < 5; i++) {
			System.out.println(threadLocalRandom.nextInt(5));
		}
	}
}
