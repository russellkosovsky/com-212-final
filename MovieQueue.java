// Queue class implementation using a linked list
// QueueLL.java
// for storing userses top 20 movies (a watchlist??)

public class MovieQueue {
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
            tail.setRight(newMovie);
            tail = newMovie;
        }
        size++;
    }

    public Movie dequeue() {
        if (head == null) {
            return null;
        }
        Movie temp = head;
        head = head.getRight();
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
        while (temp != null) {
            System.out.println(temp.getTitle());
            temp = temp.getRight();
        }
    }
}
