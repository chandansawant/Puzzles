package com.practice.puzzles.leetcode.lc420;

import java.util.*;

class Strong_Password_Checker {

	public int strongPasswordChecker(String s) {
		int len = s.length();

        /*
        empty password = "" -> 6
        1 lowercase = a -> 5
        1 uppercase = A -> 5
        1 digit = 1 -> 5

        repeate but less chars
        aa -> 4
        aaa -> 4
        aaaa -> 3
        aaaaa -> 2
        aaaaaa -> 2

        repeate but less special chars
        * -> 5
        ** -> 4
        *** -> 3
        **** -> 3
        ***** -> 3
        ****** -> 3

        repeate but extra chars
            20 chars = aaaaaaaaaaaaaaaaaaaa -> 6
            21 chars = aaaaaaaaaaaaaaaaaaaaa -> 7
            22 chars = aaaaaaaaaaaaaaaaaaaaaa -> 8
            23 chars = aaaaaaaaaaaaaaaaaaaaaaa -> 9
            24 chars = aaaaaaaaaaaaaaaaaaaaaaaa -> 10

        combinations but less chars
        4 char = Aaa1 -> 2

        combinations but more chars
        22 chars = Aa01234567890123456789 -> 2
        */

		//min 6 char
		//max 20 chars
		int addChars = 0;
		int deleteChars = 0;

		if (len < 6)
			addChars = 6 - len;
		else if (len > 20)
			deleteChars = len - 20;

		System.out.println("\n******** condition 1 ********");
		System.out.println("addChars = " + addChars);
		System.out.println("deleteChars = " + deleteChars + "\n");

        /*
        no lowercase with short length
        AB12 -> 2

        no uppercase with short length
        ab12 -> 2

        no digit with short length
        abAB -> 2

        only lowercase with short length
        abab -> 2

        only uppercase with short length
        ABAB -> 2

        only digit with short length
        1212 -> 2



        no lowercase with extra length
        AB01234567890123456789 -> 3

        no uppercase with extra length
        ab01234567890123456789 -> 3

        no digit with extra length
        abcdefghijabcdefghijAB -> 3

        only lowercase with extra length
        abcdefghijabcdefghijab -> 4

        only uppercase with extra length
        ABCDEFGHIJABCDEFGHIJAB -> 4

        only digit with extra length
        0123456789012345678901 -> 4
        */

		//atleast 1 lowercase
		//atleast 1 uppercase
		//atleast 1 digit
		boolean lowercaseFound = false;
		boolean uppercaseFound = false;
		boolean digitFound = false;

        /*
        short, 3 repeat chars once = aaaA1 -> 2
        short, 4 repeat chars once = aaaaA -> 2
        5 repeat chars once = aaaaaA -> 1

        short, 3 repeat chars once, no lowercase = AAA12 -> 2
        short, 4 repeat chars once, no lowercase = AAAA1 -> 2
        5 repeat chars once, no lowercase = AAAAA1 -> 1

        short, 3 repeat chars once, no uppercase = aaa12 -> 2
        short, 4 repeat chars once, no uppercase = aaaa1 -> 2
        5 repeat chars once, no uppercase = aaaaa1 -> 1

        short, 3 repeat chars once, no digit = aaaAA -> 2
        short, 4 repeat chars once, no digit = aaaaA -> 2
        5 repeat chars once, no digit = aaaaa (repeat) -> 2


        normal, 3 repeat chars once = aaaA12 -> 1
        normal, 4 repeat chars once = aaaaA12 -> 1
        normal, 5 repeat chars once = aaaaaA12 -> 1
        normal, 6 repeat chars once = aaaaaaA12 -> 2

        normal, 3 repeat chars twice = aaaA12bbb -> 2
        normal, 4 repeat chars twice = aaaaA12bbbb -> 2
        normal, 5 repeat chars twice = aaaaaA12bbbbb -> 2
        normal, 6 repeat chars twice = aaaaaaA12bbbbbb -> 4


        long, 3 repeat chars once = aaaA0123456789012345678 -> 3
        long, 4 repeat chars once = aaaaA0123456789012345678 -> 2
        5 repeat chars once = aaaaaA0123456789012345678 -> 1

        long, 3 repeat chars once, no lowercase = AAA12 -> 2
        long, 4 repeat chars once, no lowercase = AAAA1 -> 2
        5 repeat chars once, no lowercase = AAAAA1 -> 1

        long, 3 repeat chars once, no uppercase = aaa12 -> 2
        long, 4 repeat chars once, no uppercase = aaaa1 -> 2
        5 repeat chars once, no uppercase = aaaaa1 -> 1

        long, 3 repeat chars once, no digit = aaaAA -> 2
        long, 4 repeat chars once, no digit = aaaaA -> 2
        5 repeat chars once, no digit = aaaaa (repeat) -> 2





        short and 3 repeat chars once = aaaA12 -> 3
        short and 4 repeat chars = aaaaA1 -> 3
        short and 5 repeat chars
        aaaaaA -> 3
        aaaaa1 -> 3

        6 repeat chars = aaaaaaA123
        multiple 3 repeat chars = aaaA123bbb
        multiple 4 repeat chars = aaaaA123aaaa
        */

		//no 3 consecutive same characters
		int charRepeatCount = 1;
		PriorityQueue<Integer> repeatedCountsQueue = new PriorityQueue<Integer>(Collections.reverseOrder());

		char[] str = s.toCharArray();

		for (int i = 0; i < str.length; ++i) {
			if ((int)str[i] >= 48 && (int)str[i] <= 57)
				digitFound = true;

			if ((int)str[i] >= 65 && (int)str[i] <= 90)
				uppercaseFound = true;

			if ((int)str[i] >= 97 && (int)str[i] <= 122)
				lowercaseFound = true;

			if (i > 0) {
				if (str[i] == str[i - 1])
					charRepeatCount++;

				if (str[i] != str[i - 1]
						|| i == (str.length - 1)) {
					if (charRepeatCount >= 3) {
						repeatedCountsQueue.add(charRepeatCount);
						System.out.println("For '" + str[i - 1] + "', repeat count = " + charRepeatCount);
					}

					charRepeatCount = 1;
				}
			}
		}

		List<Integer> repeatedCounts = new ArrayList<Integer>();

		while (!repeatedCountsQueue.isEmpty())
			repeatedCounts.add(repeatedCountsQueue.poll());

		System.out.println("repeatedCounts = " + repeatedCounts);

		System.out.println("\n******** condition 2 ********");
		System.out.println("lowercaseFound = " + lowercaseFound);
		System.out.println("uppercaseFound = " + uppercaseFound);
		System.out.println("digitFound = " + digitFound);

		//check condition 1 - already calculated

		//check condition 2
		int addOrReplaceMissingTypeCount = 0;

		if (!lowercaseFound)
			addOrReplaceMissingTypeCount++;

		if (!uppercaseFound)
			addOrReplaceMissingTypeCount++;

		if (!digitFound)
			addOrReplaceMissingTypeCount++;

		System.out.println("lowercasr/uppercase/digit check, addOrReplaceMissingTypeCount = " + addOrReplaceMissingTypeCount);


		//short or long password
		int minAdd = 0;

		if (addChars > 0) {
			minAdd += addChars;

			if (addChars >= addOrReplaceMissingTypeCount)
				addOrReplaceMissingTypeCount = 0;
			else
				addOrReplaceMissingTypeCount -= addChars;

			System.out.println("add chars = " + minAdd);
			System.out.println("replace after add = " + addOrReplaceMissingTypeCount);

			if (repeatedCounts.size() == 1) {
				Integer num = repeatedCounts.get(0);

				if (num == 3 || num == 4)
					repeatedCounts.set(0, 2);
				else if (num == 5)
					repeatedCounts.set(0, 3);
			}
		}

		//clean list
		int minDel = 0;
		System.out.println("\nrepeatedCounts list = " + repeatedCounts);

		boolean atleastOneDeleted = true;

		while (deleteChars > 0 && atleastOneDeleted) {
			atleastOneDeleted = false;

			for (int i = 0; deleteChars > 0 && i < repeatedCounts.size(); ++i) {
				Integer num = repeatedCounts.get(i);

				if (num > 5) {
					deleteChars--;
					minDel++;
					repeatedCounts.set(i, num - 1);
					atleastOneDeleted = true;
				}
			}
		}

		atleastOneDeleted = true;

		while (deleteChars > 0 && atleastOneDeleted) {
			atleastOneDeleted = false;

			for (int i = repeatedCounts.size() - 1; deleteChars > 0 && i >= 0; --i) {
				Integer num = repeatedCounts.get(i);

				if (num < 3)
					continue;

				int maxCharsCanBeDeleted = num - 2;
				int charsToDelet = (maxCharsCanBeDeleted <= deleteChars) ? maxCharsCanBeDeleted : deleteChars;

				deleteChars -= charsToDelet;
				minDel += charsToDelet;
				repeatedCounts.set(i, num - charsToDelet);
				atleastOneDeleted = true;
			}
		}

		if (deleteChars > 0) {
			minDel += deleteChars;
			deleteChars = 0;
		}

		System.out.println("\nminDel = " + minDel);

		//check condition 3
		int mustReplaceCount
				= repeatedCounts.stream()
					.filter(repeateCount -> repeateCount >= 3)
					.map(repeateCount -> (int)repeateCount / 3)
					.reduce(0, Integer::sum).intValue();

		System.out.println("for repeating chars, mustReplaceCount = " + mustReplaceCount);

		System.out.println("\n******** checks ********");

		//normal length password
		int minReplace = 0;
		System.out.println("must mustReplaceCount = " + mustReplaceCount);

		minReplace = (addOrReplaceMissingTypeCount > mustReplaceCount)
						? addOrReplaceMissingTypeCount
						: mustReplaceCount;

		System.out.println("minAdd = " + minAdd);
		System.out.println("minDel = " + minDel);
		System.out.println("minReplace = " + minReplace);

        /*
        A01234567890123456789aaaaaaaaa
        Aa0123456789aaaaaaaaa

        1 less char + missing upper/lower case or digit
        a1234
        A1234
        abcAB

        extra char + repeate char
        a0123456789AAA0123456
        a0123456789AAAA012345
        a0123456789AAAAA01234

        missing uppercase + repeate char
        0123456789aaa01234567
        0123456789aaaa0123456
        0123456789aaaaa012345

        missing lowercase + repeate char
        0123456789AAA01234567
        0123456789AAAA0123456
        0123456789AAAAA012345

        missing digit + repeate char
        abcabcabcAAAabcabcab
        abcabcabcAAAAabcabca
        abcabcabcAAAAAabcabc
        */

        /*
        count fixes

        aaa1aaa1aaa1aaa1aaa1aaa1aaa1
        */

		return minAdd + minReplace + minDel;
	}


