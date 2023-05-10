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
    private MovieBinarySearchTree MoviesByID;
    private bstByDate MoviesByDate;
    private Customer customer;
    private CustomerHashTable customers;

    public UserGUI(Customer customer1, CustomerHashTable customers1, MovieBinarySearchTree MoviesByID1, bstByDate MoviesByDate1){

        this.MoviesByID = MoviesByID1;
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

        // create jlabel that is MoviesByID title that says "Welcome to Camel Films"
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
        if (wishlist.isEmpty() == true) {
            nextMovie.setText("No Movies In Wishlist");
        } else {
            nextMovie.setText("No Movie Playing");
        }
        c.gridx=0;
        c.anchor = GridBagConstraints.NORTH;
        c.gridy=1;
        c.gridwidth=2;
        panel.add(nextMovie, c);

        JButton delMovie = new JButton("Delete Movie");
        delMovie.setBounds(0, 45, 80, 25);
        delMovie.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                delMovie.setText("Delete Movie");
                if (wishlist.length() == 0){
                    delMovie.setText("No Movies In Wishlist");
                } else {
                    nextMovie.setText("No Movie Playing");
                    wishlist.dequeue();
                }
            }
        });
        c.gridx = 0;
        c.anchor = GridBagConstraints.SOUTH;
        c.gridy = 1;
        c.gridwidth = 2;
        panel.add(delMovie, c);

        JButton watchMovie = new JButton("Watch Movie");
        watchMovie.setBounds(0, 45, 80, 25);
        watchMovie.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try{
                    if (customer.getWishlist().front().Availablility() == true) {
                        nextMovie.setText("Playing: " + customer.getWishlist().front().getTitle() + " " + customer.getWishlist().front().getReleaseDate());
                        if (customer.getWatched().searchReturn(customer.getWishlist().front().getUniqueID()) == null) {
                            customer.getWatched().insert(customer.getWishlist().front());
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Movie Unavalible, Removing From Wishlist");
                    }
                    } catch (Exception v) {
                        nextMovie.setText("No Movies In Wishlist");
                    }
            }
        });
        c.gridx = 0;
        c.anchor = GridBagConstraints.CENTER;
        c.gridy = 1;
        c.gridwidth = 2;
        panel.add(watchMovie, c);


        textArea = new JTextArea("", 10, 20);
        textArea.setLineWrap(true);
        scrollText = new JScrollPane(textArea);
        scrollText.setPreferredSize(new Dimension(300, 200));
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
                submit.setText("Submit");
                try{
                if (MoviesByID.searchBST(Integer.parseInt(movieID.getText())) != null && MoviesByID.searchBST(Integer.parseInt(movieID.getText())).Availablility() == true) {
                wishlist.enqueue(MoviesByID.searchBST(Integer.parseInt(movieID.getText())));
                System.out.println("Added Movie");
                System.out.println(wishlist.front());
                nextMovie.setText("Press Watch to View Next Moive");
                } else {
                    JOptionPane.showMessageDialog(null, "Movie Not Avalible or Invalid ID");
                }
                } catch (NullPointerException er){
                    submit.setText("No ID Found");
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
                menu.saveByID(MoviesByID);
                menu.saveCustomers(customers);
                menu.saveByDate(MoviesByDate);
                System.exit(0);
            }
        });
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 4;
        panel.add(back, c);

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        JMenuItem historyMenu = new JMenuItem("View History");
        historyMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JOptionPane.showMessageDialog(null, customer.getWatched().printList());
            }
        });
        menu.add(historyMenu);

        JMenuItem wishlistMenu = new JMenuItem("View Wishlist");
        wishlistMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent event) {
                JOptionPane.showMessageDialog(null, customer.getWishlist().printQueueString());
            }
        });
        menu.add(wishlistMenu);

        JMenuItem dateMenu = new JMenuItem("Sort By Date");
        dateMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent event) {
                textArea.setText("");
                treeToDate();
            }
        });
        menu.add(dateMenu);

        JMenuItem IDMenu = new JMenuItem("Sort By ID");
        IDMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent event) {
                textArea.setText("");
                treeToText();
            }
        });
        menu.add(IDMenu);

        menuBar.add(menu);
        setJMenuBar(menuBar);
        frame.setJMenuBar(menuBar);

        frame.pack();
        frame.setVisible(true);

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                WelcomeGUI menu = new WelcomeGUI();
                menu.saveByID(MoviesByID);
                menu.saveCustomers(customers);
                menu.saveByDate(MoviesByDate);
                System.exit(0);
            }
        });
    }

    public void treeToText() {
        try{
        printTree2(MoviesByID.getRoot());
        System.out.println("Updated Movies Successfully");
        } catch (NullPointerException e) {
            System.out.println("Updated Movies Failed");
            e.printStackTrace();
            textArea.setText("No Movies :(");
        }
    }

    private void printTree2(Movie movie) {
        if (movie != null) {
            printTree2(movie.getLeft());
            textArea.append(movie.getTitle() + " (" + movie.getReleaseDate() + ") " + "(ID): " + movie.getUniqueID() + " (Score): " + movie.getRottenTomatoesScore() + "(Avalibility): " + movie.Availablility() + "\n\n");
            printTree2(movie.getRight());
        }
    }

    public void treeToDate() {
        try{
        printTree2Date(MoviesByDate.getRoot());
        System.out.println("Updated Movies Successfully");
        } catch (NullPointerException e) {
            System.out.println("Updated Movies Failed");
            e.printStackTrace();
            textArea.setText("No Movies :(");
        }
    }

    private void printTree2Date(Movie movie) {
        if (movie != null) {
            printTree2Date(movie.getLeftDate());
            textArea.append(movie.getTitle() + " (" + movie.getReleaseDate() + ") " + "(ID): " + movie.getUniqueID() + " (Score): " + movie.getRottenTomatoesScore() + "(Avalibility): " + movie.Availablility() + "\n\n");
            printTree2Date(movie.getRightDate());
        }
    }
}
