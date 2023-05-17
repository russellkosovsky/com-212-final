package CORE;
// Queue class implementation using a linked list
// Computer Science Data Structures Final Project
// By Russell, Brooke, Jay, and Miles
// for storing userses top 20 movies (a watchlist??)

import java.io.Serializable;

public class MovieQueue implements Serializable{
    private Movie array[];
    private int front = 0;
    private int n = 0;

    public MovieQueue(){ //Constructor
        array = new Movie[20];
    }

    public Movie front(){ //Returns array place 1
        return array[front];
    }

    public void printQueue() { //Prints Queue
        int tail = (front + n) % 20;
        if (front <= tail) { // Checks if the tail is not looped around the array
           for(int i = front; i < tail; i++) {
                Movie temp = array[i];
                System.out.println("          " + temp.getTitle() + " Released: " + temp.getReleaseDate());
           }
        }
        else { // Sepereate system if it has to loop around array
           for(int i = front; i < 20; i++) {
                Movie temp = array[i];
                System.out.println("          " + temp.getTitle() + " Released: " + temp.getReleaseDate());
            }        
            for(int i = 0; i < tail; i++) {
                Movie temp = array[i];
                System.out.println("          " + temp.getTitle() + " Released: " + temp.getReleaseDate());
            }
        }          
    }

    public String printQueueString() { //Prints Queue
        int tail = (front + n) % 20;
        StringBuilder buildString = new StringBuilder();
        if (front <= tail) { // Checks if the tail is not looped around the array
           for(int i = front; i < tail; i++) {
                Movie temp = array[i];
                String avalTemp;
                if (temp.Availablility() == true) {
                    avalTemp = "Available";
                } else {avalTemp = "Unvailable";}
                buildString.append(temp.getTitle() + " " + temp.getReleaseDate() + " " + avalTemp + "\n");
           }
        }
        else { // Sepereate system if it has to loop around array
           for(int i = front; i < 20; i++) {
                Movie temp = array[i];
                String avalTemp;
                if (temp.Availablility() == true) {
                    avalTemp = "Available";
                } else {avalTemp = "Unvailable";}
                buildString.append(temp.getTitle() + " " + temp.getReleaseDate() + " " + avalTemp + "\n");   
            }        
            for(int i = 0; i < tail; i++) {
                Movie temp = array[i];
                String avalTemp;
                if (temp.Availablility() == true) {
                    avalTemp = "Available";
                } else {avalTemp = "Unvailable";}
                buildString.append(temp.getTitle() + " " + temp.getReleaseDate() + " " + avalTemp + "\n");
            }
        }
        String string = buildString.toString();
        return string;          
    }

    public Movie dequeue(){ // Dequeues and returns node
        Movie temp = array[front];
        front = (front+1)%20;
        n = (n-1)%20;
        return temp;
    }

    public Movie enqueue(Movie x){ // Adds node
        int tail = (front + n)  % 20;
        array[tail] = x;
        n = (n+1)%20;
        return array[tail];
    }

    public boolean isEmpty(){ // Returns empty T/F
        return n == 0;
    }

    public int length(){ // Returns length
        return n;
    }
}
