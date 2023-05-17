import java.nio.ByteBuffer;

/**
 * Main memory pool for use
 * 
 * @author kylem
 * @version April 2023
 */
public class MemPool {

    /**
     * bytepool for memory storage
     */
    private byte[] pool;

    /**
     * list of blocks to show free memory locations
     */
    private FreeList list;

    /**
     * Byte buffer used for manipulating the pool
     */
    private ByteBuffer buff;

    /**
     * Size to expand the pool by
     */
    private int sizeInc;

    /**
     * Constructor
     * 
     * @param poolsize
     *            defines the size of the memory pool in bytes
     */
    public MemPool(int poolsize) {
        sizeInc = poolsize;
        pool = new byte[poolsize];
        list = new FreeList();
        list.insert(new FreeBlock(0, poolsize));

        buff = ByteBuffer.wrap(pool);
    }


    /**
     * Insert a record and return its position handle.
     * 
     * @param str
     *            string to be inserted
     * @return handle of the record position
     */
    public Handle insert(String str) {

        byte[] strBytes = str.getBytes();
        short len = (short)strBytes.length;

        // finds best fit node
        FreeNode node = list.findBestFit(strBytes.length + 2);
        // if node isn't found then expand and find again
        while (node == null) {
            this.expand();
            node = list.findBestFit(strBytes.length + 2);
        }

        FreeBlock block = node.data();
        int strt = block.getStart();

        buff.position(strt);

        block.insert(strBytes.length + 2);
        buff.putShort(len);
        buff.put(strBytes);

        node.setData(block);
        list.merge();

        return new Handle(strt);
    }


    /**
     * Removes a string from the pool by freeing up the freelist
     * 
     * @param han
     *            Handle of the string being removed
     * @return
     */
    public void remove(Handle han) {
        int pos = han.getVal();
        buff.position(pos);
        // Gets the length of the string from the handle and
        int len = Short.toUnsignedInt(buff.getShort()) + 2;

        FreeBlock block = new FreeBlock(pos, len);
        list.addBlock(block);
    }


    /**
     * Gets the string at the specific handles
     * 
     * @param han
     *            handle position to be grabbed
     * @return string at the handle position
     */
    public String getString(Handle han) {
        int pos = han.getVal();
        buff.position(pos);
        // Gets the length of the string from the handle
        int len = Short.toUnsignedInt(buff.getShort());
        byte[] strBytes = new byte[len];

        buff.get(strBytes, 0, len);
        return new String(strBytes);
    }


    /**
     * Expands the memory pool when there is not enough space
     */
    public void expand() {
        byte[] newPool = new byte[pool.length + sizeInc];
        System.arraycopy(pool, 0, newPool, 0, pool.length);
        list.insert(new FreeBlock(pool.length, sizeInc));
        pool = newPool;
        buff = ByteBuffer.wrap(pool);

        System.out.println("Memory pool expanded to be " + pool.length
            + " bytes.");

    }


    /**
     * Prints out all the free blocks in the pool
     */
    public void printBlocks() {
        list.print();
    }
}
