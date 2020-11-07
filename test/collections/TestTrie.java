/*
 * UNIVERSIDAD ICESI
 * TAREA INTEGRADORA 2 - ESTRUCTURAS DE DATOS
 * RODAS / DIAZ / MARTINEZ
 */

package collections;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Hashtable;

import org.junit.jupiter.api.Test;

class TestTrie {

	//------------------------------------------------------------------------------------
	
	// Relation with the class TRIE

	Trie trie;

	//------------------------------------------------------------------------------------
	
	// Set up 1

	void setUp1() {

		trie = new Trie();

	}

	//------------------------------------------------------------------------------------
	
	// Set up 2

	void setUp2() {

		trie = new Trie();

		trie.add("Ana");
		trie.add("Anita");
		trie.add("Felix");
		trie.add("Zelda");	

	}

	//------------------------------------------------------------------------------------
	
	// Set up 3

	void setUp3() {

		trie = new Trie();

		trie.add("AAAA");
		trie.add("AAA");
		trie.add("AABB");
		trie.add("AB");	
		trie.add("ABBD");
		trie.add("CA");
		trie.add("CAI");
		trie.add("CHI");	

	}

	//------------------------------------------------------------------------------------
	
	// Set up 4

	void setUp4() {

		trie = new Trie();

		for(int i = 0; i< 2; i++) {
			
			trie.add("AAAA");
			trie.add("AAA");
			trie.add("AABB");
			trie.add("AB");	
			trie.add("ABBD");
			trie.add("CA");
			trie.add("CAI");
			trie.add("CHI");
			
		}

	}

	//------------------------------------------------------------------------------------
	
	// Set up 5

	void setUp5() {

		trie = new Trie();

		for(int i = 0 ; i < 10000; i++) {
			
			String word = "";

			word += Character.toString((char)(Math.random()*Trie.ASCII_CHARACTER_COUNT));
			word += Character.toString((char)(Math.random()*Trie.ASCII_CHARACTER_COUNT));
			word += Character.toString((char)(Math.random()*Trie.ASCII_CHARACTER_COUNT));
			word += Character.toString((char)(Math.random()*Trie.ASCII_CHARACTER_COUNT));

			trie.add(word);
			
		}
		
	}
	//------------------------------------------------------------------------------------
	
	// Test add 1

	@Test
	void testAdd1() {
		
		setUp1();

		String[] words = {"Ana","Anita","Felix","Zelda"};

		for (String word : words) {
			
			assertTrue(trie.add(word));
			
		}

		TrieNode root = trie.getRoot();
		
		TrieNode temp;
		
		TrieNode[] children;

		for (String word : words) {
			
			temp = root;
			
			children = root.getChildren();

			char[] chars = word.toCharArray();

			for (int i = 0; i < chars.length; i++) {
				
				assertEquals(chars[i],children[chars[i]].getCharacter());
				
				assertEquals((i == chars.length-1)? 1 : 0,children[chars[i]].getEndOfWords());
				
				assertEquals(temp,children[chars[i]].getParent());

				temp = children[chars[i]];
				
				children = temp.getChildren();
				
			}	

		}
		
		assertEquals(4,trie.getStoredWordsCount());
		
	}

	//------------------------------------------------------------------------------------
	
	// Test add 2

	@Test
	void testAdd2() {
		
		setUp2();
		
		String[] addedWords = {"Feliza","Analu","Camilo"};
		
		//The order in which the words are verified is important
		String[] totalWords = {"Ana","Anita","Felix","Zelda","Feliza","Analu","Camilo"};

		for (String word : addedWords) {
			
			assertTrue(trie.add(word));
			
		}

		TrieNode root = trie.getRoot();
		
		TrieNode temp;
		
		TrieNode[] children;
		
		Hashtable<TrieNode,TrieNode> ht = new Hashtable<>();

		for (String word : totalWords) {
			
			temp = root;
			
			children = root.getChildren();

			char[] chars = word.toCharArray();			

			for (int i = 0; i < chars.length; i++) {	
				
				assertEquals(chars[i],children[chars[i]].getCharacter());				

				assertEquals(temp,children[chars[i]].getParent());

				if(i == chars.length - 1)
					ht.put(children[chars[i]],children[chars[i]]);

				//This will fail if the order is not taken into account, first check shorter words with same prefix 1."Ana" 2. "Analu"
				assertEquals((ht.containsKey(children[chars[i]]))? 1 : 0,children[chars[i]].getEndOfWords());

				temp = children[chars[i]];
				
				children = temp.getChildren();								

			}	

		}
		
		assertEquals(7,trie.getStoredWordsCount());	
		
	}

	//------------------------------------------------------------------------------------
	
	// Test add 3

