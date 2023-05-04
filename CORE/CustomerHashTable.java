package CORE;
// Hash Table java implementation for storing Customer data

import java.io.Serializable;

public class CustomerHashTable implements Serializable{
    // INSTANCE
    private List[] table;
    private int hash;
    
    //CONSTRUCTOR
    public CustomerHashTable(int size) {
        table = new List[size];
        hash = size;
        for (int i=0; i < size; i++){
            table[i] = new List();
        }
    }
    
    public void insert(Customer customer){
        table[customer.getCreditCardNumber()%hash].insert(customer);
    }

    public Customer lookUp(int key){
        return table[key%hash].searchReturn(key);
    }

    public Customer delete(int key){
        return table[key%hash].searchRemove(key);
    }

    public boolean isEmpty(){
        for (int i=0; i < 7; i++){
            if (!table[i].isEmptyList()) return false;
        }
        return true;
    }

    public void printHash(){
        System.out.println("Dict Contents:");
        for (int i=0; i < 7; i++){
            table[i].printList();
        }
        System.out.println("End Dict Contents");
    }
}