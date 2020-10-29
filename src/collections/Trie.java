package collections;

import java.io.Serializable;
import java.util.ArrayList;

public class Trie implements TrieInterface, Serializable {

	//Auto generated
	private static final long serialVersionUID = -8221686898088440044L;

	public static final int ASCII_CHARACTER_COUNT = 128;
	
	private TrieNode root;		
	private int storedWords;	
	
	public Trie() {
		root = new TrieNode(' ');
		storedWords = 0;
	}

	//Adapted from geek for geeks
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
		}
		else {
			return false;
		}
	}

	
	public boolean remove(String s) {
		if(storedWords > 0 && s.length() > 0) {
			
			TrieNode currentNode = root;		
			TrieNode[] children = root.getChildren();
			int level;
			
			//Checks if word exists and gets to last node
			for(level = 0; level < s.length(); level++) {
				int index = s.charAt(level);
				children =  currentNode.getChildren();			
				
				if(children[index] == null) 
					return false;
				
				currentNode = children[index];			
			}
			
			//Case 0: If there multiple additions of the word or currentNode is not end of word
			
			if(currentNode.getEndOfWords() == 0) {
				return false;
			}
			
			if(currentNode.getEndOfWords() > 1) {
				currentNode.setEndOfWords(currentNode.getEndOfWords() - 1);
				storedWords--;
				return true;				
			}			
			
			//Case 1: last word node has at least one child
			for(TrieNode tN: children) {
				if(tN != null) {
					currentNode.setEndOfWords(currentNode.getEndOfWords() - 1);	
					storedWords--;
					return true;
				}
			}
			
			//Case 2: last word node has no children, disconnects until parent has children different from this
			//At this point currentNode will never be root
			boolean hasChildren = false;	
			
			currentNode = currentNode.getParent();	
			level--;
			
			while(level >= 0 && !hasChildren) {
				children = currentNode.getChildren();
				children[s.charAt(level+1)] = null;
				
				for (int i = 0; i < children.length && !hasChildren; i++) {
					if(children[i] != null) {
						hasChildren = true;
					}
				}						
				
				currentNode = currentNode.getParent();
				level--;
			}
			
			storedWords--;
			return true;
		}
		else {
			return false;
		}
	}


	//Adapted from geeks for geeks
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

	@Override
	public boolean isEmpty() {
		return storedWords==0;
	}
	
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

	private ArrayList<String> getPredictionsRecursive(String prefix, TrieNode currentNode, ArrayList<String> predictions) {
		if(currentNode.getEndOfWords() > 0) {
			for (int i = 0; i < currentNode.getEndOfWords(); i++) {
				predictions.add(prefix + currentNode.getCharacter());
			}
			return predictions;
		}
		else {
			TrieNode[] children = currentNode.getChildren();
			
			for (int i = 0; i < children.length; i++) {
				if(children[i] != null) {
					predictions = getPredictionsRecursive(prefix + currentNode.getCharacter(),children[i],predictions);
				}
			}
			
			return predictions;
		}
	}
	
	
}
