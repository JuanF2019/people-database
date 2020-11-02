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
	
	@Test
	void testAdd1() {
		
		setup1();
		
		int key = 1;
		
		int value = 125;
		
		binarySearch.add(key, value);
		
		assertNotNull(binarySearch.getRoot());
		
	}
	
	//--------------------------------------------------------------------------------
	
	@Test
	void testAdd2() {
		
		setup1();
		
		for(int i = 0 ; i < 1000000 ; i ++) {
			
			int key = (int) Math.random()*1000;
			
			int value = (int) Math.random()*1000;
			
			binarySearch.add(key, value);
			
		}
		
		
		
	}
	
	//--------------------------------------------------------------------------------
	
	@Test
	void testAdd3() {
		
		setup1();
		
	}
	
	//--------------------------------------------------------------------------------
	
	@Test
	void testRemove1() {
		
		
		
	}
	
	//--------------------------------------------------------------------------------
	
	@Test
	void testRemove2() {
		
		
		
	}
	
	//--------------------------------------------------------------------------------
	
	@Test
	void testRemove3() {
		
		
		
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