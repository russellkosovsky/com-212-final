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
    
    public Terminal(){
    
    }
    
    //////////////////  SAVING  ////////////////////////
    
    //function to save the MoviePQ
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
        Terminal menu = new Terminal();
        
        // movie priority queue to store movies by score(for admin)
        MoviePQ MoviesByScore = new MoviePQ();
        // create a movie bst to store them by date (for user)
        MovieBinarySearchTree MoviesByDate = new MovieBinarySearchTree();
        // Create a movie queue that is the users wishlisht
        MovieQueue Wishlist = new MovieQueue();        
        //crteate Hash table to store customers
        CustomerHashTable Customers = new CustomerHashTable(100);

        System.out.println("\nPlease enter either 1, 2, or 3 to continue.");
        System.out.println("\nIf this is the first time using this program, please enter 3!");
        System.out.println("1. Login");
        System.out.println("2. Create Account");
        System.out.println("3. Create database");
        System.out.println("0. Exit");
        int choice = scanner.nextInt(); //users input for choice
        scanner.nextLine(); //clears scanner

        // IF user is returning and its not the first time the program has been run
        // will only work once we do serialization
        // will need it to add a check if its not a valid card
        if (choice == 1){

            //load all info below
            MoviesByScore = menu.loadByScore();
            MoviesByDate = menu.loadByDate();
            Customers = menu.loadCustomers();
            Wishlist = menu.loadWishList();

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
            Movie movie1 = new Movie("The Secret Life of Pets", 20161011, 11111, 75, true);
            Movie movie2 = new Movie("The Dark Knight", 20080114, 22222, 94, true);
            Movie movie3 = new Movie("Jurassic Park", 19931229, 33333, 91, false);
            Movie movie4 = new Movie("The Godfather", 19720817, 44444, 98, true);
            Movie movie5 = new Movie("Inception", 20100527, 55555, 87, true);
            Movie movie6 = new Movie("Avatar", 20090320, 66666, 82, true);
            Movie movie7 = new Movie("The Shawshank Redemption", 19940911, 77777, 91, false);
            Movie movie8 = new Movie("Pulp Fiction", 19940105, 88888, 94, false);
            Movie movie9 = new Movie("Forrest Gump", 19940621, 99999, 72, true);
            Movie movie10 = new Movie("The Matrix", 19991115, 10000, 87, true);

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
            Movie movie1 = new Movie("The Secret Life of Pets", 20161011, 11111, 75, true);
            Movie movie2 = new Movie("The Dark Knight", 20080114, 22222, 94, true);
            Movie movie3 = new Movie("Jurassic Park", 19931229, 33333, 91, false);
            Movie movie4 = new Movie("The Godfather", 19720817, 44444, 98, true);
            Movie movie5 = new Movie("Inception", 20100527, 55555, 87, true);
            Movie movie6 = new Movie("Avatar", 20090320, 66666, 82, true);
            Movie movie7 = new Movie("The Shawshank Redemption", 19940911, 77777, 91, false);
            Movie movie8 = new Movie("Pulp Fiction", 19940105, 88888, 94, false);
            Movie movie9 = new Movie("Forrest Gump", 19940621, 99999, 72, true);
            Movie movie10 = new Movie("The Matrix", 19991115, 10000, 87, true);

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
            
            System.out.println("Please enter a username.");
            String name = scanner.nextLine();
            
            System.out.println("Please enter your email.");
            String email = scanner.nextLine();
            
            System.out.println("Please enter your credit card number.");
            int cardNUM = scanner.nextInt();

            Customer customer = new Customer(name, cardNUM, email, Wishlist);
            Customers.add(customer);

        }
        else if (choice == 0){
            System.out.println("Thank you for using CamelFilms!");
            System.exit(0);
        }
        else {
            System.out.println("Please enter a valid number.");
            System.exit(0);
        }
        
        System.out.println("What is your credit card number to proceed?");
        int card = scanner.nextInt();    
        Customer CurrCustomer = Customers.lookUp(card);
        int choice2 = 1;
        while (choice2 != 0) {
            //asks the user if they are a customer or administrator
            System.out.println("\nGreetings, "+CurrCustomer.getName());
            System.out.println("\nWelcome to CamelFilms!\n");
            System.out.println("To quit this program, type the number '0' and anytime.");
            System.out.println("Are you proceeding as a costumer or administrator?");
            System.out.println("Please enter either 1 or 2 to continue.");
            System.out.println("1. Customer");
            System.out.println("2. Administrator");
  
            choice2 = scanner.nextInt();
            
            
            if (choice2 == 1) { //then the user is a customer
  
                //choices the customer has to choose from
                System.out.println("\nWelcome Customer!\n");
                System.out.println("How may we assist you?");
                System.out.println("1. See your wishlist");
                System.out.println("2. View all movies in order of release date");
                System.out.println("3. Delete movie from your wishlist");
                System.out.println("4. Insert movie into wishlist");
                System.out.println("5. Back to main menu");
  
                int custchoice1 = scanner.nextInt();
  
                if (custchoice1 == 1){ //then the user is able to see their wishlist
                    //CurrCustomer.getWishlist().printQueue();
                    Wishlist.printQueue();
                }
                
                else if (custchoice1 == 2){ //then the user views all movies in order of release date
                    //print all movies in order of release date(BST)
                    System.out.println("Here is all the movies on record in order of release date.");
                    MoviesByDate.traverse(); //list the movies in order of release date
                }
                else if (custchoice1 == 3){ //then the user wants to delete a movie
                    //gets next movie in list for the user to watch
                    //ask user if they would like to delete this movie
                    Movie next = CurrCustomer.getWishlist().front();
                    System.out.println("Removing the next movie in your wishlist: " + next.getTitle());
                    
                    System.out.println("Would you like to delete this movie now?");
                    System.out.println("1. Yes");
                    System.out.println("2. No");
                    int del = scanner.nextInt();
                    if (del == 1){ //if the user would like to delete the movie
                        //searches for the movie by ID in order to delete it
                        CurrCustomer.getWishlist().dequeue();
                    }
                    CurrCustomer.getWishlist().printQueue();
                } 
                else if (custchoice1 == 4){ //then the user wants to insert a movie
                    //displays the movies in the database
                    System.out.println("Here is the list of movies on record:");
                    MoviesByScore.printPQ();
                    System.out.println("\nWhat is the ID of the movie you would like to add?");
                    System.out.println("Please choose from the above list.");
                    int id = scanner.nextInt();
                    Movie insertMovie = MoviesByDate.searchID(id); 
                    System.out.println("Adding: ("+insertMovie+") to your wishlist.");
                    CurrCustomer.getWishlist().enqueue(insertMovie);
                    CurrCustomer.getWishlist().printQueue(); //prints the new wishlist
                }
            }
            
            else if (choice2 == 2){ //then the user is an administrator
                //the program displays the choices for the administrator
                    System.out.println("\nWelcome Administrator!\n");
                    System.out.println("How may we assist you?");
                    System.out.println("1. See the least rated movie");
                    System.out.println("2. Remove the least rated movie");
                    System.out.println("3. Add new movie");
                    System.out.println("4. Deleter a movie by ID");
                    System.out.println("5. Back to main menu");
  
                    int adminchoice = scanner.nextInt();
  
                    if (adminchoice == 1){ //then the admin wants to see the least rated movie
                        //find min in priority queue
                        Movie min = MoviesByScore.findMin();
                        System.out.println("\nLeast Rated Movie: " + min.getTitle() + " Score: " + min.getRottenTomatoesScore());
                    }
                        
                    else if (adminchoice == 2){ //then the admin wants to delete the least rated movie
                        //delete min in priority queue
                        MoviesByScore.deleteMin();
                        System.out.println("\nThe least rated movie has been deleted.");
                        System.out.println("Here is a list of the remaining movies:");
                        MoviesByScore.printPQ(); //prints the updated list of movies
                    }
                    
                    else if (adminchoice == 3){ //then the admin wants to add a new movie
                        //insert function of priotity queue
                        //asks the admin for the movie's information so it can be added
                        System.out.println("\nWhat is the title of the movie you would like to add?");
                        String title = scanner.nextLine();
                        System.out.println("What is this movie's release date? Give year month and day. (ex.20230101)");
                        int date = scanner.nextInt();
                        System.out.println("What is this movie's ID code? (ex.11111)");
                        int id = scanner.nextInt();
                        System.out.println("What is the rotten tomato score? (ex.99)");
                        int score = scanner.nextInt();
  
                        Movie movieName = new Movie(title, date, id, score, true);
                        MoviesByDate.insert(movieName); //inserts the movie
                        MoviesByScore.insert(movieName);
                        System.out.println("\nHere is the updated admin movie list:");
                        MoviesByScore.printPQ(); //prints the updated movie list for the admin
                        System.out.println("\nHere is the updated user movie list:");
                        MoviesByDate.traverse(); //prints the updated movie list for the user

                    }

                    else if (adminchoice == 4){ //then the admin wants to delete a movie by ID
                        //asks the admin for the ID of the movie they want to delete
                        System.out.println("\nWhat is the ID of the movie you would like to delete?");
                        int id = scanner.nextInt();
                        Movie deleteMovie = MoviesByDate.searchID(id);
                        MoviesByDate.delete(deleteMovie);
                        System.out.println("\nHere is the updated movie list:");
                        MoviesByDate.traverse(); //prints the updated movie list for the admin
                    }
                }
            }
  
        //closing goodbye for the user once they want to exit
        System.out.println("Have a great day and come again soon.\n");
  
        //saves all the information from the other data structures
        menu.saveByScore(MoviesByScore);
        menu.saveByDate(MoviesByDate);
        menu.saveWishList(Wishlist);
        menu.saveCustomers(Customers);
        
    }   
}
        
        
