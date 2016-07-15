package com.sean.datastructure.tree;

import java.util.Random;

import lombok.extern.slf4j.Slf4j;

import org.junit.Test;

/**
 * @AUTHOR: guozb
 * @DATE: 2014-6-11
 */
@Slf4j
public class TestTrie {
	private Trie trie = new TreeRoot(55);
	@Test
	public void testTrie(){
		Random rd = new Random();
		for (int i = 0; i < 1000; i++) {
			int content = rd.nextInt(100);
			char[] chrs = String.valueOf(content).toCharArray();
//			log.info(String.valueOf(content));
			testInsert(chrs);
		}
		for (int i = 0; i < 1000; i++) {
			int content = rd.nextInt(100);
			char[] chrs = String.valueOf(content).toCharArray();
//			log.info(String.valueOf(content)+":"+testSearch(chrs));
		}
		/*for (int i = 0; i < chrs.length; i++) {
			trie.insertBranch(new Branch(chrs[i]));
		}*/
		
//		Trie branch = trie.getBranch('1');
		
	}
	
	public boolean testSearch(char[] chrs){
		boolean isFound = false;
		if(null == chrs || chrs.length==0){
			return isFound;
		}
		Trie first = trie.getBranch(chrs[0]);
		if(first != null){
			int i = 1;
			for (; first != null && i < chrs.length; i++) {
				first = first.getBranch(chrs[i]);
				System.out.println(null == first?"":first.getChar());
			}
			if(i == chrs.length){
				isFound = true;
			}
		}
		
		return isFound;
	}
	
	public void testInsert(char[] chrs){
		if(null == chrs || chrs.length==0){
			return;
		}
		Trie first = trie.getBranch(chrs[0]);
		if(first == null){
			trie.insertBranch(new Branch(chrs[0]));
		}
		first = trie.getBranch(chrs[0]);
		if(null != first){
			Trie branch = null;
			for (int i = 1; i < chrs.length; i++) {
				branch = first.getBranch(chrs[i]);
				if(null == branch){
					first.insertBranch(new Branch(chrs[i]));
				}
				first = first.getBranch(chrs[i]);
			}
		}
		
	}

}
