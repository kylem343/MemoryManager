/**
 * HashTable class for storing memory handles
 * 
 * @author kylem
 * @version April 2023
 */
public class HashTable {

    /**
     * Handle array to be used as hash table
     */
    private Handle[] table;

    /**
     * Keeps track the number of elements in the table
     */
    private int tableSize;

    /**
     * Capacity of the table
     */
    private int cap;

    /**
     * HashTable constructor
     * 
     * @param hashSize
     *            initial size of the hashtable
     */
    public HashTable(int hashSize) {
        table = new Handle[hashSize];
        tableSize = 0;
        cap = hashSize;
    }


    /**
     * Insert method similar to the project video explanation
     * 
     * @param name
     *            name of artist/song to be inserted
     * @param han
     *            memory handle of the string being inserted
     * @return true if successfully added, false if not
     */
    public boolean insert(String name, Handle han) {

        // gets the index from the sFolding algorithm
        int home = sFold(name, cap);
        int cols = 0;

        // While the spot or there is no tombstone is taken we probe to the next
        // one
        while (isCollision(home, cols) && !isTombstone(home, cols)) {
            cols++;
        }
        table[(home + cols * cols) % cap] = han;
        tableSize++;
        return true;
    }


    /**
     * Searches for a specified string
     * 
     * @param str
     *            string to be searched for
     * @param pool
     *            pool to be used with searching
     * @return index of the proper memory handle, -1 if none exists
     */
    public int search(String str, MemPool pool) {

        int pos = sFold(str, cap);
        int cols = 0;

        while (isCollision(pos, cols)) {
            int index = (pos + (cols * cols)) % cap;
            // If the string matches and it is not a tombstone, we return the
            // index
            if (str.equals(pool.getString(table[index])) && !isTombstone(pos,
                cols)) {
                return index;
            }
            cols++;
        }
        return -1;
    }


    /**
     * Removes the handle at the specified index by setting a tombstone
     * 
     * @param idx
     *            index of the handle being removed
     * @return handle being removed
     */
    public Handle remove(int idx) {
        table[idx].tomb();
        tableSize--;
        return table[idx];
    }


    /**
     * Gets the tombstone at the calculated index
     * 
     * @param home
     *            home index to start
     * @param cols
     *            number of collisions for quadratic probing
     * @return true if it is a tombstone, false if not
     */
    private boolean isTombstone(int home, int cols) {
        int index = (home + (cols * cols)) % cap;
        return table[index].getTomb();
    }


    /**
     * Simple check for collisions in insertion
     * 
     * @param home
     *            home index to be inserted
     * @param cols
     *            number of collisions for quadratic probing
     * @return true if collision occured, false if not
     */
    private boolean isCollision(int home, int cols) {
        int index = (home + (cols * cols)) % cap;
        return table[index] != null;
    }


    /**
     * Our main SFolding Algorithm for hashing
     * 
     * @param s
     *            string to be folded
     * @param tableLength
     *            length of the hashtable
     * @return home index for the string to be inserted into the table
     */
    private int sFold(String s, int tableLength) {
        int intLength = s.length() / 4;
        long sum = 0;
        for (int j = 0; j < intLength; j++) {
            char[] c = s.substring(j * 4, (j * 4) + 4).toCharArray();
            long mult = 1;
            for (int k = 0; k < c.length; k++) {
                sum += c[k] * mult;
                mult *= 256;
            }
        }
        char[] c = s.substring(intLength * 4).toCharArray();
        long mult = 1;
        for (int k = 0; k < c.length; k++) {
            sum += c[k] * mult;
            mult *= 256;
        }
        return (int)(Math.abs(sum) % tableLength);
    }


    /**
     * Expands the table by doubling it's size
     * 
     * @param pool
     *            used for getting a string for rehashing
     * @return true if expanded, false if not
     */
    public boolean expand(MemPool pool) {

        if (tableSize >= cap / 2) {

            Handle[] oldTable = table;
            cap = table.length * 2;
            table = new Handle[cap];

            // Re insert hash table values
            for (int i = 0; i < oldTable.length; i++) {
                if (oldTable[i] != null && !oldTable[i].getTomb()) {
                    tableSize = 0;
                    insert(pool.getString(oldTable[i]), oldTable[i]);
                }
            }
            return true;
        }
        return false;
    }


    /**
     * Prints all available elements in the hash table
     * 
     * @param pool
     *            pool to be used with accessing values
     * @return number of handles printed
     */
    public int print(MemPool pool) {
        int count = 0;
        for (int i = 0; i < cap; i++) {
            if (table[i] != null && !table[i].getTomb()) {
                String item = pool.getString(table[i]);
                System.out.println("|" + item + "| " + i);
                count++;
            }
        }
        return count;
    }

}
