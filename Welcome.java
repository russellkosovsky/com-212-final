import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.Image;


public class Welcome extends JFrame {

    public static void main(String[] args) {

        JPanel panel = new JPanel();
        JFrame frame = new JFrame();
        frame.setSize(300, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("resources/CLOGO3.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Image dimg = img.getScaledInstance(200, 200, Image.SCALE_SMOOTH);

        ImageIcon imageIcon = new ImageIcon(dimg);
        JLabel imageLabel = new JLabel(imageIcon);
        imageLabel.setBounds(0,0,50,50);
        c.fill = GridBagConstraints.NONE;
        c.insets = new Insets(5, 5, 5, 5);
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        panel.add(imageLabel, c);

        JButton loginBTN = new JButton("User Login");
        loginBTN.setBounds(0, 0, 80, 25);
        loginBTN.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new UserLoginGUI();
                frame.setVisible(false);
            }
        });
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        panel.add(loginBTN, c);

        JButton adminBTN = new JButton("Admin Login");
        adminBTN.setBounds(0, 0, 80, 25);
        adminBTN.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new AdminLoginGUI();
                frame.setVisible(false);
            }
        });
        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 1;
        panel.add(adminBTN, c);

        frame.setVisible(true);
    }
}
