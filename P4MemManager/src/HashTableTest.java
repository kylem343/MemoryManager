import student.TestCase;

/**
 * Tests the hash table class
 * 
 * @author kylem
 * @version April 2023
 */
public class HashTableTest extends TestCase {

    /**
     * Hash table used for testing
     */
    private HashTable table;

    /**
     * pool used to assist in testing
     */
    private MemPool pool;

    /**
     * Sets up the testing
     */
    public void setUp() {
        table = new HashTable(10);
        pool = new MemPool(64);
    }


    /**
     * tests the insert method
     */
    public void testInsert() {
        Handle han = pool.insert("test");
        assertTrue(table.insert("test", han));
        Handle han2 = pool.insert("test2");
        assertTrue(table.insert("test2", han2));

        table.remove(table.search("test", pool));
        Handle han3 = pool.insert("test");
        assertTrue(table.insert("test", han3));

    }


    /**
     * Tests the remove method
     */
    public void testRemove() {
        Handle han = pool.insert("test");
        table.insert("test", han);
        int pos = table.search("test", pool);
        assertTrue(table.search("test", pool) != -1);
        table.remove(pos);
        assertEquals(-1, table.search("test", pool));
    }


    /**
     * tests the print method
     */
    public void testPrint() {

        assertEquals(0, table.print(pool));

        Handle han = pool.insert("test");
        table.insert("test", han);
        assertEquals(1, table.print(pool));
        assertEquals("|test| 8\n", systemOut().getHistory());
        systemOut().clearHistory();

    }


    /**
     * tests the search method
     */
    public void testSearch() {
        Handle han = pool.insert("test");
        table.insert("test", han);

        assertTrue(table.search("test", pool) != -1);
        assertEquals(-1, table.search("wrong", pool));

        table.remove(table.search("test", pool));
        assertEquals(-1, table.search("test", pool));

    }

}
