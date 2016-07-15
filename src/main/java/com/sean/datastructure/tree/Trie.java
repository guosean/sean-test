package com.sean.datastructure.tree;

/**
 * @AUTHOR: guozb
 * @DATE: 2014-6-6
 */
public interface Trie {
	/**
	 * 
	 * @param branch
	 */
	void insertBranch(Branch branch);
	/**
	 * 
	 * @param chr
	 * @return
	 */
	Trie getBranch(char chr);
	/**
	 * 
	 * @return
	 */
	char getChar();

}
