package CORE;
// Queue class implementation using a linked list
// QueueLL.java
// for storing userses top 20 movies (a watchlist??)

import java.io.Serializable;

public class MovieQueue implements Serializable{
    private Movie head;

    public MovieQueue() {
        head = null;
    }

    public void enqueue(Movie newMovie) {
        if (head == null) {
            head = newMovie;
        } else {
            Movie temp = head;
            while (temp.getNext() != null) {
                temp = temp.getNext();
            }
            temp.setNext(newMovie);
        }
    }

    public Movie dequeue() {
        Movie temp = head;
        head = head.getNext();
        temp.setNext(null);
        return temp;
    }

    public Movie front() {
        return head;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int size() {
        int size = 0;
        Movie temp = head;
        while (temp != null) {
            size++;
            temp = temp.getNext();
        }
        return size;
    }

    public void printQueue() {
        Movie temp = head;
        while (temp != null) {
            System.out.println(temp.getTitle());
            temp = temp.getNext();
        }
    }
}
