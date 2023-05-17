import student.TestCase;

/**
 * Test class for the FreeList
 * 
 * @author kylem
 * @version April 2023
 */
public class FreeListTest extends TestCase {

    private FreeList list;

    /**
     * Sets up the freelist
     */
    public void setUp() {

        list = new FreeList();
    }


    /**
     * Tests the insert method
     */
    public void testInsert() {
        FreeBlock block = new FreeBlock(0, 32);
        assertTrue(list.insert(block));
        list.print();
        assertEquals("(0,32)", systemOut().getHistory());
        systemOut().clearHistory();

        System.out.println();

    }


    /**
     * Tests the addBlock method
     */
    public void testAddBlock() {
        FreeBlock block = new FreeBlock(16, 16);
        list.insert(block);

        block = new FreeBlock(0, 8);
        list.addBlock(block);
        list.print();
        assertEquals("(0,8) -> (16,16)", systemOut().getHistory());
        systemOut().clearHistory();
        assertEquals(2, list.length());
        System.out.println();
        systemOut().clearHistory();

        block = new FreeBlock(40, 10);
        list.addBlock(block);
        list.print();
        assertEquals("(0,8) -> (16,16) -> (40,10)", systemOut().getHistory());
        systemOut().clearHistory();
        assertEquals(3, list.length());
        System.out.println();
        systemOut().clearHistory();

        block = new FreeBlock(8, 8);
        list.addBlock(block);
        list.print();
        assertEquals("(0,32) -> (40,10)", systemOut().getHistory());
        systemOut().clearHistory();
        assertEquals(2, list.length());

        System.out.println();
    }


    /**
     * Tests the length method
     */
    public void testLength() {
        assertEquals(0, list.length());
        FreeBlock block = new FreeBlock(0, 32);
        list.insert(block);

        assertEquals(1, list.length());

    }


    /**
     * Tests the print method
     */
    public void testPrint() {
        FreeBlock block = new FreeBlock(0, 31);
        list.insert(block);
        list.print();
        assertEquals("(0,31)", systemOut().getHistory());
        systemOut().clearHistory();
        System.out.println();
        systemOut().clearHistory();

        block = new FreeBlock(32, 15);
        list.insert(block);
        list.print();
        assertEquals("(0,31) -> (32,15)", systemOut().getHistory());
        systemOut().clearHistory();

        System.out.println();

    }


    /**
     * Tests the merge method to merge two adjacent blocks
     */
    public void testMerge() {

        FreeBlock block = new FreeBlock(0, 32);
        list.insert(block);
        block = new FreeBlock(32, 15);
        list.insert(block);
        block = new FreeBlock(50, 10);
        list.insert(block);
        block = new FreeBlock(60, 30);
        list.insert(block);
        block = new FreeBlock(90, 12);
        list.insert(block);

        list.merge();
        list.print();
        assertEquals("(0,47) -> (50,52)", systemOut().getHistory());
        systemOut().clearHistory();
        assertEquals(2, list.length());
        System.out.println();

    }


    /**
     * Tests the findBestFit method for finding a proper block
     */
    public void testFindBestFit() {

        assertEquals(null, list.findBestFit(10));
        FreeBlock block = new FreeBlock(0, 30);
        list.insert(block);
        block = new FreeBlock(32, 15);
        list.insert(block);
        block = new FreeBlock(49, 10);
        list.insert(block);
        assertEquals(null, list.findBestFit(40));
        assertEquals(30, list.findBestFit(20).data().length());
        assertEquals(15, list.findBestFit(14).data().length());
        assertEquals(10, list.findBestFit(8).data().length());

    }
}
