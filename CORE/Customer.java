// Computer Science Data Structures Final Project
// Authors: Russell, Brooke, Jay, and Miles
// Description: This code implements a custom node for a customer in a movie rental system.
// The Customer class includes instance variables for the customer's name, credit card number, email address, wishlist,
// and watched list. It also includes methods for adding and removing movies from the wishlist, getting the next movie in the wishlist,
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
    private Customer next;
    private watchList watchedList;

    // Constructor
    public Customer(String name, int creditCardNumber, String emailAddress, MovieQueue wishlist) {
        this.name = name;
        this.creditCardNumber = creditCardNumber;
        this.emailAddress = emailAddress;
        this.wishlist = wishlist;
        this.watchedList = new watchList();

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

    public void watched(Movie movie){
        watchedList.insert(movie);
    }

    public watchList getWatched() {
        return watchedList;
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

    public Customer getNext() {
        return next;
    }

    public void setNext(Customer x) {
        next = x;
    }
}
