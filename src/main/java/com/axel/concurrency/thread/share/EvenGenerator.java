package com.axel.concurrency.thread.share;

/**
 * des
 *
 * @author chenzhaohui
 * @date 2019/9/18
 */
public class EvenGenerator extends BaseIntGenerator {
	private int currentEvenValue = 0;

	@Override
	public int next() {
		++currentEvenValue;
		++currentEvenValue;
		++currentEvenValue;
		++currentEvenValue;
		return currentEvenValue;
	}

	public static void main(String[] args) {
		EvenChecker.test(new EvenGenerator());
	}
}
