// MovieHashTable.java
import java.util.*;

public class MovieHashTable {
    private static final int TABLE_SIZE = 7;
    private Movie[] table;
    private int prime;

    public MovieHashTable(int size) {
        table = new Movie[size];
        prime = getPrime(size);
    }

    private int getPrime(int size) {
        for (int i = size - 1; i >= 1; i--) {
            if (isPrime(i)) {
                return i;
            }
        }
        return 1;
    }

    private boolean isPrime(int num) {
        if (num <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    public void add(Movie movie) {
        int key = movie.getUniqueID();
        int index = hash1(key);
        int step = hash2(key);
        int originalIndex = index;
        while (table[index] != null) {
            index = (index + step) % TABLE_SIZE;
            if (index == originalIndex) {
                System.out.println("Table is full.");
                return;
            }
        }
        table[index] = movie;
    }

    public Movie lookUp(int uniqueID) {
        int index = hash1(uniqueID);
        int step = hash2(uniqueID);
        while (table[index] != null) {
            if (table[index].getUniqueID() == uniqueID) {
                return table[index];
            }
            index = (index + step) % TABLE_SIZE;
        }
        return null;
    }

    public void remove(int uniqueID) {
        int index = hash1(uniqueID);
        int step = hash2(uniqueID);
        while (table[index] != null) {
            if (table[index].getUniqueID() == uniqueID) {
                table[index] = null;
                return;
            }
            index = (index + step) % TABLE_SIZE;
        }
    }

    public boolean isEmptyHash() {
        for (int i = 0; i < TABLE_SIZE; i++) {
            if (table[i] != null) {
                return false;
            }
        }
        return true;
    }

    public void printTable() {
        for (int i = 0; i < TABLE_SIZE; i++) {
            if (table[i] != null) {
                System.out.println("Index: " + i + " -> " + table[i].getTitle() + " : " + table[i].getUniqueID());
            } else {
                System.out.println("Index: " + i + " -> null");
            }
        }
    }

    private int hash1(int key) {
        return key % TABLE_SIZE;
    }

    private int hash2(int key) {
        return prime - (key % prime);
    }
}

