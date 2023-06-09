import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import CORE.*;
import java.awt.event.ActionListener;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.Image;
import java.io.*;

// Computer Science Data Structures Final Project
// By Jay, Russell, Brooke, and Miles
// Welcome GUI to seperate users and admins, also saves and loads data and passes it to other GUI windows

public class WelcomeGUI extends JFrame {

    public MoviePQ MoviesByScore;
    public MovieBinarySearchTree MoviesByID;
    public bstByDate MoviesByDate;
    public CustomerHashTable Customers;

    public static void main(String[] args) { // Main starts GUIs
        new WelcomeGUI();
    }

    public WelcomeGUI(){ // Constructor
        MoviesByScore = loadByScore();
        MoviesByID = loadByID();
        Customers = loadCustomers();
        MoviesByDate = loadByDate();

        JPanel panel = new JPanel();
        JFrame frame = new JFrame();
        frame.setSize(300, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("resources/CLOGO3.png")); // Logo!
        } catch (IOException e) {
            e.printStackTrace();
        }

        Image dimg = img.getScaledInstance(200, 200, Image.SCALE_SMOOTH);

        ImageIcon imageIcon = new ImageIcon(dimg);
        JLabel imageLabel = new JLabel(imageIcon);
        imageLabel.setBounds(0,0,50,50);
        c.fill = GridBagConstraints.NONE;
        c.insets = new Insets(5, 5, 5, 5);
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        panel.add(imageLabel, c);

        JButton loginBTN = new JButton("User Login");
        loginBTN.setBounds(0, 0, 80, 25);
        loginBTN.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println(Customers);
                System.out.println(MoviesByID);
                System.out.println(MoviesByScore);
                new UserLoginGUI(Customers, MoviesByID, MoviesByScore, MoviesByDate);
                frame.setVisible(false);
            }
        });
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        panel.add(loginBTN, c);

        JButton adminBTN = new JButton("Admin Login");
        adminBTN.setBounds(0, 0, 80, 25);
        adminBTN.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println(MoviesByID);
                System.out.println(Customers);
                System.out.println(MoviesByScore);
                new AdminLoginGUI(Customers, MoviesByID, MoviesByScore, MoviesByDate);
                frame.setVisible(false);
            }
        });
        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 1;
        panel.add(adminBTN, c);

        frame.setVisible(true);
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
    //function to save the MoviesByID database
    public void saveByID(MovieBinarySearchTree MoviesByID){
        try {
            FileOutputStream fileOut = new FileOutputStream("MoviesByID.txt");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(MoviesByID);
            out.close();
            fileOut.close();
            System.out.println("Serialized object successfully in MoviesByID.txt");
        } 
        catch(IOException i) {
            i.printStackTrace();
        }
    }

    //function to save the MoviesByDate database
    public void saveByDate(bstByDate MoviesByDate){
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
            System.out.println("IO Error");
            i.printStackTrace();
        }
    }
    
    //////////////////  LOADING  ////////////////////////
    
    //function to load the AdminPG so that we have the necessary info
    public MoviePQ loadByScore(){
        MoviePQ MoviesByScoreL = null;
        try{
            FileInputStream fileIn = new FileInputStream("MoviePQ.txt");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            MoviesByScoreL = (MoviePQ) in.readObject();
            if (MoviesByScoreL == null){
                MoviesByScoreL = new MoviePQ();
            }
            in.close();
            fileIn.close();
            return MoviesByScoreL;
        }
        catch(IOException i){
            MoviesByScoreL = new MoviePQ();
            return MoviesByScoreL;
        }
        catch(ClassNotFoundException v){
            System.out.println("MoviePQ class not found");
            v.printStackTrace();
             return null;
        }
    }
    //function to load the movies in the database for the user
    public MovieBinarySearchTree loadByID(){
        MovieBinarySearchTree MoviesByIDL = null;
        try{
            FileInputStream fileIn = new FileInputStream("MoviesByID.txt");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            MoviesByIDL = (MovieBinarySearchTree) in.readObject();
            if (MoviesByIDL == null){
                MoviesByIDL = new MovieBinarySearchTree();
            }
            in.close();
            fileIn.close();
            return MoviesByIDL;
        }
        catch(IOException i){
            MoviesByIDL = new MovieBinarySearchTree();
            return MoviesByIDL;
        }
        catch(ClassNotFoundException v){
            System.out.println("MoviesByID class not found");
            v.printStackTrace();
            return null;
        }
    }
    // Loads BST by date
    public bstByDate loadByDate(){
        bstByDate MoviesByDateL = null;
        try{
            FileInputStream fileIn = new FileInputStream("MoviesByDate.txt");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            MoviesByDateL = (bstByDate) in.readObject();
            if (MoviesByDateL == null){
                MoviesByDateL = new bstByDate();
            }
            in.close();
            fileIn.close();
            return MoviesByDateL;
        }
        catch(IOException i){
            MoviesByDateL = new bstByDate();
            return MoviesByDateL;
        }
        catch(ClassNotFoundException v){
            System.out.println("MoviesByDate class not found");
            v.printStackTrace();
            return null;
        }
    }

    //funcion to load the customers within the database that have created an account
    public CustomerHashTable loadCustomers(){
        CustomerHashTable CustomersL = null;
        try{
            FileInputStream fileIn = new FileInputStream("Customers.txt");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            CustomersL = (CustomerHashTable) in.readObject();
            if (CustomersL == null){
                CustomersL = new CustomerHashTable(128);
            }
            in.close();
            fileIn.close();
            System.out.println(CustomersL);
            return CustomersL;
        }
        catch(IOException i){
            CustomersL = new CustomerHashTable(128);
            return CustomersL;
        }
        catch(ClassNotFoundException v){
            System.out.println("Customers class not found");
            v.printStackTrace();
            return null;
        }
    }
}
