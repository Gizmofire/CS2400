



/**
    A class of bags whose entries are stored in a chain of linked nodes.
    The bag is never full.
    INCOMPLETE DEFINITION; includes definitions for the methods add,
    toArray, isEmpty, and getCurrentSize.
    @author Frank M. Carrano, Timothy M. Henry
    @version 5.0
    * 
    * Extra methods added for lab exercise by Charles Hoot
    * //- * @version 5.1
*/
public final class LinkedBag<T> implements BagInterface<T>
{
	private Node firstNode;       // Reference to first node
	private int numberOfEntries;

	public LinkedBag()
	{
		firstNode = null;
      numberOfEntries = 0;
	} // end default constructor

	/** Adds a new entry to this bag.
	    @param newEntry  The object to be added as a new entry.
	    @return  True. */
	public boolean add(T newEntry) // OutOfMemoryError possible
	{
      // Add to beginning of chain:
		Node newNode = new Node(newEntry);
		     // New node is at beginning of chain
    
        if (firstNode == null || numberOfEntries == 0) {
            newNode.next = firstNode;
            firstNode = newNode;
        } else {
            int randomIndex = (int) ((Math.random() * numberOfEntries));

            if (randomIndex == 0) { 

                newNode.next = firstNode;
                firstNode = newNode;
            } else {

                Node currentNode = firstNode;
                for (int i = 1; i < randomIndex; i++) {
                    currentNode = currentNode.next;
                }
                newNode.next = currentNode.next;
                currentNode.next = newNode;
            }


        }
    
        numberOfEntries++;
      
		return true;
	} // end add

	/** Retrieves all entries that are in this bag.
       @return  A newly allocated array of all the entries in this bag. */
	public T[] toArray()
	{
      // The cast is safe because the new array contains null entries.
      @SuppressWarnings("unchecked")
      T[] result = (T[])new Object[numberOfEntries]; // Unchecked cast
      
      int index = 0;
      Node currentNode = firstNode;
      while ((index < numberOfEntries) && (currentNode != null))
      {
         result[index] = currentNode.data;
         index++;
         currentNode = currentNode.next;
      } // end while
      
      return result;
      // Note: The body of this method could consist of one return statement,
      // if you call Arrays.copyOf
	} // end toArray
   
	/** Sees whether this bag is empty.
       @return  True if the bag is empty, or false if not. */
	public boolean isEmpty()
	{
		return numberOfEntries == 0;
	} // end isEmpty
   
	/** Gets the number of entries currently in this bag.
       @return  The integer number of entries currently in the bag. */
	public int getCurrentSize()
	{
		return numberOfEntries;
	} // end getCurrentSize
   

	/** Removes one unspecified entry from this bag, if possible.
       @return  Either the removed entry, if the removal
                was successful, or null. */
    public T remove() {
        T result = null;

        // MODIFY THIS METHOD TO REMOVE A RANDOM ITEM FROM THE BAG
        // Node scout = firstNode;
        // int randomIndex = (int) ((Math.random() * numberOfEntries)-1);
        // scout = firstNode;
        // for (int i = 0; i < randomIndex; i++) {
        //     scout = scout.next;
        // }
        // result = scout.data;
        
        if (firstNode != null) {
            result = firstNode.data;
            firstNode = firstNode.next; // Remove first node from chain
            numberOfEntries--;
        } // end if


        return result;
    } // end remove


   
	/** Removes one occurrence of a given entry from this bag.
       @param anEntry  The entry to be removed.
       @return  True if the removal was successful, or false otherwise. */
	public boolean remove(T anEntry) {
        boolean result = false;
        Node nodeN = getReferenceTo(anEntry);
        if (nodeN != null) {
            nodeN.data = firstNode.data; // Teplace located entry with entry
                // in first node
            firstNode = firstNode.next; // Remove first node
            numberOfEntries--;
            result = true;
        } // end if
        return result;
    } // end remove
        
        
// Locates a given entry within this bag.
// Returns a reference to the node containing the entry, if located,
// or null otherwise.
    private Node getReferenceTo(T anEntry) {
        boolean found = false;
        Node currentNode = firstNode;
        while (!found && (currentNode != null)) {
            if (anEntry.equals(currentNode.data)) {
                found = true;
            } else {
                currentNode = currentNode.next;
            }
        } // end while
        return currentNode;
    } // end getReferenceTo


	
	/** Removes all entries from this bag. */
    public void clear() {
        while (!isEmpty()) {
            remove();
        }
    } // end clear
	
	/** Counts the number of times a given entry appears in this bag.
		 @param anEntry  The entry to be counted.
		 @return  The number of times anEntry appears in the bag. */
    public int getFrequencyOf(T anEntry) {
        int frequency = 0;
        int loopCounter = 0;
        Node currentNode = firstNode;
        while ((loopCounter < numberOfEntries) && (currentNode != null)) {
            if (anEntry.equals(currentNode.data)) {
                frequency++;
            }
            loopCounter++;
            currentNode = currentNode.next;
        } // end while
        return frequency;
    } // end getFrequencyOf

	
	/** Tests whether this bag contains a given entry.
		 @param anEntry  The entry to locate.
		 @return  True if the bag contains anEntry, or false otherwise. */
    public boolean contains(T anEntry) {
        boolean found = false;
        Node currentNode = firstNode;
        while (!found && (currentNode != null)) {
            if (anEntry.equals(currentNode.data)) 
                found = true;
             else 
                currentNode = currentNode.next;
        } // end while
        return found;
    } // end contains

