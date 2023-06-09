// Computer Science Data Structures Final Project
// By Jay, Russell, Brooke, and Miles
// GUI for admin login, very simple as there is only one admin account


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import CORE.*;


public class AdminLoginGUI implements ActionListener {
    // Declarations
    private static JLabel userLabel;
    private static JTextField userText;
    private static JLabel passwordLabel;
    private static JPasswordField passwordText;
    private static JButton loginBTN;
    private MoviePQ MoviesByScore;
    private CustomerHashTable Customers;
    private MovieBinarySearchTree MoviesByID;
    private bstByDate MoviesByDate;

    public AdminLoginGUI(CustomerHashTable Customers1, MovieBinarySearchTree MoviesByID1, MoviePQ MoviesByScore1, bstByDate MoviesByDate1){ // Constructor
        this.MoviesByID = MoviesByID1;
        this.Customers = Customers1;
        this.MoviesByScore = MoviesByScore1;
        this.MoviesByDate = MoviesByDate1;
        //GUI is built
        JPanel panel = new JPanel();
        JFrame frame = new JFrame();
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        // create jlabel that is a title that says "Welcome to Camel Films"
        JLabel title = new JLabel("Administrator Login");
        title.setBounds(10, 0, 300, 25);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(5, 5, 5, 5);
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        panel.add(title, c);
        
        userLabel = new JLabel("Username");
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

        passwordLabel = new JLabel("Password");
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
        //Login button checks user inputs to username/password and then lets admin login
        loginBTN = new JButton("Login");
        loginBTN.setBounds(10, 100, 80, 25);
        loginBTN.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String user = userText.getText();
                String password = String.valueOf(passwordText.getPassword());
                System.out.println(user + ", " + password);

                if (user.equals("username") && password.equals("password")) {
                    loginBTN.setText("Login successful!");
                    new AdminGUI(Customers, MoviesByID, MoviesByScore, MoviesByDate);
                    frame.setVisible(false);
                }
                else {
                    loginBTN.setText("Login failed!");
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
