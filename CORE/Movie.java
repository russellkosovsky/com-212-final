package CORE;
// Movie.java
// Computer Science Data Structures Final Project
// By Russell, Brooke, Jay, and Miles
// custom node class to represent the elements of any movie

import java.io.Serializable;

public class Movie implements Serializable{
    // Instance variables
    private String title;
    private int releaseDate;
    private int uniqueID;
    private int rottenTomatoesScore;
    private boolean isAvailable;
    private Movie left;
    private Movie right;
    private Movie leftDate;
    private Movie rightDate;
    private Movie next;

    // Constructor
    public Movie(String title1, int releaseDate1, int uniqueID1, int rottenTomatoesScore1, boolean isAvailable1) {
        this.title = title1;
        this.releaseDate = releaseDate1;
        this.uniqueID = uniqueID1;
        this.rottenTomatoesScore = rottenTomatoesScore1;
        this.isAvailable = isAvailable1;
        this.left = null;
        this.right = null;
        this.next = null;
    }

    public Movie(Movie other) {
        this.title = other.title;
        this.releaseDate = other.releaseDate;
        this.uniqueID = other.uniqueID;
        this.rottenTomatoesScore = other.rottenTomatoesScore;
        this. isAvailable = true;

        this.next = null;
    }

    // Getters and Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(int releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getUniqueID() {
        return this.uniqueID;
    }

    public void setUniqueID(int uniqueID) {
        this.uniqueID = uniqueID;
    }

    public int getRottenTomatoesScore() {
        return rottenTomatoesScore;
    }

    public void setRottenTomatoesScore(int rottenTomatoesScore) {
        this.rottenTomatoesScore = rottenTomatoesScore;
    }

    public boolean Availablility() {
        return isAvailable;
    }

    public void setAvailablility(boolean Availability) {
        this.isAvailable = Availability;
    }

    public Movie getLeft() {
        return left;
    }

    public void setLeft(Movie left) {
        this.left = left;
    }

    public Movie getRight() {
        return right;
    }

    public void setRight(Movie right) {
        this.right = right;
    }

    public Movie getLeftDate() {
        return leftDate;
    }

    public void setLeftDate(Movie left) {
        this.leftDate = left;
    }

    public Movie getRightDate() {
        return rightDate;
    }

    public void setRightDate(Movie right) {
        this.rightDate = right;
    }
    public Movie getNext() {
        return next;
    }

    public void setNext(Movie next) {
        this.next = next;
    }
}