	private class Node
	{
	  private T    data; // Entry in bag
	  private Node next; // Link to next node

		private Node(T dataPortion)
		{
			this(dataPortion, null);	
		} // end constructor
		
		private Node(T dataPortion, Node nextNode)
		{
			data = dataPortion;
			next = nextNode;	
		} // end constructor
	} // end Node

    /*********************************************************************
     * 
     * METHODS TO BE COMPLETED
     * 
     * 
     ************************************************************************/
    
    /** Check to see if two bags are equals.  
     * @param aBag Another object to check this bag against.
     * @return True if the two bags contain the same objects with the same frequencies.
     */
    public boolean equals(LinkedBag<T> aBag) {
        if (this == aBag) {
            return true;
        }

        if (aBag == null || this.numberOfEntries != aBag.numberOfEntries) {
            return false;
        }

        Node currentNode = this.firstNode;
        while (currentNode != null) {
            T data = currentNode.data;
            if (this.getFrequencyOf(data) != aBag.getFrequencyOf(data)) {
                return false;
            }
            currentNode = currentNode.next;
        }

        return true;
    }  // end equals

    /** Duplicate all the items in a bag.
     * @return True if the duplication is possible.
     */
    public boolean duplicateAll() {
        boolean success = true; // should always return true
                                // if there is a problem allocating nodes
                                // an exception will be thrown

        Node currentNode = firstNode;
        while (currentNode != null) {
            Node newNode = new Node(currentNode.data); //makes new node to insert front
          
          
            newNode.next = currentNode.next;
            currentNode.next = newNode;
            currentNode = newNode.next;
            // swap locations of Nodes to in fronrt of current node

            numberOfEntries++; //self explanatory - inc entries to parent class using Nodes
        }
        return success;
    }  // end duplicateAll


    /** Duplicate all the items in a bag.
     * @return True if the duplication is possible.
     */
    public boolean duplicateAll2() {
        boolean success = true; // should always return true
                                // if there is a problem allocating nodes
                                // an exception will be thrown

        Node currentNode = firstNode;
        Node dataNode = firstNode;


        for (int i = 0; i < numberOfEntries; i++) {
            currentNode = currentNode.next;
            // MOves to the end of the list
        }

        // excutes dupe from previous method just at the end of the list shifted 
        while (dataNode != null) {
            Node newNode = new Node(dataNode.data); //makes new node to insert front
          
          
            newNode.next = dataNode.next;
            dataNode.next = newNode;
            dataNode = newNode.next;
            // swap locations of Nodes to in fronrt of current node
        }
            numberOfEntries++; //self explanatory - inc entries to parent class using Nodes
        


        return success;
    }  // end duplicateAll



    /** Remove all duplicate items from a bag
     */
    public void removeDuplicates() {

        // COMPLETE THIS METHOD 
        while (firstNode != null) {
           T curNodedata = firstNode.data;
           
            if (this.getFrequencyOf(curNodedata) > 1) {
                // chcks if more than one of the same data is in the bag
                Node scoutNode = firstNode;

                // Scouts through the nodes an removes any dupenodes
               while (scoutNode != null) {
                   if (scoutNode.next != null && scoutNode.next.data.equals(curNodedata)) {
                       scoutNode.next = scoutNode.next.next;
                    //    replaces nodes with the next node - cool . . method thing
                       numberOfEntries--;
                   } else {
                       scoutNode = scoutNode.next;
                   }
               }
           } else {
               firstNode = firstNode.next;
           }
        }

    
    }  // end removeDuplicates

    /** Remove all duplicate items from a bag
     */
    public void removeDuplicates2() {

        // COMPLETE THIS METHOD 
        Node currentNode = firstNode;

        while (currentNode != null) {
            Node scoutNode = currentNode;


            while (scoutNode.next != null) {
                // scout checks the data ahead on the same chain,
                // if it exists its removed, then moves on to the next node
                if (scoutNode.next.data.equals(currentNode.data)) {
                    scoutNode.next = scoutNode.next.next;
                    numberOfEntries--;
                } else {
                    scoutNode = scoutNode.next;
                }
            }
            currentNode = currentNode.next;
        }
    
    }  // end removeDuplicates


    public boolean splitInto(BagInterface<T> first, BagInterface<T> second) {
        Node currentNode = firstNode;
        boolean addToFirst = true;
    
        while (currentNode != null) {
            
            // toggle added to determine which bag to add to
            if (addToFirst) {
                if (!first.add(currentNode.data)) {
                    return false; // Overflow in the first bag
                }
            } else {
                if (!second.add(currentNode.data)) {
                    return false; // Overflow in the second bag
                }
            }
            addToFirst = !addToFirst;
            currentNode = currentNode.next;
        }
    
        return true;
    } // end splitInto

    public boolean addAll(BagInterface<T> toAdd) {
        T[] itemsToAdd = toAdd.toArray();

        // adds items to the node chain
        for (T item : itemsToAdd) {
            this.add(item);
        }
        

        return false; 
    } // end addAll






} // end LinkedBag



