/**
    A class of bags whose entries are stored in a fixed-size array.
    @author Frank M. Carrano, Timothy M. Henry
    
    * The toString method is overwritten to give a nice display of the items in
    * the bag in this format Bag{Size:# [1] [2] [3] [4] }
    * 
    * Extra methods added for lab exercise by Charles Hoot
    * //- * @version 5.1

*/
public final class ArrayBag<T> implements BagInterface<T>
{
	private final T[] bag; 
	private int numberOfEntries;
   private boolean integrityOK = false;
	private static final int DEFAULT_CAPACITY = 25;
	private static final int MAX_CAPACITY = 10000;

	/** Creates an empty bag whose initial capacity is 25. */
	public ArrayBag() 
	{
		this(DEFAULT_CAPACITY);
	} // end default constructor

	/** Creates an empty bag having a given capacity.
	    @param desiredCapacity  The integer capacity desired. */
	public ArrayBag(int desiredCapacity)
	{
      if (desiredCapacity <= MAX_CAPACITY)
      {
         // The cast is safe because the new array contains null entries
         @SuppressWarnings("unchecked")
         T[] tempBag = (T[])new Object[desiredCapacity]; // Unchecked cast
         bag = tempBag;
         numberOfEntries = 0;
         integrityOK = true;
         // Test that contents are nulls - OK
         //      for (int index = 0; index < desiredCapacity; index++) 
         //         System.out.print(bag[index] + " ");
         //      System.out.println();
      }
      else
         throw new IllegalStateException("Attempt to create a bag " +
                                         "whose capacity exceeds " +
                                         "allowed maximum.");
	} // end constructor
   
	/** Adds a new entry to this bag.
       @param newEntry  The object to be added as a new entry.
       @return  True if the addition is successful, or false if not. */
	public boolean add(T newEntry)
	{
		checkIntegrity();
      boolean result = true;
      if (isArrayFull())
      {
         result = false;
      }
      else
      {  // Assertion: result is true here
         bag[numberOfEntries] = newEntry;
         numberOfEntries++;
      } // end if
      
      return result;
	} // end add
   
	/** Retrieves all entries that are in this bag.
       @return  A newly allocated array of all the entries in this bag. */
   //	public <T> T[] toArray() //OK
	public T[] toArray() //OK
	{
		checkIntegrity();
      
      // The cast is safe because the new array contains null entries.
      @SuppressWarnings("unchecked")
      T[] result = (T[])new Object[numberOfEntries]; // Unchecked cast

      for (int index = 0; index < numberOfEntries; index++) 
      {
         result[index] = bag[index];
      } // end for
      return result;
      // Note: The body of this method could consist of one return statement,
      // if you call Arrays.copyOf
	} // end toArray

	/** Sees whether this bag is empty.
       @return  True if this bag is empty, or false if not. */
	public boolean isEmpty()
	{
      return numberOfEntries == 0;
	} // end isEmpty

	/** Gets the current number of entries in this bag.
       @return  The integer number of entries currently in this bag. */
	public int getCurrentSize()
	{
      return numberOfEntries;
	} // end getCurrentSize

	/** Counts the number of times a given entry appears in this bag.
       @param anEntry  The entry to be counted.
       @return  The number of times anEntry appears in this ba. */
	public int getFrequencyOf(T anEntry)
	{
		checkIntegrity();
      int counter = 0;

      for (int index = 0; index < numberOfEntries; index++) 
      {
         if (anEntry.equals(bag[index]))
         {
            counter++;
         } // end if
      } // end for

      return counter;
	} // end getFrequencyOf

	/** Tests whether this bag contains a given entry.
       @param anEntry  The entry to locate.
       @return  True if this bag contains anEntry, or false otherwise. */
   public boolean contains(T anEntry)
	{
		checkIntegrity();
      return getIndexOf(anEntry) > -1; // or >= 0
	} // end contains
   
	/** Removes all entries from this bag. */
	public void clear() 
	{
      while (!isEmpty())
         remove();
	} // end clear
	
