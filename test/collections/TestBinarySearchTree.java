/*
 * UNIVERSIDAD ICESI
 * TAREA INTEGRADORA 2 - ESTRUCTURAS DE DATOS
 * RODAS / DIAZ / MARTINEZ
 */

package collections;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestBinarySearchTree<K, V> {
	
	//--------------------------------------------------------------------------------
	
	// RELATION WITH THE BINARY SEARCH TREE CLASS
	
	BinarySearchTree<Integer, Integer> binarySearch;
	
	//--------------------------------------------------------------------------------
	
	// SETUP1
	
	void setup1() {
		
		binarySearch = new BinarySearchTree<>();
		
	}
	
	//--------------------------------------------------------------------------------
	
	// SETUP2
	
	void setup2() {
		
		setup1();
		
		for(int i = 0 ; i < 50000 ; i ++) {
			
			int key = 1;
			
			int value = 5;
			
			binarySearch.add(key, value);
			
		}
		
	}
	
	//--------------------------------------------------------------------------------
	
	// SETUP3
	
	void setup3() {
		
		binarySearch = new BinarySearchTree<>();
		
		binarySearch.add(50, 0);
		
		binarySearch.add(25, 0);
		
		binarySearch.add(75, 0);
		
		binarySearch.add(37, 0);
		
		binarySearch.add(14, 0);
		
		binarySearch.add(63, 0);
		
		binarySearch.add(80, 0);
		
	}
	
	//--------------------------------------------------------------------------------
	
	// SETUP4
	
	void setup4() {
		
		setup1();
		
		binarySearch.add(1, 15);
		
		binarySearch.add(2, 30);
		
		binarySearch.add(3, 45);
		
		binarySearch.add(4, 60);
		
		binarySearch.add(5, 75);
		
	}
	
	//--------------------------------------------------------------------------------
	
	// We added only one elements to the system. So, the root is not null.
	
	@Test
	void testAdd1() {
		
		setup1();
		
		int key = 1;
		
		int value = 125;
		
		binarySearch.add(key, value);
		
		assertNotNull(binarySearch.getRoot());
		
	}
	
	//--------------------------------------------------------------------------------
	
	// We added one million of elements. After that, compare the information.
	
	@Test
	void testAdd2() {
		
		setup1();
		
		for(int i = 0 ; i < 100000 ; i ++) {
			
			int key = (int) Math.random() * 1000;
			
			int value = (int) Math.random() * 1000;
			
			binarySearch.add(key, value);
			
		}
		
		assertTrue(binarySearch.getWeight() == 100000);
		
	}
	
	//--------------------------------------------------------------------------------
	
	// We added two elements with the same key. So, the weight is two.
	
	// TEST ADD 3
	
	@Test
	void testAdd3() {
		
		setup1();
		
		int key = 1;
		
		int value1 = 100;
		
		int value2 = 200;
		
		binarySearch.add(key, value1);
		
		binarySearch.add(key, value2);
		
		assertEquals(binarySearch.getWeight(), 2);
		
	}
	
	//--------------------------------------------------------------------------------
	
	/*
	 * We added one element. After that, we remove the same element. So, the weight 
	 * have to be 0.
	 */
	
	@Test
	void testRemove1() {
		
		setup1();
		
		int key = (int) Math.random() * 10;
		
		int value = (int) Math.random() * 10;
		
		binarySearch.add(key, value);
		
		assertTrue(binarySearch.remove(key, value));
		
		assertEquals(binarySearch.getWeight(), 0);
		
	}
	
	//--------------------------------------------------------------------------------
	
	/*
	 * We use "setup2" to add 50 thousand of elements, and after that remove only
	 * 5 thousand. So, the original weight is higher than the another one.
	 */
	
	@Test
	void testRemove2() {
		
		setup2();
		
		for(int i = 0 ; i < 5000 ; i ++) {
			
			int key = 1;
			
			int value = 5;
			
			binarySearch.remove(key, value);
			
		}
		
		assertTrue(binarySearch.getWeight() < 50000);
		
	}
	
	//--------------------------------------------------------------------------------
	
	/*
	 * We use "setup3" to add seven elements. 
	 * We will compare the information.
	 */
	
	@Test
	void testRemove3() {
		
		setup3();
		
		assertTrue(binarySearch.remove(50, 0));
		
		assertTrue(binarySearch.remove(25, 0));
		
		assertEquals(5,binarySearch.getWeight());
		
		Node<Integer,Integer> root = binarySearch.getRoot();
		
		assertEquals(5,binarySearch.getWeight());
		
		assertEquals(63, root.getKey());
		
		assertNull(root.getRight().getLeft());
		
		assertNull(root.getLeft().getRight());
		
	}
	
	//--------------------------------------------------------------------------------
	
	/* 
	 * 1. We use the setup3
	 * 2. With assert false, we compare the information in the search method.
	 */
	
	@Test
	void testSearch1() {
		
		setup3();
		
		assertFalse(binarySearch.search(14, 0) == 10);
		
	}
	
	//--------------------------------------------------------------------------------
	
	/* 
	 * 1. We use the setup3
	 * 2. With assert false, we compare the information in the search method.
	 */
	
	@Test
	void testSearch2() {
		
		setup3();
		
		assertNull(binarySearch.search(105));
		
	}
	
	//--------------------------------------------------------------------------------
	
	/* 
	 * 1. We use the setup3
	 * 2. With assert false, we compare the information in the search method.
	 */
	
	@Test
	void testSearch3() {
		
		setup3();
		
		assertNotNull(binarySearch.search(75));
		
	}
	
	//--------------------------------------------------------------------------------
	
	/*
	 * 1. We use the setup4
	 * 2. We compare the String
	 * 3. Have to be different to null.
	 */
	
	@Test
	void testPreOrder() {
		
		setup4();
		
		assertTrue(binarySearch.preOrder() != "");
		
	}
	
	//--------------------------------------------------------------------------------
	
	/*
	 * 1. We use the setup4
	 * 2. We compare the String
	 * 3. Have to be different to null.
	 */
	
	@Test
	void testInOrder() {
		
		setup4();
		
		assertTrue(binarySearch.inOrder() != "");
		
	}
	
	//--------------------------------------------------------------------------------
	
	/*
	 * 1. We use the setup4
	 * 2. We compare the String
	 * 3. Have to be different to null.
	 */
	
	@Test
	void testPostOrden() {
		
		setup4();
		
		assertTrue(binarySearch.postOrder() != "");
		
	}
	
	//--------------------------------------------------------------------------------
	
}