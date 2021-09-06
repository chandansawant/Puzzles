package com.practice.puzzles.leetcode.lc_challenge_2021.sep.week1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Array_Nesting_Test {

	private Array_Nesting cut = new Array_Nesting();

	@Test
	public void test_1() {
		assertEquals(4, cut.arrayNesting(new int[] {5, 4, 0, 3, 1, 6, 2}));
		assertEquals(1, cut.arrayNesting(new int[] {0, 1, 2}));
		assertEquals(1, cut.arrayNesting(new int[] {0}));
		assertEquals(2, cut.arrayNesting(new int[] {1, 0}));
	}
}
