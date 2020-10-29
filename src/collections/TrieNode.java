package collections;

public class TrieNode {
	private char character;
	private int endOfWords;
	private TrieNode[] children;
	private TrieNode parent;
	
	public TrieNode(char c) {
		character = c;
		endOfWords = 0;
		children = new TrieNode[Trie.ASCII_CHARACTER_COUNT];
	}

	//Setters
	public void setCharacter(char character) { this.character = character; }
	public void setEndOfWords(int endOfWords) { this.endOfWords = endOfWords; }
	public void setParent(TrieNode parent) { this.parent = parent; }	
	
	//Getters
	public char getCharacter() { return character; }
	public int getEndOfWords() { return endOfWords; }
	public TrieNode[] getChildren() { return children; }
	public TrieNode getParent() { return parent; }

	
}
