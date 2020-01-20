package com.axel.leet;

import java.util.Arrays;

/**
 * des
 *
 * @author chenzhaohui
 * @date 2020/1/16
 */
public class Rotate {

	public static void main(String[] args) {
		int[][] matrix = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
		rotate(matrix);
		System.out.println(Arrays.deepToString(matrix));
	}

	private static void rotate(int[][] matrix) {
		int n = matrix.length;
		int[][] newMatrix = new int[n][n];
		for (int i = 0; i < n; i++) {
			System.arraycopy(matrix[i], 0, newMatrix[i], 0, n);
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				matrix[j][n - 1 - i] = newMatrix[i][j];
			}
		}
	}
}
