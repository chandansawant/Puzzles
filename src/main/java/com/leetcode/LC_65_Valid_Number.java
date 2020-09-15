package com.leetcode;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.lang.Character.isDigit;

public class LC_65_Valid_Number {

	public boolean isNumber(String s) {
		if (s.trim().length() == 0)
			return false;

		char[] str = s.trim().toCharArray();

		boolean decimalFound = str[0] == '.';
		boolean digitFound = false;

		int i = (str[0] == '+'
						|| str[0] == '-'
						|| decimalFound)
					? 1 : 0;

		for (; i < str.length; ++i) {
			if (isDigit(str[i])) {
				digitFound = true;
				continue;
			}
			else if (str[i] == '.') {
				if (decimalFound)
					return false;

				decimalFound = true;
			} else if (str[i] == 'e')
				break;
			else
				return false;
		}

		if (i < str.length
				&& str[i] == 'e') {
			digitFound &= validateExponent(str, i);
		}

		return digitFound;
	}

	public boolean validateExponent(final char[] str, final int startIndex) {
		if (startIndex >= str.length)
			return true;
		else if (str.length == startIndex + 1)
			return false;

		if (str[startIndex] != 'e')
			return false;

		boolean digitFound = false;

		int i = startIndex + 1 + ((str[startIndex + 1] == '+' || str[startIndex + 1] == '-') ? 1 : 0);

		for (; i < str.length; ++i) {
			if (isDigit(str[i]))
				digitFound = true;
			else
				return false;
		}

		return digitFound;
	}


	public static Map<String, Boolean> testValues() {
		Map<String, Boolean> testCases = new HashMap();

		testCases.put("0", TRUE);
		testCases.put(" 0.1 ", TRUE);
		testCases.put("abc", FALSE);
		testCases.put("1 a", FALSE);
		testCases.put("2e10", TRUE);
		testCases.put(" -90e3   ", TRUE);
		testCases.put(" 1e", FALSE);
		testCases.put("e3", FALSE);
		testCases.put(" 6e-1", TRUE);
		testCases.put(" 99e2.5 ", FALSE);
		testCases.put("53.5e93", TRUE);
		testCases.put(" --6 ", FALSE);
		testCases.put("-+3", FALSE);
		testCases.put("95a54e53", FALSE);

		testCases.put("", FALSE);
		testCases.put("+", FALSE);
		testCases.put("-", FALSE);

		testCases.put("1.", TRUE);
		testCases.put(".1", TRUE);
		testCases.put("-.1", TRUE);
		testCases.put("+.1", TRUE);
		testCases.put("1+1", FALSE);

		testCases.put("1e+", FALSE);
		testCases.put("1e-", FALSE);
		testCases.put("1e.", FALSE);

		testCases.put("1e1", TRUE);
		testCases.put("1e+1", TRUE);
		testCases.put("1e-1", TRUE);
		testCases.put("1e.1", FALSE);
		testCases.put("1e1.", FALSE);

		return testCases;
	}
}
