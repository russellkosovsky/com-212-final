// LOGIN GUI
// technichally extra credit but we will get it looking nice

import javax.swing.*;
import CORE.*;

import java.awt.event.ActionListener;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;

public class AdminGUI {
    private static JTextArea textArea;
    private static JScrollPane scrollText;
    private static JButton back;
    private static MovieBinarySearchTree a;

    public AdminGUI(){

        a = new MovieBinarySearchTree();
        Movie movieA = new Movie("Movie A", 2001, 1, 85, true);
        Movie movieB = new Movie("Movie B", 1999, 2, 70, true);
        Movie movieC = new Movie("Movie C", 2005, 3, 95, true);
        Movie movieD = new Movie("Movie D", 2003, 4, 80, true);
        Movie movieE = new Movie("Movie E", 2007, 5, 85, true);
        Movie movieF = new Movie("Movie F", 1995, 6, 70, true);
        Movie movieG = new Movie("Movie G", 2004, 7, 95, true);
        Movie movieH = new Movie("Movie H", 2007, 8, 80, true);
        Movie movieI = new Movie("Movie I", 2002, 9, 85, true);
        Movie movieJ = new Movie("Movie J", 1923, 10, 70, true);
        Movie movieK = new Movie("Movie K", 2053, 11, 95, true);
        Movie movieL = new Movie("Movie L", 2013, 12, 80, true);
        Movie movieM = new Movie("Movie M", 2074, 13, 85, true);
        Movie movieN = new Movie("Movie N", 1932, 14, 70, true);
        Movie movieO = new Movie("Movie O", 2075, 15, 95, true);
        Movie movieP = new Movie("Movie P", 2053, 16, 80, true);
        Movie movieQ = new Movie("Movie Q", 2036, 17, 85, true);
        Movie movieR = new Movie("Movie R", 1946, 18, 70, true);
        Movie movieS = new Movie("Movie S", 2034, 19, 95, true);
        Movie movieT = new Movie("Movie T", 2064, 20, 80, true);

        a.insert(movieA);
        a.insert(movieB);
        a.insert(movieC);
        a.insert(movieD);
        a.insert(movieE);
        a.insert(movieF);
        a.insert(movieG);
        a.insert(movieH);
        a.insert(movieI);
        a.insert(movieJ);
        a.insert(movieK);
        a.insert(movieL);
        a.insert(movieM);
        a.insert(movieN);
        a.insert(movieO);
        a.insert(movieP);
        a.insert(movieQ);
        a.insert(movieR);
        a.insert(movieS);
        a.insert(movieT);

        JPanel panel = new JPanel();
        JFrame frame = new JFrame();
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        // create jlabel that is a title that says "Welcome to Camel Films"
        JLabel title = new JLabel("Administrator Control Panel");
        title.setBounds(10, 0, 300, 25);
        c.fill = GridBagConstraints.NONE;
        c.insets = new Insets(5, 5, 5, 5);
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        panel.add(title, c);

        textArea = new JTextArea("", 10, 20);
        textArea.setLineWrap(false);
        scrollText = new JScrollPane(textArea);
        textArea.setEditable(false);
        scrollText.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 2;
        treeToText();
        scrollText.setViewportView(textArea);
        panel.add(scrollText, c);

        back = new JButton("Logout");
        back.setBounds(10, 100, 80, 25);
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
            }
        });
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 2;
        panel.add(back, c);

        frame.setVisible(true);
    }

        public static void main(String[] args) {

        new AdminGUI();
    }

    public void treeToText() {
        printTree2(a.root);
    }

    private void printTree2(Movie movie) {
        if (movie != null) {
            textArea.append(movie.getTitle() + " (" + movie.getReleaseDate() + ") \n");
            printTree2(movie.getLeft());
            printTree2(movie.getRight());
        }
    }
}
