/*
 * UNIVERSIDAD ICESI
 * TAREA INTEGRADORA 2 - ESTRUCTURAS DE DATOS
 * RODAS / DIAZ / MARTINEZ
 */

package collections;

import java.io.Serializable;

public class HashTableNode<K,V> implements Serializable{
	
	//------------------------------------------------------------------------------------
	
	// ATTRIBUTES OF THE HASH TABLE NODE
	
	//Auto generated
	private static final long serialVersionUID = -2398324095779943294L;

	private K key;
	
	private V value;
	
	private HashTableNode<K,V> prevNode;
	
	private HashTableNode<K,V> nextNode;
	
	//------------------------------------------------------------------------------------
	
	// CONSTRUCTOR METHOD OF THE HASH TABLE NODE
	
	public HashTableNode(K key, V value, HashTableNode<K,V> prevNode, HashTableNode<K,V> nextNode) {
		
		this.key = key;
		this.value = value;
		this.prevNode = prevNode;
		this.nextNode = nextNode;
		
	}
	
	//------------------------------------------------------------------------------------
	
	// GET'S METHOD OF THE HASH TABLE NODE
	
	public K getKey() {
		return key;
	}
	
	public V getValue() {
		return value;
	}
	
	public HashTableNode<K, V> getPrevNode() {
		return prevNode;
	}
	
	public HashTableNode<K, V> getNextNode() {
		return nextNode;
	}
	
	//------------------------------------------------------------------------------------
	
	// SET'S METHOD OF THE HASH TABLE NODE
	
	public void setKey(K key) {
		this.key = key;
	}

	public void setValue(V value) {
		this.value = value;
	}

	public void setPrevNode(HashTableNode<K, V> prevNode) {
		this.prevNode = prevNode;
	}

	public void setNextNode(HashTableNode<K, V> nextNode) {
		this.nextNode = nextNode;
	}
	
	//------------------------------------------------------------------------------------

}
