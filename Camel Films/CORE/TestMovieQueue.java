// Gary Parker
// Test for the Linked-List Queue
package CORE;
public class TestMovieQueue {
    public static void main(String[] args){
        MovieQueue a = new MovieQueue();
        
        Movie movie1 = new Movie("The Secret Life of Pets", 2016, 11267, 75, true);
        Movie movie2 = new Movie("The Dark Knight", 2008, 15639, 94, true);
        Movie movie3 = new Movie("Jurassic Park", 1993, 14281, 91, false);
        Movie movie4 = new Movie("The Godfather", 1972, 17893, 98, true);
        Movie movie5 = new Movie("Inception", 2010, 12356, 87, false);
        Movie movie6 = new Movie("Avatar", 2009, 19274, 82, true);
        Movie movie7 = new Movie("The Shawshank Redemption", 1994, 13645, 91, true);
        Movie movie8 = new Movie("Pulp Fiction", 1994, 11937, 94, false);
        Movie movie9 = new Movie("Forrest Gump", 1994, 14582, 72, true);
        Movie movie10 = new Movie("The Matrix", 1999, 16471, 87, true);
        
        a.enqueue(movie1);
        a.enqueue(movie2);
        a.enqueue(movie3);
        a.enqueue(movie4);
        
        a.printQueue();
        System.out.println();
        
        System.out.println(a.front().getTitle());
        System.out.println();
        
        a.printQueue();
        System.out.println();
        
        System.out.println(a.dequeue().getTitle());
        System.out.println();
        
        a.printQueue();
        System.out.println();
        
        System.out.println(a.isEmpty());
        
        while(!a.isEmpty()) {
            System.out.println(a.dequeue().getTitle());
            System.out.println();
        }
        
        a.printQueue();
        System.out.println(a.isEmpty());
        System.out.println();
    }
}