	@Test
	void testAdd3() {
		
		setUp3();
		
		String[] addedWords = {"AA","A","ABA"};
		
		//The order in which the words are verified is important
		String[] totalWords = {"A","AA","AB","AAA","AAAA","AABB","ABA","ABBD","CA","CAI","CHI"};

		for (String word : addedWords) {
			
			assertTrue(trie.add(word));
			
		}

		TrieNode root = trie.getRoot();
		
		TrieNode temp;
		
		TrieNode[] children;
		
		Hashtable<TrieNode,TrieNode> ht = new Hashtable<>();

		for (String word : totalWords) {
			
			temp = root;
			
			children = root.getChildren();

			char[] chars = word.toCharArray();			

			for (int i = 0; i < chars.length; i++) {	
				
				assertEquals(chars[i],children[chars[i]].getCharacter());				

				assertEquals(temp,children[chars[i]].getParent());

				if(i == chars.length - 1)
					ht.put(children[chars[i]],children[chars[i]]);

				//This will fail if the order is not taken into account, first check shorter words with same prefix 1."Ana" 2. "Analu"
				assertEquals((ht.containsKey(children[chars[i]]))? 1 : 0,children[chars[i]].getEndOfWords());

				temp = children[chars[i]];
				
				children = temp.getChildren();								

			}	
			

		}
		
		assertEquals(11,trie.getStoredWordsCount());	

	}
	
	//------------------------------------------------------------------------------------
	
	// Test add 4
	
	@Test
	void testAdd4() {
		
		setUp3();
		
		String[] addedWords = {"AAAA","AAA","CAI","AABB","AB","CHI","ABBD","CA"};
		
		//The order in which the words are verified is important
		String[] totalWords = {"AB","AAA","AAAA","AABB","ABBD","CA","CAI","CHI"};

		for (String word : addedWords) {
			
			assertTrue(trie.add(word));
			
		}

		TrieNode root = trie.getRoot();
		
		TrieNode temp;
		
		TrieNode[] children;
		
		Hashtable<TrieNode,TrieNode> ht = new Hashtable<>();

		for (String word : totalWords) {

			temp = root;
			
			children = root.getChildren();

			char[] chars = word.toCharArray();			

			for (int i = 0; i < chars.length; i++) {

				assertEquals(chars[i],children[chars[i]].getCharacter());				

				assertEquals(temp,children[chars[i]].getParent());

				if(i == chars.length - 1)
					ht.put(children[chars[i]],children[chars[i]]);

				//This will fail if the order is not taken into account, first check shorter words with same prefix 1."Ana" 2. "Analu"
				assertEquals((ht.containsKey(children[chars[i]]))? 2 : 0,children[chars[i]].getEndOfWords());

				temp = children[chars[i]];
				
				children = temp.getChildren();	
				

			}
			

		}
		
		assertEquals(16,trie.getStoredWordsCount());	

	}
	
	//------------------------------------------------------------------------------------
	
	// Test remove 1

	@Test
	void testRemove1() {
		
		setUp1();		

		assertFalse(trie.remove("AAA"));
		
		assertEquals(0,trie.getStoredWordsCount());	

		TrieNode[] children = trie.getRoot().getChildren();

		for (TrieNode child : children) {
			
			assertNull(child);
			
		}

	}

	//------------------------------------------------------------------------------------
	
	// Test remove 2

	@Test
	void testRemove2() {
		
		setUp2();
		
		String[] removedWords = {"Felix","Ana","Camilo"};
		
		//The order in which the words are verified is important
		String[] totalWords = {"Zelda","Anita"};

		for (String word : removedWords) {
			
			boolean result = trie.remove(word);
			
			assertTrue((word.equals("Camilo")? !result:result));
		}

		TrieNode root = trie.getRoot();
		
		TrieNode temp;
		
		TrieNode[] children;
		
		Hashtable<TrieNode,TrieNode> ht = new Hashtable<>();

		for (String word : totalWords) {
			
			temp = root;
			
			children = root.getChildren();

			char[] chars = word.toCharArray();			

			for (int i = 0; i < chars.length; i++) {	
				
				assertEquals(chars[i],children[chars[i]].getCharacter());				

				assertEquals(temp,children[chars[i]].getParent());

				if(i == chars.length - 1)
					ht.put(children[chars[i]],children[chars[i]]);

				//This will fail if the order is not taken into account, first check shorter words with same prefix 1."Ana" 2. "Analu"
				assertEquals((ht.containsKey(children[chars[i]]))? 1 : 0,children[chars[i]].getEndOfWords());

				temp = children[chars[i]];
				
				children = temp.getChildren();								

			}	

		}
		
		assertEquals(2,trie.getStoredWordsCount());	
		
	}

	//------------------------------------------------------------------------------------
	
	// Test remove 3

