package collections;

public class RedBlackTree<K extends Comparable<K>,V> extends BinarySearchTree<K, V> {

	
	
	public RedBlackTree() {
		super();
	}
	
	public boolean add(K key, V value) {
		Node<K,V> addedNode = super.addBase(key, value);		
		
		if(addedNode != null) {
			//Replaces Node with RedBlackTreeNode
			RedBlackTreeNode<K,V> replacementNode = new RedBlackTreeNode<>(addedNode.getKey(), addedNode.getValue());
			
			//Left and right are null, always when adding
			RedBlackTreeNode<K,V> parent = (RedBlackTreeNode<K,V>)addedNode.getParent();
			
			replacementNode.setParent(parent);			
			
			if(parent.getLeft() == addedNode) {
				parent.setLeft(replacementNode);
			}
			else {
				parent.setRight(replacementNode);
			}
				
			addFixUp(replacementNode);					
			
			return true;
							
		}
		else {
			return false;
		}
	}
	
	//Based on pseudocode from Cormen Introduction to algorithms 3ed
	//When z is red there is no violation on tree properties
	public void addFixUp(RedBlackTreeNode<K,V> z){
		while(((RedBlackTreeNode<K,V>)z.getParent()).getColor() == Color.RED) {
			RedBlackTreeNode<K,V> uncle;
			if(z.getParent() == z.getParent().getParent().getLeft()) {
				uncle = (RedBlackTreeNode<K,V>)z.getParent().getParent().getRight();
				
				//Case 1: Uncle color is red
				if(uncle != null && uncle.getColor() == Color.RED) {
					((RedBlackTreeNode<K,V>) z.getParent()).setColor(Color.BLACK);
					uncle.setColor(Color.BLACK);
					((RedBlackTreeNode<K,V>) z.getParent().getParent()).setColor(Color.RED);
					z = (RedBlackTreeNode<K,V>) z.getParent().getParent();
				}
				else {//Case 2 and 3: Uncle color is black
					
					//Case 2 forms < triangle
					if(z == z.getParent().getRight()) {
						z = (RedBlackTreeNode<K, V>) z.getParent();
						rotateLeft(z);
					}
					
					//Case 3 forms / line
					((RedBlackTreeNode<K, V>) z.getParent()).setColor(Color.BLACK);
					((RedBlackTreeNode<K,V>) z.getParent().getParent()).setColor(Color.RED);
					rotateRight(z);
				}			
			}
			else {
				uncle = (RedBlackTreeNode<K,V>)z.getParent().getParent().getLeft();
			
				//Case 1: Uncle color is red
				if(uncle != null && uncle.getColor() == Color.RED) {
					((RedBlackTreeNode<K,V>) z.getParent()).setColor(Color.BLACK);
					uncle.setColor(Color.BLACK);
					((RedBlackTreeNode<K,V>) z.getParent().getParent()).setColor(Color.RED);
					z = (RedBlackTreeNode<K,V>) z.getParent().getParent();
				}
				else {//Case 2 and 3: Uncle color is black (when exist and its black or when it does not exist)
					
					//Case 2 forms > triangle
					if(z == z.getParent().getLeft()) {
						z = (RedBlackTreeNode<K, V>) z.getParent();
						rotateRight(z);
					}
					
					//Case 3 forms \ line
					((RedBlackTreeNode<K, V>) z.getParent()).setColor(Color.BLACK);
					((RedBlackTreeNode<K,V>) z.getParent().getParent()).setColor(Color.RED);
					rotateLeft(z);
				}
			
			}
		}
		//Case 0: Z is black, covered by always setting root to black
		((RedBlackTreeNode<K,V>)root).setColor(Color.BLACK);
				
	}
	
	public boolean remove() {
		return false;
	}
	
	public RedBlackTreeNode<K,V> removeFixUp(){
		return null;
	}
	
	//This method does not fix the balance factor of nodes that are not modified here
	//Returns new subtree root
	private RedBlackTreeNode<K,V> rotateRight(RedBlackTreeNode<K,V> node) {
		if(node.getLeft() == null) {
			return node;
		}
		else {
			RedBlackTreeNode<K,V> left = (RedBlackTreeNode<K,V>)node.getLeft();
			RedBlackTreeNode<K,V> parent = (RedBlackTreeNode<K,V>)node.getParent();
			
			//Updates node parent
			if(node != root) {
				if(parent.getLeft() == node) {
						parent.setLeft(left);
				}
				else {
					parent.setRight(left);
				}	
			}
			else {
				//Updates root
				root = left;
			}
			
				//Updates Node			
			node.setParent(left);
			node.setLeft(left.getRight());
			
			//Update left
			if(left.getRight() != null) {
				left.getRight().setParent(node);
			}
			left.setParent(parent);	//Sets null if node is root	
			left.setRight(node);
			
			//Update nodes heights
			node.updateNode();
			left.updateNode();
			
			if(parent != null) {
				parent.updateNode();	
			}
			
			return left;
		}		
	}
	//This method does not fix the balance factor of nodes that are not modified here
	//Returns new subtree root
	private RedBlackTreeNode<K,V> rotateLeft(RedBlackTreeNode<K,V> node) {
		if(node.getRight() == null) {
			return node;
		}
		else {
			RedBlackTreeNode<K,V> right = (RedBlackTreeNode<K,V>)node.getRight();
			RedBlackTreeNode<K,V> parent = (RedBlackTreeNode<K,V>)node.getParent();
				
			//Updates node parent
			if(node != root) {
				if(parent.getRight() == node) {
						parent.setRight(right);
				}
				else {
					parent.setLeft(right);
				}	
			}
			else {
				//Updates root
				root = right;
			}
			
			//Updates Node			
			node.setParent(right);
			node.setRight(right.getLeft());
			
			//Update right
			if(right.getLeft() != null) {
				right.getLeft().setParent(node);
			}
			right.setParent(parent);	//Sets null if node is root	
			right.setLeft(node);
			
			//Update nodes heights
			node.updateNode();
			right.updateNode();
			
			if(parent != null) {
				parent.updateNode();	
			}
			
			return right;
		}	
	}		
}
