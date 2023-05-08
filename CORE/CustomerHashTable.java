// Computer Science Data Structures Final Project
// Authors: Russell, Brooke, Jay, and Miles
// Description: This code implements a hash table in Java for storing customer data

import java.io.Serializable;

public class CustomerHashTable implements Serializable{
// INSTANCE VARIABLES
private List[] table; // an array of linked lists to store customer data
private int hash; // the size of the hash table    
    
// CONSTRUCTOR
public CustomerHashTable(int size) {
    table = new List[size];
    hash = size;
    for (int i=0; i < size; i++){
        table[i] = new List(); // initialize each element of the table with a new linked list
    }
}

// Inserts a new customer into the hash table
public void insert(Customer customer){
    table[customer.getCreditCardNumber()%hash].insert(customer); // inserts the customer into the corresponding linked list based on their credit card number
}

// Looks up a customer in the hash table based on their credit card number
public Customer lookUp(int key){
    try {
        return table[key%hash].searchReturn(key); // searches the corresponding linked list for the customer with the given credit card number and returns it
    } catch (Exception e) {
        return null; // if the customer is not found, returns null
    }
}

// Deletes a customer from the hash table based on their credit card number
public Customer delete(int key){
    return table[key%hash].searchRemove(key); // searches the corresponding linked list for the customer with the given credit card number, removes it from the list, and returns it
}

// Checks if the hash table is empty
public boolean isEmpty(){
    for (int i=0; i < 7; i++){
        if (!table[i].isEmptyList()) return false; // if any of the linked lists in the table is not empty, the table is not empty
    }
    return true; // otherwise, the table is empty
}

// Prints the contents of the hash table
public void printHash(){
    System.out.println("Dict Contents:");
    for (int i=0; i < 7; i++){
        table[i].printList(); // prints the contents of each linked list in the table
    }
    System.out.println("End Dict Contents");
}

