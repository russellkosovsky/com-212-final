package CORE;
// Customer.java
// Customer *nodetype? ... idk what to call. custom nodes

import java.io.Serializable;

public class Customer implements Serializable{
    // Instance variables
    private String name;
    private int creditCardNumber;
    private String emailAddress;
    private MovieQueue wishlist;

    // Constructor
    public Customer(String name, int creditCardNumber, String emailAddress, MovieQueue wishlist) {
        this.name = name;
        this.creditCardNumber = creditCardNumber;
        this.emailAddress = emailAddress;
        this.wishlist = wishlist;

    }

    // Getters and Setters
    public String getName() {
        return name; 
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCreditCardNumber() {
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
}
