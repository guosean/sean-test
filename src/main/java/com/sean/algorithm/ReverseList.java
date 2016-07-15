package com.sean.algorithm;

public class ReverseList {
	
	static class Node{
		 String str;
		 Node next;
	}
	 
	 public static void main(String[] args) {
		Node node = new Node();
		node.str = "0";
		Node current = node;
		for(int i=1; i<10; i++){
			Node tmp = new Node();
			tmp.str = String.valueOf(i);
			current.next = tmp;
			current = tmp;
		}
		/*while(null != node){
			System.out.println(node.str);
			node = node.next;
		}*/
		Node head = node;
		Node pre = node;
		Node cur = node.next;
		Node tmpNext = null;
		while(cur !=null){
			tmpNext = cur.next;
			cur.next = pre;
			pre = cur;
			cur = tmpNext;
		}
		head.next = null;
		head = pre;
		while(head != null){
			System.out.println(head.str);
			head = head.next;
		}
		// a->b->c->d
		// a <- b <- c <- d
	}

}
