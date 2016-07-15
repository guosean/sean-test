package com.sean.datastructure.tree;

/**
 * @AUTHOR: guozb
 * @DATE: 2014-6-6
 */
public class TreeRoot implements Trie {
	
	private Branch[] roots;
	
	public TreeRoot(final int size){
		roots = new Branch[size];
	}

	/* (non-Javadoc)
	 * @see com.sean.datastructure.tree.Trie#insertBranch(com.sean.datastructure.tree.Branch)
	 */
	@Override
	public void insertBranch(Branch branch) {
		if(null != branch){
			char chr = branch.getChar();
			if(chr < roots.length && null == roots[chr]){
				roots[chr] = branch;
			}
		}

	}

	/* (non-Javadoc)
	 * @see com.sean.datastructure.tree.Trie#getBranch(char)
	 */
	@Override
	public Trie getBranch(char chr) {
		if(null != roots){
			if(chr < roots.length){
				Branch rt = roots[chr];
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
		return 0;
	}

}
