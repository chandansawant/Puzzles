package com.practice.puzzles.leetcode.lc_2210;

import java.util.Arrays;

public class Count_Hills_And_Valleys {

	public int countHillValley(int[] nums) {

		System.out.println("Input = " + Arrays.toString(nums));

		int count = 0;

		int leftNeighbor = 0;
		int currPos = findClosestNonEqualNeighborOnRight(nums, leftNeighbor);
		int rightNeighbor = findClosestNonEqualNeighborOnRight(nums, currPos);

		while (rightNeighbor != nums.length) {
			System.out.println("[" + nums[leftNeighbor] + ", " + nums[currPos] + ", " + nums[rightNeighbor] + "]");

			if (isHill(nums, leftNeighbor, currPos, rightNeighbor)
					|| isValley(nums, leftNeighbor, currPos, rightNeighbor))
				count++;

			leftNeighbor = currPos;
			currPos = rightNeighbor;
			rightNeighbor = findClosestNonEqualNeighborOnRight(nums, currPos);
		}

		System.out.println("countHillValley = " + count);
		return count;
	}

	private static int findClosestNonEqualNeighborOnRight(int[] nums, int currPos) {

		if (currPos < 0 || currPos >= nums.length - 1)
			return nums.length;

		int i = currPos + 1;
		for (; i < nums.length && nums[i] == nums[currPos]; ++i);
		return i;
	}

	private static boolean isHill(int[] nums, int leftNeighbor, int currPos, int rightNeighbor) {
		return nums[currPos] > nums[leftNeighbor] && nums[currPos] > nums[rightNeighbor];
	}

	private static boolean isValley(int[] nums, int leftNeighbor, int currPos, int rightNeighbor) {
		return nums[currPos] < nums[leftNeighbor] && nums[currPos] < nums[rightNeighbor];
	}

	/******************************************************************
	 * tests
	 ******************************************************************/

	public static void main(String[] args) {
		int[] nums = {6, 6, 5, 5, 4, 1};

		Count_Hills_And_Valleys cut = new Count_Hills_And_Valleys();
		cut.countHillValley(nums);
	}
}
