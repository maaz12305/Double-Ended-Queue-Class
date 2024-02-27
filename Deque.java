package hw5;

import java.util.NoSuchElementException;

import week5.LinkedQueue.Node;

/**
 * This is a skeleton file for your homework. Complete the functions below. You
 * may also edit the function "main" to test your code.
 * 
 * You should not use any loops or recursions. Your code needs to run in
 * constant time. It is OK if your testing code has loops (like in
 * checkInvariants).
 *
 * You must not add fields or static variables. As always, you must not change
 * the declaration of any method nor the name of the class or of this file.
 */

public class Deque<T> {

	private Node first; 	// A reference to the first item in the Dequeue (or
							// null if empty)
	private Node last; 		// A reference to the last item in the Dequeue (or
							// null if empty)
	private int N; 			// The number of items currently in the Dequeue

	private class Node {

		public T item; 		// The data stored at this node.
		public Node next; 	// The node to the right (or null if there is no
							// node to the right)
		public Node prev; 	// The node to the lett (or null if there is no
							// node to the left)
	}

	/**
	 * Construct an empty <code>Deque</code>.
	 */
	public Deque() {
		// TODO - Repalce the line below with a correct solution.
		first = null;
		last  = null;
        N = 0;
        assert check();
	}

	/**
	 * Tests if the <code>Dequeue</code> is empty.
	 * 
	 * @return <code>true</code> if this <code>Deque</code> is empty and false
	 *         otherwise.
	 */
	public boolean isEmpty() {
		// TODO - Repalce the line below with a correct solution.
		return first == null;
	}

	/**
	 * Returns the number of items currenlty in this <code>Deque</code>.
	 * 
	 * @return the number of items currenlty in this <code>Deque</code>
	 */
	public int size() {
		// TODO - Repalce the line below with a correct solution.
		return N;
	}

	/**
	 * Inserts an item into the front of this <code>Deque</code>.
	 * 
	 * @param item the item to be inserted
	 */
	public void pushFront(T item) {
		// TODO - Repalce the line below with a correct solution.
		Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        first.prev = null;
        if (N == 0) {
        	last = first;
        }else {
        	oldfirst.prev = first;
        }
        
        N++;
        assert check();
	}

	/**
	 * Inserts an item into the back of this <code>Deque</code>.
	 * 
	 * @param item the item to be inserted
	 */
	public void pushBack(T item) {
		// TODO - Repalce the line below with a correct solution.
		Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        last.prev = oldlast;
        if (isEmpty()) 
        	first = last;
        else
        	oldlast.next = last;
        N++;
        assert check();
	}

	/**
	 * Removes and returns the item at the front of this <code>Deque</code>.
	 * 
	 * @return the item at the front of this <code>Deque</code>.
	 * @throws NoSuchElementException if this <code>Deque</code> is empty.
	 */
	public T popFront() {
		// TODO - Repalce the line below with a correct solution.
		if (isEmpty()) 
			throw new NoSuchElementException();
        T item = first.item;
        first = first.next;
        if (first != null)
            first.prev = null;
        else
            last = null;  
        N--;
        assert check();
        return item;
	}

	/**
	 * Removes and returns the item at the back of this <code>Deque</code>.
	 * 
	 * @return the item at the back this <code>Deque</code>.
	 * @throws NoSuchElementException if this <code>Deque</code> is empty.
	 */
	public T popBack() {
		// TODO - Repalce the line below with a correct solution.
		 if (isEmpty()) 
			throw new NoSuchElementException();
		 T item = last.item;
		 if (N == 1) {
			 first = null;
			 last = null;
		 }else {
	     last = last.prev;
	     if (last != null)
	         last.next = null;
		 }
	     N--;
	     assert check();
	     return item;    
	}
	private boolean check() {
        if (N >= 1) {
			if (first.prev != null || last.next != null) {
	       	return false;
	        }
        }
        else if (N < 0) {
            return false;
        }
        else if (N == 0) {
            if (first != null) 
            	return false;
            if (last  != null) 
            	return false;
        }
        else if (N == 1) {
            if (first == null || last == null) 
            	return false;
            if (first != last)                 
            	return false;
            if (first.next != null)            
            	return false;
            if (first.prev != null)            
            	return false;
        }
        else {
            if (first == null || last == null) 
            	return false;
            if (first == last)      
            	return false;
            if (first.next == null) 
            	return false;
            if (last.next  != null) 
            	return false;

            // check internal consistency of instance variable n
            int numberOfNodes = 0;
            for (Node x = first; x != null && numberOfNodes <= N; x = x.next) {
                numberOfNodes++;
            }
            if (numberOfNodes != N) return false;

            // check internal consistency of instance variable last
            Node lastNode = first;
            while (lastNode.next != null) {
                lastNode = lastNode.next;
            }
            if (last != lastNode) 
            	return false;
        }

        return true;
    } 
}
