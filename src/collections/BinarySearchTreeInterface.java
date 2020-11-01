/*
 * UNIVERSIDAD ICESI
 * TAREA INTEGRADORA 2 - ESTRUCTURAS DE DATOS
 * RODAS / DIAZ / MARTINEZ
 */

package collections;

public interface BinarySearchTreeInterface<K extends Comparable<K>,V> {
	
	//------------------------------------------------------------------------------------
	
	// METHOD OF THE BINARY SEARCH TREE INTERFACE
	
	public boolean add(K key, V value);
	
	public boolean update(K key, V value);
	
	public boolean remove(K key);
	
	public boolean isEmpty();
	
	public V search(K key);
	
	public int getWeight();
	
	public int getHeight();	
	
	public String preOrder();
	
	public String inOrder();
	
	public String postOrder();		
	
	//------------------------------------------------------------------------------------
	
}
