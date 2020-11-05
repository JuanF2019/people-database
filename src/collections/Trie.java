/*
 * UNIVERSIDAD ICESI
 * TAREA INTEGRADORA 2 - ESTRUCTURAS DE DATOS
 * RODAS / DIAZ / MARTINEZ
 */

package collections;

import java.io.Serializable;
import java.util.ArrayList;

public class Trie implements TrieInterface, Serializable {
	
	//------------------------------------------------------------------------------------

	// AUTO GENERATED
	
	private static final long serialVersionUID = -8221686898088440044L;

	public static final int ASCII_CHARACTER_COUNT = 128;
	
	private TrieNode root;	
	
	private int storedWords;
	
	//------------------------------------------------------------------------------------
	
	// CONSTRUCTOR METHOD
	
	public Trie() {
		
		root = new TrieNode(' ');
		
		storedWords = 0;
		
	}
	
	//------------------------------------------------------------------------------------

	//Adapted from "GEEK" for "GEEKS"
	//Inserts each of s characters as a single node, marks last node as end of word
	//It is not necessary to store character in node but we will still keep it there
	//Always adds except when s length is 0
	
	public boolean add(String s) {
		
		if(s.length() > 0) {
			
			TrieNode currentNode = root;
			
			for(int level = 0; level < s.length(); level++) {
				
				int index = s.charAt(level);
				
				TrieNode[] children =  currentNode.getChildren();			
				
				if(children[index] == null) {
					
					children[index] = new TrieNode((char) index);
					
					children[index].setParent(currentNode);
					
				}
				
				currentNode = children[index];	
				
			}
			
			currentNode.setEndOfWords(currentNode.getEndOfWords() + 1);	
				
			storedWords++;		
			
			return true;
			
		} else {
			
			return false;
			
		}
		
	}
	
	//------------------------------------------------------------------------------------
	
	// REMOVE METHOD FROM THE TRIE CLASS	
	
	public boolean remove(String word) {		
		if(remove(word,root,word.length(),0)) {
			storedWords--;
			return true;
		}
		else {
			return false;
		}
	}

	//ADAPTED FROM GEEKS FOR GEEKS
	private boolean remove(String word, TrieNode currentNode, int length, int level) {
		
		if(currentNode == null) {
			return false;
		}
		
		if(level == length){
			
			if(currentNode.getEndOfWords() > 1) {
				
				currentNode.setEndOfWords(currentNode.getEndOfWords()-1);
				return true;
				
			}
			else if(currentNode.getEndOfWords() == 1) {
				
				if(hasChildren(currentNode)) {
					currentNode.setEndOfWords(0);
					return true;
				}		
				else {
					currentNode.getParent().getChildren()[word.charAt(level-1)] = null;
					return true;
				}		
				
			}
			else {
				
				return false;
				
			}				
		}
		
		boolean removed = remove(word,currentNode.getChildren()[word.charAt(level)],length,level + 1);
		
		if(removed && currentNode != root && !hasChildren(currentNode) && currentNode.getEndOfWords() == 0) {
			currentNode.getParent().getChildren()[word.charAt(level-1)] = null;
			
		}
		
		return removed;
	}
	
	//------------------------------------------------------------------------------------
	
	// HAS CHILDREN METHOD FROM THE TRIE CLASS
	
	private boolean hasChildren(TrieNode nd) {
		TrieNode[] children = nd.getChildren();
		for(TrieNode child : children) {
			if(child != null) {
				return true;
			}
		}
		return false;
	}
	
	//------------------------------------------------------------------------------------

	//Adapted from "GEEK" for "GEEKS"
	
	public boolean search(String s) {
		
		TrieNode currentNode = root;
		
		for(int level = 0; level < s.length(); level++) {
			
			int index = s.charAt(level);
			
			TrieNode[] children =  currentNode.getChildren();			
			
			if(children[index] == null)
				return false;
			
			currentNode = children[index];	
			
		}
		
		return currentNode.getEndOfWords() > 0;
		
	}

	//------------------------------------------------------------------------------------
	
	// IS EMPTY METHOD 
	
	@Override
	public boolean isEmpty() {
		
		return storedWords==0;
		
	}
	
	//------------------------------------------------------------------------------------
	
	// GET PREDICTIONS METHOD FROM THE TRIE CLASS
	
	public ArrayList<String> getPredictions(String prefix) {
		
		TrieNode currentNode = root;
		
		for(int level = 0; level < prefix.length(); level++) {
			
			int index = prefix.charAt(level);
			
			TrieNode[] children =  currentNode.getChildren();	
			
			
			if(children[index] == null)
				return null;
			
			currentNode = children[index];
			
		}		
		
		return getPredictionsRecursive(prefix,currentNode,new ArrayList<String>());
		
	}
	
	//------------------------------------------------------------------------------------

	private ArrayList<String> getPredictionsRecursive(String prefix, TrieNode currentNode, ArrayList<String> predictions) {
		
		if(currentNode.getEndOfWords() > 0) {
			
			for (int i = 0; i < currentNode.getEndOfWords(); i++) {
				
				predictions.add(prefix + currentNode.getCharacter());
				
			}
			
			return predictions;
			
		} else {
			
			TrieNode[] children = currentNode.getChildren();
			
			
			for (int i = 0; i < children.length; i++) {
				
				if(children[i] != null) {
					
					predictions = getPredictionsRecursive(prefix + currentNode.getCharacter(),children[i],predictions);
				
				}
				
			}
			
			return predictions;
			
		}
		
	}

	//------------------------------------------------------------------------------------

	
	public TrieNode getRoot() {
		return root;
	}
	
	//------------------------------------------------------------------------------------
	
	public int getStoredWordsCount() {
		return storedWords;
	}
}
