// Queue class implementation using a linked list
// QueueLL.java
// for storing userses top 20 movies (a watchlist??)

import java.io.Serializable;

public class MovieQueue implements Serializable{
    private Movie head;
    private Movie tail;
    private int size;

    public MovieQueue() {
        head = null;
        tail = null;
        size = 0;
    }

    public void enqueue(Movie newMovie) {
        if (tail == null) {
            head = newMovie;
            tail = newMovie;
        } else {
            tail.setNext(newMovie);
            tail = newMovie;
        }
        size++;
    }

    public Movie dequeue() {
        if (head == null) {
            return null;
        }
        Movie temp = head;
        head = head.getNext();
        if (head == null) {
            tail = null;
        }
        size--;
        return temp;
    }

    public Movie front() {
        return head;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printQueue() {
        Movie temp = head;
        if (size == 0) {
            System.out.println("Wishlist is Empty");
        }
        while (temp != null) {
            System.out.println(temp.getTitle());
            temp = temp.getNext();
        }
    }
}
