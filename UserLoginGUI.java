// Computer Science Data Structures Final Project
// By Jay, Russell, Brooke, and Miles
// User login GUI, if user not found prompts user to create new profile


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.*;

import CORE.*;

import javax.swing.JPasswordField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JMenu;

public class UserLoginGUI extends JFrame implements ActionListener {

    private static JMenuBar menuBar;
    private static JMenu menu;
    private static JLabel userLabel;
    private static JTextField userText;
    private static JLabel passwordLabel;
    private static JPasswordField passwordText;
    private static JButton loginBTN;
    private static JMenuItem newUserMenu;
    private MoviePQ MoviesByScore;
    private CustomerHashTable Customers;
    private MovieBinarySearchTree MoviesByID;
    private bstByDate MoviesByDate;
    

    public UserLoginGUI(CustomerHashTable Customers1, MovieBinarySearchTree MoviesByID, MoviePQ MoviesByScore1, bstByDate MoviesByDate1){ // Constructor
        
        this.MoviesByID = MoviesByID;
        this.Customers = Customers1;
        this.MoviesByScore = MoviesByScore1;
        this.MoviesByDate = MoviesByDate1;

        JPanel panel = new JPanel();
        JFrame frame = new JFrame();
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        menuBar = new JMenuBar();
        menu = new JMenu("New User Form");
        newUserMenu = new JMenuItem("Add User");
        newUserMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                new addUserGUI(Customers);
            }
        });
        menu.add(newUserMenu);
        menuBar.add(menu);
        setJMenuBar(menuBar);
        frame.setJMenuBar(menuBar);

        JLabel title = new JLabel("Welcome User, Please Login or Add New User From Menu");
        title.setBounds(10, 0, 300, 25);
        c.fill = GridBagConstraints.NONE;
        c.insets = new Insets(5, 5, 5, 5);
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        panel.add(title, c);
        
        userLabel = new JLabel("Email");
        userLabel.setBounds(10, 30, 80, 25);
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        panel.add(userLabel, c);

        userText = new JTextField(20);
        userText.setBounds(100, 30, 165, 25);
        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 1;
        panel.add(userText, c);

        passwordLabel = new JLabel("Credit Card");
        passwordLabel.setBounds(10, 60, 80, 25);
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 1;
        panel.add(passwordLabel, c);

        passwordText = new JPasswordField(20);
        passwordText.setBounds(100, 60, 165, 25);
        c.gridx = 1;
        c.gridy = 2;
        c.gridwidth = 1;
        panel.add(passwordText, c);

        loginBTN = new JButton("Login"); // Tries to find user based on credit card #, then tries to match email, if either one doesnt work it tells you why
        loginBTN.setBounds(10, 100, 80, 25);
        loginBTN.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String user = userText.getText();
                String password = String.valueOf(passwordText.getPassword());
                System.out.println(user + ", " + password);
                try{
                Customer temp = Customers.lookUp(Integer.parseInt(password));
                if (temp != null) {
                    if (temp.getEmailAddress().equals(user) != true) {
                        System.out.println(temp.getEmailAddress() + user);
                        loginBTN.setText("Email Incorrect");
                    } else {
                        frame.setVisible(false);
                        new UserGUI(temp, Customers, MoviesByID, MoviesByDate);
                    }
                } else {loginBTN.setText("User not found");}
                } catch (NullPointerException error) {
                    loginBTN.setText("User not found");
                }
            }
        });
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 2;
        panel.add(loginBTN, c);

        frame.setVisible(true);

    }

    public void actionPerformed(ActionEvent e) {

        String user = userText.getText();
        String password = String.valueOf(passwordText.getPassword());
        System.out.println(user + ", " + password);

        if (user.equals("username") && password.equals("password")) {
            loginBTN.setText("Login successful!");
        }
        else {
            loginBTN.setText("Login failed!");
        }
    }
}
