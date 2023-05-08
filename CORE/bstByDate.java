package CORE;

import java.io.Serializable;

public class bstByDate implements Serializable{

  private Movie root;

  public bstByDate() {
    root = null;
  }


public void dateInsert(Movie node){

    if ( root == null ) {
      root = node;
      return;
    }

    insertRec(root, node);

  }

  private void insertRec(Movie latestRoot, Movie node){

    if ( latestRoot.getReleaseDate() > node.getReleaseDate()){

      if ( latestRoot.getLeft() == null ){
        latestRoot.setLeft(node);
        return;
      }
      else{
        insertRec(latestRoot.getLeft(), node);
      }
    }
    else{
      if (latestRoot.getRight() == null){
        latestRoot.setRight(node);
        return;
      }
      else{
        insertRec(latestRoot.getRight(), node);
      }
    }
  }

  public void dateDelete(Movie node) {
    root = dateDeleteR(root, node.getReleaseDate());
  }

  private Movie dateDeleteR(Movie root, int key) {
    if (root == null) {
      return root;
    }

    if (key < root.getReleaseDate()) {
      root.setLeft(dateDeleteR(root.getLeft(), key));
    } else if (key > root.getReleaseDate()) {
      root.setRight(dateDeleteR(root.getRight(), key));
    } else {
      if (root.getLeft() == null) {
        return root.getRight();
      } else if (root.getRight() == null) {
        return root.getLeft();
      }

      root.setReleaseDate(dateMinValue(root.getRight()));
      root.setRight(dateDeleteR(root.getRight(), root.getReleaseDate()));
    }

    return root;
  }

  private int dateMinValue(Movie root) {
    int dateMinValue = root.getReleaseDate();

    while (root.getLeft() != null) {
      dateMinValue = root.getLeft().getReleaseDate();
      root = root.getLeft();
    }

    return dateMinValue;
  }

  public void traverseByDate() {
    traverseByDate2(root);
    System.out.println();
  }

  private void traverseByDate2(Movie node) {
    if (node == null) {
        return;
        } else {
      traverseByDate2(node.getLeft());
      System.out.print("Title: " + node.getTitle() + ", ID: " + node.getUniqueID() + ", Score: " + node.getRottenTomatoesScore() + ", Relese Date: " + node.getReleaseDate() + ", Available: " + node.Availablility() + "\n");
      traverseByDate2(node.getRight());
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


  public void printDateTree(){
    printDateTree2(root);
    System.out.println();
  }

  private void printDateTree2(Movie tree) {
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
      printDateTree2(tree.getLeft());
      printDateTree2(tree.getRight());
    }
  }

  private Movie dateFindParent(Movie p){
    Movie temp = root;

    while (temp.getLeft() != p && temp.getRight() != p){
      if (p.getReleaseDate() < temp.getReleaseDate()){
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
        if (dateFindParent(temp).getLeft() == temp){
          p.setTitle(temp.getTitle());
          p.setReleaseDate(temp.getReleaseDate());
          p = temp;
          dateFindParent(temp).setLeft(temp2);
        }
        else {
          p.setTitle(temp.getTitle());
          p.setReleaseDate(temp.getReleaseDate());
          p = temp;
          dateFindParent(temp).setRight(temp2);
        }
      }
      else{
        if (dateFindParent(temp).getLeft() == temp){
          p.setTitle(temp.getTitle());
          p.setReleaseDate(temp.getReleaseDate());
          p = temp;
          dateFindParent(temp).setLeft(temp2);
          temp.setRight(null);
        }
        else {
          p.setTitle(temp.getTitle());
          p.setReleaseDate(temp.getReleaseDate());
          p = temp;
          dateFindParent(temp).setRight(temp2);
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

      if (dateFindParent(p).getLeft() == p){
        dateFindParent(p).setLeft(temp);
      }
      else {
        dateFindParent(p).setRight(temp);
      }
    }
    else if (p.getLeft() == null && p.getRight() == null){
      if (dateFindParent(p).getLeft().getReleaseDate() == p.getReleaseDate()){
        p.setLeft(null);
      }
      else if (dateFindParent(p).getRight().getReleaseDate() == p.getReleaseDate()){
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

   

