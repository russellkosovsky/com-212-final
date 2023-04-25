// Customer.java
public class Customer {
    // Instance variables
    private String name;
    private String creditCardNumber;
    private String emailAddress;
    private QueueLL wishlist;

    // Constructor
    public Customer(String name, String creditCardNumber, String emailAddress) {
        this.name = name;
        this.creditCardNumber = creditCardNumber;
        this.emailAddress = emailAddress;
        this.wishlist = new QueueLL();
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public QueueLL getWishlist() {
        return wishlist;
    }

    // Wishlist methods
    public void addToWishlist(Movie movie) {
        wishlist.enqueue(movie);
    }

    public Movie removeFromWishlist() {
        Movie nextMovie = wishlist.dequeue();
        if (nextMovie != null && !nextMovie.isAvailable()) {
            System.out.println("This movie is no longer available: " + nextMovie.getTitle());
            return removeFromWishlist();
        }
        return nextMovie;
    }

    public Movie getNextMovie() {
        Movie nextMovie = wishlist.front();
        if (nextMovie != null && !nextMovie.isAvailable()) {
            System.out.println("This movie is no longer available: " + nextMovie.getTitle());
            wishlist.dequeue();
            return getNextMovie();
        }
        return nextMovie;
    }

    public void printWishlist() {
        wishlist.printQueue();
    }
}
