package collections;

public class BinarySearchTree<K extends Comparable<K>,V> implements BinarySearchTreeInterface<K,V>{
	
	//--------------------------------------------------------------------------------
	
	protected Node<K,V> root;
	
	protected int weight;
	
	//--------------------------------------------------------------------------------

	public BinarySearchTree(){
		
		weight = 0;
		
	}		
	
	//--------------------------------------------------------------------------------
		
	public boolean add(K key, V value) {
		return addBase(key,value) != null;
	}
		
	protected Node<K,V> addBase(K key, V value) {
		
		Node<K,V> newNode = new Node<K,V>(key,value);
		
		if(root != null) {		
			return addRecursive(root,newNode);			
		}		
		else {			
			root = newNode;			
			weight++;			
			return root;			
		}			
	}	
	
	private Node<K,V> addRecursive(Node<K,V> currentNode, Node<K,V> newNode){
		
		if(newNode.getKey().compareTo(currentNode.getKey()) > 0) {
			
			Node<K,V> right = currentNode.getRight();
			
			if(right != null) {		
				Node<K,V> addedNode = addRecursive(right,newNode);
				
				if(addedNode != null) {
					right.updateNode();
				}				
				
				return addedNode;		
			}
			
			else {				
				currentNode.setRight(newNode);	
				newNode.setParent(currentNode);
				weight++;				
				return newNode;				
			}			
		}		
		else if(newNode.getKey().compareTo(currentNode.getKey()) < 0) {			
			Node<K,V> left = currentNode.getLeft();
			
			if(left != null) {				
				Node<K,V> addedNode =  addRecursive(left,newNode);	
				
				if(addedNode != null) {
					left.updateNode();
				}
								
				return addedNode;
			}
			
			else {				
				currentNode.setLeft(newNode);	
				newNode.setParent(currentNode);
				weight++;				
				return newNode;				
			}			
		}	
		else {
			return null;
		}
	}
	
	//--------------------------------------------------------------------------------
	
	public boolean remove(K key) {
		return removeBase(key) != null;
	}
	
	//For case 3 returned node parent is not real parent
	protected Node<K,V> removeBase(K key) {	
		if(root != null) {
			return removeRecursive(key,root,null);			
		}		
		else {
			return null;
		}
	}
	
	private Node<K, V> removeRecursive(K key, Node<K,V> currentNode, Node<K,V> parent){
		if(currentNode != null) {
			if(key.compareTo(currentNode.getKey()) < 0) {
				return removeRecursive(key, currentNode.getLeft(),currentNode);
			}
			else if(key.compareTo(currentNode.getKey()) >  0) {
				return removeRecursive(key, currentNode.getRight(),currentNode);
			}
			else {	
				Node<K,V> returnNode = new Node<>(currentNode.getKey(),currentNode.getValue());
				returnNode.setParent(parent);
				if(currentNode.getLeft() == null && currentNode.getRight() == null) {
					
					if(currentNode == root) {
						root = null;
					}
					else {
						if(parent.getRight() != null && parent.getRight() == currentNode) {					
							parent.setRight(null);
						}
						else {
							parent.setLeft(null);
						}						
					}	
					returnNode = currentNode;
					
				}
				else if(currentNode.getRight() == null) {	
					
					if(currentNode == root) {
						root.getLeft().setParent(null);
						root = root.getLeft();
					}
					else {
						currentNode.getLeft().setParent(parent);
												
						if(parent.getRight() != null && parent.getRight() == currentNode)				
							parent.setRight(currentNode.getLeft());
							
						else
							parent.setLeft(currentNode.getLeft());						
					}					
				}
				else if(currentNode.getLeft() == null) {
					
					if(currentNode == root) {
						root.getRight().setParent(null);
						root = root.getRight();
					}
					else {
						currentNode.getRight().setParent(parent);
						
						if(parent.getRight() != null && parent.getRight() == currentNode)					
							parent.setRight(currentNode.getRight());
						else
							parent.setLeft(currentNode.getRight());
					}					
				}
				else {
					Node<K,V> rightMin = getMin(currentNode.getRight());
					returnNode = new Node<>(currentNode.getKey(),currentNode.getValue());	
					
					currentNode.setKey(rightMin.getKey());
					currentNode.setValue(rightMin.getValue());				
									
					
					returnNode.setParent(removeRecursive(rightMin.getKey(),rightMin,rightMin.getParent()).getParent());					
				}
				
				weight--;								
				return returnNode;				
			}
		}
		else {
			return null;
		}
	}
		
