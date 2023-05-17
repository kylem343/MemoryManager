import student.TestCase;

/**
 * Test class for the handles
 * 
 * @author kylem
 * @version April 2023
 */
public class HandleTest extends TestCase {

    /**
     * test handle to be used
     */
    private Handle test;

    /**
     * Sets up the test cases
     */
    public void setUp() {
        test = new Handle(12);
    }


    /**
     * tests the getVal method
     */
    public void testGetVal() {
        assertEquals(12, test.getVal());
    }

}
