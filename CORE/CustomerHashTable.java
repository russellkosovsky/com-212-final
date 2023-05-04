package CORE;
// Hash Table java implementation for storing Customer data

import java.io.Serializable;

public class CustomerHashTable implements Serializable{
    // INSTANCE
    private static final int TABLE_SIZE = 67;
    private Customer[] table;
    private int prime;
    
    //CONSTRUCTOR
    public CustomerHashTable(int size) {
        table = new Customer[size];
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
    
    public void add(Customer customer) {
        int key = customer.getCreditCardNumber();
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
        table[index] = customer;
    }
    
    public Customer lookUp(int key) {
        int index = hash1(key);
        int step = hash2(key);
        while (table[index] != null) {
            if (table[index].getCreditCardNumber() == key) {
                return table[index];
            }
            index = (index + step) % TABLE_SIZE;
        }
        return null;
    }
    
    public void remove(int key) {
        int index = hash1(key);
        int step = hash2(key);
        while (table[index] != null) {
            if (table[index].getCreditCardNumber() == key) {
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
                System.out.println("Index: " + i + " -> " + table[i].getName() + " : " + table[i].getCreditCardNumber());
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
