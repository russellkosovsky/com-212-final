import CORE.*;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;

public class search extends javax.swing.JFrame{
	private MovieBinarySearchTree MoviesByID;

	public search(MovieBinarySearchTree MoviesByID1){
		super("Search Movies By ID");
		this.MoviesByID = MoviesByID1;
		setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		buildApp();
		pack();
		setSize(350,250);
		setVisible(true);
	}
	
	void buildApp(){ //Lots and Lots of GUI stuff
		JLabel addInstruction = new JLabel("Enter Movie ID");
        JTextField ID = new JTextField("",20);
		JButton submit = new JButton("Submit");
		JButton cancel = new JButton("Cancel");
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		buttonPanel.add(cancel);
		buttonPanel.add(submit);
		JPanel textPanel = new JPanel();
		textPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		textPanel.add(ID);
		add(addInstruction, BorderLayout.NORTH);
		add(textPanel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);
		
		cancel.addActionListener(new ActionListener(){ // If cancel button pressed, close window
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		submit.addActionListener(new ActionListener(){ // If submit button pressed, create new node from inputs and append to tree, then close window with message that node was added
			@Override
			public void actionPerformed(ActionEvent e){
                try {
                Movie movie = MoviesByID.searchBST(Integer.parseInt(ID.getText()));
                JOptionPane.showMessageDialog(null, movie.getTitle() + " (" + movie.getReleaseDate() + ") " + "(ID): " + movie.getUniqueID() + " (Score): " + movie.getRottenTomatoesScore() + "(Avalibility): " + movie.Availablility() + "\n\n");
                } catch (NullPointerException nullpointer) {
                    JOptionPane.showMessageDialog(null, "No Movie By This ID");
                }
			}
		});
	}
}