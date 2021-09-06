package com.practice.puzzles.leetcode.lc_challenge_2021.sep.week1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Slowest_Key_Test {

	private Slowest_Key cut = new Slowest_Key();

	@Test
	public void test_1() {
		assertEquals('c', cut.slowestKey(new int[] {9, 29, 49, 50}, "cbcd"));
		assertEquals('a', cut.slowestKey(new int[] {12, 23, 36, 46, 62}, "spuda"));
		assertEquals('b', cut.slowestKey(new int[] {1, 3}, "ab"));
		assertEquals('a', cut.slowestKey(new int[] {3, 4}, "ab"));
		assertEquals('b', cut.slowestKey(new int[] {2, 4, 5}, "abc"));
		assertEquals('b', cut.slowestKey(new int[] {1, 3, 5}, "cba"));
	}
}
