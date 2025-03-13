
import QueuePackage.*;
import java.util. Vector;


public class SimulationEventQueue implements SimulationEventQueueInterface
{
    private Vector<SimulationEvent> queue;
    private double atTime;

    /**
     * Constructor for objects of class BankLine
     */
    public SimulationEventQueue()
    {
        queue = new Vector<SimulationEvent>();
        atTime = 0.0;
    } // end constructor
    
    public void add(SimulationEvent newEvent)
    {

        if(newEvent.getTime() < atTime)
        {
            return; 
        }
        
        if (queue.isEmpty()) 
        {
            queue.add(newEvent);
    
        } else {
        

            for (int i = 0; i < queue.size(); i++) {
                if (newEvent.getTime() < queue.get(i).getTime()) {
                    queue.add(i, newEvent);
                    
                    return;
                }
            }
    
            queue.add(newEvent);
            
        }

           

    } // end add

    public SimulationEvent peek()
    {
     
        return queue.get(0);
    } // end get
    

    public SimulationEvent remove() {
        SimulationEvent se = null;
        if (isEmpty()) {

            throw new EmptyQueueException("empty queue");

        } else {
           se = queue.get(0);
            atTime = queue.remove(0).getTime();
        } // end if
        return se;
    } // end remove

    public boolean isEmpty()
    {
        return queue.isEmpty();
    } // end isEmpty
    
    public void clear()
    {
        queue.clear();
    } // end clear

	/** Gets the size of the event queue.
        * @return The number of entries currently in the event queue.
        */
	public int getSize()
	{
	   return queue.size();
	}
	
	/**
	 * Get the current time of the simulation
	 * 
	 * @return The time for the first event on the queue.
	 */
	public double getCurrentTime()
	{
	   return atTime;
	}

}
