public final class LinkedBag<T> implements BagInterface<T> {
    private Node firstNode;       // Reference to first node
    private int numberOfEntries;

    public LinkedBag() {
        firstNode = null;
        numberOfEntries = 0;
    } // end default constructor

    private class Node {
        private T data; // Entry in bag
        private Node next; // Link to next node
        private int count; // Count of the number of times the item is in the bag

        private Node(T dataPortion) {
            this(dataPortion, null, 1);
        } // end constructor

        private Node(T dataPortion, Node nextNode, int count) {
            data = dataPortion;
            next = nextNode;
            this.count = count;
        } // end constructor
    } // end Node

    public boolean add(T newEntry) {
        Node node = getReferenceTo(newEntry);
        if (node != null) {
            node.count++;
        } else {
            Node newNode = new Node(newEntry);
            newNode.next = firstNode;
            firstNode = newNode;
        }
        numberOfEntries++;
        return true;
    } // end add

    public T[] toArray() {
        @SuppressWarnings("unchecked")
        T[] result = (T[]) new Object[numberOfEntries];
        int index = 0;
        Node currentNode = firstNode;
        while ((index < numberOfEntries) && (currentNode != null)) {
            for (int i = 0; i < currentNode.count; i++) {
                result[index++] = currentNode.data;
            }
            currentNode = currentNode.next;
        }
        return result;
    } // end toArray

    public boolean isEmpty() {
        return numberOfEntries == 0;
    } // end isEmpty

    public int getCurrentSize() {
        return numberOfEntries;
    } // end getCurrentSize

    public T remove() {
        if (firstNode == null) {
            return null;
        }

        int randomIndex = (int) (Math.random() * numberOfEntries);
        Node currentNode = firstNode;
        Node previousNode = null;
        int currentIndex = 0;

        while (currentIndex < randomIndex) {
            previousNode = currentNode;
            currentNode = currentNode.next;
            currentIndex += currentNode.count;
        }

        T result = currentNode.data;
        currentNode.count--;
        if (currentNode.count == 0) {
            if (previousNode == null) {
                firstNode = currentNode.next;
            } else {
                previousNode.next = currentNode.next;
            }
        }

        numberOfEntries--;
        return result;
    } // end remove

    public boolean remove(T anEntry) {
        Node node = getReferenceTo(anEntry);
        if (node != null) {
            node.count--;
            if (node.count == 0) {
                if (node == firstNode) {
                    firstNode = firstNode.next;
                } else {
                    Node previousNode = firstNode;
                    while (previousNode.next != node) {
                        previousNode = previousNode.next;
                    }
                    previousNode.next = node.next;
                }
            }
            numberOfEntries--;
            return true;
        }
        return false;
    } // end remove

    private Node getReferenceTo(T anEntry) {
        Node currentNode = firstNode;
        while (currentNode != null) {
            if (anEntry.equals(currentNode.data)) {
                return currentNode;
            }
            currentNode = currentNode.next;
        }
        return null;
    } // end getReferenceTo

    public void clear() {
        firstNode = null;
        numberOfEntries = 0;
    } // end clear

    public int getFrequencyOf(T anEntry) {
        Node node = getReferenceTo(anEntry);
        return (node != null) ? node.count : 0;
    } // end getFrequencyOf

    public boolean contains(T anEntry) {
        return getReferenceTo(anEntry) != null;
    } // end contains

    public boolean equals(LinkedBag<T> aBag) {
        if (this == aBag) {
            return true;
        }

        if (aBag == null || this.numberOfEntries != aBag.numberOfEntries) {
            return false;
        }

        Node currentNode = this.firstNode;
        while (currentNode != null) {
            if (this.getFrequencyOf(currentNode.data) != aBag.getFrequencyOf(currentNode.data)) {
                return false;
            }
            currentNode = currentNode.next;
        }

        return true;
    }  // end equals

    public boolean duplicateAll() {
        Node currentNode = firstNode;
        while (currentNode != null) {
            for (int i = 0; i < currentNode.count; i++) {
                add(currentNode.data);
            }
            currentNode = currentNode.next;
        }
        return true;
    }  // end duplicateAll

    public void removeDuplicates() {
        Node currentNode = firstNode;
        while (currentNode != null) {
            currentNode.count = 1;
            Node scoutNode = currentNode.next;
            Node previousNode = currentNode;
            while (scoutNode != null) {
                if (scoutNode.data.equals(currentNode.data)) {
                    previousNode.next = scoutNode.next;
                    numberOfEntries -= scoutNode.count;
                } else {
                    previousNode = scoutNode;
                }
                scoutNode = scoutNode.next;
            }
            currentNode = currentNode.next;
        }
    }  // end removeDuplicates

    public boolean splitInto(BagInterface<T> first, BagInterface<T> second) {
        Node currentNode = firstNode;
        boolean addToFirst = true;

        while (currentNode != null) {
            for (int i = 0; i < currentNode.count; i++) {
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
            }
            currentNode = currentNode.next;
        }

        return true;
    } // end splitInto

    public boolean addAll(BagInterface<T> toAdd) {
        T[] itemsToAdd = toAdd.toArray();
        for (T item : itemsToAdd) {
            this.add(item);
        }
        return true; // Always return true as overflow is not a concern in this implementation
    } // end addAll

    public boolean isSet() {
        Node currentNode = firstNode;
        while (currentNode != null) {
            if (currentNode.count > 1) {
                return false;
            }
            currentNode = currentNode.next;
        }
        return true;
    } // end isSet

    public T getMode() {
        if (firstNode == null) {
            return null;
        }
    
        Node currentNode = firstNode;
        T mode = currentNode.data;
        int maxCount = currentNode.count;
        boolean unique = true;
    
        while (currentNode != null) {
            if (currentNode.count > maxCount) {
                mode = currentNode.data;
                maxCount = currentNode.count;
                unique = true;
            } else if (currentNode.count == maxCount && !currentNode.data.equals(mode)) {
                unique = false;
            }
            currentNode = currentNode.next;
        }
    
        return unique ? mode : null;
    } // end getMode

    
} // end LinkedBag