	/** Removes one unspecified entry from this bag, if possible.
       @return  Either the removed entry, if the removal
                was successful, or null. */
	public T remove()
	{
            checkIntegrity();
                
            // MODIFY THIS METHOD TO REMOVE A RANDOM ITEM FROM THE BAG
        T result = removeEntry(numberOfEntries - 1);

            return result;
	} // end remove
	
	/** Removes one occurrence of a given entry from this bag.
    @param anEntry  The entry to be removed.
    @return  True if the removal was successful, or false if not. */
	public boolean remove(T anEntry)
	{
		checkIntegrity();
                
                
      int index = getIndexOf(anEntry);
      T result = removeEntry(index);         
      return anEntry.equals(result);
	} // end remove   
	
   // Returns true if the array bag is full, or false if not.
	private boolean isArrayFull()
	{
		return numberOfEntries >= bag.length;
	} // end isArrayFull
   
 	// Locates a given entry within the array bag.
	// Returns the index of the entry, if located,
	// or -1 otherwise.
   // Precondition: checkInitialization has been called.
	private int getIndexOf(T anEntry)
	{
		int where = -1;
		boolean found = false;      
		int index = 0;
      
      while (!found && (index < numberOfEntries))
		{
			if (anEntry.equals(bag[index]))
			{
				found = true;
				where = index;
			} // end if
         index++;
		} // end while
      
      // Assertion: If where > -1, anEntry is in the array bag, and it
      // equals bag[where]; otherwise, anEntry is not in the array.
      
		return where;
	} // end getIndexOf
   
   // Removes and returns the entry at a given index within the array.
   // If no such entry exists, returns null.
   // Precondition: 0 <= givenIndex < numberOfEntries.
   // Precondition: checkInitialization has been called.
	private T removeEntry(int givenIndex)
	{
		T result = null;
      
		if (!isEmpty() && (givenIndex >= 0))
		{
         result = bag[givenIndex];          // Entry to remove
         int lastIndex = numberOfEntries - 1;
         bag[givenIndex] = bag[lastIndex];  // Replace entry to remove with last entry
         bag[lastIndex] = null;             // Remove reference to last entry
         numberOfEntries--;
		} // end if
      
      return result;
	} // end removeEntry
   
   // Throws an exception if this object is not initialized.
   private void checkIntegrity()
   {
      if (!integrityOK)
         throw new SecurityException("ArrayBag object is corrupt.");
   } // end checkIntegrity
   
    /** Override the toString() method so that we get a more useful display of
     * the contents in the bag.
     * @return a string representation of the contents of the bag */
    public String toString() {
        
        String result = "Bag[ ";
        
        for (int index = 0; index < numberOfEntries; index++) {
            result += bag[index] + " ";
        } // end for
        
        result += "]";
        return result;
    } // end toString
    
    /*********************************************************************
     * 
     * METHODS TO BE COMPLETED
     * 
     * 
     ************************************************************************/
    
    /** Check to see if two bags are equals.  
     * @param aBag Another object to check this bag against.
     * @return True the two bags contain the same objects with the same frequencies.
     */
    public boolean equals(ArrayBag<T> aBag) {
        boolean result = false; // result of comparison of bags

      // COMPLETE THIS METHODZ
         try {        
            if (aBag.getCurrentSize()  ==  numberOfEntries) {
               return true;
            }

         for (int index = 0; index < numberOfEntries; index++) {
               if (aBag.getFrequencyOf(bag[index]) == getFrequencyOf(bag[index])) {
                  result = true;
               } else {
                  result = false;
                  break;
               }
         } 
         for (int index = 0; index < aBag.getCurrentSize() ; index++) {
            if (aBag.getFrequencyOf(bag[index]) == getFrequencyOf(bag[index])) {
               result = true;
            } else {
               result = false;
               break;
            }
            } // end for 

         } catch (Exception e) {
            result = false;
         }



        return result;
    }  // end equals

