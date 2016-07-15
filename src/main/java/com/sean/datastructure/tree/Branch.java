package com.sean.datastructure.tree;

import lombok.Getter;

import com.google.common.base.Preconditions;

/**
 * @AUTHOR: guozb
 * @DATE: 2014-6-6
 */
public class Branch implements Trie {
	@Getter
	private char content;
	/**
	 * 子分支
	 */
	private Branch[] subBranches;
	
	private static int len = 55;
	/**
	 * 
	 * @param chr
	 */
	public Branch(char chr){
		this.content = chr;
	}

	/* (non-Javadoc)
	 * @see com.sean.datastructure.tree.Trie#insertBranch(com.sean.datastructure.tree.Branch)
	 */
	@Override
	public void insertBranch(Branch branch) {
		if(null != branch){
			if(subBranches==null){
				subBranches = new Branch[len];
			}
			if(branch.getChar() < len && null == subBranches[branch.getChar()]){
				subBranches[branch.getChar()] = branch;
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.sean.datastructure.tree.Trie#getBranch(char)
	 */
	@Override
	public Trie getBranch(char chr) {
		if(null != subBranches){
			if(chr < len){
				Branch rt = subBranches[chr];
				if(null != rt){
					return rt;
				}
			}
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.sean.datastructure.tree.Trie#getChar()
	 */
	@Override
	public char getChar() {
		return content;
	}

}
