/*
 * UNIVERSIDAD ICESI
 * TAREA INTEGRADORA 2 - ESTRUCTURAS DE DATOS
 * RODAS / DIAZ / MARTINEZ
 */

package collections;

public class RedBlackTreeNode<K extends Comparable<K>,V> extends Node<K, V> {
	
	//------------------------------------------------------------------------------------

	// AUTO GENERATED
	
	private static final long serialVersionUID = 2895497754988539751L;
	
	private Color color;
	
	//------------------------------------------------------------------------------------
	
	// CONSTRUCTOR METHOD
	
	public RedBlackTreeNode(K key, V value) {
		
		super(key, value);
		
		color = Color.RED;
		
	}
	
	//------------------------------------------------------------------------------------
	
	// GET METHOD

	public Color getColor() { return color;	}
	
	//------------------------------------------------------------------------------------
	
	// SET METHOD

	public void setColor(Color color) { this.color = color;	}
	
	//------------------------------------------------------------------------------------
		
}
