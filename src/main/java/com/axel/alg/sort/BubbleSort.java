package com.axel.alg.sort;

import java.util.Arrays;

/**
 * 简单冒泡排序
 *
 * @author chenzhaohui
 * @date 2020/1/20
 */
public class BubbleSort<T extends Comparable<T>> {

	private BubbleSort() {
	}

	public static <T extends Comparable<T>> T[] sort(T[] unsorted) {

		boolean swap = true;
		int length = unsorted.length;
		while (swap) {
			swap = false;
			for (int i = 1; i < length; i++) {
				if (unsorted[i].compareTo(unsorted[i - 1]) < 0) {
					swap(i, i - 1, unsorted);
					swap = true;
				}
			}
			length--;
		}
		return unsorted;
	}

	private static <T extends Comparable<T>> void swap(int i, int j, T[] unsorted) {

		T temp = unsorted[i];
		unsorted[i] = unsorted[j];
		unsorted[j] = temp;
	}

	static class Tester {
		public static void main(String[] args) {
			System.out.println(Arrays.toString(sort(new Integer[]{5, 4, 2, 5, 4, 1})));
		}
	}
}
