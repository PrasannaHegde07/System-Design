package com.studyds;

import java.util.HashMap;
import java.util.Map;

public class LRU {

	static int memSize = 20;
	static DoubleNode tailNode = null;
	static DoubleNode headNode = null;

	static Map<Integer, DoubleNode> map = new HashMap<>();

	public void put(Integer data) {

		if (map.containsKey(data)) {
			DoubleNode currentNode = map.get(data);

			if (tailNode.data == data)
				return;

			currentNode.next.prev = currentNode.prev;
			currentNode.prev.next = currentNode.next;
			currentNode.next = null;
			insertNodeAtTail(tailNode, currentNode);

		} else {
			DoubleNode newNode = new DoubleNode(data);

			if (map.size() < memSize) {

				insertNodeAtTail(tailNode, newNode);
				map.put(data, newNode);

			} else {
				deleteNodeAtHead();
				insertNodeAtTail(tailNode, newNode);
			}

		}

	}

	public DoubleNode get(Integer data) {

		if (!map.containsKey(data))
			return null;

		if (tailNode.data == data)
			return map.get(data);

		DoubleNode currentNode = map.get(data);
		currentNode.next.prev = currentNode.prev;
		currentNode.prev.next = currentNode.next;
		currentNode.next = null;
		insertNodeAtTail(tailNode, currentNode);

		return currentNode;

	}

	private DoubleNode deleteNodeAtHead() {

		DoubleNode temp = headNode;
		DoubleNode deleteNode = temp;
		temp = temp.next;
		temp.prev = null;
		headNode = temp;
		return deleteNode;
	}

	private void insertNodeAtTail(DoubleNode tail, DoubleNode newNode) {

		if (tail == null) {
			tailNode = newNode;
			headNode = newNode;
		} else {
			tail.next = newNode;
			newNode.prev = tail;
			tailNode = newNode;

		}

	}

	private void print() {
		DoubleNode temp = headNode;
		while (temp != null) {

			System.out.print(temp.data);
			temp = temp.next;
			if (temp != null)
				System.out.print("->");

		}
		System.out.println("->null");

	}

	public static void main(String[] args) {

		LRU lru = new LRU();

		lru.put(10);
		lru.put(11);
		lru.put(12);
		lru.put(13);
		lru.put(14);

		lru.print();

		lru.put(12);

		lru.print();

		lru.put(12);

		lru.print();

		lru.get(11);

		lru.print();

		lru.put(15);

		lru.print();

	}

}
