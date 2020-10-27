/*
 * ALGORITMOS Y ESTRUCTURAS DE DATOS
 * TAREA INTEGRADORA 2
 * MARTINEZ - DIAZ - RODAS
 */

package collections;

public class HashTable<K,V> implements HashTableInterface<K,V> {
	
	//------------------------------------------------------------------------------------
	
	// ATTRIBUTES OF THE HASH TABLE 

	public static final int DEFAULT_CAPACITY = 10000000;

	private int capacity;

	private int size;

	private HashTableNode<K,V>[] nodesArray;
	
	//------------------------------------------------------------------------------------
	
	// CONSTRUCTOR METHOD 1 WITHOUT ANY PARAMETER

	@SuppressWarnings("unchecked")
	public HashTable() {
		
		nodesArray = (HashTableNode<K,V>[]) new HashTableNode[DEFAULT_CAPACITY];
		
		capacity = DEFAULT_CAPACITY;
		
	}
	
	//**************************************************
	
	// CONSTRUCTOR METHOD 2 WITH ONLY ONE PARAMETER

	@SuppressWarnings("unchecked")
	public HashTable(int capacity) {
		
		nodesArray = (HashTableNode<K,V>[]) new HashTableNode[capacity];
		
		this.capacity = capacity;
		
	}
	
	//------------------------------------------------------------------------------------
	
	// INSERT METHOD OF THE HASH TABLE

	public void insert(K key, V value) {

		int i = hash(key);

		if(nodesArray[i] == null) {

			nodesArray[i] = new HashTableNode<K,V>(key,value,null,null);

		} else {						

			nodesArray[i] = new HashTableNode<K,V>(key,value,null,nodesArray[i]);	

		}	

		size++;

	}
	
	//------------------------------------------------------------------------------------
	
	// DELETE METHOD OF THE HASH TABLE

	public V delete(K key) {

		int i = hash(key);

		if(nodesArray[i] == null) {

			return null;

		} else {

			boolean found = false;

			HashTableNode<K,V> temp = nodesArray[i];

			while(temp != null && !found) {

				if(temp.getKey().equals(key)) {

					found = true;

				} else {

					temp = temp.getNextNode();

				}		

			}

			if(found) {

				if(temp.getPrevNode() == null && temp.getNextNode() == null) {

					nodesArray[i] = null;

				} else if(temp.getPrevNode() == null) {

					nodesArray[i] = temp.getNextNode();

					nodesArray[i].setPrevNode(null);

				} else if(temp.getNextNode() == null) {

					temp.getPrevNode().setNextNode(null);

				} else {

					temp.getNextNode().setPrevNode(temp.getPrevNode());

					temp.getPrevNode().setNextNode(temp.getNextNode());

				}	

				size--;

				return temp.getValue();

			} else {

				return null;

			}	

		}	

	}
	
	//------------------------------------------------------------------------------------
	
	// SEARCH METHOD OF THE HASH TABLE

	public V search(K key) {

		int i = hash(key);

		if(nodesArray[i] == null) {

			return null;

		} else {

			HashTableNode<K,V> temp = nodesArray[i];

			while(temp != null) {

				if(temp.getKey().equals(key)) {

					return temp.getValue();

				} else {

					temp = temp.getNextNode();

				}

			}

			return null;
		}

	}
	
	//------------------------------------------------------------------------------------
	
	// HASH METHOD OF THE HASH TABLE

	public int hash(K key) {
		
		return (key.hashCode() % capacity < 0) ? (key.hashCode() % capacity) * -1 : key.hashCode() % capacity;
	
	}
	
	//------------------------------------------------------------------------------------
	
	// SIZE METHOD

	public int size() {
		
		return size;
		
	}
	
	//------------------------------------------------------------------------------------
	
	// GET CAPACITY METHOD

	public int getCapacity() {
		
		return capacity;
		
	}
	
	//------------------------------------------------------------------------------------
	
	// IS EMPTY METHOD

	public boolean isEmpty() {
		
		return size == 0;
		
	}
	
	//------------------------------------------------------------------------------------
	
	// GET ALL METHOD

	@Override
	public V[] getAll() {
		
		@SuppressWarnings("unchecked")
		V[] vArray = (V[]) new Object[size];
		
		int j = 0;

		for (int i = 0; i < nodesArray.length; i++) {
			
			if(nodesArray[i] != null) {
				
				HashTableNode<K,V> temp = nodesArray[i];

				while(temp != null) {
					
					vArray[j] = temp.getValue();
					
					temp = temp.getNextNode();
					
					j++;
					
				}
				
			}
			
		}
		
		return vArray;
		
	}
	
	//------------------------------------------------------------------------------------

}
