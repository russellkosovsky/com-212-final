// Computer Science Data Structures Final Project
// By Jay, Russell, Brooke, and Miles
// Admin GUI panel with list of movies by ID, ability to add movie/set movie unavalible, and to access and edit user profiles

import javax.swing.*;
import CORE.*;

import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.io.*;
import java.lang.Integer;

public class AdminGUI extends JFrame{
    // Bunch of declarations
    private JTextArea textArea;
    private JScrollPane scrollText;
    private JButton back;
    private JButton submit;
    private JTextField movieTitle;
    private JTextField date;
    private JTextField tomatos;
    private int ID;
    private MoviePQ MoviesByScore;
    private CustomerHashTable Customers;
    private MovieBinarySearchTree MoviesByID;
    private bstByDate MoviesByDate;
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem lookUpUser;
    public AdminGUI(CustomerHashTable Customers1, MovieBinarySearchTree MoviesByID1, MoviePQ MoviesByScore1, bstByDate MoviesByDate1){ // Constructor takes all serialized info from adminLogin

        this.MoviesByID = MoviesByID1;
        this.Customers = Customers1;
        this.MoviesByScore = MoviesByScore1;
        this.MoviesByDate = MoviesByDate1;
        loadID(); //Loads current ID from File
        JPanel panel = new JPanel();
        JFrame frame = new JFrame();
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        // create jlabel that is MoviesByID title that says "Welcome to Camel Films"
        JLabel title = new JLabel("Administrator Control Panel");
        title.setBounds(10, 0, 300, 25);
        c.fill = GridBagConstraints.NONE;
        c.weightx = 0.5;
        c.insets = new Insets(5, 5, 5, 5);
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 4;
        panel.add(title, c);

        // GUI building code
        JTextArea lowMovie = new JTextArea("", 2, 5);
        lowMovie.setBounds(0, 0, 160, 25);
        lowMovie.setLineWrap(false);
        lowMovie.setEditable(false);
        try{
            lowMovie.setText(MoviesByScore.findMin().getTitle() + " : " + String.valueOf(MoviesByScore.findMin().getRottenTomatoesScore()));
            } catch (NullPointerException v) {
                lowMovie.setText("No Movies");
            }
        c.gridx=0;
        c.anchor = GridBagConstraints.NORTH;
        c.gridy=1;
        c.gridwidth=2;
        panel.add(lowMovie, c);
        // Button to remove Moive
        JButton RemMovie = new JButton("Remove Movie");
        RemMovie.setBounds(0, 45, 80, 25);
        RemMovie.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (MoviesByScore.isEmptySet() != true){ // Bunch of checks here
                    try{
                        Movie temp = MoviesByScore.findMin();
                        temp.setAvailablility(false);
                        MoviesByScore.deleteMin();
                        if (MoviesByScore.isEmptySet() != true){
                        lowMovie.setText(MoviesByScore.findMin().getTitle() + " : " + String.valueOf(MoviesByScore.findMin().getRottenTomatoesScore()));
                        } else {
                            lowMovie.setText("No Movies");
                        }
                    } catch (Exception v) {
                            lowMovie.setText("Error");
                    }
                } else {
                    lowMovie.setText("No Movies");
                }
                textArea.setText("");
                treeToText();
            }
        });
        c.gridx = 0;
        c.anchor = GridBagConstraints.SOUTH;
        c.gridy = 1;
        c.gridwidth = 2;
        panel.add(RemMovie, c);
        c.anchor = GridBagConstraints.CENTER;


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
        // Moive textfields for new movie inputs
        movieTitle = new JTextField("Movie Title",20);
        movieTitle.setBounds(0, 0, 65, 25);
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 1;
        panel.add(movieTitle, c);

        date = new JTextField("Movie Date",20);
        date.setBounds(0, 0, 65, 25);
        c.gridx = 1;
        c.gridy = 2;
        c.gridwidth = 1;
        panel.add(date, c);
        
        tomatos = new JTextField("Tomato Score",20);
        tomatos.setBounds(0, 0, 65, 25);
        c.gridx = 2;
        c.gridy = 2;
        c.gridwidth = 1;
        panel.add(tomatos, c);
        // Submit button to create new movie from inputs
        submit = new JButton("Submit");
        submit.setBounds(0, 0, 80, 25);
        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Movie newMovie = new Movie(movieTitle.getText(), Integer.parseInt(date.getText()), ID, Integer.parseInt(tomatos.getText()), true);
                MoviesByID.insert(newMovie);
                MoviesByDate.dateInsert(newMovie);
                MoviesByScore.insert(newMovie);
                System.out.println("Added Movie");
                ID++;
                try{
                    lowMovie.setText(MoviesByScore.findMin().getTitle() + " : " + String.valueOf(MoviesByScore.findMin().getRottenTomatoesScore()));
                    System.out.println(MoviesByScore.findMin().getTitle() + " : " + String.valueOf(MoviesByScore.findMin().getRottenTomatoesScore()));
                    } catch (NullPointerException v) {
                        lowMovie.setText("No Movies");
                    }
                textArea.setText("");
                treeToText();
            }
        });
        c.gridx = 3;
        c.gridy = 2;
        c.gridwidth = 1;
        panel.add(submit, c);
        // Logout button that saves all data
        back = new JButton("Logout");
        back.setBounds(10, 100, 80, 25);
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                WelcomeGUI menu = new WelcomeGUI();
                menu.saveByID(MoviesByID);
                menu.saveByScore(MoviesByScore);
                menu.saveCustomers(Customers);
                menu.saveByDate(MoviesByDate);
                saveID(ID);
                System.exit(0);
            }
        });
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 4;
        panel.add(back, c);

        menuBar = new JMenuBar();
        menu = new JMenu("User Search");
        lookUpUser = new JMenuItem("Lookup User");
        lookUpUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                new lookUpUser(Customers);
            }
        });
        
        menu.add(lookUpUser);
        menuBar.add(menu);
        setJMenuBar(menuBar);
        frame.setJMenuBar(menuBar);

        frame.pack();
        frame.setVisible(true);
        // Windowlistener unfortunately doesnt work, admin must press logout for changes to be saved
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                frame.setVisible(false);
                WelcomeGUI menu = new WelcomeGUI();
                menu.saveByID(MoviesByID);
                menu.saveByScore(MoviesByScore);
                menu.saveCustomers(Customers);
                menu.saveByDate(MoviesByDate);
                saveID(ID);
                System.exit(0);
            }
        });
    }
    // Seperate recursive function to print tree contents to a java swing text area, here instead of in BST for ease of simply using .append instead of StringBuilder in the BST
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
            textArea.append(movie.getTitle() + " (" + movie.getReleaseDate() + ") " + "(ID): " + movie.getUniqueID() + " (Score): " + movie.getRottenTomatoesScore() + "(Avalibility): " + movie.Availablility() + "\n\n");
            printTree2(movie.getLeft());
            printTree2(movie.getRight());
        }
    }
    // fucntion to save ID to text file
    public void saveID(int ID){
        try {
            PrintWriter out = new PrintWriter(new FileOutputStream("CurrentID.txt", false));
            out.write(String.valueOf(ID));
            out.close();
            out.close();
            System.out.println("Saved Current ID To File");
        } 
        catch(IOException i) {
            i.printStackTrace();
        }
    }
    
    //function to load ID
    public void loadID(){
        try{
            BufferedReader in = new BufferedReader(new FileReader("CurrentID.txt"));
            ID = Integer.valueOf(in.readLine());
            in.close();
        }
        catch(IOException i){
            i.printStackTrace();
        }
    }

    
}
