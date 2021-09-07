package com.practice.puzzles.leetcode.lc_challenge_2021.sep.week1;

import java.util.Objects;

public class Reverse_Linked_List {

	public ListNode reverseList(ListNode head) {

		ListNode prevNode = null;
		ListNode currNode = head;
		ListNode nextNode = (Objects.isNull(head)) ? head : head.next;

		while (Objects.nonNull(currNode)) {
			currNode.next = prevNode;

			prevNode = currNode;
			currNode = nextNode;

			if (Objects.nonNull(nextNode))
				nextNode = nextNode.next;
		}

		head = prevNode;
		return head;
	}

	public static class ListNode {

		private int val;
		private ListNode next;

		public ListNode() {}
		public ListNode(int val) { this.val = val; }
		public ListNode(int val, ListNode next) { this.val = val; this.next = next; }

		@Override
		public boolean equals(Object node) {

			if (Objects.isNull(node)
					|| !(node instanceof ListNode))
				return false;

			ListNode listNode = (ListNode)node;

			return this.val == listNode.val
					&& this.next == listNode.next;
		}

		@Override
		public String toString() {

			StringBuilder sb = new StringBuilder("[")
									.append(val);

			ListNode node = this.next;

			while (Objects.nonNull(node)) {
				sb.append(", ");
				sb.append(node.val);
				node = node.next;
			}

			return sb.append("]").toString();
		}
	}
}
