import CORE.*;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;

public class addUserGUI extends javax.swing.JFrame{
	private CustomerHashTable Customers;

	public addUserGUI(CustomerHashTable Customers1){
		super("Add User");
		this.Customers = Customers1;
		setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		buildApp();
		pack();
		setSize(250,150);
		setVisible(true);
	}
	
	void buildApp(){ //Lots and Lots of GUI stuff
		JLabel addInstruction = new JLabel("Enter name, email, credit card");
		JTextField Name = new JTextField("Name");
		JTextField Email = new JTextField("Email");
        JTextField CreditCard = new JTextField("CC");
		JButton submit = new JButton("Submit");
		JButton cancel = new JButton("Cancel");
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		buttonPanel.add(cancel);
		buttonPanel.add(submit);
		JPanel textPanel = new JPanel();
		textPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		textPanel.add(Name);
		textPanel.add(Email);
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
            MovieQueue wishlist = new MovieQueue();
			Customer newCustomer = new Customer(Name.getText(),Integer.parseInt(CreditCard.getText()), Email.getText(), wishlist);
			Customers.insert(newCustomer);
            System.out.println("Added New Customer");
			dispose();
			}
		});
	}
}