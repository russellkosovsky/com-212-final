package CORE;

import java.io.Serializable;

// Computer Science Data Structures Final Project
// By Russell, Brooke, Jay, and Miles
// BST used to sort movies by date

public class bstByDate implements Serializable{

  private Movie root;

  public bstByDate() { // Constructor
    root = null;
  }


public void dateInsert(Movie node){ // Inserts movie by date into BST

    if ( root == null ) {
      root = node;
      return;
    }
    else {
    insertRec(root, node);
  }

  }

  private void insertRec(Movie latestRoot, Movie node){ // Recursive insert by date

    if ( latestRoot.getReleaseDate() > node.getReleaseDate()){

      if ( latestRoot.getLeftDate() == null ){
        latestRoot.setLeftDate(node);
        return;
      }
      else{
        insertRec(latestRoot.getLeftDate(), node);
      }
    }
    else{
      if (latestRoot.getRightDate() == null){
        latestRoot.setRightDate(node);
        return;
      }
      else{
        insertRec(latestRoot.getRightDate(), node);
      }
    }
  }

  public void dateDelete(Movie node) { // Delete BST function
    root = dateDeleteR(root, node.getReleaseDate());
  }

  private Movie dateDeleteR(Movie root, int key) { // Recursive BST delete functions
    if (root == null) {
      return root;
    }

    if (key < root.getReleaseDate()) {
      root.setLeftDate(dateDeleteR(root.getLeftDate(), key));
    } else if (key > root.getReleaseDate()) {
      root.setRightDate(dateDeleteR(root.getRightDate(), key));
    } else {
      if (root.getLeftDate() == null) {
        return root.getRightDate();
      } else if (root.getRightDate() == null) {
        return root.getLeftDate();
      }

      root.setReleaseDate(dateMinValue(root.getRightDate()));
      root.setRightDate(dateDeleteR(root.getRightDate(), root.getReleaseDate()));
    }

    return root;
  }

  private int dateMinValue(Movie root) { // finds min date value for delete function
    int dateMinValue = root.getReleaseDate();

    while (root.getLeftDate() != null) {
      dateMinValue = root.getLeftDate().getReleaseDate();
      root = root.getLeftDate();
    }

    return dateMinValue;
  }

  public void traverseByDate() { // traverse tree and print
    traverseByDate2(root);
    System.out.println();
  }

  private void traverseByDate2(Movie node) {
    if (node != null) {
      traverseByDate2(node.getLeftDate());
      System.out.print("Title: " + node.getTitle() + ", ID: " + node.getUniqueID() + ", Score: " + node.getRottenTomatoesScore() + ", Relese Date: " + node.getReleaseDate() + ", Available: " + node.Availablility() + "\n");
      traverseByDate2(node.getRightDate());
    }
    else {
        return;
    }
  }

  public Movie searchDate(int date) { // search function
    return searchDate2(root,date);
  }

  private Movie searchDate2(Movie root, int date) { // recursive part of search function
    if (root == null){
      return null;
    }
    else if (date == root.getReleaseDate()){
      return root;
    }
    else if (date < root.getReleaseDate()){
      return searchDate2(root.getLeftDate(), date);
    }
    else {
      return searchDate2(root.getRightDate(), date);
    }
  }


  public void printDateTree(){ // prints tree
    printDateTree2(root);
    System.out.println();
  }

  private void printDateTree2(Movie tree) {
    if (tree != null) {
      System.out.print(tree.getReleaseDate() + " ");
      if (tree.getLeftDate() != null)
        System.out.print("Left: " + tree.getLeftDate().getReleaseDate() + " ");
      else
        System.out.print("Left: null ");
      if (tree.getRightDate() != null)
        System.out.println("Right: " + tree.getRightDate().getReleaseDate() + " ");
      else
      System.out.println("Right: null ");
      printDateTree2(tree.getLeftDate());
      printDateTree2(tree.getRightDate());
    }
  }
  
  public boolean isEmptyTree(){ // returns tree empty T/F
    if (root == null){
      return true;
    }
    else{
      return false;
    }
  }

  public Movie getRoot() { // returns tree root
    return root;
  }
}

   

