// LOGIN GUI
// technichally extra credit but we will get it looking nice

import javax.swing.*;
import CORE.*;

import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.lang.Integer;

public class UserGUI extends JFrame{
    private JTextArea textArea;
    private JScrollPane scrollText;
    private JButton back;
    private JButton submit;
    private JLabel addMovie;
    private JTextField movieID;
    private MovieQueue wishlist;
    private MovieBinarySearchTree MoviesByDate;
    private Customer customer;
    private CustomerHashTable customers;

    public UserGUI(Customer customer1, CustomerHashTable customers1, MovieBinarySearchTree MoviesByDate1){

        this.MoviesByDate = MoviesByDate1;
        this.customer = customer1;
        this.customers = customers1;
        this.wishlist = customer.getWishlist();
        JPanel panel = new JPanel();
        JFrame frame = new JFrame();
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        // create jlabel that is MoviesByDate title that says "Welcome to Camel Films"
        JLabel title = new JLabel("Welcome " + customer.getName());
        title.setBounds(10, 0, 300, 25);
        c.fill = GridBagConstraints.NONE;
        c.weightx = 0.5;
        c.insets = new Insets(5, 5, 5, 5);
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 4;
        panel.add(title, c);

        JTextArea nextMovie = new JTextArea("", 2, 5);
        nextMovie.setBounds(0, 0, 160, 25);
        nextMovie.setLineWrap(false);
        nextMovie.setEditable(false);
        try{
            nextMovie.setText(customer.getWishlist().front().getTitle() + " " + customer.getWishlist().front().getReleaseDate());
            } catch (NullPointerException v) {
                nextMovie.setText("No Movies In Wishlist");
            }
        c.gridx=0;
        c.anchor = GridBagConstraints.NORTH;
        c.gridy=1;
        c.gridwidth=2;
        panel.add(nextMovie, c);

        JButton watchMovie = new JButton("Watch Movie");
        watchMovie.setBounds(0, 45, 80, 25);
        watchMovie.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                wishlist.dequeue();
                try{
                    nextMovie.setText(customer.getWishlist().front().getTitle() + " " + customer.getWishlist().front().getReleaseDate());
                    } catch (NullPointerException v) {
                        nextMovie.setText("No Movies In Wishlist");
                    }
                textArea.setText("");
                treeToText();
            }
        });
        c.gridx = 0;
        c.anchor = GridBagConstraints.SOUTH;
        c.gridy = 1;
        c.gridwidth = 2;
        panel.add(watchMovie, c);
        c.anchor = GridBagConstraints.CENTER;


        textArea = new JTextArea("", 10, 20);
        textArea.setLineWrap(true);
        textArea.setPreferredSize(new Dimension(200, 100));
        scrollText = new JScrollPane(textArea);
        textArea.setEditable(false);
        scrollText.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        c.gridx = 2;
        c.gridy = 1;
        c.gridwidth = 2;
        treeToText();
        scrollText.setViewportView(textArea);
        panel.add(scrollText, c);

        addMovie = new JLabel("Input Movie ID To Add To Wishlist");
        addMovie.setBounds(0, 0, 65, 25);
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 1;
        panel.add(addMovie, c);

        movieID = new JTextField("",20);
        movieID.setBounds(0, 0, 65, 25);
        c.gridx = 1;
        c.gridy = 2;
        c.gridwidth = 2;
        panel.add(movieID, c);

        submit = new JButton("Submit");
        submit.setBounds(0, 0, 80, 25);
        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                wishlist.enqueue(MoviesByDate.searchBST(Integer.parseInt(movieID.getText())));
                System.out.println("Added Movie");
                try{
                    nextMovie.setText(customer.getWishlist().front().getTitle() + " " + customer.getWishlist().front().getReleaseDate());
                    } catch (NullPointerException v) {
                        nextMovie.setText("No Movies In Wishlist");
                    }
            }
        });
        c.gridx = 3;
        c.gridy = 2;
        c.gridwidth = 1;
        panel.add(submit, c);

        back = new JButton("Logout");
        back.setBounds(10, 100, 80, 25);
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                WelcomeGUI menu = new WelcomeGUI();
                menu.saveByDate(MoviesByDate);
                menu.saveCustomers(customers);
                System.exit(0);
            }
        });
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 4;
        panel.add(back, c);

        frame.pack();
        frame.setVisible(true);

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                WelcomeGUI menu = new WelcomeGUI();
                menu.saveByDate(MoviesByDate);
                menu.saveCustomers(customers);
                System.exit(0);
            }
        });
    }

    public void treeToText() {
        try{
        printTree2(MoviesByDate.getRoot());
        System.out.println("Updated Movies Successfully");
        } catch (NullPointerException e) {
            System.out.println("Updated Movies Failed");
            e.printStackTrace();
            textArea.setText("No Movies :(");
        }
    }

    private void printTree2(Movie movie) {
        if (movie != null) {
            textArea.append(movie.getTitle() + " (" + movie.getReleaseDate() + ") " + "(ID): " + movie.getUniqueID() + " (Score): " + movie.getRottenTomatoesScore() + "\n\n");
            printTree2(movie.getLeft());
            printTree2(movie.getRight());
        }
    }

    
}
