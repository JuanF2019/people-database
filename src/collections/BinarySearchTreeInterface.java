/*
 * UNIVERSIDAD ICESI
 * TAREA INTEGRADORA 2 - ESTRUCTURAS DE DATOS
 * RODAS / DIAZ / MARTINEZ
 */

package collections;

import java.util.List;

//This interface was made including support for repeated values, for that reason the update method is not included

public interface BinarySearchTreeInterface<K extends Comparable<K>,V> {
	
	//------------------------------------------------------------------------------------
	
	// METHOD OF THE BINARY SEARCH TREE INTERFACE
	
	public boolean add(K key, V value);
	
	public List<V> search(K key);
	
	public V search(K key, V value);
	
	public boolean remove(K key, V value);
	
	public boolean isEmpty();	
	
	public int getWeight();
	
	public int getHeight();	
	
	public String preOrder();
	
	public String inOrder();
	
	public String postOrder();		
	
	//------------------------------------------------------------------------------------
	
}
