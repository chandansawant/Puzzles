package com.practice.puzzles.leetcode.lc_challenge_2021.sep.week2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Shifting_Letters_Test {

	private Shifting_Letters cut = new Shifting_Letters();

	@Test
	public void test() {
		assertEquals("a", cut.shiftingLetters("a", new int[] { 0 }));
		assertEquals("b", cut.shiftingLetters("a", new int[] { 1 }));
		assertEquals("z", cut.shiftingLetters("z", new int[] { 52 }));
		assertEquals("a", cut.shiftingLetters("z", new int[] { 53 }));

		assertEquals("ddd", cut.shiftingLetters("abc", new int[] { 1, 1, 1 }));
		assertEquals("rpl", cut.shiftingLetters("abc", new int[] { 3, 5, 9 }));
		assertEquals("gfd", cut.shiftingLetters("aaa", new int[] { 1, 2, 3 }));
	}
}
