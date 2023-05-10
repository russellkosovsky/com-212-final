package CORE;
import java.io.Serializable;
public class MoviePQ implements Serializable{

	private int n;
	private Movie s [];

	public MoviePQ() { //creates the admin priority queue
		n = 0;
		s = new Movie [255]; //max of 255 movies in database
	}

	//return root
	public Movie findMin(){ //finds the least rated movie
		return s[0];
	}

	public Movie insert(Movie Movie){
		int temp = n;
		s[n++] = Movie;
		while (s[temp].getRottenTomatoesScore() < s[findParent(temp)].getRottenTomatoesScore()) {
			swap(temp, findParent(temp));
			temp = findParent(temp);
		}
		return Movie;
	}

	public Movie deleteMin(){
		Movie temp = s[0];
		s[0] = s[--n];
		deleteCheck(0);
		return temp;
	}
		
		
	private void deleteCheck(int temp){
		if (isLeaf(temp) == false){
			int nextLow = temp;
			if (rightChild(temp) <= n) {
				nextLow = s[leftChild(temp)].getRottenTomatoesScore() < s[rightChild(temp)].getRottenTomatoesScore() ? leftChild(temp):rightChild(temp);
			} else nextLow = leftChild(temp);
			if (s[nextLow] != null){
				if (s[nextLow].getRottenTomatoesScore() < s[temp].getRottenTomatoesScore()) {
					swap(temp, nextLow);
					temp = nextLow;
					deleteCheck(temp);
				}
			}
		}
	}

	private boolean isLeaf(int target) { return (target > n/2); }

	private int leftChild(int findParent) { 
		if (findParent != 0) { 
			return (findParent*2);
		} else return 1;
		}
		
		private int rightChild(int findParent) { 
		if (findParent != 0) { 
			return (findParent*2+1);
		} else return 2;
		}

	//check if empty
	public boolean isEmptySet(){
		if (n == 0){
			return true;
		}
		else{
			return false;
		}
	}

	//swap position of two Movies, return new position of swapped Movie
	private int swap(int one, int two){
		Movie temp = s[one];
		s[one] = s[two];
		s[two] = temp;
		return one;
	}

	//find findParent of Movie
	private int findParent(int x){
		int findParent = (x-1)/2;
		return findParent;
	}

	//creates print method
  public void printPQ() {

      //for item in heap
      for ( int i = 0; i < n; i++) {
          //if i is not null
					if (s[i] != null) {
          //prints the location of i
          System.out.print("Movie: " + s[i].getTitle() + " ID: " + s[i].getUniqueID() + "\n");
          }

        	//otherwise...
        	else {
            //prints null if necessary
            System.out.print("null");
          }
        }
        //prints line
        System.out.println();
    }
}
