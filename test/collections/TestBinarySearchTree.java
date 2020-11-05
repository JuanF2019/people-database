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
	
	void setUp3() {
		
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
	
	@Test
	void testRemove3() {
		setUp3();
		
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
	
	@Test
	void testSearch1() {
		
		
		
	}
	
	//--------------------------------------------------------------------------------
	
	@Test
	void testSearch2() {
		
		
		
	}
	
	//--------------------------------------------------------------------------------
	
	@Test
	void testSearch3() {
		
		
		
	}
	
	//--------------------------------------------------------------------------------
	
	@Test
	void testPreOrder() {
		
		
	}
	
	//--------------------------------------------------------------------------------
	
	@Test
	void testInOrder() {
		
		
		
	}
	
	//--------------------------------------------------------------------------------
	
	@Test
	void testPostOrden() {
		
		
		
	}
	
	//--------------------------------------------------------------------------------
	
}