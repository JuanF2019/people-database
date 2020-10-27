package collections;

public class AVLTreeNode<K extends Comparable<K>, V> extends Node<K, V> {
	private int balanceFactor;	
			
	public AVLTreeNode(K key, V value) {
		super(key,value);
		balanceFactor = 0;		
	}

	public int getBalanceFactor() { return balanceFactor; }
	public void setBalanceFactor(int balanceFactor) { this.balanceFactor = balanceFactor; }
	
	public void updateAVLNode() {		
		int lh =  left != null? ((AVLTreeNode<K,V>)left).height:0;
		int rh =  right != null? ((AVLTreeNode<K,V>)right).height:0;
		
		height = ((lh > rh)? lh : rh) + 1;
		balanceFactor = rh - lh;
	}
}
