/*```````````````````````````````````````````````````````````````````````````````````````````````````````
 * Assignment: 3 
 * Question:  1.a
 * Due Date: March 14th, 2017
 * Name: Jordan Reardon-Smith
 * Student Number: 115 7135
 * Course ID: 2947-50
 * Course Name: Data Structures and Algorithms
 * 
 * Description: A linked-Heap-Priority-Queue which implements a LinkedBinaryTree to store the values as a heap.
 */




package Assignment3;

import java.util.Comparator;


/*
 * @author Jordan
 */
public class LinkedHeapPriorityQueue<K,V> implements PriorityQueue<K, V>{

	//core tree which stores our positions
	LinkedBinaryTree<Entry<K,V>> heap = new LinkedBinaryTree<Entry<K, V>>();
	
	/**
	 * 
	 * @author jordan
	 *
	 *Nested Constructor class for our PQEntry
	 *
	 * @param <K> Type of Key
	 * @param <V> Type of Value
	 */
	protected static class PQEntry<K,V> implements Entry<K, V>{
		private K k;//key
		private V v; //value
		
		/**
		 * Constructor for our PQEntry
		 * @param key to store in PQEntry
		 * @param value to store in PQEntry
		 */
		public PQEntry(K key, V value){
			this.k = key;
			this.v = value;
		}
		
		/**
		* Returns the key stored in this entry.
		* @return the entry's key
		*/
		public K getKey(){return this.k;}

		/**
		* Returns the value stored in this entry.
		* @return the entry's Value
		*/
		public V getValue(){return this.v;}
		
		/**
		 * Setter methods:
		 * @param key to store in entry
		 * @param value to store in entry
		 */
		protected void setKey(K key){this.k = key;}
		protected void setValue(V value){this.v = value;}
		
		/**
		 * @return a string to print out the entry
		 */
		@Override
		public String toString(){
			return (getValue() +"   <Key:" + getKey() +">");
		}
	}
	
	/**
	 * @return a string of our binary tree object with all entries
	 */
	@Override
	public String toString(){
		
		return "" + heap;
	
	}
	
	//comp is our comparator object
	private Comparator<K> comp;
	
	/***
	 * 
	 * @param c comparator type to set our comp object too
	 */
	protected LinkedHeapPriorityQueue(Comparator<K> c){comp = c;}
	
	/**
	 * Sets our default comparator should one not be chosen innitialy
	 */
	protected LinkedHeapPriorityQueue(){this(new DefaultComparator<K>());}

	/**
	 * Compares to entries and returns an integer value which represents the result
	 * @param a entry to compare to b
	 * @param b entry to compare to a
	 * @return an integer value corresponding to whhhter a is smaller, equal or larger then b
	 */
	protected int compare(Entry<K,V> a, Entry<K,V>b){return comp.compare(a.getKey(), b.getKey());}

	/**
	 * Validates the key against itself
	 * 
	 * @param key to validate
	 * @return true if the key is valid
	 * @throws IllegalArgumentException if key is not valid
	 */
	protected boolean checkKey(K key) throws IllegalArgumentException{
		try{return(comp.compare(key,key)==0	);}
		catch(ClassCastException e){throw new IllegalArgumentException("Incompatible Key");}
		
	}
	
	
	/**
	 * @return an int representation of the size of the tree
	 */
	public int size() {return heap.size();}
	
	
	/**
	 * @return true if the heaps size is 0
	 */
	public boolean isEmpty() {return (heap.size()==0);}


	
	/**
	 * Creates a new entry and adds it to our heap 
	 * 
	 * @param k key to add to the heap
	 * @param v value to add to the heap
	 * @throws IllegalArgumentException
	 */
	public Entry<K,V> insert(K k, V v) throws IllegalArgumentException {
	
		int newSize= (heap.size()+1);
		K key = (K)k;
		V value = (V)v;
		Position<Entry<K,V>> target=null;
		checkKey(key);//validate key
		
		Entry<K,V> newest = new PQEntry<>(key,value);//create an entry with input values
		if(heap.size()==0){heap.addRoot(newest);return newest;}//if tree is empty, add entry to root

		else{//if tree contains elements, determine final position to add to
			String binary = Integer.toBinaryString(newSize);//convert size with new entry to binary
			char[] bin = binary.toCharArray(); //convert binary string to char array			
			
			target = heap.root(); //start at root
		
			
			for(int i = 0; i< bin.length-1; i++){ //loop through array moving to corect position (stop before last elemeent)
				
				if(i==0){target = heap.root();}	//start at root
				else if(bin[i]=='0'){target = heap.left(target);} //go left if next value is 0
				else{target = heap.right(target);} //otherwise go right
			}
			if(bin[bin.length-1] == '0'){  //check final value, if 0 add to right position of current node
				heap.addLeft(target, newest);
				target= heap.left(target);
				
			}
			else{
				heap.addRight(target, newest);//otherwise add to right position of current node
				target= heap.right(target);
				
			}
			upheap(target); //upheat to reorder the tree
			
		}
		return newest; //return the position of the newly created entry
	
	
	}

