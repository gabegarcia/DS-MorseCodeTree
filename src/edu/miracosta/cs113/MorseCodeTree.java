package edu.miracosta.cs113;

import java.io.*;
import java.util.Scanner;

/**
 * MorseCodeTree : A BinaryTree, with Nodes of type Character to represent each letter of the English alphabet,
 * and a means of traversal to be used to decipher Morse code.
 *
 * @version 1.0
 */
public class MorseCodeTree<E> extends BinaryTree<Character> implements Serializable{
	
    // TODO:
    // Build this class, which includes the parent BinaryTree implementation in addition to
    // the `translateFromMorseCode` and `readMorseCodeTree` methods. Documentation has been suggested for the former,
    // where said exceptional cases are to be handled according to the corresponding unit tests.
	
	BinaryTree<Character> myTree;
	
	//Constructor
	public MorseCodeTree() {
		myTree = new BinaryTree<Character>(null, new BinaryTree<Character>(), new BinaryTree<Character>());
		
		createTreeFromFile();
	}
	
	public void createTreeFromFile() {
		BinaryTree<Character> subTree = new BinaryTree<Character>(null, new BinaryTree<Character>(), new BinaryTree<Character>());
		char currentChar, lastChar; //chars from morseCode
		
		try {
			File myFile = new File("MorseCode.txt");
			Scanner myReader = new Scanner(myFile);
			
			//read letters and Morse Code from file
			while(myReader.hasNextLine()) {
				String data = myReader.nextLine();
				String[] arrData = data.split(" ");
				Character letter = arrData[0].charAt(0);
				String morseCode = arrData[1];
				System.out.println("letter: " + letter);
				System.out.println("morseCode: " + morseCode);
				//check morseCode to determine where to place letter
				//find subtree
				for(int i = 0; i < morseCode.length(); i++) {
					currentChar = morseCode.charAt(i);
					System.out.println("currentChar: " + currentChar);
					if(currentChar == '*') {
						subTree = subTree.getLeftSubtree();
					} else
					{
						subTree = subTree.getRightSubtree();
					}
				}
				
				lastChar = morseCode.charAt(morseCode.length() - 1);
				
				if(lastChar == '*') {
					
					subTree.addLeft(letter);
					
				} else if(lastChar == '-') {
					
					subTree.addRight(letter);
				}
			}
		
			myReader.close();
		
		} catch(FileNotFoundException e) {
			System.out.println("Something went wrong.");
			e.printStackTrace();
		}
	}
	
	public void insertNodeIntoTree(String letter) {
		Scanner scan = new Scanner(letter);
		readBinaryTree(scan);
	}
    /**
     * Non-recursive method for translating a String comprised of morse code values through traversals
     * in the MorseCodeTree.
     *
     * The given input is expected to contain morse code values, with '*' for dots and '-' for dashes, representing
     * only letters in the English alphabet.
     *
     * This method will also handle exceptional cases, namely if a given token's length exceeds that of the tree's
     * number of possible traversals, or if the given token contains a character that is neither '*' nor '-'.
     *
     * @param morseCode The given input representing letters in Morse code
     * @return a String representing the decoded values from morseCode
     */
    public String translateFromMorseCode(String morseCode) {
        return "";
    }

} // End of class MorseCodeTree