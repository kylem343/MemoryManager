import student.TestCase;

/**
 * Test class for the FreeNode
 * @author kylem
 * @version April 2023
 */
public class FreeNodeTest extends TestCase {
    
    /**
     * Node for testing
     */
    private FreeNode node;
    
    
    /**
     * Tests setup on nodes
     */
    public void setUp() {
        FreeBlock data = new FreeBlock(0, 32);
        
        FreeNode prv = new FreeNode(null, node);
        FreeNode nxt = new FreeNode(node, null);
        node = new FreeNode(data, prv, nxt);        
        
    }
    
    /**
     * Tests the data method
     */
    public void testData() {
        assertEquals(0,  node.data().getStart());
    }
    
    /**
     * Tests the next method
     */
    public void testNext() {
        assertEquals(null, node.next().data());
    }
    
    /**
     * Tests the prev method
     */
    public void testPrev() {
        assertEquals(null, node.next().data());
    }
    
    /**
     * Tests the setData method
     */
    public void testSetData() {
        FreeBlock newBlock = new FreeBlock(5, 30);
        node.setData(newBlock);
        assertEquals(5, node.data().getStart());
                
    }
    
    /**
     * Tests the setNext() method
     */
    public void testSetNext() {
        FreeBlock newBlock = new FreeBlock(5, 30);
        node.setNext(new FreeNode(newBlock, node, null));
        assertEquals(5, node.next().data().getStart());
    }
    
    /**
     * Tests the setPrev() method
     */
    public void testSetPrev() {
        FreeBlock newBlock = new FreeBlock(5, 30);
        node.setPrev(new FreeNode(newBlock, null, node));
        assertEquals(5, node.prev().data().getStart());
        
    }

}
