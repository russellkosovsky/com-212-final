import CORE.*;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;

public class lookUpUser extends javax.swing.JFrame{
	private CustomerHashTable Customers;

	public lookUpUser(CustomerHashTable Customers1){
		super("Look Up User");
		this.Customers = Customers1;
		setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		buildApp();
		pack();
		setSize(350,250);
		setVisible(true);
	}
	
	void buildApp(){ //Lots and Lots of GUI stuff
		JLabel addInstruction = new JLabel("Enter User Credit Card To View Record");
        JTextField CreditCard = new JTextField("CC",20);
		JButton submit = new JButton("Submit");
		JButton cancel = new JButton("Cancel");
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		buttonPanel.add(cancel);
		buttonPanel.add(submit);
		JPanel textPanel = new JPanel();
		textPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		textPanel.add(CreditCard);
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
			//int intCRN = parseInt(bookCRN.getText(),5);
            try {
                new userFile(Customers, Integer.parseInt(CreditCard.getText()));
                dispose();
            } catch (NullPointerException npe) {
                submit.setText("No User Found");
            }
			}
		});
	}
}