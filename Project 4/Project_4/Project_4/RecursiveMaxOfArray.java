
/**
 * Use a double recursion to compute the maximum of an array of values.
 * 
 * @author Charles Hoot
//- * @version 5.0
//vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv        
 * @version 5.1 (Modified for the Solution)
//^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
 */
public class RecursiveMaxOfArray
{

    
    /**
     * Compute the maximum of a range of values in an array recursively.
     *
     * @param data   An array of integers.
     * @param from  The low index for searching for the maximum.
     * @param to    The high index for searching for the maximum.
     * 
     * preconditions: from LTE Zero to, from LTE 0, to LT length, length GT 0
     *                
     * @return     The maximum value in the array.
     */
    
    public  int max(int data[], int from, int to)
    {

       
        int result = 0;
        
        // ADD YOUR CODE HERE
        
        if(  (data == null)
            || (data.length < 1)
            || (from < 0)
            || (to >= data.length)
            || (from > to))
        throw new BadArgumentsForMaxException("check arguments");

        if (to == from) {
            // base case
            result = data[from];
        } else {

            // finds the max value from the two arrays, the magical recursive split
            int mid = (from + to)/2;
            int max1 = max(data, from, mid);
            int max2 = max(data, mid+1, to);

            // comparing the output of the two recursive arrays
            if(max1 > max2)
                result = max1;
            else
                result = max2;
            
        }
       



//vvvvvvvvv ADDED CODE vvvvvvvvvvvvvvvvvvvvvvvvvvvvvv   
// sample solution  
        // if( (data == null)
        //     || (data.length < 1)
        //     || (from > to)
        //     || (from < 0)
        //     || (to >= data.length))
        //     throw new BadArgumentsForMaxException("check arguments");
        
        // if(from == to)
        //     //base case
        //     result = data[from];
        // else
        // {
        //     // recursive case
        //     // split into two parts and find the max of both
        //     int middle = (from + to)/2;
        //     int result1 = max(data, from, middle);
        //     int result2 = max(data, middle+1, to);
        //     if(result1 > result2)
        //         result = result1;
        //     else
        //         result = result2;
        // }
//^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

        return result;
    }
    
    
}
