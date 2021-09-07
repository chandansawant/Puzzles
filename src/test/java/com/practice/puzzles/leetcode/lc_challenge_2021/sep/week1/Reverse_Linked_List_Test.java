package com.practice.puzzles.leetcode.lc_challenge_2021.sep.week1;

import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Reverse_Linked_List_Test {

	private Reverse_Linked_List cut = new Reverse_Linked_List();

	@Test
	public void test_1() {

		assertEquals(prepareLinkedList(null),
						cut.reverseList(prepareLinkedList(null)));

		assertEquals(prepareLinkedList().toString(),
						cut.reverseList(prepareLinkedList()).toString());

		assertEquals(prepareLinkedList(1).toString(),
						cut.reverseList(prepareLinkedList(1)).toString());

		assertEquals(prepareLinkedList(5, 4, 3, 2, 1).toString(),
						cut.reverseList(prepareLinkedList(1, 2, 3, 4, 5)).toString());

		assertEquals(prepareLinkedList(2, 1).toString(),
						cut.reverseList(prepareLinkedList(1, 2)).toString());
	}

	private Reverse_Linked_List.ListNode prepareLinkedList(int... nodes) {

		if (Objects.isNull(nodes))
			return null;

		if (0 == nodes.length)
			return new Reverse_Linked_List.ListNode();

		Reverse_Linked_List.ListNode head = null;
		Reverse_Linked_List.ListNode prevNode = null;

		for (int i = nodes.length - 1; i >=0; --i) {
			head = new Reverse_Linked_List.ListNode(nodes[i], prevNode);
			prevNode = head;
		}

		return head;
	}
}
