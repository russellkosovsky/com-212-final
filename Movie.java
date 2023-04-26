// Movie.java
// custom node class to represent the elements of any movie

public class Movie {
    // Instance variables
    private String title;
    private int releaseDate;
    private int uniqueID;
    private int rottenTomatoesScore;
    private boolean isAvailable;
    private Movie left;
    private Movie right;

    // Constructor
    public Movie(String title, int releaseDate, int uniqueID, int rottenTomatoesScore, boolean isAvailable) {
        this.title = title;
        this.releaseDate = releaseDate;
        this.uniqueID = uniqueID;
        this.rottenTomatoesScore = rottenTomatoesScore;
        this.isAvailable = isAvailable;
        this.left = null;
        this.right = null;
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
        return uniqueID;
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

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
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
}

