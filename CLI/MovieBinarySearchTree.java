//This program creates the movie archive

//serializes the movie archive
public class MovieBinarySearchTree implements java.io.Serializable{

  private Movie t;
	private Movie root;

	public MovieBinarySearchTree() {
    root = null;
		t = null;
	}

  //adds new movie to the database
  public Movie insert(Movie p) {
    //set root
		if (t == null){
			t = p;
			root = t;
		}
		else {
			insert2(t, p);
		}
		return t;
	}

	private void insert2(Movie t, Movie p){
		//go left
		if (p.getReleaseDate() < t.getReleaseDate()){
			if (t.getLeft() == null){
				t.setLeft(p);
			}
			else{
				insert2(t.getLeft(), p);
			}
		}
		//go right
		else {
			if (t.getRight() == null){
				t.setRight(p);
			}
			else {
				insert2(t.getRight(), p);
			}
		}
  }

  public void traverse(){ //prints the movie archive
		if (t != null){
			traverse2(t.getLeft());
			System.out.println(t.getTitle() + " " + t.getReleaseDate());
			traverse2(t.getRight());
		}
	}

	private void traverse2(Movie t){
		if (t != null){
			traverse2(t.getLeft());
			System.out.println(t.getTitle() + " " + t.getReleaseDate());
			traverse2(t.getRight());
		}
	}

  public Movie searchBST(int date){ //search the movie by the release date
    if (t == null){
      return null;
    }
    //key is found by the release date
    else if (date == t.getReleaseDate()){
      return t;
    }
    //search left for key
    else if (date < t.getReleaseDate()){
      return search2(t.getLeft(), date);
    }
    //search right for key
    else {
      return search2(t.getRight(), date);
    }
  }

  private Movie search2(Movie t, int date){
    if (t == null){
      return null;
    }
    //key is found
    else if (date == t.getReleaseDate()){
      return t;
    }
    //search left for key
    else if (date < t.getReleaseDate()){
      return search2(t.getLeft(), date);
    }
    //search right for key
    else {
      return search2(t.getRight(), date);
    }
  }

  public Movie searchID(int id){ //searches for the movie by its id
    if (t == null){
      return null;
    }
    //key is found
    else if (id == t.getUniqueID()){ //id is found
      return t;
    }
    //search left for key
    else if (id < t.getUniqueID()){
      return searchID2(t.getLeft(), id);
    }
    //search right for key
    else {
      return searchID2(t.getRight(), id);
    }
  }

  private Movie searchID2(Movie t, int id){
    if (t == null){
      return null;
    }
    //key is found
    else if (id == t.getUniqueID()){
      return t;
    }
    //search left for key
    else if (id < t.getUniqueID()){
      return searchID2(t.getLeft(), id);
    }
    //search right for key
    else {
      return searchID2(t.getRight(), id);
    }
  }

  //print all movies in the database
  public void printTree(){
		printMovies2(root);
		System.out.println();
	}

	private void printMovies2(Movie tree) {
		if (tree != null) {
			System.out.print(tree.getReleaseDate() + " ");
			if (tree.getLeft() != null)
				System.out.print("Left: " + tree.getLeft().getReleaseDate() + " ");
			else
				System.out.print("Left: null ");
			if (tree.getRight() != null)
				System.out.println("Right: " + tree.getRight().getReleaseDate() + " ");
			else
				System.out.println("Right: null ");
			printMovies2(tree.getLeft());
			printMovies2(tree.getRight());
		}
	}
  public void delete(Movie p){
    if (isEmptyTree() == false){
      //if root is p, delete
      if (root.getReleaseDate() == p.getReleaseDate()){
        deleteRoot(p);
      }
      //delete other node in bst
      else {
        delete2(root, p);
      }
    }
  }

  //return parent node of delete node
  private Movie findParent(Movie p){
    Movie temp = root;

    //parent is found
    while (temp.getLeft() != p && temp.getRight() != p){
      //key is less than, go left
      if (p.getReleaseDate() < temp.getReleaseDate()){
        temp = temp.getLeft();
      }
      //key is greater than, go right
      else{
        temp = temp.getRight();
      }
    }
    return temp;
  }

  //find successor of deleted node
  private void successor( Movie p){

    Movie temp = p;
    Movie temp2 = temp;
    //two children
    if (p.getLeft() != null && p.getRight() !=null){
      //find smallest value on right
      temp = temp.getRight();
      while (temp.getLeft() != null){
        temp = temp.getLeft();
      }

      //check if successor has children
      if (temp.getLeft() == null && temp.getRight() != null){
        //right child-set temp2 to right child
        temp2 = temp.getRight();

      }
      //left child-set temp2 to right child
      else{
        temp2 = temp.getLeft();
      }
      // if successor child is on the left
      if (temp.getLeft() != null){
        //if successor is on the left of parent
        if (findParent(temp).getLeft() == temp){
          //set parent of successor to child of successor
          p.setTitle(temp.getTitle());
          p.setUniqueID(temp.getUniqueID());
          p = temp;
          findParent(temp).setLeft(temp2);
        }
        //if successor is on the right of parent
        else {
          p.setTitle(temp.getTitle());
          p.setUniqueID(temp.getUniqueID());
          p = temp;
          findParent(temp).setRight(temp2);
        }
      }
      // if successor child is on the right
      else{
        if (findParent(temp).getLeft() == temp){
          p.setTitle(temp.getTitle());
          p.setUniqueID(temp.getUniqueID());
          p = temp;
          findParent(temp).setLeft(temp2);
          temp.setRight(null);
        }
        else {
          //copy data
          p.setTitle(temp.getTitle());
          p.setUniqueID(temp.getUniqueID());
          p = temp;
          findParent(temp).setRight(temp2);
        }
      }
    }
  }

  private void deleteRoot(Movie p){ //deletes the first movie in the archive
    //root has no left subtree
    if (root.getLeft() == null){
      root = root.getRight();
      root.setLeft(root.getLeft());
      root.setRight(root.getRight());
    }
    //root has left node
    else {
      successor(root);
    }
  }

  private void delete2(Movie root, Movie p){
    Movie temp = root;
      //only one child, left/right
      if (p.getLeft() == null || p.getRight() == null){
        if (p.getLeft() == null){
          //successor
          temp = p.getRight();
        }
        else {
          temp = p.getLeft();
        }
        //if deleted node is on the left, set parent to right child
        if (findParent(p).getLeft() == p){
          findParent(p).setLeft(temp);
        }
        //of deleted node is on the right, set parent to left child
        else {
          findParent(p).setRight(temp);
        }
      }
      //no children
      else if (p.getLeft() == null && p.getRight() == null){
        //check if p is left or right of parent
        if (findParent(p).getLeft().getReleaseDate() == p.getReleaseDate()){
          p.setLeft(null);
        }
        else if (findParent(p).getRight().getReleaseDate() == p.getReleaseDate()){
          p.setRight(null);
        }
      }
      //two children
      else {
        successor(p);
      }
  }

  public boolean isEmptyTree(){ //returns if the movie archive is empty or not
    if (t == null){
      return true;
    }
    else{
      return false;
    }
  }
}
