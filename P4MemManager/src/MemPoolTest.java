import student.TestCase;

/**
 * Test class for the MemPool
 * 
 * @author kylem
 * @verison April 2023
 */
public class MemPoolTest extends TestCase {

    /**
     * pool to be tested
     */
    private MemPool pool;

    /**
     * Sets up the test cases
     */
    public void setUp() {
        pool = new MemPool(64);
    }


    /**
     * tests the PrintBlocks method
     */
    public void testPrintBlocks() {
        pool.printBlocks();
        assertEquals("(0,64)", systemOut().getHistory());
        systemOut().clearHistory();
    }


    /**
     * tests the insert Method
     */
    public void testInsert() {
        Handle han = pool.insert("test");
        pool.printBlocks();
        assertEquals("(6,58)", systemOut().getHistory());
        systemOut().clearHistory();

        assertEquals(0, han.getVal());

        han = pool.insert("tesh");
        pool.printBlocks();
        assertEquals("(12,52)", systemOut().getHistory());
        systemOut().clearHistory();

        assertEquals(6, han.getVal());

    }


    /**
     * tests the remove method
     */
    public void testRemove() {
        Handle han = pool.insert("test");
        pool.insert("tset");
        pool.remove(han);
        pool.printBlocks();
        assertEquals("(0,6) -> (12,52)", systemOut().getHistory());
        systemOut().clearHistory();
    }


    /**
     * tests the GetString method
     */
    public void testGetString() {
        Handle han = pool.insert("test");
        assertEquals("test", pool.getString(han));
    }

}
