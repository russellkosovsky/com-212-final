// Customer.java
// Customer *nodetype? ... idk what to call. custom nodes
package CORE;
public class Customer {
    // Instance variables
    private String name;
    private long creditCardNumber;
    private String emailAddress;
    private MovieQueue wishlist;
    private int key;

    // Constructor
    public Customer(String name, long creditCardNumber, String emailAddress, MovieQueue wishlist) {
        this.name = name;
        this.creditCardNumber = creditCardNumber;
        this.wishlist = wishlist;
        this.key = (int)creditCardNumber%100000;
    }

    public Customer(String name, long creditCardNumber, String emailAddress) {
        this.name = name;
        this.creditCardNumber = creditCardNumber;
        this.key = (int)creditCardNumber%100000;
        this.wishlist = new MovieQueue();
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(int creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public MovieQueue getWishlist() {
        return wishlist;
    }

    // Wishlist methods
    public void addToWishlist(Movie movie) {
        wishlist.enqueue(movie);
    }

    public Movie removeFromWishlist() {
        Movie nextMovie = wishlist.dequeue();
        if (nextMovie != null && !nextMovie.Availablility()) {
            System.out.println("This movie is no longer available: " + nextMovie.getTitle());
            return removeFromWishlist();
        }
        return nextMovie;
    }

    public Movie getNextMovie() {
        Movie nextMovie = wishlist.front();
        if (nextMovie != null && !nextMovie.Availablility()) {
            System.out.println("This movie is no longer available: " + nextMovie.getTitle());
            wishlist.dequeue();
            return getNextMovie();
        }
        return nextMovie;
    }

    public void printWishlist() {
        wishlist.printQueue();
    }

    public int getKey() {
        return key;
    }
}
