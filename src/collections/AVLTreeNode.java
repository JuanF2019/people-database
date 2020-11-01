/*
 * UNIVERSIDAD ICESI
 * TAREA INTEGRADORA 2 - ESTRUCTURAS DE DATOS
 * RODAS / DIAZ / MARTINEZ
 */

package collections;

public class AVLTreeNode<K extends Comparable<K>, V> extends Node<K, V> {
	
	//------------------------------------------------------------------------------------
	
	//AUTO GENERATED
	
	private static final long serialVersionUID = 49097610139670076L;
	
	private int balanceFactor;	
	
	//------------------------------------------------------------------------------------
	
	// CONTRUCTOR METHOD
			
	public AVLTreeNode(K key, V value) {
		
		super(key,value);
		
		balanceFactor = 0;	
		
	}
	
	//------------------------------------------------------------------------------------
	
	// GET AND SET METHOD

	public int getBalanceFactor() { return balanceFactor; }
	
	public void setBalanceFactor(int balanceFactor) { this.balanceFactor = balanceFactor; }
	
	//------------------------------------------------------------------------------------
	
	// UPDATE NODE METHOD
	
	public void updateAVLNode() {	
		
		int lh =  left != null? ((AVLTreeNode<K,V>)left).height:0;
		
		int rh =  right != null? ((AVLTreeNode<K,V>)right).height:0;
		
		height = ((lh > rh)? lh : rh) + 1;
		
		balanceFactor = rh - lh;
		
	}
	
	//------------------------------------------------------------------------------------
	
}
