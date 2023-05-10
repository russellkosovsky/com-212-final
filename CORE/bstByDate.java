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
    else {
    insertRec(root, node);
  }

  }

  private void insertRec(Movie latestRoot, Movie node){

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

  public void dateDelete(Movie node) {
    root = dateDeleteR(root, node.getReleaseDate());
  }

  private Movie dateDeleteR(Movie root, int key) {
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

  private int dateMinValue(Movie root) {
    int dateMinValue = root.getReleaseDate();

    while (root.getLeftDate() != null) {
      dateMinValue = root.getLeftDate().getReleaseDate();
      root = root.getLeftDate();
    }

    return dateMinValue;
  }

  public void traverseByDate() {
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
      return searchDate2(root.getLeftDate(), date);
    }
    else {
      return searchDate2(root.getRightDate(), date);
    }
  }


  public void printDateTree(){
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

  private Movie dateFindParent(Movie p){
    Movie temp = root;

    while (temp.getLeftDate() != p && temp.getRightDate() != p){
      if (p.getReleaseDate() < temp.getReleaseDate()){
        temp = temp.getLeftDate();
      }
      else{
        temp = temp.getRightDate();
      }
    }
    return temp;
  }

  private void successor( Movie p){
    Movie temp = p;
    Movie temp2 = temp;

    if (p.getLeftDate() != null && p.getRightDate() !=null){
      temp = temp.getRightDate();
      while (temp.getLeftDate() != null){
        temp = temp.getLeftDate();
      }

      if (temp.getLeftDate() == null && temp.getRightDate() != null){
        temp2 = temp.getRightDate();
      }
      else{
        temp2 = temp.getLeftDate();
      }
      
      if (temp.getLeftDate() != null){
        if (dateFindParent(temp).getLeftDate() == temp){
          p.setTitle(temp.getTitle());
          p.setReleaseDate(temp.getReleaseDate());
          p = temp;
          dateFindParent(temp).setLeftDate(temp2);
        }
        else {
          p.setTitle(temp.getTitle());
          p.setReleaseDate(temp.getReleaseDate());
          p = temp;
          dateFindParent(temp).setRightDate(temp2);
        }
      }
      else{
        if (dateFindParent(temp).getLeftDate() == temp){
          p.setTitle(temp.getTitle());
          p.setReleaseDate(temp.getReleaseDate());
          p = temp;
          dateFindParent(temp).setLeftDate(temp2);
          temp.setRightDate(null);
        }
        else {
          p.setTitle(temp.getTitle());
          p.setReleaseDate(temp.getReleaseDate());
          p = temp;
          dateFindParent(temp).setRightDate(temp2);
        }
      }
    }
  }

  private void deleteRoot(Movie p){
    if (root.getLeftDate() == null){
      root = root.getRightDate();
      root.setLeftDate(root.getLeftDate());
      root.setRightDate(root.getRightDate());
    }
    else {
      successor(root);
    }
  }

  private void delete2(Movie root, Movie p){
    Movie temp = root;
    if (p.getLeftDate() == null || p.getRightDate() == null){
      if (p.getLeftDate() == null){
        temp = p.getRightDate();
      }
      else {
        temp = p.getLeftDate();
      }

      if (dateFindParent(p).getLeftDate() == p){
        dateFindParent(p).setLeftDate(temp);
      }
      else {
        dateFindParent(p).setRightDate(temp);
      }
    }
    else if (p.getLeftDate() == null && p.getRightDate() == null){
      if (dateFindParent(p).getLeftDate().getReleaseDate() == p.getReleaseDate()){
        p.setLeftDate(null);
      }
      else if (dateFindParent(p).getRightDate().getReleaseDate() == p.getReleaseDate()){
        p.setRightDate(null);
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

   

