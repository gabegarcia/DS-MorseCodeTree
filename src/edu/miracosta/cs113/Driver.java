package edu.miracosta.cs113;

import java.util.Scanner;

public class Driver {
	public static void main(String args[]) {
		
		MorseCodeTree newTree = new MorseCodeTree();
				
		final String FULL_INPUT = "A \nB \nnull \nnull \nC \nD \nnull \nnull \nE \nnull \nnull";
		BinaryTree<String> testTree = BinaryTree.readBinaryTree(new Scanner(FULL_INPUT));
		
		System.out.println("newTree: " + newTree);
		//System.out.println("testTree: " + testTree);
		//newTree.toString();
		
		System.out.println(newTree.translateFromMorseCode("-"));
	}

}
