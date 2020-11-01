/*
 * UNIVERSIDAD ICESI
 * TAREA INTEGRADORA 2 - ESTRUCTURAS DE DATOS
 * RODAS / DIAZ / MARTINEZ
 */

package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import collections.HashTable;

class TestHashTable<K,V> {
	//------------------------------------------------------------------------------------
	
		// Relation with the class Hash Table

		HashTable <K,V> hashTable;
		
		//------------------------------------------------------------------------------------
		
		// Setup1

		void setup1() {

			hashTable = new HashTable<K, V>();

		}
		
		//------------------------------------------------------------------------------------

		// We check a case where we have the same data
		
		@Test
		void testInsert1() {

			setup1();
			
			for(int i = 0 ; i < 3 ; i ++) {
				
				@SuppressWarnings("unchecked")
				K key = (K) new Integer(10);
				
				@SuppressWarnings("unchecked")
				V value = (V) new Integer(100);
				
				hashTable.insert(key, value);
				
			}
			
			assertEquals(hashTable.size(), 3);

		}
		
		//------------------------------------------------------------------------------------

		@Test
		void testInsert2() {

			setup1();

		}
		
		//------------------------------------------------------------------------------------

		@Test
		void testInsert3() {

			setup1();

		}
		
		//------------------------------------------------------------------------------------
		
}
