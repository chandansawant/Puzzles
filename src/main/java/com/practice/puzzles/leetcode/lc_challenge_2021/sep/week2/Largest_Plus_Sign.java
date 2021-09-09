package com.practice.puzzles.leetcode.lc_challenge_2021.sep.week2;

import java.util.Arrays;

public class Largest_Plus_Sign {

	public int orderOfLargestPlusSign(int n, int[][] mines) {

		if (0 == mines.length)
			return (n + 1) / 2;
		else if (n * n == mines.length)
			return 0;

		boolean[][] grid = new boolean[n][n];

		for (int i = 0; i < n; ++i)
			Arrays.fill(grid[i], true);

		for (int[] mine : mines)
			grid[mine[0]][mine[1]] = false;

//		print(grid);
		return orderOfLargestPlusSign(grid);
	}

	private int orderOfLargestPlusSign(boolean[][] grid) {

		int maxK = (grid.length + 1) / 2;

		for (int offset = 0; offset < maxK; ++offset) {

			int maxIndex = grid.length + (0 == grid.length % 2 ? -1 : 0) - 1 - 2 * offset;
			int order = maxK - offset;

//			System.out.println("offset = " + offset + ", maxIndex = " + maxIndex + ", order = " + order);

			for (int x = 0; x + maxIndex < grid.length; ++x)
				for (int y = 0; y + maxIndex < grid.length; ++y) {
//					System.out.println("x = " + x + ", y = " + y + ", order = " + order);
					if (hasPlusSign(grid, x + order - 1, y + order - 1, order))
						return maxK - offset;
				}
		}

		return 0;
	}

	private boolean hasPlusSign(boolean[][] grid, int centerX, int centerY, int order) {

//		System.out.println("check center[" + centerX + ", " + centerY + "] for order = " + order);

		if (!grid[centerX][centerY])
			return false;
		else if (1 == order)
			return true;

		for (int i = 1; i < order; ++i) {
//			System.out.println(
//					"check [" + centerX + ", " + (centerY - i)
//					+ "] and [" + centerX + ", " + (centerY + i) + "]"
//					+ "] and [" + (centerX - i) + ", " + centerY + "]"
//					+ "] and [" + (centerX + i) + ", " + centerY + "]");

			if (!grid[centerX][centerY - i]
					|| !grid[centerX][centerY + i]
					|| !grid[centerX - i][centerY]
					|| !grid[centerX + i][centerY])
				return false;
		}

//		System.out.println("found plus sign");
		return true;
	}

	private void print(boolean[][] grid) {
		for (int i = 0; i < grid.length; ++i) {
			for (int j = 0; j < grid.length; ++j)
				System.out.print(grid[i][j] ? " 1" : " .");
			System.out.println();
		}
	}
}