	public static Map<String, Integer> testValues() {
		return new HashMap<String, Integer>() {{

			put("", Integer.valueOf(6));
			put("1", Integer.valueOf(5));
			put("A", Integer.valueOf(5));
			put("a", Integer.valueOf(5));

			put(".", Integer.valueOf(5));
			put("..", Integer.valueOf(4));
			put("...", Integer.valueOf(3));
			put("....", Integer.valueOf(3));
			put(".....", Integer.valueOf(3));
			put("......", Integer.valueOf(3));

			put("aa", Integer.valueOf(4));
			put("aaa", Integer.valueOf(3));
			put("aaaa", Integer.valueOf(2));
			put("aaaaa", Integer.valueOf(2));
			put("aaaaaa", Integer.valueOf(2));

			put("aaaaaaaaaaaaaaaaaaaa", Integer.valueOf(6));
			put("aaaaaaaaaaaaaaaaaaaaa", Integer.valueOf(7));
			put("aaaaaaaaaaaaaaaaaaaaaa", Integer.valueOf(8));
			put("aaaaaaaaaaaaaaaaaaaaaaa", Integer.valueOf(9));
			put("aaaaaaaaaaaaaaaaaaaaaaaa", Integer.valueOf(10));

			put("Aaa1", Integer.valueOf(2));
			put("Aa01234567890123456789", Integer.valueOf(2));

			put("AB12", Integer.valueOf(2));
			put("ab12", Integer.valueOf(2));
			put("abAB", Integer.valueOf(2));
			put("abab", Integer.valueOf(2));
			put("ABAB", Integer.valueOf(2));
			put("1212", Integer.valueOf(2));

			put("AB01234567890123456789", Integer.valueOf(3));
			put("ab01234567890123456789", Integer.valueOf(3));
			put("abcdefghijabcdefghijAB", Integer.valueOf(3));
			put("abcdefghijabcdefghijab", Integer.valueOf(4));
			put("ABCDEFGHIJABCDEFGHIJAB", Integer.valueOf(4));
			put("0123456789012345678901", Integer.valueOf(4));

			put("aaaaabbbb1234567890ABA", Integer.valueOf(3));
			put("aaaaaaaAAAAAA6666bbbbaaaaaaABBC", Integer.valueOf(13)); //aa***** AA_AA* 66** bb** aa_aa* ABBC
			put("aaa111", Integer.valueOf(2)); //aaa111
		}};
	}
}
