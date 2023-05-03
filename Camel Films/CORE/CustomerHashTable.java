// Hash Table java implementation for storing Customer data
package CORE;

import java.io.Serializable;

public class CustomerHashTable implements Serializable {
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
        long key = customer.getCreditCardNumber();
        long index = hash1(key);
        long step = hash2(key);
        long originalIndex = index;
        while (table[(int)index] != null) {
            index = (index + step) % TABLE_SIZE;
            if (index == originalIndex) {
                System.out.println("Table is full.");
                return;
            }
        }
        table[(int)index] = customer;
    }
    
    public Customer lookUp(long key) {
        long index = hash1(key);
        long step = hash2(key);
        while (table[(int)index] != null) {
            if (table[(int)index].getCreditCardNumber() == key) {
                return table[(int)index];
            }
            index = (index + step) % TABLE_SIZE;
        }
        return null;
    }
    
    public void remove(long key) {
        long index = hash1(key);
        long step = hash2(key);
        while (table[(int)index] != null) {
            if (table[(int)index].getCreditCardNumber() == key) {
                table[(int)index] = null;
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
    
    private long hash1(long key) {
        return key % TABLE_SIZE;
    }
    
    private long hash2(long key) {
        return prime - (key % prime);
    }
}
