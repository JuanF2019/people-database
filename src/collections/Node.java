/*
 * UNIVERSIDAD ICESI
 * TAREA INTEGRADORA 2 - ESTRUCTURAS DE DATOS
 * RODAS / DIAZ / MARTINEZ
 */

package collections;

import java.io.Serializable;
import java.util.ArrayList;

public class Node<K extends Comparable<K>,V> implements Serializable {
	
	//------------------------------------------------------------------------------------
	
	// AUTO GENERATED	
	private static final long serialVersionUID = 793059671853761706L;
	
	protected K key;
	
	protected ArrayList<V> values;
	
	protected int height;
	
	protected Node<K,V> right;
	
	protected Node<K,V> left;
	
	protected Node<K,V> parent;
	
	//------------------------------------------------------------------------------------
	
	// CONSTRUCTOR METHOD
	
	public Node(K key, V value) {
		
		this.key = key;
		
		values = new ArrayList<V>();
		
		values.add(value);
				
		height = 1;		
	}
	
	//------------------------------------------------------------------------------------
	
	// UPDATE NODE METHOD

	public void updateNode() {
		
		int lh =  left != null? left.height:0;
		
		int rh =  right != null? right.height:0;
		
		height = ((lh > rh)? lh : rh) + 1;
		
	}
	
	//------------------------------------------------------------------------------------
	
	// GET'S METHOD
	
	public K getKey() { return key;	}
	
	public ArrayList<V> getValues() { return values;	}
	
	public int getHeight() { return height; }
	
	public Node<K,V> getRight() { return right; }
	
	public Node<K,V> getLeft() { return left; }
	
	public Node<K,V> getParent(){ return parent; }	
	
	//------------------------------------------------------------------------------------
	
	// SET'S METHOD
	
	public void setKey(K key) { this.key = key; }
	
	public void setValues(ArrayList<V> values) { this.values = values; }
	
	public void setRight(Node<K, V> right) { this.right = right; }
	
	public void setHeight(int height) { this.height = height; }
	
	public void setLeft(Node<K, V> left) { this.left = left; }
	
	public void setParent(Node<K, V> father) { this.parent = father; }	
	
	//------------------------------------------------------------------------------------
	
}
