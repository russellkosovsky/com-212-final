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


public class WelcomeGUI extends JFrame {

    public MoviePQ MoviesByScore;
    public MovieBinarySearchTree MoviesByDate;
    public CustomerHashTable Customers;

    public static void main(String[] args) {
        new WelcomeGUI();
    }

    public WelcomeGUI(){

        MoviesByScore = this.loadByScore();
        MoviesByDate = this.loadByDate();
        Customers = this.loadCustomers();

        JPanel panel = new JPanel();
        JFrame frame = new JFrame();
        frame.setSize(300, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("resources/CLOGO3.png"));
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
                new UserLoginGUI(Customers, MoviesByDate, MoviesByScore);
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
                System.out.println(MoviesByDate);
                new AdminLoginGUI(Customers, MoviesByDate, MoviesByScore);
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
            System.out.println("IO Error");
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
}