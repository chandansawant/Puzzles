package com.practice.puzzles.leetcode.lc76;

import java.util.*;

import static java.util.Arrays.asList;

public class Minimum_Window_Substring {

	private boolean[] pattern = new boolean[255];
	int[] counts = new int[255];

	public String minWindow(String s, String t) {

		if (s.trim().length() == 0 || t.trim().length() == 0)
			return "";

		for (char ch : t.toCharArray()) {
			pattern[getIndex(ch)] = true;
		}

		char[] chars = s.toCharArray();

        /* test
        single char pattern
        "" pattern
        */
		int minStart = 0;
		int minEnd = chars.length + 1;

		Set<Character> patternSet = getCharSet(t);
		int totalPatternChars = patternSet.size();

		for (int start = minStart, end = minStart
				; start <= end && end < chars.length
				; ++end) {

			//trim from start to find next possible substring
			if (patternSet.size() == totalPatternChars) {
				for (; start < chars.length; ++start)
					if (isCharInPattern(chars[start])) {
						end = start;
						break;
					}

				//no more solutions possible
				if (start >= chars.length)
					break;
			}

			char ch = chars[end];

			if (!isCharInPattern(ch))
				continue;

			//if initial char repeats then trim from start
			for (; start < end; ++start) {
				if (chars[start] == chars[end])
					counts[getIndex(ch)]--;
				else if (isCharInPattern(chars[start]))
					break;
			}

			counts[getIndex(ch)]++;
			patternSet.remove(ch);

			if (!patternSet.isEmpty())
				continue;

			//found valid substring
			if ((end - start + 1) < (minEnd - minStart + 1)) {
				minStart = start;
				minEnd = end;
			}

			++start;
			patternSet = getCharSet(t);
		}

		return (minEnd == chars.length + 1) ? "" : s.substring(minStart, minEnd + 1);
	}

	public static int getIndex(final char ch) { return (int)ch % 256; }

	public boolean isCharInPattern(final char ch) { return pattern[getIndex(ch)]; }

	public Set<Character> getCharSet(final String str) {
		Set<Character> charSet = new HashSet<Character>();

		for (char ch : str.toCharArray())
			charSet.add(new Character(ch));

		return charSet;
	}

	public static List<List<String>> testValues() {
		List<List<String>> testCases = new ArrayList<List<String>>();

//		testCases.add(asList("ADOBECODEBANC", "ABC", "BANC"));

//		testCases.add(asList("", "", ""));
//		testCases.add(asList("", "xyz", ""));
//		testCases.add(asList("xyz", "", ""));
//		testCases.add(asList("ab12cd", "xyz", ""));
//		testCases.add(asList("ab12cd", "bb", "b"));
//		testCases.add(asList("ab12cd", "1", "1"));
//		testCases.add(asList("ab12cd", "21", "12"));
//		testCases.add(asList("ab12cd", "212", "12"));
//		testCases.add(asList("abcd", "cb", "bc"));
//		testCases.add(asList("ab1z2ycxd", "xyz", "z2ycx"));

//		testCases.add(asList("1aa1bb1cc1dd1bcad1", "abcd", "bcad"));
//		testCases.add(asList("bcad11aa1bb1cc1dd1", "abcd", "bcad"));
//		testCases.add(asList("abcd", "badc", "abcd"));

		testCases.add(asList("abcdbcdbcde", "abcde", "abcdbcdbcde"));

		return testCases;
	}
}