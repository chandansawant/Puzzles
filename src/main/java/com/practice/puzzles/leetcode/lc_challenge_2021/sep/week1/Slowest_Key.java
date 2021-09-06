package com.practice.puzzles.leetcode.lc_challenge_2021.sep.week1;

public class Slowest_Key {

	public char slowestKey(int[] releaseTimes, String keysPressed) {

		char[] keys = keysPressed.toCharArray();
		int keyIndex = 0;
		char key = keys[keyIndex];
		int maxTime = releaseTimes[keyIndex];

		for (int i = keyIndex + 1; i < releaseTimes.length; ++i) {
			int releaseTime = releaseTimes[i] - releaseTimes[i - 1];

			if (maxTime < releaseTime
					|| (maxTime == releaseTime
							&& (int)key < (int)keys[i])) {
				keyIndex = i;
				key = keys[keyIndex];
				maxTime = releaseTime;
			}
		}

		return key;
	}
}
