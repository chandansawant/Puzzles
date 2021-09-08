package com.practice.puzzles.leetcode.lc_challenge_2021.sep.week2;

public class Shifting_Letters {

	public String shiftingLetters(String s, int[] shifts) {

		char[] str = s.toCharArray();
		int prevShift = 0;

		for (int i = shifts.length - 1; i >= 0; --i) {
			int shiftCount = (prevShift + shifts[i]) % 26;
			str[i] = shiftChar(str[i], shiftCount);
			prevShift = shiftCount;
		}

		return String.valueOf(str);
	}

	private char shiftChar(char ch, int shiftCount) {
		return (char)(((int)ch - 97 + shiftCount) % 26 + 97);
	}
}
