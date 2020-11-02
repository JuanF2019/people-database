/*
 * UNIVERSIDAD ICESI
 * TAREA INTEGRADORA 2 - ESTRUCTURAS DE DATOS
 * RODAS / DIAZ / MARTINEZ
 */

package collections;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class TestHashTable<K,V> {

	//------------------------------------------------------------------------------------

	// Relation with the class Hash Table

	HashTable <Integer, Integer> hashTable;

	//------------------------------------------------------------------------------------

	// Setup1

	void setup1() {

		hashTable = new HashTable<>();

	}

	//------------------------------------------------------------------------------------

	// We check a case where we have one million people

	@Test
	void testInsert1() {

		setup1();

		for(int i = 0 ; i < 1000000 ; i ++) {

			int key = (int) Math.random() * 1000;

			int value = (int) Math.random() * 1000;

			hashTable.insert(key, value);

		}

		assertEquals(hashTable.size(), 1000000);

	}

	//------------------------------------------------------------------------------------

	// We check a case where five objects has the same key

	@Test
	void testInsert2() {

		setup1();

		for(int i = 0 ; i < 5 ; i ++) {

			int key = 1;

			int value = (int) Math.random() * 1000;

			hashTable.insert(key, value);

		}

		assertTrue(hashTable.size() == 5);

	}

	//------------------------------------------------------------------------------------

	// We check is empty and get all method with the creation of 2 objects

	@Test
	void testInsert3() {

		setup1();

		int key1 = 1;

		int value1 = (int) Math.random() * 1000;

		int key2 = 2;

		int value2 = (int) Math.random() * 1000;

		hashTable.insert(key1, value1);

		hashTable.insert(key2, value2);

		assertFalse(hashTable.isEmpty());

		assertNotNull(hashTable.getAll());

	}

	//------------------------------------------------------------------------------------

	// We check delete method with a object doesn't exists

	@Test
	void testDelete1() {

		setup1();

		int key = (int) Math.random() * 1000;

		assertNull(hashTable.delete(key));

	}

	//------------------------------------------------------------------------------------

	// We check insert and delete method with the creation of only one object

	@Test
	void testDelete2() {

		setup1();

		int key = (int) Math.random() * 1000;

		int value = (int) Math.random() * 1000;

		hashTable.insert(key, value);

		assertEquals(hashTable.size(), 1);

		// Create one object and we will delete it

		assertNotNull(hashTable.delete(key));

		assertEquals(hashTable.size(), 0);

	}

	//------------------------------------------------------------------------------------

	// We check delete method with the creation of one million people and with N deletes

	@Test
	void testDelete3() {

		setup1();

		for(int i = 0 ; i < 1000000 ; i ++) {

			int key = (int) Math.random() * 1000;

			int value = (int) Math.random() * 1000;

			hashTable.insert(key, value);

		}

		int total1 = hashTable.size();

		for(int a = 0 ; a < 100000 ; a ++) {

			int key = (int) Math.random() * 1000;

			hashTable.delete(key);

		}

		int total2 = hashTable.size();

		assertTrue(total2 < total1);

	}

	//------------------------------------------------------------------------------------

	/*
	 * We check the search method with the creation of one object
	 * After that, we will to compare the information
	 */

	@Test
	void testSearch1() {

		setup1();

		int key = (int) Math.random() * 1000;

		int value = (int) Math.random() * 1000;

		hashTable.insert(key, value);

		assertEquals(hashTable.size(), 1);

		assertNotNull(hashTable.search(key));

	}

	//------------------------------------------------------------------------------------

	/*
	 * First: We will create three objects with the same key
	 * Second: With "equals", compare the information
	 */

	@Test
	void testSearch2() {

		setup1();

		for(int i = 0 ; i < 3 ; i ++) {

			int key = (int) Math.random() * 1000;

			int value = (int) Math.random() * 1000;

			hashTable.insert(key, value);

		}

		assertNotNull(hashTable.getAll());

		// We create two keys to search the information on the system

		int key = (int) Math.random() * 1000;

		assertNull(hashTable.search(8000000));

		assertNotNull(hashTable.search(key));

	}

	//------------------------------------------------------------------------------------

	/*
	 * We will check what happen when the system try to delete a object
	 * that doesn't exists
	 */

	@Test
	void testSearch3() {

		setup1();

		int key = (int) Math.random() * 1000;

		assertNull(hashTable.search(key));

	}

	//------------------------------------------------------------------------------------

}