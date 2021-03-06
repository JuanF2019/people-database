/*
 * UNIVERSIDAD ICESI
 * TAREA INTEGRADORA 2 - ESTRUCTURAS DE DATOS
 * RODAS / DIAZ / MARTINEZ
 */

package collections;

public class RedBlackTree<K extends Comparable<K>,V> extends BinarySearchTree<K, V> implements BinarySearchTreeInterface<K,V> {

	//------------------------------------------------------------------------------------

	// AUTO GENERATED

	private static final long serialVersionUID = -367507416441450498L;

	private RedBlackTreeNode<K,V> nillNode;

	//------------------------------------------------------------------------------------

	// CONSTRUCTOR METHOD

	public RedBlackTree() {

		super();

		nillNode = new RedBlackTreeNode<K,V>(null,null);

		nillNode.setColor(Color.BLACK);

	}

	//------------------------------------------------------------------------------------

	// ADD METHOD FROM THE RED BLACK TREE CLASS

	public boolean add(K key, V value) {
		
		RedBlackTreeNode<K,V> addedNode = new RedBlackTreeNode<K,V>(key,value);
		
		add(addedNode);				

		addedNode.setLeft(nillNode);

		addedNode.setRight(nillNode);
		
		if(addedNode == root || addedNode.getParent() == null) {
			
			return true;
			
		} else {

			addFixUp(addedNode);					

			return true;

		}
		
	}

	//------------------------------------------------------------------------------------

	//Based on PSEUDOCODE from CORMEN Introduction to algorithms 3ed
	//When z is red there is no violation on tree properties

	public void addFixUp(RedBlackTreeNode<K,V> z){

		while(((RedBlackTreeNode<K,V>)z.getParent()).getColor() == Color.RED) {

			RedBlackTreeNode<K,V> uncle;

			if(z.getParent() == z.getParent().getParent().getLeft()) {

				uncle = (RedBlackTreeNode<K,V>)z.getParent().getParent().getRight();

				//Case 1: Uncle color is red
				if(uncle.getColor() == Color.RED) {

					((RedBlackTreeNode<K,V>) z.getParent()).setColor(Color.BLACK);

					uncle.setColor(Color.BLACK);

					((RedBlackTreeNode<K,V>) z.getParent().getParent()).setColor(Color.RED);

					z = (RedBlackTreeNode<K,V>) z.getParent().getParent();

				} else { //Case 2 and 3: Uncle color is black

					//Case 2 forms < triangle
					if(z == z.getParent().getRight()) {

						z = (RedBlackTreeNode<K, V>) z.getParent();

						rotateLeft(z);

					}

					//Case 3 forms / line
					((RedBlackTreeNode<K, V>) z.getParent()).setColor(Color.BLACK);

					((RedBlackTreeNode<K,V>) z.getParent().getParent()).setColor(Color.RED);

					rotateRight((RedBlackTreeNode<K, V>) z.getParent().getParent());

				}

			} else {

				uncle = (RedBlackTreeNode<K,V>)z.getParent().getParent().getLeft();

				//Case 1: Uncle color is red
				if(uncle.getColor() == Color.RED) {

					((RedBlackTreeNode<K,V>) z.getParent()).setColor(Color.BLACK);

					uncle.setColor(Color.BLACK);

					((RedBlackTreeNode<K,V>) z.getParent().getParent()).setColor(Color.RED);

					z = (RedBlackTreeNode<K,V>) z.getParent().getParent();

				} else { //Case 2 and 3: Uncle color is black (when exist and its black or when it does not exist)

					//Case 2 forms > triangle
					if(z == z.getParent().getLeft()) {

						z = (RedBlackTreeNode<K, V>) z.getParent();

						rotateRight(z);

					}

					//Case 3 forms \ line

					((RedBlackTreeNode<K, V>) z.getParent()).setColor(Color.BLACK);

					((RedBlackTreeNode<K,V>) z.getParent().getParent()).setColor(Color.RED);

					rotateLeft((RedBlackTreeNode<K, V>) z.getParent().getParent());

				}

			}

		}

		//Case 0: Z is root, covered by always setting root to black

		((RedBlackTreeNode<K,V>)root).setColor(Color.BLACK);

	}

	//------------------------------------------------------------------------------------

	// REMOVE METHOD FROM THE RED BLACK TREE CLASS

	public boolean remove(K key, V value) {

		RedBlackTreeNode<K,V> removedNode = (RedBlackTreeNode<K, V>) super.removeBase(key,value);

		//Values list from a node will only have size 1 when the node had been removed
		
		if(removedNode != null && removedNode.getValues().size() == 1) {

			if(removedNode.getColor() == Color.BLACK) {
				//Sends right or left, as real removed node was replaced in tree with its successor, in case is a leaf it points to nill node
				removeFixUp((removedNode.getLeft() == nillNode)? ((RedBlackTreeNode<K, V>)removedNode.getRight()):((RedBlackTreeNode<K, V>)removedNode.getLeft()));			
				
			}
			
			return true;

		} else {
			
			return false;

		}	

	}

	//------------------------------------------------------------------------------------

	//Based on PSEUDOCODE from CORMEN Introduction to algorithms 3ed

