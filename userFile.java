// Computer Science Data Structures Final Project
// By Jay, Russell, Brooke, and Miles
// GUI element that is called by lookupUser, displays customer info and lets admins edit name and email (CC cannot be edited)

import javax.swing.*;
import CORE.*;

import java.awt.event.ActionListener;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;

public class userFile extends JFrame{
    private JButton back;
    private JButton edit;
    private JButton printWishlist;
    private JTextField name;
    private JTextField email;
    private JTextField creditCard;
    private JLabel nameLabel;
    private JLabel emailLabel;
    private JLabel creditCardLabel;
    private CustomerHashTable Customers;
    private Customer customerFile;

    public userFile(CustomerHashTable Customers1, int creditCardNum){

        this.Customers = Customers1;
        this.customerFile = Customers.lookUp(creditCardNum);
        JPanel panel = new JPanel();
        JFrame frame = new JFrame();
        frame.setSize(500, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        JLabel title = new JLabel("Customer File");
        title.setBounds(10, 0, 300, 25);
        c.fill = GridBagConstraints.NONE;
        c.weightx = 0.5;
        c.insets = new Insets(5, 5, 5, 5);
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 3;
        panel.add(title, c);

        nameLabel = new JLabel("User Name:");
        nameLabel.setBounds(10, 30, 80, 25);
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        panel.add(nameLabel, c);

        name = new JTextField(customerFile.getName(),20);
        name.setBounds(0, 0, 160, 25);
        name.setEditable(false);
        c.gridx=1;
        c.gridy=1;
        c.gridwidth=2;
        panel.add(name, c);

        emailLabel = new JLabel("Email:");
        emailLabel.setBounds(10, 30, 80, 25);
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 1;
        panel.add(emailLabel, c);

        email = new JTextField(customerFile.getEmailAddress(),20);
        email.setBounds(0, 0, 160, 25);
        email.setEditable(false);
        c.gridx=1;
        c.gridy=2;
        c.gridwidth=2;
        panel.add(email, c);

        creditCardLabel = new JLabel("Credit Card:");
        creditCardLabel.setBounds(10, 30, 80, 25);
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 1;
        panel.add(creditCardLabel, c);

        creditCard = new JTextField(String.valueOf(customerFile.getCreditCardNumber()),20);
        creditCard.setBounds(0, 0, 160, 25);
        creditCard.setEditable(false);
        c.gridx=1;
        c.gridy=3;
        c.gridwidth=2;
        panel.add(creditCard, c);
        // Exits and saves userfile
        back = new JButton("Exit File");
        back.setBounds(0, 45, 80, 25);
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                frame.setVisible(false);
                customerFile.setName(name.getText());
                customerFile.setEmailAddress(email.getText());
                dispose();
                } catch (Exception err) {
                    back.setText("Parse Failure");
                }
            }
        });
        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 1;
        panel.add(back, c);
        // Text areas start as uneditable so they have to be unlocked by this button
        edit = new JButton("Edit File");
        edit.setBounds(0, 45, 80, 25);
        edit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                back.setText("Exit and Save File");
                name.setEditable(true);
                email.setEditable(true);
                creditCard.setText("Uneditable");
                edit.setText("File Editable");
            }
        });
        c.gridx = 1;
        c.gridy = 4;
        c.gridwidth = 1;
        panel.add(edit, c);
        //Prints the wishlist of the user if admins wish to view it
        printWishlist = new JButton("Print Wishlist");
        printWishlist.setBounds(0, 45, 80, 25);
        printWishlist.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, customerFile.getWishlist().printQueueString());
            }
        });
        c.gridx = 2;
        c.gridy = 4;
        c.gridwidth = 1;
        panel.add(printWishlist, c);

        frame.setVisible(true);
    }
}
