package CORE;

import java.io.Serializable;

// Jay Nash 3/8/23
// Program to impliment Linked List, list is created by creating a pointer to null and int n to 0
// Customers can be added using the insert function and can be found to be returned or deleted using searchRemove
// or searchReturn. The entire list can be interated over and printed using printList and it can be found if the
// list is empty using isEmptyList.

public class watchList implements Serializable{
	private Movie head; // Head Pointer
	private int n; // Number of Customers in List

	public watchList() { // Constructor of List
		head = null;
		n = 0;
	}
	
	public int length(){ // Returns N as it is the length of the List
		return n;
	}
	
	public boolean isEmptyList(){ // Checks if N is 0
		return n == 0;
	}
	
	public Movie searchReturn(int key){ // returns Movie with specified key
		Movie temp = head;
		while (temp != null){
			if (temp.getUniqueID() == key) {
				return temp;
			}
			temp = temp.getNext();
		}
		return null;
	}
	
	public Movie searchRemove(int key){ // Returns and removes Movie with specified key
		Movie lastTemp = null;
		Movie temp = head;
		while (temp != null) {
			if (temp.getUniqueID() == key) {
				if (lastTemp == null) {
					head = temp.getNext();
				} else {
					lastTemp.setNext(temp.getNext());
				}
				n--;
				return temp;
			}
			lastTemp = temp;
			temp = temp.getNext();
		}
		return null;
	}
	
	public void insert(Movie x){ // Appends specified Movie to list
		x.setNext(head);
		head = x;
		n++;
	}
	
	public String printList(){ // Prints entire list
        StringBuilder buildString = new StringBuilder();
		Movie temp = head;
		while (temp != null){
			buildString.append(temp.getTitle() + " " + String.valueOf(temp.getReleaseDate()) + "\n");
			temp = temp.getNext();
		}
        return buildString.toString();
	}
}

