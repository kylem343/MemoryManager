/**
 * FreeList for storing free blocks in the form a double linked list
 * 
 * @author kylem
 * @version April 2023
 */
public class FreeList {

    /**
     * head of the list
     */
    private FreeNode head;

    /**
     * tail of the list
     */
    private FreeNode tail;

    /**
     * Current node in the list
     */
    private FreeNode curr;

    /**
     * Size of the list
     */
    private int size;

    /**
     * List constructor
     */
    public FreeList() {
        head = new FreeNode(null, null, null);
        tail = new FreeNode(null, head, null);
        head.setNext(tail);
        curr = tail;
        size = 0;
    }


    /**
     * inserts a block at the end of the list
     * 
     * @param block
     *            block to be inserted
     * @return true if inserted
     */
    public boolean insert(FreeBlock block) {
        moveToEnd();
        curr = new FreeNode(block, curr.prev(), curr);
        curr.prev().setNext(curr);
        curr.next().setPrev(curr);

        size++;
        merge();
        return true;
    }


    /**
     * Adds a block to it's ordered location in the list
     * 
     * @param block
     *            freeblock to be added
     */
    public void addBlock(FreeBlock block) {
        moveToStart();
        while (curr != tail) {
            // if node surpasses the block then we add behind it
            if (block.getStart() < curr.data().getStart()) {
                FreeNode node = new FreeNode(block, curr.prev(), curr);
                curr.prev().setNext(node);
                curr.setPrev(node);
                size++;
                break;
            }
            next();
            // if no nodes are found then we add it to the end
            if (curr == tail) {
                FreeNode node = new FreeNode(block, curr.prev(), curr);
                curr.prev().setNext(node);
                curr.setPrev(node);
                size++;
            }
        }
        merge();
    }


    /**
     * Goes next in the list
     */
    private void next() {
        if (curr != tail) {
            curr = curr.next();
        }
    }


    /**
     * moves the curr to the start of the list
     */
    private void moveToStart() {
        curr = head.next();
    }


    /**
     * moves curr to the end of the list
     */
    private void moveToEnd() {
        curr = tail;
    }


    /**
     * Gets the length of the list
     * 
     * @return length of the list
     */
    public int length() {
        return size;
    }


    /**
     * Finds the best fit node in the list for the length
     * 
     * @param len
     *            length to be inserted
     * @return node of the list to be inserted into, null if none exist
     */
    public FreeNode findBestFit(int len) {
        moveToStart();
        FreeNode small = null;
        while (curr != tail) {
            if (curr.data().length() >= len) {
                if (small == null) {
                    small = curr;
                }
                else if (small.data().length() > curr.data().length()) {
                    small = curr;
                }
            }
            next();
        }
        return small;
    }


    /**
     * Merges the current node to it's surrounding nodes if needed to
     * 
     * @param node
     */
    public void merge() {
        moveToStart();
        // iterate through all nodes to check
        while (curr != tail) {
            // checks to see if node needs to be merged with previous one
            if (curr.prev() != head && curr.prev().data().getEnd() == curr
                .data().getStart()) {
                // Creates the new block with updated start and length
                FreeBlock block = new FreeBlock(curr.prev().data().getStart(),
                    curr.prev().data().length() + curr.data().length());

                FreeNode newNode = new FreeNode(block, curr.prev().prev(), curr
                    .next());
                curr.prev().prev().setNext(newNode);
                curr.next().setPrev(newNode);
                size--;
            }
            next();
        }
    }


    /**
     * Prints out the list
     */
    public void print() {

        moveToStart();
        while (curr != tail) {
            System.out.print(curr.data().toString());
            if (curr.next() != tail) {
                System.out.print(" -> ");
            }
            next();
        }

    }

}
