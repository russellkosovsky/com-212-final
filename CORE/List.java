package CORE;

import java.io.Serializable;

// Jay Nash 3/8/23
// Program to impliment Linked List, list is created by creating a pointer to null and int n to 0
// Customers can be added using the insert function and can be found to be returned or deleted using searchRemove
// or searchReturn. The entire list can be interated over and printed using printList and it can be found if the
// list is empty using isEmptyList.

public class List implements Serializable{
	private Customer head; // Head Pointer
	private int n; // Number of Customers in List

	public List() { // Constructor of List
		head = null;
		n = 0;
	}
	
	public int length(){ // Returns N as it is the length of the List
		return n;
	}
	
	public boolean isEmptyList(){ // Checks if N is 0
		return n == 0;
	}
	
	public Customer searchReturn(int key){ // returns Customer with specified key
		Customer temp = head;
		while (temp != null){
			if (temp.getCreditCardNumber() == key) {
				return temp;
			}
			temp = temp.getNext();
		}
		return null;
	}
	
	public Customer searchRemove(int key){ // Returns and removes Customer with specified key
		Customer lastTemp = null;
		Customer temp = head;
		while (temp != null) {
			if (temp.getCreditCardNumber() == key) {
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
	
	public void insert(Customer x){ // Appends specified Customer to list
		x.setNext(head);
		head = x;
		n++;
	}
	
	public void printList(){ // Prints entire list
		Customer temp = head;
		System.out.println(n);
		while (temp != null){
			System.out.println(temp.getCreditCardNumber());
			temp = temp.getNext();
		}
	}
}

