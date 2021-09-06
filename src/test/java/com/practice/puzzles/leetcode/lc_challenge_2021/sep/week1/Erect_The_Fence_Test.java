package com.practice.puzzles.leetcode.lc_challenge_2021.sep.week1;

import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Erect_The_Fence_Test {

	@Test
	public void testOuterTrees() {
		singleDot();
		dotInTiltedSquare();
		test1();
		test2();
		test3();
	}

	/*
			9 . . . . . . . . . .
			8 . . . . . . . . . .
			7 . . . . . . . . . .
			6 . . . . . . . . . .
			5 . . . . . . . . . .
			4 . . . . . . . . . .
			3 . . . . . . . . . .
			2 . . . . . . . . . .
			1 . . . . . . . . . .
			0 . . . . . . . . . .
			  0 1 2 3 4 5 6 7 8 9
		 */

	private void test1() {
		/*
			4 . T . .   =>  4 . T . .
			3 . . T .       3 . . T .
			2 . T . T       2 . . . T
			1 T . . .       1 T . . .
			0 . T . .       0 . T . .
			  1 2 3 4         1 2 3 4
		 */

		assertResults(
				new int[][] {{1,1}, {2,0}, {3,3}, {2,4}, {4,2}},
				new Erect_The_Fence().outerTrees(new int[][] {{1,1}, {2,2}, {2,0}, {2,4}, {3,3}, {4,2}}));
	}

	private void test3() {
		/*
			9 . . T . . . . . . .   =>  9 . . T . . . . . . .
			8 T . T T . . . . . T       8 T . . . . . . . . T
			7 . . T . . . T . T .       7 . . . . . . . . . .
			6 . T . . T . . . . .       6 . . . . . . . . . .
			5 . . . . . . . . T T       5 . . . . . . . . . T
			4 . . . . T . . T T .       4 . . . . . . . . . .
			3 . T . . . . . . . .       3 . . . . . . . . . .
			2 T . . . . T . T . .       2 T . . . . . . . . .
			1 T . . . . . T T . .       1 T . . . . . . T . .
			0 . T . . . . . . . .       0 . T . . . . . . . .
			  0 1 2 3 4 5 6 7 8 9         0 1 2 3 4 5 6 7 8 9
		 */

//		[5, 2], [6, 1], [7, 2]
		assertResults(
				new int[][] {{9,5}, {2,9}, {0,1}, {9,8}, {0,8}, {1,0}, {0,2}, {7,1}},
				new Erect_The_Fence().outerTrees(
						new int[][] {
								{0,1}, {0,2}, {0,8}, {1,0}, {1,3}, {1,6}, {2,7}, {2,8}, {2,9},
								{3,8}, {4,4}, {4,6}, {5,2}, {6,1}, {6,7}, {7,1}, {7,2}, {7,4},
								{8,4}, {8,5}, {8,7}, {9,5}, {9,8}}));
	}

	private void singleDot() {
		/*
			. . .       . . .
			. T .   =>  . T .
			. . .       . . .
		 */

		int[][] expected = new int[][] {{1, 1}};
		int[][] actual = new Erect_The_Fence().outerTrees(expected);

//		assertEquals(1, actual.length);
//		assertEquals(1, actual[0][0]);
//		assertEquals(1, actual[0][1]);
	}

	private void dotInTiltedSquare() {
		/*
			. T .       . T .
			T T T   =>  T . T
			. T .       . T .
		 */

		int[][] actual = new Erect_The_Fence().outerTrees(new int[][] {{2, 1}, {1, 2}, {2, 2}, {3, 2}, {2, 3}});

		int[][] expected = new int[][] {{2, 1}, {1, 2}, {3, 2}, {2, 3}};
//		assertEquals(1, actual.length);
//		assertEquals(1, actual[0][0]);
//		assertEquals(1, actual[0][1]);
	}

	private void test2() {
		/*
			9 . . . . . . . T       9 . . . . . . . T
			  . . . . . . . T         . . . . . . . T
			  . . . . . . T .   =>    . . . . . . . .
			  T . . . . . . .         T . . . . . . .
			  . . . . . . . .         . . . . . . . .
			4 . . . . . . . .       4 . . . . . . . .
			3 . . . . . . T .       3 . . . . . . T .
			  2 3 4         9
		 */

		assertResults(
				new int[][] {{9,8}, {2,6}, {9,9}, {8,3}},
				new Erect_The_Fence().outerTrees(new int[][] {{8,3}, {9,8}, {2,6}, {8,7}, {9,9}}));
	}

	private void assertResults(int[][] expected, int[][] actual) {
		assertEquals(getPoints(expected), getPoints(actual));
	}

	private Set<Erect_The_Fence.Point> getPoints(int[][] points) {
		return new ConcurrentSkipListSet(
						Stream
							.of(points)
							.map(xy -> new Erect_The_Fence.Point(xy[0], xy[1]))
							.collect(Collectors.toSet()));
	}
}
