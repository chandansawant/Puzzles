package com.practice.puzzles.leetcode.lc_challenge_2021.sep.week1;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Erect_The_Fence {

	private static int MAX_X = 100;
	private static int MAX_Y = 100;

	private static final Long OUTSIDE_POINTS = Long.valueOf(1);
	private static final Long INLINE_POINTS = Long.valueOf(2);

	int[][] nodes;

	private boolean[][] grid = new boolean[MAX_X + 1][MAX_Y + 1];
	private int minX = MAX_X;
	private int minY = MAX_Y;
	private int maxX = 0;
	private int maxY = 0;

	private Set<Point> perimeterPoints = new ConcurrentSkipListSet<>();

	public int[][] outerTrees(int[][] trees) {

		this.nodes = trees;

		setLimits();

		//add all nodes with [minX, *] to result
		perimeterPoints.addAll(getAllNodes(0, minX));

		//add all nodes with [*, minY] to result
		perimeterPoints.addAll(getAllNodes(1, minY));

		//add all nodes with [maxX, *] to result
		perimeterPoints.addAll(getAllNodes(0, maxX));

		//add all nodes with [*, maxY] to result
		perimeterPoints.addAll(getAllNodes(1, maxY));

		//start with [lowest X, minY] node
		//initial reference line between [lowest X, minY] and [minX, lowest Y]
		processEdge(new Point(getLowestXForY(minY), minY), new Point(minX, getLowestYForX(minX)), false);

		//start with [minX, highest Y] node
		//initial reference line between [minX, highest Y] and [lowest X, maxY]
		processEdge(new Point(minX, getHighestYForX(minX)), new Point(getLowestXForY(maxY), maxY), false);

		//start with [highest X, minY] node
		//initial reference line between [highest X, minY] and [maxX, lowest Y]
		processEdge(new Point(getHighestXForY(minY), minY), new Point(maxX, getLowestYForX(maxX)), true);

		//start with [maxX, highest Y] node
		//initial reference line between [maxX, highest Y] and [highest X, maxY]
		processEdge(new Point(maxX, getHighestYForX(maxX)), new Point(getHighestXForY(maxY), maxY), true);

		System.out.println("perimeterPoints = " + perimeterPoints);
		return getPoints(perimeterPoints);
	}

	private void setLimits() {

		for (int i = 0; i < nodes.length; ++i) {
			grid[nodes[i][0]][nodes[i][1]] = true;

			if (nodes[i][0] < minX)
				minX = nodes[i][0];

			if (nodes[i][0] > maxX)
				maxX = nodes[i][0];

			if (nodes[i][1] < minY)
				minY = nodes[i][1];

			if (nodes[i][1] > maxY)
				maxY = nodes[i][1];
		}
	}

	private void processEdge(Point lower, Point upper, boolean reverseCheck) {

		if (lower.equals(upper))
			return;

//		System.out.println("==== Try edge " + lower + " to " + upper + " with reverseCheck = " + reverseCheck);
		Map<Long, List<Point>> edgeStatus = getPointsOutsideOfLine(lower, upper, reverseCheck);
		List<Point> outsidePoint = edgeStatus.get(OUTSIDE_POINTS);
//		System.out.println("outsidePoint = " + outsidePoint.toString());

		if (outsidePoint.isEmpty()) {
//			System.out.println("Add " + lower + " and " + upper + " to perimeter");
			perimeterPoints.add(lower);
			perimeterPoints.add(upper);
			perimeterPoints.addAll(edgeStatus.get(INLINE_POINTS));
		} else {
			outsidePoint
					.forEach(point -> {
//						System.out.println("======== Try sub-edge " + lower + " to " + point + " with reverseCheck = " + reverseCheck);
						Map<Long, List<Point>> subEdgeStatus = getPointsOutsideOfLine(lower, point, reverseCheck);
						List<Point> outsidePointForSubEdge = subEdgeStatus.get(OUTSIDE_POINTS);
//						System.out.println("outsidePointForSubEdge = " + outsidePointForSubEdge.toString());

						if (outsidePointForSubEdge.isEmpty()) {
//							System.out.println("Add " + lower + " and " + point + " to perimeter");
							perimeterPoints.add(lower);
							perimeterPoints.add(point);
							perimeterPoints.addAll(subEdgeStatus.get(INLINE_POINTS));
							processEdge(point, upper, reverseCheck);
						}
					});
		}
	}

	private Map<Long, List<Point>> getPointsOutsideOfLine(Point lowerPoint, Point upperPoint, boolean reverseCheck) {

		List<Point> outsiderPoints = new ArrayList<>();
		List<Point> inlinePoints = new ArrayList<>();
		Map<Long, List<Point>> result = new HashMap<Long, List<Point>>() {{
											put(OUTSIDE_POINTS, outsiderPoints);
											put(INLINE_POINTS, inlinePoints);
										}};

		if (lowerPoint.equals(upperPoint))
			return result;

		Line referenceLine = new Line(lowerPoint, upperPoint);

		for (int i = 0; i < nodes.length; ++i) {
				Point p = new Point(nodes[i][0], nodes[i][1]);

				if (!lowerPoint.equals(p)
						&& !upperPoint.equals(p)) {
//					System.out.println("check point " + p);
					double xProjection = referenceLine.getProjectionOnX(p).doubleValue();

					if ((double)nodes[i][0] == xProjection) {
//						System.out.println("it's inline");
						inlinePoints.add(p);
					} else  if ((!reverseCheck && (double)nodes[i][0] < xProjection)
									|| (reverseCheck && (double)nodes[i][0] > xProjection)) {
						outsiderPoints.add(p);
//						System.out.println("it's outside");
					}
				}
			}

		return result;
	}

	private List<Point> getAllNodes(int index, int matchValue) {
		return getMatchedNodes(index, matchValue)
				.map(Point::new)
				.collect(Collectors.toList());
	}

	private Stream<int[]> getMatchedNodes(int index, int matchValue) {
		return Stream
				.of(nodes)
				.filter(node -> node[index] == matchValue);
	}

	private int getLowestXForY(int fixedY) {
		int x = minX;
		for (; !grid[x][fixedY]; ++x);
		return x;
	}

	private int getHighestXForY(int fixedY) {
		int x = maxX;
		for (; !grid[x][fixedY]; --x);
		return x;
	}

	private int getLowestYForX(int fixedX) {
		int y = minY;
		for (; !grid[fixedX][y]; ++y);
		return y;
	}

	private int getHighestYForX(int fixedX) {
		int y = maxY;
		for (; !grid[fixedX][y]; --y);
		return y;
	}

	private int[][] getPoints(Set<Point> points) {
		int[][] result = new int[points.size()][2];
		Point[] pointArr = points.toArray(new Point[0]);

		for (int i = 0; i < points.size(); ++i)
			result[i] = pointArr[i].getXY();

		return result;
	}

	public static class Point implements Comparable {

		private final int x;
		private final int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public Point(int[] xy) {
			this.x = xy[0];
			this.y = xy[1];
		}

		public int getX() { return x; }
		public int getY() { return y; }
		public int[] getXY() { return new int[] {x, y}; }
		public String toString() { return "[" + x + ", " + y + "]"; }

		@Override
		public boolean equals(Object other) {
			if (Objects.isNull(other)
					|| !(other instanceof Point))
				return false;

			Point otherPoint = (Point)other;

			return this.x == otherPoint.x
					&& this.y == otherPoint.y;
		}

		@Override
		public int hashCode() {
			return Objects.hash(x, y);
		}

		@Override
		public int compareTo(Object other) {
			Point o = (Point)other;
			return (this.x < o.x)
					? -1
					: (this.x > o.x)
						? 1
						: (this.y < o.y)
							? -1
							: (this.y > o.y) ? 1 : 0;
		}
	}

	private static class Line {

		private BigDecimal m;
		private BigDecimal c;

		public Line(Point p1, Point p2) {
			m = BigDecimal.valueOf((double)(p1.getY() - p2.getY()) / (p1.getX() - p2.getX()));
			c = BigDecimal.valueOf(p1.getY()).subtract(m.multiply(BigDecimal.valueOf(p1.getX())));
		}

		public BigDecimal getProjectionOnX(Point p) {
			return BigDecimal.valueOf(p.y).subtract(c).divide(m, 2, RoundingMode.HALF_UP);
		}
	}
}
