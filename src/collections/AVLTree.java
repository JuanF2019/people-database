package collections;

public class AVLTree<K extends Comparable<K>,V> extends BinarySearchTree<K,V> implements BinarySearchTreeInterface<K,V>{
			
	public AVLTree() {
		super();
	}
	
	public boolean add(K key, V value) {
		Node<K,V> addedNode = super.addBase(key, value);		
		
		if(addedNode != null) {
			//Replaces Node with AVLTreeNode
			AVLTreeNode<K,V> replacementNode = new AVLTreeNode<>(addedNode.getKey(), addedNode.getValue());
			
			if(addedNode == root) {
				root = replacementNode;	
				
				return true;
			}
			else {
				AVLTreeNode<K,V> left = (AVLTreeNode<K,V>)addedNode.getLeft();
				AVLTreeNode<K,V> right = (AVLTreeNode<K,V>)addedNode.getRight();
				AVLTreeNode<K,V> parent = (AVLTreeNode<K,V>)addedNode.getParent();
							
				replacementNode.setLeft(left);
				replacementNode.setRight(right);
				replacementNode.setParent(parent);
				
				if(right != null) {
					right.setParent(replacementNode);
				}
				
				if(left != null) {
					left.setParent(replacementNode);
				}
				
				if(parent.getLeft() == addedNode) {
					parent.setLeft(replacementNode);
				}
				else {
					parent.setRight(replacementNode);
				}
				
				//Checks for balance and balance if necessary
				AVLTreeNode<K,V> currentNode = (AVLTreeNode<K,V>)replacementNode.getParent();
								
				while(currentNode != null) {
					currentNode.updateAVLNode();						
					balanceNode(currentNode);					
					currentNode = (AVLTreeNode<K, V>) currentNode.getParent();
				}							
				return true;
			}				
		}
		else {
			return false;
		}
	}
	
	
	private AVLTreeNode<K,V> balanceNode (AVLTreeNode<K,V> node){
		int nodeBF = node.getBalanceFactor();
		if(Math.abs(nodeBF) > 1 && node != null) {
			if(nodeBF > 1 && node.getRight() != null) {
				
				AVLTreeNode<K,V> right =  (AVLTreeNode<K,V>) node.getRight();
				
				if(right.getBalanceFactor() < 0) {
					rotateRight(right);
				}
				 
				return rotateLeft(node);
			}
			else if(nodeBF < -1 && node.getLeft() != null){
				
				AVLTreeNode<K,V> left =  (AVLTreeNode<K,V>) node.getLeft();
				if(left.getBalanceFactor() >= 0) {
					rotateLeft(left);
				}
				
				return rotateRight(node);
				
			}
			else {
				return node;
			}			
		}
		else {
			return node;
		}
	}
	
	public boolean remove(K key) {		
		
		Node<K,V> removed = super.removeBase(key);
		AVLTreeNode<K,V> removedParent = (removed == null)? null : (AVLTreeNode<K,V>) removed.getParent();		
		
		if(removed != null) {			
			AVLTreeNode<K,V> currentNode = removedParent;
						
			while(currentNode != null) {				
				currentNode.updateAVLNode();	
				balanceNode(currentNode);
				currentNode = (AVLTreeNode<K,V>) currentNode.getParent();					
			}						
	 
			return true;
		}
		else {
			return false;
		}		
	}
	
	//This method does not fix the balance factor of nodes that are not modified here
	//Returns new subtree root
	private AVLTreeNode<K,V> rotateRight(AVLTreeNode<K,V> node) {
		if(node.getLeft() == null) {
			return node;
		}
		else {
			AVLTreeNode<K,V> left = (AVLTreeNode<K,V>)node.getLeft();
			AVLTreeNode<K,V> parent = (AVLTreeNode<K,V>)node.getParent();
			
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
			
			//Update nodes heights and balance factors
			node.updateAVLNode();
			left.updateAVLNode();
			
			if(parent != null) {
				parent.updateAVLNode();	
			}
			
			return left;
		}		
	}
	//This method does not fix the balance factor of nodes that are not modified here
	//Returns new subtree root
	private AVLTreeNode<K,V> rotateLeft(AVLTreeNode<K,V> node) {
		if(node.getRight() == null) {
			return node;
		}
		else {
			AVLTreeNode<K,V> right = (AVLTreeNode<K,V>)node.getRight();
			AVLTreeNode<K,V> parent = (AVLTreeNode<K,V>)node.getParent();
			
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
			
			//Update nodes heights and balance factors
			node.updateAVLNode();
			right.updateAVLNode();
			
			if(parent != null) {
				parent.updateAVLNode();	
			}
			
			return right;
		}	
	}	
}
