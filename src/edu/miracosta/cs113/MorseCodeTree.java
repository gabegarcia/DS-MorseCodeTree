package edu.miracosta.cs113;

import java.io.*;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.StringTokenizer;
/**
 * MorseCodeTree : A BinaryTree, with Nodes of type Character to represent each letter of the English alphabet,
 * and a means of traversal to be used to decipher Morse code.
 *
 * @version 1.0
 */
public class MorseCodeTree extends BinaryTree<Character> {
	
    // TODO:
    // Build this class, which includes the parent BinaryTree implementation in addition to
    // the `translateFromMorseCode` and `readMorseCodeTree` methods. Documentation has been suggested for the former,
    // where said exceptional cases are to be handled according to the corresponding unit tests.
	
	
	public Node<Character> root;
	//Constructor
	public MorseCodeTree() {
				
		root = new Node<>('n'); //set the root of the tree to something.
		
		createTreeFromFile();
	}
	
	public void createTreeFromFile() {
				
		
		try {
			File myFile = new File("MorseCode.txt");
			Scanner myReader = new Scanner(myFile);
			
			//read letters and Morse Code from file
			while(myReader.hasNextLine()) {
				String data = myReader.nextLine();
				
				String[] arrData = data.split(" "); //split data with space(" ") delimiter
				
				String letter = arrData[0]; //set 'letter' to the first char
				
				String morseCode = arrData[1]; //set morseCode to the string after the space(" ")
				
				//verify things are working up this point
				System.out.println("letter: " + letter);
				System.out.println("morseCode: " + morseCode);
				
				insertNodeIntoTree(data);
			}
		
			myReader.close();
		
		} catch(FileNotFoundException e) {
			System.out.println("Something went wrong.");
			e.printStackTrace();
		}
		
		
		
	
	}
	
	/*public void insertNodeIntoTree(String letter, String morseCode) {
		
		Node<Character> tempRoot = root;
		char tempStr;
		
		//iterate through the Morse Code
		for(int i = 2; i < morseCode.length() - 1; i++) {
			//tempStr = morseCode.charAt(i);
			
			if(morseCode.charAt(i) == '*') {
				
				tempRoot = tempRoot.left;
				
			} else if (morseCode.charAt(i) == '-') {
				
				tempRoot = tempRoot.right;
				
			} else if(morseCode.charAt(i) != '*' && morseCode.charAt(i) != '-')
            {

            }
			
		}
		
		//insert the node
		
		if(morseCode.charAt(morseCode.length() - 1) == '*') {
			
			Node<Character> newNodeToInsert = new Node<Character>(letter.charAt(0));
			tempRoot.left = newNodeToInsert;
			System.out.println("tempRoot.left: " + tempRoot.left);
			
		} else if 
			(morseCode.charAt(morseCode.length() - 1) == '-') {
			
			Node<Character> newNodeToInsert = new Node<Character>(letter.charAt(0));
			tempRoot.right = newNodeToInsert;
			System.out.println("tempRoot.right: " + tempRoot.right);
			
		}
	}*/
	
	//Professor code:
	 public void insertNodeIntoTree(String nextLineFromTextFile)
	    {
	        String [] arrayOfString = nextLineFromTextFile.split("");
	        Node<Character> tmpRoot = root;

	        //get to the nodes location for nodes deeper than 1 level.
	        for(int i = 2; i<arrayOfString.length-1; i++)
	        {
	            if(arrayOfString[i].equals("*"))
	            {
	                tmpRoot = tmpRoot.left;
	            }
	            else if(arrayOfString[i].equals("-"))
	            {
	                tmpRoot = tmpRoot.right;
	            }
	            else if(arrayOfString[i].equals("null"))
	            {

	            }

	        }
	        //insert the node.
	        if(arrayOfString[arrayOfString.length-1].equals("*"))
	        {
	            Node<Character> nodeToInsert = new Node<Character>(arrayOfString[0].charAt(0));
	            tmpRoot.left = nodeToInsert;
	        }
	        else if(arrayOfString[arrayOfString.length-1].equals("-"))
	        {
	            Node<Character> nodeToInsert = new Node<Character>(arrayOfString[0].charAt(0));
	            tmpRoot.right = nodeToInsert;
	        }
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
    /*public String translateFromMorseCode(String morseCode) {
        return "";
    }*/
	
	//Professor's code:
	
	private char decodeCharacterHelper(String message, Node<Character> nodeOfTree)
    {
        char current;
        if(message.length() > 0)
        {
            current = message.charAt(0);
            message = message.substring(1,message.length());
        }
        else
        {
            if(nodeOfTree == null)
            {
                return '!';
            }
            return nodeOfTree.data;
        }
        //beggin the search.
        if(current == '*')
        {
            if(nodeOfTree == null)
            {
                return '!';
            }
            nodeOfTree = nodeOfTree.left;
            return decodeCharacterHelper(message ,nodeOfTree);
        }
        else if(current == '-')
        {
            if(nodeOfTree == null)
            {
                return '!';
            }
            nodeOfTree = nodeOfTree.right;
            return decodeCharacterHelper(message,nodeOfTree);
        }
        return nodeOfTree.data;
    }

    public char decodeCharacter(String message)
    {
        return decodeCharacterHelper(message,root);
    }


    public String translateFromMorseCode(String morseCode)
    {
        //check to see if appropriate characters are used.
        for(int i = 0; i < morseCode.length(); i++)
        {
            if((morseCode.charAt(i) != '-') && (morseCode.charAt(i) != '*')&& (morseCode.charAt(i) != ' ') )
            {
                throw new InputMismatchException();
            }
        }
        StringTokenizer test = new StringTokenizer(morseCode, " ");
        String message = "";
        while (test.hasMoreTokens())
        {
            char tmp = decodeCharacter(test.nextToken());
            if(tmp == '!')
            {
                throw new InputMismatchException();
            }
            message += tmp;
        }
        return message;
    }

} // End of class MorseCodeTree