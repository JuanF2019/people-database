/*
 * UNIVERSIDAD ICESI
 * TAREA INTEGRADORA 2 - ESTRUCTURAS DE DATOS
 * RODAS / DIAZ / MARTINEZ
 */

package collections;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class TestAVLTree {

	//------------------------------------------------------------------------------------

	// Relation with the class AVLTree

	AVLTree<Integer,Integer> avl;

	//------------------------------------------------------------------------------------

	// Setup1

	void setup1() {

		avl = new AVLTree<Integer,Integer>();

	}

	//------------------------------------------------------------------------------------

	// Setup2

	void setup2() {

		avl = new AVLTree<Integer,Integer>();
		avl.add(3, 7);
		avl.add(6, 10);
		avl.add(17, 5);
		avl.add(9, 36);
		avl.add(24, 21);
	}

	//------------------------------------------------------------------------------------

	@Test
	void addTest1 () {
		setup1();
		Integer x = 2;
		Integer y = 6;
		assertTrue(avl.add(x, y));
		assertFalse(avl.isEmpty());
		assertTrue(avl.getHeight()==1);
	}

	//------------------------------------------------------------------------------------

	@Test
	void addTest2 () {
		setup2();
		assertTrue(avl.add(60, 8));
		assertTrue(avl.add(14, 19));
		assertTrue(avl.add(22, 30));
		assertTrue(avl.add(13, 4));
		assertFalse(avl.isEmpty());
		System.out.println(avl.getHeight());
	}

	//------------------------------------------------------------------------------------

	@Test
	void addTest3 () {
		setup1();

		for(int i = 0 ; i < 200000 ; i ++) {

			Integer key =  new Integer((int) (Math.random()*2000));

			Integer value = new Integer((int) Math.random()*2000);

			avl.add(key, value);

			assertTrue(avl.add(key, value));

		}


	}

	//------------------------------------------------------------------------------------

	@Test
	void removeTest1() {
		setup1();
		Integer x = 35;
		Integer y = 70;
		assertFalse(avl.remove(x,y));
	}

	//------------------------------------------------------------------------------------

	@Test
	void removeTest2() {
		setup2();
		assertTrue(avl.remove(3, 7));
		assertTrue(avl.remove(24, 21));
		assertTrue(avl.remove(17, 5));
		assertTrue(avl.getHeight()==2);
	}

	//------------------------------------------------------------------------------------

	@Test
	void removeTest3() {
		setup1();

		for(int i = 0 ; i < 200000 ; i++) {

			Integer key =  i;

			Integer value = i+1;

			avl.add(key, value);

		}

		for(int a = 0 ; a < 200000 ; a ++) {

			assertTrue(avl.remove(a, a+1));

		}
		assertTrue(avl.isEmpty()==true);
	}

	//------------------------------------------------------------------------------------

}