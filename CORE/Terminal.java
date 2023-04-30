// Command Line Interface
//

import java.io.*;
import java.util.Scanner;
import java.util.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Terminal implements java.io.Serializable{
    
    public MoviePQ MoviesByScore;
    public MovieBinarySearchTree MoviesByDate;
    public MovieQueue Wishlist;
    public CustomerHashTable Customers;
    
    public Terminal(){
    }
    
    //////////////////  SAVING  ////////////////////////
    
    //function to save the AdminPG
    public void saveByScore(MoviePQ MoviesByScore){
        try {
            FileOutputStream fileOut = new FileOutputStream("MoviePQ.txt");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(MoviesByScore);
            out.close();
            fileOut.close();
            System.out.println("Serialized object successfully in MoviePQ.txt");
        } 
        catch(IOException i) {
        i.printStackTrace();
        }
    }
    //function to save the MoviesByDate database
    public void saveByDate(MovieBinarySearchTree MoviesByDate){
        try {
            FileOutputStream fileOut = new FileOutputStream("MoviesByDate.txt");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(MoviesByDate);
            out.close();
            fileOut.close();
            System.out.println("Serialized object successfully in MoviesByDate.txt");
        } 
        catch(IOException i) {
            i.printStackTrace();
        }
    }
    //function to save each user's wish list
    public void saveWishList(MovieQueue Wishlist){
        try {
            FileOutputStream fileOut = new FileOutputStream("WishList.txt");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(Wishlist);
            out.close();
            fileOut.close();
            System.out.println("Serialized object successfully in WishList.txt");

        }
        catch(IOException i) {
            i.printStackTrace();
        }
    }
    //function to save the customers that have created an account
    public void saveCustomers(CustomerHashTable Customers){
        try {
            FileOutputStream fileOut = new FileOutputStream("Customers.txt");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(Customers);
            out.close();
            fileOut.close();
            System.out.println("Serialized object successfully in Customers.txt");
        } 
        catch(IOException i) {
            i.printStackTrace();
        }
    }
    
    //////////////////  LOADING  ////////////////////////
    
    //function to load the AdminPG so that we have the necessary info
    public MoviePQ loadByScore(){
        MoviePQ MoviesByScore = null;
        try{
            FileInputStream fileIn = new FileInputStream("MoviePQ.txt");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            MoviesByScore = (MoviePQ) in.readObject();
            in.close();
            fileIn.close();
            return MoviesByScore;
        }
        catch(IOException i){
            i.printStackTrace();
            return null;
        }
        catch(ClassNotFoundException v){
            System.out.println("MoviePQ class not found");
            v.printStackTrace();
            return null;
        }
    }
    //function to load the movies in the database for the user
    public MovieBinarySearchTree loadByDate(){
        MovieBinarySearchTree MoviesByDate = null;
        try{
            FileInputStream fileIn = new FileInputStream("MoviesByDate.txt");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            MoviesByDate = (MovieBinarySearchTree) in.readObject();
            in.close();
            fileIn.close();
            return MoviesByDate;
        }
        catch(IOException i){
            i.printStackTrace();
            return null;
        }
        catch(ClassNotFoundException v){
            System.out.println("MoviesByDate class not found");
            v.printStackTrace();
            return null;
        }
    }
    //function to load the wishlist in the database for the user
    public MovieQueue loadWishList(){
        MovieQueue Wishlist = null;
        try{
            FileInputStream fileIn = new FileInputStream("WishList.txt");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Wishlist = (MovieQueue) in.readObject();
            in.close();
            fileIn.close();
            return Wishlist;
        }
        catch(IOException i){
            i.printStackTrace();
            return null;
        }
        catch(ClassNotFoundException v){
            System.out.println("WishList class not found");
            v.printStackTrace();
            return null;
        }
    }
    //funcion to load the customers within the database that have created an account
    public CustomerHashTable loadCustomers(){
        CustomerHashTable Customers = null;
        try{
            FileInputStream fileIn = new FileInputStream("Customers.txt");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Customers = (CustomerHashTable) in.readObject();
            in.close();
            fileIn.close();
            return Customers;
        }
        catch(IOException i){
            i.printStackTrace();
            return null;
        }
        catch(ClassNotFoundException v){
            System.out.println("Customers class not found");
            v.printStackTrace();
            return null;
        }
    }

    //////////////////  MAIN  ////////////////////////
    
    public static void main(String[] args){
        
        //creates scanner
        Scanner scanner = new Scanner(System.in);
        int choice;
        int choice1;
        
        Terminal menu = new Terminal();

        System.out.println("\nPlease enter either 1, 2, or 3 to continue.");
        System.out.println("\nIf this is the first time using this program, please enter 3!");
        System.out.println("1. Login");
        System.out.println("2. Create Account");
        System.out.println("3. Create database");
        choice = scanner.nextInt();
        
        // movie priority queue to store movies by score(for admin)
        MoviePQ MoviesByScore = new MoviePQ();
        
        // create a movie bst to store them by date (for user)
        MovieBinarySearchTree MoviesByDate = new MovieBinarySearchTree();
        
        // Create a movie queue that is the users wishlisht
        MovieQueue Wishlist = new MovieQueue();
        
        //crteate Hash table to store customers
        CustomerHashTable Customers = new CustomerHashTable(100);
        
        // IF user is returning and its not the first time the program has been run
        // will only work once we do serialization
        // will need it to add a check if its not a valid card
        if (choice == 1){

            System.out.println("Welcome Back!");
            //the program remebers existing users by their credit card number
            System.out.println("Enter credit card number.");
            int card = scanner.nextInt(); //users input for credit card
            Customer customer = Customers.lookUp(card);
            System.out.println(customer);
        }

        //if user is a new customer and its not the first time the program has been run
        else if (choice == 2){
        
            //load all info below
            MoviesByScore = menu.loadByScore();
            MoviesByDate = menu.loadByDate();
            Customers = menu.loadCustomers();
            
            MoviesByScore = new MoviePQ();
            MoviesByDate = new MovieBinarySearchTree();
            Wishlist = new MovieQueue();

            //creates movies for the user to begin with
            MoviesByScore.setMovie(MoviesByDate);
            Movie movie1 = new Movie("The Secret Life of Pets", 20161011, 00001, 75, true);
            Movie movie2 = new Movie("The Dark Knight", 20080114, 00002, 94, true);
            Movie movie3 = new Movie("Jurassic Park", 19931229, 00003, 91, false);
            Movie movie4 = new Movie("The Godfather", 19720817, 00004, 98, true);
            Movie movie5 = new Movie("Inception", 20100527, 00005, 87, true);
            Movie movie6 = new Movie("Avatar", 20090320, 00006, 82, true);
            Movie movie7 = new Movie("The Shawshank Redemption", 19940911, 00007, 91, false);
            Movie movie8 = new Movie("Pulp Fiction", 19940105, 10008, 94, false);
            Movie movie9 = new Movie("Forrest Gump", 19940621, 10009, 72, true);
            Movie movie10 = new Movie("The Matrix", 19991115, 00010, 87, true);

            //inserts movies for the admin
            MoviesByScore.insert(movie1);
            MoviesByScore.insert(movie2);
            MoviesByScore.insert(movie3);
            MoviesByScore.insert(movie4);
            MoviesByScore.insert(movie5);
            MoviesByScore.insert(movie6);
            MoviesByScore.insert(movie7);
            MoviesByScore.insert(movie8);
            MoviesByScore.insert(movie9);
            MoviesByScore.insert(movie10);
            //insertmovies for user
            MoviesByDate.insert(movie1);
            MoviesByDate.insert(movie2);
            MoviesByDate.insert(movie3);
            MoviesByDate.insert(movie3);
            MoviesByDate.insert(movie4);
            MoviesByDate.insert(movie5);
            MoviesByDate.insert(movie6);
            MoviesByDate.insert(movie7); 
            MoviesByDate.insert(movie8);
            MoviesByDate.insert(movie9);
            MoviesByDate.insert(movie10);
            //insert 3 movies to users wishlist by default
            Wishlist.enqueue(movie2);
            Wishlist.enqueue(movie5);
            Wishlist.enqueue(movie10);
            Wishlist.enqueue(movie7);

            //asks new user for their information to create their account
            System.out.println("Welcome To The Camel Film Database!");
            System.out.println("Please create a username.");
            String name = scanner.nextLine();
            
            System.out.println("Please enter your email.");
            String email = scanner.nextLine();
            
            System.out.println("Please enter your credit card number.");
            int card = scanner.nextInt();

            Customer customer = new Customer(name, card, email, Wishlist);
            Customers.add(customer);
        }
        
        else if (choice == 3){
            
            MoviesByScore = new MoviePQ();
            MoviesByDate = new MovieBinarySearchTree();
            Wishlist = new MovieQueue();
            Customers = new CustomerHashTable(100);

            //creates some sample movies for the user
            MoviesByScore.setMovie(MoviesByDate);
            Movie movie1 = new Movie("The Secret Life of Pets", 20161011, 00001, 75, true);
            Movie movie2 = new Movie("The Dark Knight", 20080114, 00002, 94, true);
            Movie movie3 = new Movie("Jurassic Park", 19931229, 00003, 91, false);
            Movie movie4 = new Movie("The Godfather", 19720817, 00004, 98, true);
            Movie movie5 = new Movie("Inception", 20100527, 00005, 87, true);
            Movie movie6 = new Movie("Avatar", 20090320, 00006, 82, true);
            Movie movie7 = new Movie("The Shawshank Redemption", 19940911, 00007, 91, false);
            Movie movie8 = new Movie("Pulp Fiction", 19940105, 10008, 94, false);
            Movie movie9 = new Movie("Forrest Gump", 19940621, 10009, 72, true);
            Movie movie10 = new Movie("The Matrix", 19991115, 00010, 87, true);

            //inserts movies for the admin
            MoviesByScore.insert(movie1);
            MoviesByScore.insert(movie2);
            MoviesByScore.insert(movie3);
            MoviesByScore.insert(movie4);
            MoviesByScore.insert(movie5);
            MoviesByScore.insert(movie6);
            MoviesByScore.insert(movie7);
            MoviesByScore.insert(movie8);
            MoviesByScore.insert(movie9);
            MoviesByScore.insert(movie10);
            //insertmovies for user
            MoviesByDate.insert(movie1);
            MoviesByDate.insert(movie2);
            MoviesByDate.insert(movie3);
            MoviesByDate.insert(movie3);
            MoviesByDate.insert(movie4);
            MoviesByDate.insert(movie5);
            MoviesByDate.insert(movie6);
            MoviesByDate.insert(movie7); 
            MoviesByDate.insert(movie8);
            MoviesByDate.insert(movie9);
            MoviesByDate.insert(movie10);
            //insert 3 movies to users wishlist by default
            Wishlist.enqueue(movie2);
            Wishlist.enqueue(movie5);
            Wishlist.enqueue(movie10);
            Wishlist.enqueue(movie7);

            //asks new user for their information to create their account
            System.out.println("Welcome To The Camel Film Database!");
            System.out.println("Please create a username.");
            String name = scanner.nextLine();
            
            System.out.println("Please enter your email.");
            String email = scanner.nextLine();
            
            System.out.println("Please enter your credit card number.");
            int cardNUM = scanner.nextInt();

            Customer customer = new Customer(name, cardNUM, email, Wishlist);
            Customers.add(customer);


            System.out.println("What is your credit card number to proceed?");

            int card = scanner.nextInt();

            Customer CurrCustomer = Customers.lookUp(card);
        }
    }
}