	private Node<K,V> getMin(Node<K,V> node){
		
		while(node.getLeft() != null) {
			
			node = node.getLeft();
			
		}
		
		return node;	
		
	}
	
	//--------------------------------------------------------------------------------
	
	public V search(K key) {	
		
		if(root != null) {		
			Node<K,V> node = searchNodeRecursive(key,root);
			return (node != null)? node.getValue() : null;			
		}		
		else {			
			return null;			
		}
		
	}
	
	private Node<K,V> searchNode(K key){
		if(root != null) {
			
			return searchNodeRecursive(key,root);
			
		}
		
		else {
			
			return null;
			
		}
	}
	
	private Node<K,V> searchNodeRecursive(K key, Node<K,V> currentNode){	
		if(currentNode != null) {
			if(key.compareTo(currentNode.getKey()) < 0) {	
				return searchNodeRecursive(key, currentNode.getLeft());			
			}
			
			else if(key.compareTo(currentNode.getKey()) >  0) {			
				return searchNodeRecursive(key, currentNode.getRight());			
			}
			else {
				return currentNode;
			}
		}
		else {					
			return null;	
		}		
		
	}
	//--------------------------------------------------------------------------------
	
	public boolean update(K key, V value) {
		
		Node<K,V> nodeToUpdate = searchNode(key);
		
		if(nodeToUpdate != null) {
			nodeToUpdate.setValue(value);
			
			return true;			
		}		
		else {			
			return false;			
		}	
		
	}
	
	//--------------------------------------------------------------------------------
	
	public int getWeight() {		
		return weight;		
	}
	
	//--------------------------------------------------------------------------------

	public int getHeight() {
		return root.getHeight();
	}	
	
	//--------------------------------------------------------------------------------
	
	public boolean isEmpty() {			
		return root == null;
	}
		
	//--------------------------------------------------------------------------------
	
	@Override
	public String preOrder() {
		if(root == null) {
			return null;
		}
		else {			
			return preOrder(root,"");
		}		
	}	
	
	private String preOrder(Node<K, V> currentNode, String preOrder) {
		
		if(currentNode == null) {
			return preOrder;
		}
		else {
			preOrder += "<" + currentNode.getKey() + "," + currentNode.getValue() + "," +currentNode.getHeight() + "> ";
			preOrder += (currentNode instanceof AVLTreeNode<?,?>? ((AVLTreeNode<K,V>)currentNode).getBalanceFactor():"");
			preOrder = preOrder(currentNode.getLeft(),preOrder);
			preOrder = preOrder(currentNode.getRight(),preOrder);
			return preOrder;
		}		
	}
	
	//--------------------------------------------------------------------------------

	@Override	
	public String inOrder() {
		if(root == null) {
			return null;
		}
		else {			
			return inOrder(root,"");
		}		
	}	
	
	private String inOrder(Node<K, V> currentNode, String inOrder) {
		
		if(currentNode == null) {
			return inOrder;
		}
		else {
			inOrder = inOrder(currentNode.getLeft(),inOrder);
			inOrder += "<" + currentNode.getKey() + "," + currentNode.getValue() + "> ";			
			inOrder = inOrder(currentNode.getRight(),inOrder);
			return inOrder;
		}		
	}	
	
	//--------------------------------------------------------------------------------

	@Override	
	public String postOrder() {
		if(root == null) {
			return null;
		}
		else {			
			return postOrder(root,"");
		}		
	}	
	
	private String postOrder(Node<K, V> currentNode, String postOrder) {
		
		if(currentNode == null) {
			return postOrder;
		}
		else {
			postOrder = postOrder(currentNode.getLeft(),postOrder);						
			postOrder = postOrder(currentNode.getRight(),postOrder);
			postOrder += "<" + currentNode.getKey() + "," + currentNode.getValue() + "> ";
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
