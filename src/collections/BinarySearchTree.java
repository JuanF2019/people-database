/*
 * UNIVERSIDAD ICESI
 * TAREA INTEGRADORA 2 - ESTRUCTURAS DE DATOS
 * RODAS / DIAZ / MARTINEZ
 */

package collections;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BinarySearchTree<K extends Comparable<K>,V> implements BinarySearchTreeInterface<K,V>,Serializable{

	//--------------------------------------------------------------------------------

	// Relations of the BinarySearchTree class

	protected Node<K,V> root;

	//--------------------------------------------------------------------------------

	// Attributes of the BinarySearchTree class

	//Auto generated

	private static final long serialVersionUID = 953054602220219747L;

	protected int weight;

	//--------------------------------------------------------------------------------

	// Constructor of the BinarySearchTree class

	public BinarySearchTree(){

		weight = 0;

	}		

	//--------------------------------------------------------------------------------

	//Operations of the BinarySearchTree class
	
	//PUBLIC ADD METHOD CREATES A NODE AND CALLS ADD BASE METHOD, NEW NODE IS ONLY ADDED WHEN THERE IS NO KEY WITH THAT VALUE IN TREE
	//ASSUMES KEY AND VALUE != NULL
	
	public boolean add(K key, V value) {

		Node<K,V> newNode = new Node<K,V>(key,value);

		add(newNode);

		return true;
	}

	//------------------------------------------------------------------------------------

	// ADD BASE METHOD, CALLS ADD RECURSIVE

	protected void add(Node<K,V> newNode) {

		if(root != null) {	

			add(root,newNode);

		} else {	

			root = newNode;	

		}	
		weight++;
	}	

	//------------------------------------------------------------------------------------

	// ADD RECURSIVE METHOD

	private void add(Node<K,V> currentNode, Node<K,V> newNode){
		if(currentNode.getKey() != null) {
			if(newNode.getKey().compareTo(currentNode.getKey()) > 0) {
	
				Node<K,V> right = currentNode.getRight();
	
				if(right != null) {		
	
					add(right,newNode);
					
					right.updateNode();
	
				} else {	
	
					currentNode.setRight(newNode);	
	
					newNode.setParent(currentNode);
	
				}
	
			} else if(newNode.getKey().compareTo(currentNode.getKey()) < 0) {
	
				Node<K,V> left = currentNode.getLeft();
	
				if(left != null) {	
	
					add(left,newNode);
					
					left.updateNode();
	
				} else {	
	
					currentNode.setLeft(newNode);	
	
					newNode.setParent(currentNode);
	
				}
	
			} else {
	
				currentNode.getValues().add(newNode.getValues().get(0));
			}
		}
	}

	//------------------------------------------------------------------------------------

	// REMOVE METHOD 

	//If value is null iT will remove the node only if it has 1 value

	public boolean remove(K key, V value) {

		return removeBase(key,value) != null;

	}

	//------------------------------------------------------------------------------------

	// REMOVE BASE METHOD

	//For case 3 returned node parent is not real parent and neither its key and value, if values o repeated returned node is still part of the tree
	
	protected Node<K,V> removeBase(K key, V value) {	

		if(root != null) {

			return removeRecursive(key,value,root,null,false);	

		} else {

			return null;

		}

	}

	//------------------------------------------------------------------------------------

	// REMOVE RECURSIVE METHOD

	private Node<K, V> removeRecursive(K key, V value, Node<K,V> currentNode, Node<K,V> parent, boolean forceRemove){

		if(currentNode != null && currentNode.getKey() != null) {

			if(key.compareTo(currentNode.getKey()) < 0) {

				return removeRecursive(key, value, currentNode.getLeft(),currentNode,false);

			} else if(key.compareTo(currentNode.getKey()) >  0) {

				return removeRecursive(key, value, currentNode.getRight(),currentNode,false);

			} else {	

				if(currentNode.getValues().size() > 1 && !forceRemove) {
					
					if(currentNode.getValues().remove(value)) {
						
						weight--;
						
						return currentNode;
						
					}
					else {
						
						return null;
						
					}
				
				}
				else {
					if(value == null || currentNode.getValues().get(0).equals(value)) {

						Node<K,V> returnNode;
						
						if((currentNode.getLeft() == null || currentNode.getLeft().getKey() == null) 
								&& (currentNode.getRight() == null || currentNode.getRight().getKey() == null)) {

							if(currentNode == root) {

								root = null;

							} else {

								if(parent.getRight() != null && parent.getRight() == currentNode) {	

									parent.setRight(null);

								} else {

									parent.setLeft(null);

								}

							}	

							returnNode = currentNode;

						} else if(currentNode.getRight() == null || currentNode.getRight().getKey() == null) {	

							if(currentNode == root) {

								root.getLeft().setParent(null);

								root = root.getLeft();

							} else {

								currentNode.getLeft().setParent(parent);

								if(parent.getRight() != null && parent.getRight() == currentNode)				
									parent.setRight(currentNode.getLeft());

								else
									parent.setLeft(currentNode.getLeft());	

							}	
							
							returnNode = currentNode;

						} else if(currentNode.getLeft() == null || currentNode.getLeft().getKey() == null) {

							if(currentNode == root) {

								root.getRight().setParent(null);

								root = root.getRight();

							} else {

								currentNode.getRight().setParent(parent);

								if(parent.getRight() != null && parent.getRight() == currentNode)					
									parent.setRight(currentNode.getRight());

								else
									parent.setLeft(currentNode.getRight());

							}
							
							returnNode = currentNode;

						} else {

							Node<K,V> rightMin = getMin(currentNode.getRight());

							returnNode = rightMin;;

							currentNode.setKey(rightMin.getKey());

							currentNode.setValues(rightMin.getValues());				

							removeRecursive(rightMin.getKey(),null,rightMin,rightMin.getParent(),true);						
							
							weight++;

						}

						weight--;	

						return returnNode;	
					}
					else {
						return null;
					}
				}


			}

		} else {

			return null;

		}

	}

	//------------------------------------------------------------------------------------

	// GET MIN METHOD

	private Node<K,V> getMin(Node<K,V> node){

		while(node.getLeft() != null) {

			node = node.getLeft();

		}

		return node;	

	}

	//------------------------------------------------------------------------------------

	// SEARCH POSSIBLE VALUES METHOD

	public List<V> search(K key) {	

		if(root != null) {	

			Node<K,V> node = searchNodeRecursive(key,root);

			if(node!= null) {

				return node.getValues();
			}
			else {

				return null;

			}

		} 
		else {	

			return null;

		}

	}

	//------------------------------------------------------------------------------------

	// SEARCH METHOD

	public V search(K key, V value) {	

		if(root != null && value != null) {	

			Node<K,V> node = searchNodeRecursive(key,root);

			if(node!= null) {

				ArrayList<V> values = node.getValues();

				for (V v : values) {

					if(v.equals(value)) {

						return v;

					}

				}

				return null;
			}
			else {

				return null;

			}

		} 
		else {	

			return null;

		}

	}

	//------------------------------------------------------------------------------------

	// SEARCH NODE RECURSIVE METHOD

	private Node<K,V> searchNodeRecursive(K key, Node<K,V> currentNode){	

		if(currentNode != null) {

			if(key.compareTo(currentNode.getKey()) < 0) {	

				return searchNodeRecursive(key, currentNode.getLeft());		

			} else if(key.compareTo(currentNode.getKey()) >  0) {	

				return searchNodeRecursive(key, currentNode.getRight());

			} else {

				return currentNode;

			}

		} else {	

			return null;

		}		

	}

	//--------------------------------------------------------------------------------

	// GET'S METHOD

	public int getWeight() {	
		
		return weight;		
		
	}

	public int getHeight() {
		
		return root != null? root.getHeight():0;
		
	}	

	//------------------------------------------------------------------------------------

	// IS EMPTY METHOD

	public boolean isEmpty() {	

		return root == null;

	}

	//--------------------------------------------------------------------------------

	// PRE ORDER METHOD

	@Override
	public String preOrder() {

		if(root == null) {

			return null;

		} else {	

			return preOrder(root,"");

		}	

	}	

	//------------------------------------------------------------------------------------

	// PRE ORDERER METHOD (PRIVATE)

	private String preOrder(Node<K, V> currentNode, String preOrder) {

		if(currentNode == null) {

			return preOrder;

		} else {

			preOrder += "<" + currentNode.getKey() + "," + currentNode.getValues().toString() + "> ";

			preOrder = preOrder(currentNode.getLeft(),preOrder);

			preOrder = preOrder(currentNode.getRight(),preOrder);

			return preOrder;

		}

	}

	//--------------------------------------------------------------------------------

	// IN ORDER METHOD

	@Override	
	public String inOrder() {

		if(root == null) {

			return null;

		} else {

			return inOrder(root,"");

		}	

	}	

	//------------------------------------------------------------------------------------

	// IN ORDER METHOD (PRIVATE)

	private String inOrder(Node<K, V> currentNode, String inOrder) {

		if(currentNode == null) {

			return inOrder;

		} else {

			inOrder = inOrder(currentNode.getLeft(),inOrder);

			inOrder += "<" + currentNode.getKey() + "," + currentNode.getValues().toString() + "> ";	

			inOrder = inOrder(currentNode.getRight(),inOrder);

			return inOrder;

		}		

	}	

	//--------------------------------------------------------------------------------

	// POST ORDER METHOD

	@Override	
	public String postOrder() {

		if(root == null) {

			return null;

		} else {	

			return postOrder(root,"");

		}		

	}	

	//------------------------------------------------------------------------------------

	// POST ORDER METHOD (PRIVATE)

	private String postOrder(Node<K, V> currentNode, String postOrder) {

		if(currentNode == null) {

			return postOrder;

		} else {

			postOrder = postOrder(currentNode.getLeft(),postOrder);		

			postOrder = postOrder(currentNode.getRight(),postOrder);

			postOrder += "<" + currentNode.getKey() + "," + currentNode.getValues().toString() + "> ";

			return postOrder;

		}	

	}	

	//--------------------------------------------------------------------------------

	//For testing purposes

	protected Node<K,V> getRoot() {

		return root;

	}

	//--------------------------------------------------------------------------------

}
