package collections;

public class Node<K extends Comparable<K>,V> {
	protected K key;
	protected V value;
	protected int height;
	protected Node<K,V> right;
	protected Node<K,V> left;
	protected Node<K,V> parent;
	
	public Node(K key, V value) {
		this.key = key;
		this.value = value;
		height = 1;
	}

	public void updateNode() {
		int lh =  left != null? left.height:0;
		int rh =  right != null? right.height:0;
		
		height = ((lh > rh)? lh : rh) + 1;
	}
	
	public K getKey() { return key;	}
	public V getValue() { return value;	}
	public int getHeight() { return height; }
	public Node<K,V> getRight() { return right; }
	public Node<K,V> getLeft() { return left; }
	public Node<K,V> getParent(){ return parent; }	
	
	public void setKey(K key) { this.key = key; }
	public void setValue(V value) { this.value = value; }
	public void setRight(Node<K, V> right) { this.right = right; }
	public void setHeight(int height) { this.height = height; }
	public void setLeft(Node<K, V> left) { this.left = left; }	
	public void setParent(Node<K, V> father) { this.parent = father; }	
}