    /** Duplicate all the items in a bag.
     * @return True if the duplication is possible.
     */
    public boolean duplicateAll() {
        checkIntegrity();
        boolean success = false; 
      
         if (numberOfEntries == 0) {
            success = true;
         
         } else if ((numberOfEntries * 2) <= MAX_CAPACITY && (numberOfEntries * 2) <= bag.length) {
            int originalSize = numberOfEntries;
            for (int index = 0; index < originalSize; index++) {
               add(bag[index]);
                           }
            success = true;
         }  else {

            success = false; 
         }

        // COMPLETE THIS METHOD 

        return success;
    }  // end duplicateAll
    
        /** Remove all duplicate items from a bag
     */
    public void removeDuplicates() {
        checkIntegrity();


         if (numberOfEntries > 1) {
            for (int index = 0; index < numberOfEntries; index++) {
                T item = bag[index];
                while (getFrequencyOf(item) > 1) {
                  remove(item);
              }
            }

            for (int index = 0; index < numberOfEntries; index++) {
               T item = bag[index];
               while (getFrequencyOf(item) > 1) {
                 remove(item);
             }
           }
         }
        // COMPLETE THIS METHOD 
    }  // end removeDuplicates
    
    /*
     * 
      
      public void removeDuplicates() {
         checkIntegrity();

         if (numberOfEntries > 1) {
            for (int index = 0; index < numberOfEntries; index++) {
                  T item = bag[index];
                  int frequency = getFrequencyOf(item);
                  while (frequency > 1) {
                     int removeIndex = getIndexOf(item);
                     removeEntry(removeIndex);
                     frequency--;
                  }
            }
         }
      }  // end removeDuplicates
     */

   /*
      public void removeDuplicates() {
      checkIntegrity();

      BagInterface<T> uniqueItemsBag = new ArrayBag<>(numberOfEntries);

      T[] itemsArray = toArray();
      for (T item : itemsArray) {
         if (!uniqueItemsBag.contains(item)) {
            uniqueItemsBag.add(item);
         }
      }

      clear();

      T[] uniqueItemsArray = uniqueItemsBag.toArray();
      for (T item : uniqueItemsArray) {
         add(item);
      }
   }  // end removeDuplicates
   
   */ 
   /* 
   public boolean splitInto(BagInterface<T> first, BagInterface<T> second) {
      checkIntegrity();
  
      int halfSize = numberOfEntries / 2;
      int firstBagSize = (numberOfEntries % 2 == 0) ? halfSize : halfSize + 1;
  
      if (first.getCurrentSize() + firstBagSize > first.getCurrentSize() ||
          second.getCurrentSize() + halfSize > second.getCurrentSize()) {
         return false; // Overflow would occur
     }
  
      for (int i = 0; i < firstBagSize; i++) {
          if (!first.add(bag[i])) {
              return false; // Overflow occurred
          }
      }
  
      for (int i = firstBagSize; i < numberOfEntries; i++) {
          if (!second.add(bag[i])) {
              return false; // Overflow occurred
          }
      }
  
      clear();
  
      return true; // Split was successful
  } 
    */

   public boolean addAll(BagInterface<T> toAdd) {
      checkIntegrity();
  
      T[] itemsToAdd = toAdd.toArray();
      if (numberOfEntries + itemsToAdd.length > bag.length) {
          return false; // Overflow would occur
      }
  
      for (T item : itemsToAdd) {
          add(item);
      }
  
      return true; // Addition was successful
   }
    


   public boolean isSet() {
      checkIntegrity();
  
      for (int index = 0; index < numberOfEntries; index++) {
          T item = bag[index];
          if (getFrequencyOf(item) > 1) {
              return false; // Duplicate found
          }
      }
  
      return true; // No duplicates found
  }


public T getMode() {
   checkIntegrity();
   if (isEmpty()) {
         return null; 
   }

   T mode = null;
   int maxFrequency = 0;
   boolean unique = true;

   for (int index = 0; index < numberOfEntries; index++) {
         T item = bag[index];
         int frequency = getFrequencyOf(item);

         if (frequency > maxFrequency) {
            mode = item;
            maxFrequency = frequency;
            unique = true;
         } else if (frequency == maxFrequency && !item.equals(mode)) {
            unique = false;
         }
   }

   return unique ? mode : null;
}

  
} // end ArrayBag



