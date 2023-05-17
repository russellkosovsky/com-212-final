package CORE;

import java.io.Serializable;

// Computer Science Data Structures Final Project
// By Russell, Brooke, Jay, and Miles
//BST Tree used for sorting movies by ID in average O(ln(n)) time with worst case of O(n)

public class MovieBinarySearchTree implements Serializable{

  private Movie root;

  public MovieBinarySearchTree() { // Constructor
    root = null;
  }

  public void insert(Movie node) { // Insert function for BST
    root = insert2(root, node);
  }

  private Movie insert2(Movie root, Movie node) { // Recursive insert to insert movie at proper location
    if (root == null) {
      root = node;
      return root;
    }

    if (node.getUniqueID() < root.getUniqueID()) {
      root.setLeft(insert2(root.getLeft(), node));
    } else if (node.getUniqueID() > root.getUniqueID()) {
      root.setRight(insert2(root.getRight(), node));
    }

    return root;
  }

  public void delete(Movie node) { // Delete function of BST
    root = deleter(root, node.getUniqueID());
  }

  private Movie deleter(Movie root, int key) { // Recursive delete to delete movie and right subtree to replace if needed
    if (root == null) {
      return root;
    }

    if (key < root.getUniqueID()) {
      root.setLeft(deleter(root.getLeft(), key));
    } else if (key > root.getUniqueID()) {
      root.setRight(deleter(root.getRight(), key));
    } else {
      if (root.getLeft() == null) {
        return root.getRight();
      } else if (root.getRight() == null) {
        return root.getLeft();
      }

      root.setUniqueID(minValue(root.getRight()));
      root.setRight(deleter(root.getRight(), root.getUniqueID()));
    }

    return root;
  }

  private int minValue(Movie root) { // Finds the minimum value of a given subtree, used for deletions
    int minValue = root.getUniqueID();

    while (root.getLeft() != null) {
      minValue = root.getLeft().getUniqueID();
      root = root.getLeft();
    }

    return minValue;
  }

  public void traverse() { // Traverses and prints BST
    traverse2(root);
    System.out.println();
  }

  private void traverse2(Movie node) {
    if (node != null) {
      traverse2(node.getLeft());
      System.out.print(node.getUniqueID() + " ");
      traverse2(node.getRight());
    }
  }

  public Movie searchBST(int id) { // Search function for BST
    return search2(root,id);
  }

  private Movie search2(Movie root, int id) { // Recursive search function for BST
    if (root == null){
      return null;
    }
    else if (id == root.getUniqueID()){
      return root;
    }
    else if (id < root.getUniqueID()){
      return search2(root.getLeft(), id);
    }
    else {
      return search2(root.getRight(), id);
    }
  }

  public void printTree(){ // Prints BST 
    printMovies2(root);
    System.out.println();
  }

  private void printMovies2(Movie tree) {
    if (tree != null) {
      System.out.print(tree.getUniqueID() + " ");
      if (tree.getLeft() != null)
        System.out.print("Left: " + tree.getLeft().getUniqueID() + " ");
      else
        System.out.print("Left: null ");
      if (tree.getRight() != null)
        System.out.println("Right: " + tree.getRight().getUniqueID() + " ");
      else
        System.out.println("Right: null ");
      printMovies2(tree.getLeft());
      printMovies2(tree.getRight());
    }
  }
  
  public boolean isEmptyTree(){ // Returns if tree is empty
    if (root == null){
      return true;
    }
    else{
      return false;
    }
  }

  public Movie getRoot() { // Returns root of trees
    return root;
  }
}

   

