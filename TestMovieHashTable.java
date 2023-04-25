public class TestMovieHashTable {
    public static void main(String[] args) {
        MovieHashTable movieHashTable = new MovieHashTable(7);

        Movie movieA = new Movie("Movie A", 20010115, 00001, 85, true);
        Movie movieB = new Movie("Movie B", 19990522, 00002, 99, false);
        Movie movieC = new Movie("Movie C", 20120412, 00003, 90, true);
        Movie movieD = new Movie("Movie D", 20180123, 00004, 95, false);
        Movie movieE = new Movie("Movie E", 20150711, 00005, 80, true);

        // Adding movies to the hash table
        movieHashTable.add(movieA);
        movieHashTable.add(movieB);
        movieHashTable.add(movieC);
        movieHashTable.add(movieD);
        movieHashTable.add(movieE);

        // Print the hash table
        movieHashTable.printTable();

        // Look up a movie by its unique ID
        Movie movieLookup = movieHashTable.lookUp(3);
        if (movieLookup != null) {
            System.out.println("Movie found: " + movieLookup.getTitle() + " : " + movieLookup.getUniqueID());
        } else {
            System.out.println("Movie not found.");
        }

        // Remove a movie from the hash table by its unique ID
        movieHashTable.remove(2);
        System.out.println("Movie with ID 2 removed.");

        // Print the hash table after removal
        movieHashTable.printTable();
    }
}

