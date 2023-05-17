/**
 * Node for the free list class
 * 
 * @author kylem
 * @version April 2023
 */
public class FreeNode {

    /**
     * Holds the data in the form of a block
     */
    private FreeBlock block;

    /**
     * Previous Node
     */
    private FreeNode prev;

    /**
     * Next Node
     */
    private FreeNode next;

    /**
     * FreeNode Constructor
     * 
     * @param data
     *            data to be inserted in the node
     * @param prv
     *            previous node to this one
     * @param nxt
     *            next node to this one
     */
    public FreeNode(FreeBlock data, FreeNode prv, FreeNode nxt) {
        block = data;
        prev = prv;
        next = nxt;
    }


    /**
     * FreeNode constructor without data
     * 
     * @param prv
     *            previous Node pointer
     * @param nxt
     *            next node pointer
     */
    public FreeNode(FreeNode prv, FreeNode nxt) {
        prev = prv;
        next = nxt;
    }


    /**
     * Gets the data from the node
     * 
     * @return the FreeBlock data in the node
     */
    public FreeBlock data() {
        return block;
    }


    /**
     * Gets the next node to this one
     * 
     * @return next node pointer
     */
    public FreeNode next() {
        return next;
    }


    /**
     * gets the previous node to this one
     * 
     * @return previous node pointer
     */
    public FreeNode prev() {
        return prev;
    }


    /**
     * Sets the data to the node
     * 
     * @param data
     *            new data to be inserted
     */
    public void setData(FreeBlock data) {
        this.block = data;
    }


    /**
     * Sets the next node
     * 
     * @param nxt
     *            next node pointer
     */
    public void setNext(FreeNode nxt) {
        this.next = nxt;
    }


    /**
     * Sets the previous node
     * 
     * @param prv
     *            previous node pointer
     */
    public void setPrev(FreeNode prv) {
        this.prev = prv;
    }
}
