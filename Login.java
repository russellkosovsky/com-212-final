// LOGIN GUI

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Login implements ActionListener {

    private static JLabel userLabel;
    private static JTextField userText;
    private static JLabel passwordLabel;
    private static JPasswordField passwordText;
    private static JButton loginBTN;
    private static JLabel success;


    public static void main(String[] args) {

        JPanel panel = new JPanel();
        JFrame frame = new JFrame();
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        panel.setLayout(null);

        // create jlabel that is a title that says "Welcome to Camel Films"
        JLabel title = new JLabel("Welcome to Camel Films");
        title.setBounds(10, 0, 300, 25);
        panel.add(title);
        
        userLabel = new JLabel("Username");
        userLabel.setBounds(10, 30, 80, 25);
        panel.add(userLabel);

        userText = new JTextField(20);
        userText.setBounds(100, 30, 165, 25);
        panel.add(userText);

        passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10, 60, 80, 25);
        panel.add(passwordLabel);

        passwordText = new JPasswordField(20);
        passwordText.setBounds(100, 60, 165, 25);
        panel.add(passwordText);

        loginBTN = new JButton("Login");
        loginBTN.setBounds(10, 100, 80, 25);
        loginBTN.addActionListener(new Login());
        panel.add(loginBTN);

        success = new JLabel(""); 
        success.setBounds(10, 130, 300, 25);
        panel.add(success);

        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        String user = userText.getText();
        String password = String.valueOf(passwordText.getPassword());
        System.out.println(user + ", " + password);

        if (user.equals("username") && password.equals("password")) {
            success.setText("Login successful!");
        }
        else {
            success.setText("Login failed!");
        }
    }
}
