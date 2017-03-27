/*```````````````````````````````````````````````````````````````````````````````````````````````````````
 * Assignment: 3 
 * Question:  1.a
 * Due Date: March 14th, 2017
 * Name: Jordan Reardon-Smith
 * Student Number: 115 7135
 * Course ID: 2947-50
 * Course Name: Data Structures and Algorithms
 * 
 * Description: A binary tree object which uses linked list positions to organize its structure 
 */




package Assignment3;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class LinkedBinaryTree<E> implements BinaryTree<E> {
	/**
	 * Core Variables for the object, root as innitial point in the tree, and size which counts total number of elements
	 */
	private Node<E> root = null;
	private int size = 0;
	
	
	/**
	 *Nested class which creates our positional node objects within our binary tree 
	 * @author jordan
	 *
	 * @param <E> Object type which is to be stored within our tree
	 */
	public static class Node<E> implements Position<E>{
		/**
		 * Core variables for our node, including the element and references to parent,left and right linked positions
		 */
		private E element;
		private Node<E> parent;
		private Node<E> left;
		private Node<E> right;
		
		/**
		 * Constructor object
		 * @param e element to store within the positional object
		 * @param p parent node reference
		 * @param l left child node reference
		 * @param r right childe node reference
		 */
		public Node(E e, Node<E> p, Node<E> l, Node<E> r){
			setElement(e);
			setParent(p);
			setLeft(l);
			setRight(r);
		}
		
		
		public E getElement() throws IllegalStateException {return this.element;}
		public Node<E> getParent(){return this.parent;}
		public Node<E> getLeft(){return this.left;}
		public Node<E> getRight(){return this.right;}
		
		/**
		 * Setter methods
		 * @param e element to store within node
		 * @param p parent reference for node
		 * @param l left child reference for node
		 * @param r right child reference for node
		 * 
		 */
		public void setElement(E e){this.element = e;}
		public void setParent(Node<E> p){this.parent = p;}
		public void setLeft(Node<E> l){this.left = l;}
		public void setRight(Node<E> r){this.right =r;}
		
		
	}

	/**
	 * Calls our Node Constructor, and returns an instantiated Node
	 * @param e element of node
	 * @param parent reference
	 * @param left child reference
	 * @param right child reference
	 * @return
	 */
	protected Node<E> createNode(E e, Node<E> parent, Node<E> left, Node<E> right){
		return new Node<E>(e,parent,left,right);
	}
	
	
	/**
	 * Checks and validates nodes passed to it, to ensure they are valid before performing future algorithms.
	 * @param p Position to validate
	 * @return node after it is validated as existing
	 * @throws IllegalArgumentException
	 */
	protected Node<E> validate(Position<E> p) throws IllegalArgumentException{
		if(!(p instanceof Node)){throw new IllegalArgumentException("Not a valid position type");}
		Node<E> node = (Node<E>)p;
		if(node.getParent()==node) throw new IllegalArgumentException("p (" +p.getElement() + ") is no longer in the tree");
		return node;
	
	}
	
	/**
	 * @return root value
	 */
	public Position<E> root() {return this.root;}
	
	/**
	 * Checks to see if node is internal
	 * @param p Posistion to test
	 * @return True if the p has children
	 */
	public boolean isInternal(Position<E> p) throws IllegalArgumentException {return (numChildren(p)!= 0);}

	/**
	 * Checks to see if node is external
	 * @param p Posistion to test
	 * @return True if the p has no children
	 */
	public boolean isExternal(Position<E> p) throws IllegalArgumentException {return (!isInternal(p));}
	
	
	/**
	 * Checks if element is root.
	 * @param p Position tpo test
	 * @return true if element is root position in tree
	 */
	public boolean isRoot(Position<E> p) throws IllegalArgumentException {return (root()==p);}
	
	/**
	 * Returns quantity of elements sotred within the tree
	 * @return sizez of tree
	 */
	public int size() {return size;}
	
	
	/**
	 * @return True if tree contains a size of 0
	 */
	public boolean isEmpty() {return (size==0);}
	
	

	/***
	 * Returns the parent of the entered Node
	 * @param position to search parent
	 * @return the parent of target position
	 */
	public Position<E> parent(Position<E> p) throws IllegalArgumentException {
		Node<E> target = validate(p);
		return target.parent;
		
	}

	
	/**
	 * Moves through our tree tracking the positions stored within
	 * @param position to began tracking from
	 * @return snapshot iterable object of our positions
	 */
	public Iterable<Position<E>> children(Position<E> p) throws IllegalArgumentException {
		int max = 2;
		Node<E> target = (Node<E>) p;
		List<Position<E>> snapshot =new ArrayList<>(max);
		if (target.left != null){snapshot.add(target.left);}
		if (target.right != null){snapshot.add(target.right);}
		return snapshot;
	}

	/**
	 * Returns the amount of children a posistion has
	 * @param posistion to begin count from
	 * @return numChildren the quantity of children an object has
	 */
	public int numChildren(Position<E> p) throws IllegalArgumentException {
		int numChildren = 0;
		Node<E> target = (Node<E>) p;
		if(target.left != null){numChildren++;}
		if(target.right != null){numChildren++;}
		return numChildren;
		
		
	}

	/**
	 * Nested class which iterates through elemets rather then positions
	 * @author jordan
	 *
	 */
	private class ElementIterator implements Iterator<E>{
		Iterator<Position<E>> posIterator = positions().iterator();
		/**
		 * @return true if another object remains
		 */
		public boolean hasNext() {return posIterator.hasNext();}
		/***
		 * @return the next element
		 */
		public E next(){return posIterator.next().getElement();}
		
		/**remove target element
		 * 
		 */
		public void remove(){posIterator.remove();}	
	}


	/**
	 * Organizes all of our objects into a snapshot in breadthrough order
	 * @return an interable snapshot of our tree in breadfirst order
	 */
	public Iterable<Position<E>> breadthfirst(){
		List<Position<E>> snapshot = new ArrayList<>();
		if(!isEmpty()){
			Queue<Position<E>> fringe = new LinkedList<>();
			fringe.add(root());
			while(!fringe.isEmpty()){
				Position<E> p = fringe.remove();
				snapshot.add(p);
				for(Position<E> c: children(p)){fringe.add(c);}
				
			}
		}
		return snapshot;
		
	}
	
	
	
	/**
	 * To String method to print our tree in an easily readable format.
	 */
	@Override
	public String toString(){
		Iterable<Position<E>> snapshot = breadthfirst();
	
		String output ="\nBinary Tree\n----------\n";
		int i =0;
		for (Position<E> p:snapshot ){
			output+= "[" +i +"]" + p.getElement() +"\n";
			i++;
		}
		
				
		
		return output;
	}
	
	
	/*
	 * 
	 * @see Assignment3.Tree#iterator()
	 */
	public Iterator<E> iterator() {return new ElementIterator();}

	/**
	 * @return a call to preorder method, and returns an organized representation of tree positions
	 */
	public Iterable<Position<E>> positions() {return preorder();}
	
	/**
	 * returns a snapshot of the tree in preorder organziation
	 * @return snapshot of tree
	 */
	public Iterable<Position<E>> preorder(){
		List<Position<E>> snapshot = new ArrayList<>();
		if (!isEmpty()){preOrderSubtree(root(),snapshot);}
		return snapshot;
	}

	/**
	 * 
	 * Recursive method that continues the preorder tracking of a tree by examining the subtree
	 * @param p position to began tracking from
	 * @param snapshot, current snapshot to work from
	 * @param snapshot
	 */
	private void preOrderSubtree(Position<E> p, List<Position<E>> snapshot){
		snapshot.add(p);
		for (Position<E> c :children(p)){
			preOrderSubtree(c,snapshot);
		}
		
	}
	
	
	/**
	* Returns the Position of p's left child (or null if no child exists).
	*
	* @param p A valid Position within the tree
	* @return the Position of the left child (or null if no child exists)
	* @throws IllegalArgumentException if p is not a valid Position for this tree
	*/
	
	public Position<E> left(Position<E> p) throws IllegalArgumentException {
		Node<E> target = validate(p);
		return target.left;
	
	}
	
	/**
	* Returns the Position of p's right child (or null if no child exists).
	*
	* @param p A valid Position within the tree
	* @return the Position of the right child (or null if no child exists)
	* @throws IllegalArgumentException if p is not a valid Position for this tree
	*/
	public Position<E> right(Position<E> p) throws IllegalArgumentException {
		Node<E> target = validate(p);
		return target.right;
	}

	/**
	* Returns the Position of p's sibling (or null if no sibling exists).*
	* @param p A valid Position within the tree
	* @return the Position of the sibling (or null if no sibling exists)
	* @throws IllegalArgumentException if p is not a valid Position for this tree
	*/
	public Position<E> sibling(Position<E> p) throws IllegalArgumentException {
		Node<E> target = (Node<E>) p;
		
		if(target == root()){return null;}
		Node<E> parent = target.getParent();
		if(parent.getLeft() == target){
			return parent.getLeft();
		}
		return parent.getRight();
		
	}

	/**
	 * Add a root element to a posistion which is stored at root of tree
	 * @param e element to add as root
	 * @return root posistion object 
	 * @throws IllegalStateException if tree already has a root
	 */
	public Position<E> addRoot(E e) throws IllegalStateException{	
			if (!isEmpty()){throw new IllegalStateException("Tree is not empty");}
			root = createNode(e,null,null,null);
			size =1;
			return root;
	}
	
	/**
	 * Add a position to be the left child of the passed position
	 * @param e element to add as left child
	 * @return  position object 
	 * @throws IllegalStateException if the passed position already has a left child
	 */
	public Position<E> addLeft(Position<E> p, E e) throws IllegalStateException{	
		
		Node<E> parent = validate(p);
		if (parent.getLeft() != null){throw new IllegalStateException("P (" +p.getElement() + ")already has a left child");}
		Node<E> child = createNode(e,parent,null,null);
		parent.setLeft(child);
		size++;
		return child;
		
	}
	
	/**
	 * Add a position to be the right child of the passed position
	 * @param e element to add as right child
	 * @return  position object 
	 * @throws IllegalStateException if the passed position already has a right child
	 */
	public Position<E> addRight(Position<E> p, E e) throws IllegalStateException{	
		
		Node<E> parent = validate(p);
		if (parent.getRight() != null){throw new IllegalStateException("P (" +p.getElement() + ")already has a right child");}
		Node<E> child = createNode(e,parent,null,null);	
		parent.setRight(child);
		size++;
		return child;
		
	}	
	
	
	/**
	 * 
	 * Swaps out the element at a given position 
	 * @param p position to store element in
	 * @param e element to store in position
	 * @return value originally in position
	 * @throws IllegalArgumentException if position does not exist
	 */
	
	
	public E set(Position<E> p, E e) throws IllegalArgumentException{
		
		Node<E> node = validate(p);
		E temp = node.getElement();
		node.setElement(e);
		return temp;
	}
	
	/**
	 * 
	 * @param p position to remove
	 * @return temp element that was store in removed position
	 * @throws IllegalArgumentException if obejct is not at bottom of tree
	 */
	public E remove(Position<E> p) throws IllegalArgumentException{
		Node<E> node = validate(p);
		if (numChildren(p)==2)throw new IllegalArgumentException("P has two children");
		Node<E> child = (node.getLeft() != null ? node.getLeft() : node.getRight());
		if(child!=null){child.setParent(node.getParent());}
		if(node==root){root=child;}
		else{
			Node<E> parent = node.getParent();
			if(node==parent.getLeft()){parent.setLeft(child);}
			else{parent.setRight(child);}
		}
		size--;
		E temp = node.getElement();
		node.setElement(null);
		node.setLeft(null);
		node.setRight(null);
		node.setParent(node);
		return temp;
		
	}
		

}