	public void removeFixUp(RedBlackTreeNode<K,V> x){

		while(x != root && x.getColor() == Color.BLACK) {

			RedBlackTreeNode<K,V> sibling;

			if(x == x.getParent().getLeft()) {

				sibling = (RedBlackTreeNode<K,V>) x.getParent().getRight();

				//Case 1: x sibling is red
				if(sibling.getColor() == Color.RED) {

					sibling.setColor(Color.BLACK);

					((RedBlackTreeNode<K, V>) sibling.getParent()).setColor(Color.RED);

					rotateLeft((RedBlackTreeNode<K, V>) x.getParent());

					sibling = (RedBlackTreeNode<K, V>) x.getParent().getRight();

				}

				//Case 2: x sibling is black and sibling's children are black

				if(((RedBlackTreeNode<K, V>) sibling.getLeft()).getColor() == Color.BLACK 

					&& ((RedBlackTreeNode<K, V>) sibling.getRight()).getColor() == Color.BLACK) {

					sibling.setColor(Color.RED);

					x = (RedBlackTreeNode<K, V>) x.getParent();	

				} else {

					//Case 3: x sibling is black and sibling's left child is red and right child is black

					if(((RedBlackTreeNode<K, V>)sibling.getRight()).getColor() == Color.BLACK) {
						((RedBlackTreeNode<K, V>) sibling.getLeft()).setColor(Color.BLACK);

						sibling.setColor(Color.RED);

						rotateRight(sibling);

						sibling = (RedBlackTreeNode<K, V>) x.getParent().getRight();

					}

					//Case 4: x sibling is black and sibling's right child is red

					sibling.setColor(((RedBlackTreeNode<K, V>) x.getParent()).getColor());

					((RedBlackTreeNode<K, V>) x.getParent()).setColor(Color.BLACK);

					((RedBlackTreeNode<K, V>) sibling.getRight()).setColor(Color.BLACK);

					rotateLeft((RedBlackTreeNode<K, V>) x.getParent());

					x = (RedBlackTreeNode<K, V>) root;

				}		

			} else {

				sibling = (RedBlackTreeNode<K,V>) x.getParent().getLeft();

				//Case 1: x sibling is red
				if(sibling.getColor() == Color.RED) {

					sibling.setColor(Color.BLACK);

					((RedBlackTreeNode<K, V>) sibling.getParent()).setColor(Color.RED);

					rotateRight((RedBlackTreeNode<K, V>) x.getParent());

					sibling = (RedBlackTreeNode<K, V>) x.getParent().getLeft();

				}

				//Case 2: x sibling is black and sibling's children are black
				if(((RedBlackTreeNode<K, V>) sibling.getRight()).getColor() == Color.BLACK 

					&& ((RedBlackTreeNode<K, V>) sibling.getLeft()).getColor() == Color.BLACK) {

					sibling.setColor(Color.RED);

					x = (RedBlackTreeNode<K, V>) x.getParent();	

				} else {

					//Case 3: x sibling is black and sibling's right child is red and left child is black
					if(((RedBlackTreeNode<K, V>)sibling.getLeft()).getColor() == Color.BLACK) {

						((RedBlackTreeNode<K, V>) sibling.getRight()).setColor(Color.BLACK);

						sibling.setColor(Color.RED);

						rotateLeft(sibling);

						sibling = (RedBlackTreeNode<K, V>) x.getParent().getLeft();

					}

					//Case 4: x sibling is black and sibling's left child is red

					sibling.setColor(((RedBlackTreeNode<K, V>) x.getParent()).getColor());

					((RedBlackTreeNode<K, V>) x.getParent()).setColor(Color.BLACK);

					((RedBlackTreeNode<K, V>) sibling.getLeft()).setColor(Color.BLACK);

					rotateRight((RedBlackTreeNode<K, V>) x.getParent());

					x = (RedBlackTreeNode<K, V>) root;

				}

			}

		}

	}

	//------------------------------------------------------------------------------------

	//Returns new subtree root

	private RedBlackTreeNode<K,V> rotateRight(RedBlackTreeNode<K,V> node) {

		if(node.getLeft() == null) {

			return node;

		} else {

			RedBlackTreeNode<K,V> left = (RedBlackTreeNode<K,V>)node.getLeft();

			RedBlackTreeNode<K,V> parent = (RedBlackTreeNode<K,V>)node.getParent();


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

			//Update nodes heights
			node.updateNode();

			left.updateNode();

			if(parent != null) {

				parent.updateNode();

			}

			return left;

		}	

	}

	//------------------------------------------------------------------------------------

	//Returns new subtree root

	private RedBlackTreeNode<K,V> rotateLeft(RedBlackTreeNode<K,V> node) {

		if(node.getRight() == null) {

			return node;

		} else {

			RedBlackTreeNode<K,V> right = (RedBlackTreeNode<K,V>)node.getRight();

			RedBlackTreeNode<K,V> parent = (RedBlackTreeNode<K,V>)node.getParent();

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

			//Update nodes heights

			node.updateNode();

			right.updateNode();

			if(parent != null) {

				parent.updateNode();

			}

			return right;

		}	

	}	
	
	//------------------------------------------------------------------------------------

}