	/**
	 * Compares a target node with its parent to determine if a swap is required.
	 * @param target to begin upheap at
	 */
	protected void upheap(Position<Entry<K,V>> target){
		
		
		while(heap.parent(target)!=null){
			Position<Entry<K,V>> parent = heap.parent(target); //create reference to parent node
			if(compare(target.getElement(), parent.getElement())>=0){break;}
			swap(target,parent);//swap elements
			target = heap.parent(target);
		}
		
		
	}
	/**
	 * Compares a target node with its child to determine if a swap is required.
	 * @param target to begin downheap at
	 */	
	protected void downheap(Position<Entry<K,V>> target){
		while (heap.left(target)!=null){
			Position<Entry<K,V>> leftPosition = heap.left(target); //reference to left position
			Position<Entry<K,V>> smallChildPosition = leftPosition;//reference to smallest position, assumed left
			if(heap.right(target)!=null){ //if right position exists
				Position<Entry<K,V>> rightPosition = heap.right(target);
				if(compare(leftPosition.getElement(),rightPosition.getElement()) >0 ) {
					smallChildPosition = rightPosition;//if right is smaller then left, switch out smallest psoistion
				}
			}
			if(compare(smallChildPosition.getElement(), target.getElement()) >=0){break;}
			swap(target,smallChildPosition);//if smallest position is smaller then target, swap
			target= smallChildPosition;//the target is now the smallest position
		}
				
			
			
			
	}
	
	/**
	 * Swithches to positions
	 * 
	 * @param target to swap
	 * @param target2 to swap with
	 * @return target child resulted from swap (its easy to get parent from a child, but not vice versa, child is returned)
	 */
	protected Position<Entry<K,V>> swap(Position<Entry<K,V>> target, Position<Entry<K,V>> target2){
		Entry<K, V> temp = target.getElement(); //temp value to store while we switch places
		heap.set(target, target2.getElement());
		heap.set(target2, temp);
		return target;
	}
	
	
	
	
	
	
	/**
	 * Returns the element foound at the top of the heap
	 * @return root element
	 */
	public Entry<K, V> min() {
		
		if(heap.isEmpty()){return null;}
		return heap.root().getElement();
	
	}


	
	/**
	 * @return entry that is removed at top of heap
	 */
	public Entry<K,V> removeMin() {
		
		if(heap.isEmpty()){return null;}
		Position<Entry<K, V>> target = heap.root();
		
		
		Entry<K,V> answer = heap.root().getElement();
		if(heap.size()==1){heap.remove(target);return answer;}//if root is only element, simply return and remove it
		
		//find lowest value by converting to bin and following same path as insert() .
		String binary = Integer.toBinaryString(heap.size());
		char[] bin = binary.toCharArray();			
		
		
		for(int i = 0; i< bin.length; i++){
			
			if(i==0){target = heap.root();}	
			else if(bin[i]=='0'){target = heap.left(target);}
			else{target = heap.right(target);}
		}
	
	
		swap(target,heap.root());//swap final value with root so root can be removed
		
	
		heap.remove(target); //remove bottom element
		downheap(heap.root());//perform down heap to preservce order
		
		return answer; //removed element
	}

		
	
	
}