	@Test
	void testRemove3() {
		
		setUp3();
		
		String[] removedWords = {"AAA","AB","CAI","CASA"};
		
		//The order in which the words are verified is important
		String[] totalWords = {"AAAA","AABB","ABBD","CA","CHI"};

		for (String word : removedWords) {
			
			boolean result = trie.remove(word);
			
			assertTrue((word.equals("CASA")? !result:result));
			
		}

		TrieNode root = trie.getRoot();
		
		TrieNode temp;
		
		TrieNode[] children;
		
		Hashtable<TrieNode,TrieNode> ht = new Hashtable<>();

		for (String word : totalWords) {
			
			temp = root;
			
			children = root.getChildren();

			char[] chars = word.toCharArray();			

			for (int i = 0; i < chars.length; i++) {	

				assertEquals(chars[i],children[chars[i]].getCharacter());				

				assertEquals(temp,children[chars[i]].getParent());

				if(i == chars.length - 1)
					ht.put(children[chars[i]],children[chars[i]]);

				//This will fail if the order is not taken into account, first check shorter words with same prefix 1."Ana" 2. "Analu"
				assertEquals((ht.containsKey(children[chars[i]]))? 1 : 0,children[chars[i]].getEndOfWords());

				temp = children[chars[i]];
				
				children = temp.getChildren();								

			}

		}
		
		assertEquals(5,trie.getStoredWordsCount());	
		
	}


	//------------------------------------------------------------------------------------
	
	// Test remove 4

	@Test
	void testRemove4() {
		
		setUp3();
		
		String[] removedWords = {"AAAA","AAA","CAI","AABB","AB","CHI","ABBD","CA"};

		for (String word : removedWords) {
			
			assertTrue(trie.remove(word));
			
		}	

		TrieNode[] children = trie.getRoot().getChildren();

		for (TrieNode child : children) {
			
			assertNull(child);
			
		}

		assertEquals(0,trie.getStoredWordsCount());
		
	}

	//------------------------------------------------------------------------------------
	
	// Test remove 5

	@Test
	void testRemove5() {
		
		setUp4();
		
		String[] removedWords = {"AAAA","AAA","CAI","AABB","AB","CHI","ABBD","CA"};

		for (String word : removedWords) {

			trie.remove(word);
			
			trie.remove(word);
			
		}		

		TrieNode[] children = trie.getRoot().getChildren();

		for (TrieNode child : children) {
			
			assertNull(child);
			
		}

		assertEquals(0,trie.getStoredWordsCount());
		
	}

	//------------------------------------------------------------------------------------
	
	// Test search 1

	@Test
	void testSearch1() {
		
		setUp1();

		assertFalse(trie.search("Pedro"));
		
		assertFalse(trie.search("Camilo"));
		
		assertFalse(trie.search("Felipe"));	
		
	}

	//------------------------------------------------------------------------------------
	
	// Test search 2

	@Test
	void testSearch2() {
		
		setUp2();

		assertTrue(trie.search("Anita"));
		assertTrue(trie.search("Felix"));
		
		assertFalse(trie.search("Felipe"));
		assertFalse(trie.search("Sara"));
		assertFalse(trie.search("Carolina"));

	}

	//------------------------------------------------------------------------------------
	
	// Test search 3

	@Test
	void testSearch3() {
		
		setUp3();

		assertFalse(trie.search("AA"));
		
		assertTrue(trie.search("CA"));
		assertTrue(trie.search("CAI"));
		assertTrue(trie.search("ABBD"));
		
		assertFalse(trie.search("AABC"));
		
	}

	//------------------------------------------------------------------------------------
	
	// Test get predictions 1

	@Test
	void testGetPredictions1() {
		
		setUp1();

		assertTrue(trie.getPredictions("AAA").isEmpty());	
		
		assertTrue(trie.getPredictions("IDK").isEmpty());
		
	}

	//------------------------------------------------------------------------------------
	
	// Test get predictions 2

	@Test
	void testGetPredictions2() {
		
		setUp3();

		String[] list0 = {"AAAA","AAA","AABB","AB","ABBD","CA","CAI","CHI"};
		
		String[] list1 = {"AAAA","AABB","ABBD"};
		
		String[] list2 = {"AAAA","AABB"};

		ArrayList<String> predictions = trie.getPredictions("");
		
		System.out.println(predictions);

		for (String word : list0) {
			
			assertTrue(predictions.contains(word));
			
		}

		predictions = trie.getPredictions("A");
		
		System.out.println(predictions);

		for (String word : list1) {
			
			assertTrue(predictions.contains(word));
			
		}		

		predictions = trie.getPredictions("AA");
		
		System.out.println(predictions);

		for (String word : list2) {
			
			assertTrue(predictions.contains(word));
			
		}

		assertTrue(trie.getPredictions("ZEE").isEmpty());
		
	}

	//------------------------------------------------------------------------------------
	
	// Test get predictions 3

	@Test
	void testGetPredictions3() {
		
		setUp5();

		ArrayList<String> predictions = trie.getPredictions("");
		
		assertTrue(predictions.size() > 0);

		predictions = trie.getPredictions("A");
		
		assertTrue(predictions.size() > 0);
		
	}

	//------------------------------------------------------------------------------------

}
