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
import java.io.*;
import java.lang.Integer;

public class AdminGUI extends JFrame{
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
    private JMenuItem generateData;

    public AdminGUI(CustomerHashTable Customers1, MovieBinarySearchTree MoviesByID1, MoviePQ MoviesByScore1, bstByDate MoviesByDate1){

        this.MoviesByID = MoviesByID1;
        this.Customers = Customers1;
        this.MoviesByScore = MoviesByScore1;
        this.MoviesByDate = MoviesByDate1;
        loadID();
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

        JButton RemMovie = new JButton("Remove Movie");
        RemMovie.setBounds(0, 45, 80, 25);
        RemMovie.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (MoviesByScore.isEmptySet() != true){
                    try{
                        Movie temp = MoviesByScore.findMin();
                        temp.setAvailablility(false);
                        MoviesByScore.deleteMin();
                        if (MoviesByScore.findMin() != null){
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

        submit = new JButton("Submit");
        submit.setBounds(0, 0, 80, 25);
        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Movie newMovie = new Movie(movieTitle.getText(), Integer.parseInt(date.getText()), ID, Integer.parseInt(tomatos.getText()), true);
                MoviesByID.insert(newMovie);
                MoviesByDate.dateInsert(newMovie);
                MoviesByScore.insert(newMovie);
                System.out.println("Added Movie");
                //textArea.append(newMovie.getTitle() + " (" + newMovie.getReleaseDate() + ") " + "ID: " + newMovie.getUniqueID() + "Score: " + newMovie.getRottenTomatoesScore() + "\n");
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
        generateData = new JMenuItem("Generate Database");
        generateData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                if (MoviesByID.getRoot() == null) {
                    Movie starwars = new Movie("Star Wars", 1977, 4, 100, true);
                    Movie toystory = new Movie("Toy Story", 1995, 5, 98, true);
                    Movie mulan = new Movie("Mulan", 1996, 1, 93, true);
                    Movie newgroove = new Movie("The Emperor's New Groove", 2000, 3, 99, true);
                    Movie spiritedaway = new Movie("Spirited Away", 2002, 2, 97, true);
                    MoviesByID.insert(starwars);
                    MoviesByID.insert(toystory);
                    MoviesByID.insert(mulan);
                    MoviesByID.insert(newgroove);
                    MoviesByID.insert(spiritedaway);
                    MoviesByDate.dateInsert(starwars);
                    MoviesByDate.dateInsert(toystory);
                    MoviesByDate.dateInsert(mulan);
                    MoviesByDate.dateInsert(newgroove);
                    MoviesByDate.dateInsert(spiritedaway);
                    MoviesByScore.insert(starwars);
                    MoviesByScore.insert(toystory);
                    MoviesByScore.insert(mulan);
                    MoviesByScore.insert(newgroove);
                    MoviesByScore.insert(spiritedaway);
                    JOptionPane.showMessageDialog(null, "Database Populated");
                    try{
                        lowMovie.setText(MoviesByScore.findMin().getTitle() + " : " + String.valueOf(MoviesByScore.findMin().getRottenTomatoesScore()));
                        System.out.println(MoviesByScore.findMin().getTitle() + " : " + String.valueOf(MoviesByScore.findMin().getRottenTomatoesScore()));
                        } catch (NullPointerException v) {
                            lowMovie.setText("No Movies");
                        }
                    textArea.setText("");
                    treeToText();

                } else {
                    JOptionPane.showMessageDialog(null, "You cannot use this command on a database that has movies already added to it");
                }
            }
        });
        menu.add(generateData);
        menu.add(lookUpUser);
        menuBar.add(menu);
        setJMenuBar(menuBar);
        frame.setJMenuBar(menuBar);

        frame.pack();
        frame.setVisible(true);

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
    
    //function to load the AdminPG so that we have the necessary info
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
