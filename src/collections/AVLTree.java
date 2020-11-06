/*
 * UNIVERSIDAD ICESI
 * TAREA INTEGRADORA 2 - ESTRUCTURAS DE DATOS
 * RODAS / DIAZ / MARTINEZ
 */

package collections;

public class AVLTree<K extends Comparable<K>,V> extends BinarySearchTree<K,V> implements BinarySearchTreeInterface<K,V>{

	//------------------------------------------------------------------------------------

	// AUTO GENERATED

	private static final long serialVersionUID = 5794034253049998590L;

	//------------------------------------------------------------------------------------

	// CONSTRUCTOR METHOD

	public AVLTree() {

		super();

	}

	//------------------------------------------------------------------------------------

	// ADD METHOD

	public boolean add(K key, V value) {

		//Could be a new node or a node with added value
		AVLTreeNode<K,V> addedNode = new AVLTreeNode<>(key,value);

		add(addedNode);		
				
		if(addedNode == root || addedNode.getParent() == null) {//Added a value with repeated key or added root
			
			return true;
			
		} else {

			//Checks for balance and balance if necessary
			AVLTreeNode<K,V> currentNode = (AVLTreeNode<K,V>)addedNode.getParent();

			while(currentNode != null) {

				currentNode.updateAVLNode();

				balanceNode(currentNode);	

				currentNode = (AVLTreeNode<K, V>) currentNode.getParent();

			}		
			
			return true;
			
		}
		
	}

	//------------------------------------------------------------------------------------

	// BALANCE NODE METHOD

	private AVLTreeNode<K,V> balanceNode (AVLTreeNode<K,V> node){

		int nodeBF = node.getBalanceFactor();

		if(Math.abs(nodeBF) > 1 && node != null) {

			if(nodeBF > 1 && node.getRight() != null) {

				AVLTreeNode<K,V> right =  (AVLTreeNode<K,V>) node.getRight();

				if(right.getBalanceFactor() < 0) {

					rotateRight(right);

				}

				return rotateLeft(node);

			} else if(nodeBF < -1 && node.getLeft() != null){

				AVLTreeNode<K,V> left =  (AVLTreeNode<K,V>) node.getLeft();

				if(left.getBalanceFactor() >= 0) {

					rotateLeft(left);

				}

				return rotateRight(node);


			} else {

				return node;

			}

		} else {

			return node;

		}

	}

	//------------------------------------------------------------------------------------

	// REMOVE METHOD

	public boolean remove(K key, V value) {		

		Node<K,V> removed = super.removeBase(key,value);

		//Values list from a node will only have size 1 when the node had been removed
		if(removed != null && removed.getValues().size() == 1) {

			AVLTreeNode<K,V> removedParent = (AVLTreeNode<K,V>) removed.getParent();

			AVLTreeNode<K,V> currentNode = removedParent;

			while(currentNode != null) {	

				currentNode.updateAVLNode();

				balanceNode(currentNode);

				currentNode = (AVLTreeNode<K,V>) currentNode.getParent();	

			}						

			return true;

		} else {

			return false;

		}	

	}

	//------------------------------------------------------------------------------------

	//This method does not fix the balance factor of nodes that are not modified here
	//Returns new subtree root

	private AVLTreeNode<K,V> rotateRight(AVLTreeNode<K,V> node) {

		if(node.getLeft() == null) {

			return node;

		} else {

			AVLTreeNode<K,V> left = (AVLTreeNode<K,V>)node.getLeft();

			AVLTreeNode<K,V> parent = (AVLTreeNode<K,V>)node.getParent();

			//Updates node parent
			if(node != root) {

				if(parent.getLeft() == node) {

					parent.setLeft(left);

				} else {

					parent.setRight(left);

				}	

			} else {

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

	//------------------------------------------------------------------------------------

	//This method does not fix the balance factor of nodes that are not modified here
	//Returns new subtree root

	private AVLTreeNode<K,V> rotateLeft(AVLTreeNode<K,V> node) {

		if(node.getRight() == null) {

			return node;

		} else {

			AVLTreeNode<K,V> right = (AVLTreeNode<K,V>)node.getRight();

			AVLTreeNode<K,V> parent = (AVLTreeNode<K,V>)node.getParent();

			//Updates node parent
			if(node != root) {

				if(parent.getRight() == node) {

					parent.setRight(right);

				} else {

					parent.setLeft(right);

				}	

			} else {

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

	//------------------------------------------------------------------------------------

}
