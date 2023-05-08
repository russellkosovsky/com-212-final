package CORE;

import java.io.Serializable;

public class MovieBinarySearchTree implements Serializable{

  private Movie root;

  public MovieBinarySearchTree() {
    root = null;
  }

  public void insert(Movie node) {
    root = insert2(root, node);
  }

  private Movie insert2(Movie root, Movie node) {
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

  public void delete(Movie node) {
    root = deleter(root, node.getUniqueID());
  }

  private Movie deleter(Movie root, int key) {
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

  private int minValue(Movie root) {
    int minValue = root.getUniqueID();

    while (root.getLeft() != null) {
      minValue = root.getLeft().getUniqueID();
      root = root.getLeft();
    }

    return minValue;
  }

  public void traverse() {
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

  public void traverseDate() {
    traverseDate2(root);
    System.out.println();
  }

  private void traverseDate2(Movie node) {
    if (node != null) {
      traverseDate2(node.getLeft());
      System.out.print(node.getReleaseDate() + " ");
      traverseDate2(node.getRight());
    }
  }



  public Movie searchBST(int id) { 
    return search2(root,id);
  }

  private Movie search2(Movie root, int id) {
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

  public Movie searchDate(int date) { 
    return searchDate2(root,date);
  }

  private Movie searchDate2(Movie root, int date) {
    if (root == null){
      return null;
    }
    else if (date == root.getReleaseDate()){
      return root;
    }
    else if (date < root.getReleaseDate()){
      return searchDate2(root.getLeft(), date);
    }
    else {
      return searchDate2(root.getRight(), date);
    }
  }


  public void printTree(){
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

  private Movie findParent(Movie p){
    Movie temp = root;

    while (temp.getLeft() != p && temp.getRight() != p){
      if (p.getUniqueID() < temp.getUniqueID()){
        temp = temp.getLeft();
      }
      else{
        temp = temp.getRight();
      }
    }
    return temp;
  }

  private void successor( Movie p){
    Movie temp = p;
    Movie temp2 = temp;

    if (p.getLeft() != null && p.getRight() !=null){
      temp = temp.getRight();
      while (temp.getLeft() != null){
        temp = temp.getLeft();
      }

      if (temp.getLeft() == null && temp.getRight() != null){
        temp2 = temp.getRight();
      }
      else{
        temp2 = temp.getLeft();
      }
      
      if (temp.getLeft() != null){
        if (findParent(temp).getLeft() == temp){
          p.setTitle(temp.getTitle());
          p.setUniqueID(temp.getUniqueID());
          p = temp;
          findParent(temp).setLeft(temp2);
        }
        else {
          p.setTitle(temp.getTitle());
          p.setUniqueID(temp.getUniqueID());
          p = temp;
          findParent(temp).setRight(temp2);
        }
      }
      else{
        if (findParent(temp).getLeft() == temp){
          p.setTitle(temp.getTitle());
          p.setUniqueID(temp.getUniqueID());
          p = temp;
          findParent(temp).setLeft(temp2);
          temp.setRight(null);
        }
        else {
          p.setTitle(temp.getTitle());
          p.setUniqueID(temp.getUniqueID());
          p = temp;
          findParent(temp).setRight(temp2);
        }
      }
    }
  }

  private void deleteRoot(Movie p){
    if (root.getLeft() == null){
      root = root.getRight();
      root.setLeft(root.getLeft());
      root.setRight(root.getRight());
    }
    else {
      successor(root);
    }
  }

  private void delete2(Movie root, Movie p){
    Movie temp = root;
    if (p.getLeft() == null || p.getRight() == null){
      if (p.getLeft() == null){
        temp = p.getRight();
      }
      else {
        temp = p.getLeft();
      }

      if (findParent(p).getLeft() == p){
        findParent(p).setLeft(temp);
      }
      else {
        findParent(p).setRight(temp);
      }
    }
    else if (p.getLeft() == null && p.getRight() == null){
      if (findParent(p).getLeft().getUniqueID() == p.getUniqueID()){
        p.setLeft(null);
      }
      else if (findParent(p).getRight().getUniqueID() == p.getUniqueID()){
        p.setRight(null);
      }
    }
    else {
      successor(p);
    }
  }
  
  public boolean isEmptyTree(){
    if (root == null){
      return true;
    }
    else{
      return false;
    }
  }

  public Movie getRoot() {
    return root;
  }
}

   

