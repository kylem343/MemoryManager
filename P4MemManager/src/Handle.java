/**
 * Our main memory handler
 * 
 * @author kylem
 * @version April 2023
 */
public class Handle {

    /**
     * Start position for the handle
     */
    private int start;

    /**
     * Tombstone indicator for the hash table
     */
    private boolean tombstone;
    
    /**
     * Constructor
     * 
     * @param pos
     *            position for the handle
     */
    public Handle(int pos) {
        start = pos;
        tombstone = false;
    }


    /**
     * Gets the start value of the constructor
     * 
     * @return start value
     */
    public int getVal() {
        return start;
    }
    
    /**
     * Sets the tombstone to true
     */
    public void tomb() {
        tombstone = true;
    }
    
    /**
     * gets the tombstone value 
     * @return true if it is a tombstone, false if not
     */
    public boolean getTomb() {
        return tombstone;
    }
    
}
