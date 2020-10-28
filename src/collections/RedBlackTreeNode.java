/*
 * UNIVERSIDAD ICESI
 * TAREA INTEGRADORA 2 - ESTRUCTURAS DE DATOS
 * RODAS / DIAZ / MARTINEZ
 */

package collections;

public class RedBlackTreeNode<K extends Comparable<K>,V> extends Node<K, V> {

	private Color color;
	
	public RedBlackTreeNode(K key, V value) {
		super(key, value);
		color = Color.RED;
	}

	public Color getColor() { return color;	}

	public void setColor(Color color) { this.color = color;	}
		
}
