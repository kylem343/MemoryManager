import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Main class for memory management and file processing
 * 
 * @author kylem
 * @version April 2023
 */
public class MemMan {

    /**
     * HashTable for the songs
     */
    private static HashTable songs;

    /**
     * HashTable for the artists
     */
    private static HashTable artists;

    /**
     * Memory Pool storage for strings
     */
    private static MemPool pool;

    /**
     * Used for printing correctly
     */
    private static boolean firstLine;

    /**
     * Main class for running the program
     * 
     * @param args
     *            arguments for the project
     */
    public static void main(String[] args) {

        firstLine = true;

        // Checks that there is an arg of correct length
        if (args == null || args.length != 3) {
            return;
        }

        // Initialize the hashtables
        int hashLength = Integer.parseInt(args[0]);
        int poolSize = Integer.parseInt(args[1]);

        songs = new HashTable(hashLength);
        artists = new HashTable(hashLength);
        pool = new MemPool(poolSize);

        String fileName = args[2];
        File file = new File(fileName);

        // Basic Scanner checking
        try {
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                String fileLine = sc.nextLine();
                // makes sure not to pass in a blank line
                if (!fileLine.isBlank()) {
                    commandProcessor(fileLine);
                }
            }
            sc.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    /**
     * processes all the line commands
     * 
     * @param line
     *            string of line command to be done
     */
    private static void commandProcessor(String line) {

        // Checks first line for printing properly
        if (!firstLine) {
            System.out.println();
        }
        else {
            firstLine = false;
        }

        // Print methods
        if (line.contains("print")) {
            printCommands(line);
        } // Insert methods
        else if (line.contains("insert")) {
            insertCommands(line);
        } // remove methods
        else if (line.contains("remove")) {
            removeCommands(line);
        }

    }


    /**
     * Helper method for running print commands
     * 
     * @param line
     *            line for printing
     */
    private static void printCommands(String line) {
        // print block method
        if (line.contains("blocks")) {
            pool.printBlocks();
        } // print songs method
        else if (line.contains("song")) {
            int count = songs.print(pool);
            System.out.print("total songs: " + count);
        } // print artists method
        else if (line.contains("artist")) {
            int count = artists.print(pool);
            System.out.print("total artists: " + count);
        }

    }


    /**
     * runs the insert commands
     * 
     * @param line
     *            line for commands
     */
    private static void insertCommands(String line) {
        // If a single song/artist is inserted we use these smaller methods
        if (line.contains("artist")) {
            // inserts an individual artist
            insertArtist(line);
        }
        else if (line.contains("song")) {
            // inserts an individual song
            insertSong(line);
        }
        else {
            // This will be used for inserting both artists and songs
            String newLine = line.substring(7);
            String[] words = newLine.split("<SEP>");

            if (artists.search(words[0], pool) == -1) {

                // Checks if hash table needs to be expanded
                if (artists.expand(pool)) {
                    System.out.println("Artist hash table size doubled.");
                }

                // Insert into mem pool
                Handle han = pool.insert(words[0]);

                // Insert into the hash table
                artists.insert(words[0], han);
                System.out.println("|" + words[0]
                    + "| is added to the artist database.");
            }
            else {
                System.out.println("|" + words[0]
                    + "| duplicates a record already in the artist"
                    + " database.");
            }

            if (songs.search(words[1], pool) == -1) {

                // Check for table expansion
                if (songs.expand(pool)) {
                    System.out.println("Song hash table size doubled.");
                }

                // insert into mem pool
                Handle han = pool.insert(words[1]);

                // insert into the hash table
                songs.insert(words[1], han);
                System.out.print("|" + words[1]
                    + "| is added to the song database.");
            }
            else {
                System.out.print("|" + words[1]
                    + "| duplicates a record already in the song"
                    + " database.");
            }
        }
    }


    /**
     * Helper method for inserting artists
     * 
     * @param line
     *            line to be inserted
     */
    private static void insertArtist(String line) {
        String newLine = line.substring(14);

        if (artists.search(newLine, pool) == -1) {

            // Checks if hash table needs to be expanded
            if (artists.expand(pool)) {
                System.out.print("Artist hash table size doubled.");
            }

            Handle han = pool.insert(newLine);

            artists.insert(newLine, han);
            System.out.print("|" + newLine
                + "| is added to the artist database.");
        }
        else {
            System.out.print("|" + newLine
                + "| duplicates a record already in the artist" + " database.");
        }
    }


    /**
     * Helper method for inserting songs
     * 
     * @param line
     *            line to be inserted
     */
    private static void insertSong(String line) {
        String newLine = line.substring(12);

        // Check if there is a duplicate value in the table
        if (songs.search(newLine, pool) == -1) {
            // Checks if hash table needs to be expanded
            if (songs.expand(pool)) {
                System.out.println("Song hash table size doubled.");
            }

            Handle han = pool.insert(newLine);

            songs.insert(newLine, han);
            System.out.print("|" + newLine
                + "| is added to the song database.");
        }
        else {
            System.out.print("|" + newLine
                + "| duplicates a record already in the song" + " database.");
        }
    }


    /**
     * Helper method for the remove commands
     * 
     * @param line
     *            line to be removed
     */
    private static void removeCommands(String line) {
        if (line.contains("artist")) {
            // Removes a specific artist
            String newLine = line.substring(14);
            int idx = artists.search(newLine, pool);

            // Checks if artist is found
            if (idx == -1) {
                System.out.print("|" + newLine
                    + "| does not exist in the artist database.");
            }
            else {
                // If artist is found then remove
                Handle han = artists.remove(idx);
                pool.remove(han);
                System.out.print("|" + newLine
                    + "| is removed from the artist database.");
            }

        }
        else if (line.contains("song")) {
            // Removes a specific song
            String newLine = line.substring(12);
            int idx = songs.search(newLine, pool);

            // Checks if song is found
            if (idx == -1) {
                System.out.print("|" + newLine
                    + "| does not exist in the song database.");
            }
            else {
                // if song is found then remove
                Handle han = songs.remove(idx);
                pool.remove(han);
                System.out.print("|" + newLine
                    + "| is removed from the song database.");
            }

        }
    }

}
