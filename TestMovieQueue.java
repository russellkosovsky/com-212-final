// Gary Parker
// Test for the Linked-List Queue

public class TestMovieQueue {
    public static void main(String[] args){
        MovieQueue a = new MovieQueue();
        
        Movie movie1 = new Movie("The Secret Life of Pets", 2016, 11267, 75, true);
        Movie movie2 = new Movie("The Dark Knight", 2008, 15639, 94, true);
        Movie movie3 = new Movie("Jurassic Park", 1993, 14281, 91, false);
        Movie movie4 = new Movie("The Godfather", 1972, 17893, 98, true);
        
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

