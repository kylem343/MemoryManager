import student.TestCase;

/**
 * Free Block test class
 * 
 * @author kylem
 * @version April 2023
 */
public class FreeBlockTest extends TestCase {

    /**
     * block for testing
     */
    private FreeBlock block;

    /**
     * sets up the test cases
     */
    public void setUp() {
        block = new FreeBlock(0, 32);
    }


    /**
     * test method for insert()
     */
    public void testInsert() {
        block.insert(16);
        assertEquals(16, block.getStart());
        assertEquals(16, block.length());
    }


    /**
     * test for the GetStart method
     */
    public void testGetStart() {
        assertEquals(0, block.getStart());
    }


    /**
     * test for the length method
     */
    public void testLength() {
        assertEquals(32, block.length());
    }


    /**
     * test for the toString method
     */
    public void testToString() {
        assertEquals("(0,32)", block.toString());
    }

}
