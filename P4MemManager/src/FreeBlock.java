/**
 * Our free block class for managing blocks in the MemPool
 * 
 * @author kylem
 * @version April 2023
 */
public class FreeBlock {

    /**
     * Start position of the block
     */
    private int start;

    /**
     * length of the block
     */
    private int length;

    /**
     * Constructor for the block
     * 
     * @param strt
     *            start position
     * @param len
     *            length of the block
     */
    public FreeBlock(int strt, int len) {
        this.start = strt;
        this.length = len;
    }


    /**
     * Adjusts the block by the length provided
     * 
     * @param newLen
     *            length of bytes being added
     */
    public void insert(int newLen) {   
        start = start + newLen;
        length = length - newLen;
    }


    /**
     * returns the start of the block
     * 
     * @return start position of the block
     */
    public int getStart() {
        return start;
    }
    
    
    /**
     * gets the end point for this block
     * 
     * @return end position of the block
     */
    public int getEnd() {
        return start + length;
    }


    /**
     * gets the length of the block
     * 
     * @return length of the block
     */
    public int length() {
        return length;
    }


    /**
     * gets the string of the block
     * 
     * @return string representation of the block
     */
    public String toString() {
        return "(" + start + "," + length + ")";
    }

}
