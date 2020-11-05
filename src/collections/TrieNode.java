/*
 * UNIVERSIDAD ICESI
 * TAREA INTEGRADORA 2 - ESTRUCTURAS DE DATOS
 * RODAS / DIAZ / MARTINEZ
 */

package collections;

import java.io.Serializable;

public class TrieNode implements Serializable{
	
	//------------------------------------------------------------------------------------
	
	// AUTO GENERATED
	private static final long serialVersionUID = -2562613102632456678L;
	
	private char character;
	
	private int endOfWords;
	
	private TrieNode[] children;
	
	private TrieNode parent;
	
	//------------------------------------------------------------------------------------
	
	// CONSTRUCTOR METHOD
	
	public TrieNode(char c) {
		
		character = c;
		
		endOfWords = 0;
		
		children = new TrieNode[Trie.ASCII_CHARACTER_COUNT];
		
	}
	
	//------------------------------------------------------------------------------------
	
	// SET'S METHODS

	public void setCharacter(char character) { this.character = character; }
	
	public void setEndOfWords(int endOfWords) { this.endOfWords = endOfWords; }
	
	public void setParent(TrieNode parent) { this.parent = parent; }
	
	//------------------------------------------------------------------------------------
	
	// GET'S METHODS
	
	public char getCharacter() { return character; }
	
	public int getEndOfWords() { return endOfWords; }
	
	public TrieNode[] getChildren() { return children; }
	
	public TrieNode getParent() { return parent; }
	
		
	//------------------------------------------------------------------------------------
		
}
