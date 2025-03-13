package QueuePackage.*;

import java.util.Vector;
import java.util.List;

public class SimulationEventQueue implements  SimulationEventQueueInterface{



    private double atTime;
    private List<SimulationEvent> queue;

    public SimulationEventQueue()
    {
        queue = new Vector<SimulationEvent>();
        atTime = 0.0; // Vector expands in size if necessary.
    } // end default constructor

    /** Adds a new event to this event queue.  If the time of the event to be added
     * is earlier the the time for this event queue, do not add the event.
    * @param newEntry An event. 
    */
    public void add(SimulationEvent newEntry) 
    {
        
        queue.add(newEntry);
      

    }

    /** Removes and returns the item with the earliest time.
     * @return The event with the earliest time or,
     * if the event queue was empty before the operation, null. 
         */
    public SimulationEvent remove()
    {
        atTime = queue.get(0).getTime();
        return queue.remove(0);

    }
    
    /** Retrieves the item with the earliest time.
     * @return The event with the earliest time or, 
     * if the event queue was empty was empty before the operation, null. 
         */
    public SimulationEvent peek() 
    {
        return queue.get(0);
    }
    
    /** Detects whether this event queue is empty.
     * @return True if the event queue is empty. 
         */
    public boolean isEmpty()
    {
        return queue.isEmpty();
    }
    
    /** Gets the size of this event queue.
     * @return The number of entries currently in the event queue. 
         */
    public int getSize(){
        return queue.size();
    }
    
    /** Removes all entries from this event queue. 
         */
    public void clear() {
        queue.clear();
    }
    
    
    /**
     * The current time of the simulation
     * 
     * @return The time for the first event on the queue.
     */
    public double getCurrentTime(){
        return queue.get(0).getTime();
    }

}