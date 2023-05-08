import CORE.*;

public class bstByDateTEST {
    public static void main(String[] args) {
            
            
            //creates some sample movies for the user
            Movie movie1 = new Movie("The Secret Life of Pets", 2016, 11111, 75, true);
            Movie movie2 = new Movie("The Dark Knight", 2022, 22222, 94, true);
            Movie movie3 = new Movie("Jurassic Park", 1999, 33333, 91, false);
            Movie movie4 = new Movie("The Godfather", 1979, 44444, 98, true);
            Movie movie5 = new Movie("Inception", 2010, 55555, 87, true);
            Movie movie6 = new Movie("Avatar", 2009, 66666, 82, true);
            Movie movie7 = new Movie("The Shawshank Redemption", 1994, 77777, 91, false);
            Movie movie8 = new Movie("Pulp Fiction", 1994, 88888, 94, false);
            Movie movie9 = new Movie("Forrest Gump", 1994, 99999, 72, true);
            Movie movie10 = new Movie("The Matrix", 1999, 10000, 87, true);

            

            // Create a bstByDate instance and insert the movies
            bstByDate bst = new bstByDate();
            
            bst.dateInsert(movie1);
            bst.dateInsert(movie2);
            bst.dateInsert(movie3);
            bst.dateInsert(movie4);
            bst.dateInsert(movie5);
            bst.dateInsert(movie6);
            bst.dateInsert(movie7); 
            bst.dateInsert(movie8);
            bst.dateInsert(movie9);
            bst.dateInsert(movie10);

        // Test traverseByDate method
        System.out.println("Traversal by date:");
        bst.traverseByDate();

        
        
        
        // Test searchDate method
        Movie result = bst.searchDate(2022);
        System.out.println("Search result for date (2022):");
        System.out.println("Title: " + result.getTitle() + ", ID: " + result.getUniqueID() +
                           ", Score: " + result.getRottenTomatoesScore() + ", Release Date: " +
                           result.getReleaseDate() + ", Available: " + result.Availablility());

        
        
        // Test dateDelete method
        bst.dateDelete(movie10);
        System.out.println("Traversal by date after deleting The Matrix:");
        bst.traverseByDate(); // expected output: Movie 1, Movie 3
    }
}

