package com.practice.puzzles.leetcode.lc_challenge_2021.sep.week1;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class Array_Nesting {

	public int arrayNesting(int[] nums) {

		int maxLength = 1;
		Map<Integer, Integer> cachedLength = new HashMap<>();

		for (int k = 0; k < nums.length; ++k) {
			Set<Integer> processedIndices = new HashSet<>();
			int length = getNestedLength(nums, k, cachedLength, processedIndices);

			if (maxLength < length)
				maxLength = length;
		}

		return maxLength;
	}

	private int getNestedLength(int[] nums,
	                            int k,
	                            Map<Integer, Integer> cachedLength,
	                            Set<Integer> processedIndices) {

//		System.out.println("====================================================");
//		System.out.println("nums = " + Arrays.toString(nums));
//		System.out.println("k = " + k + ", num[" + k + "] = " + nums[k]);
//		System.out.println("cachedLength = " + cachedLength.toString());
//		System.out.println("processedIndices = " + processedIndices.toString());

		Integer value = cachedLength.get(k);

		if (Objects.nonNull(value))
			return value;

		if (processedIndices.contains(k))
			return 0;

		int length = 0;

		if (k != nums[k]) {
			processedIndices.add(k);
			length = getNestedLength(nums, nums[k], cachedLength, processedIndices);
		}

		length++;
		cachedLength.put(k, length);
		return length;
	}
}
