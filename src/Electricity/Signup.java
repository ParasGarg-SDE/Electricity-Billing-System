package Electricity;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;

public class Signup extends JFrame implements ActionListener {
    JLabel usernameLabel, nameLabel, passwordLabel, createAccountAsLabel, meterNoLabel;
    JTextField usernameTextField, nameTextField, passwordTextField, meterNoTextField;
    Choice userChoice;
    JButton createButton, backButton;

    Signup() {
        setBounds(600, 250, 700, 400);

        JPanel panel = new JPanel();
        panel.setBounds(30, 30, 650, 300);
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);
        panel.setForeground(new Color(34, 139, 34));
        panel.setBorder(new TitledBorder(new LineBorder(new Color(173, 216, 230), 2), "Create-Account", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(173, 216, 230)));
        add(panel);

        usernameLabel = new JLabel("Username");
        usernameLabel.setForeground(Color.DARK_GRAY);
        usernameLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        usernameLabel.setBounds(100, 50, 100, 20);
        panel.add(usernameLabel);

        usernameTextField = new JTextField();
        usernameTextField.setBounds(260, 50, 150, 20);
        panel.add(usernameTextField);

        nameLabel = new JLabel("Name");
        nameLabel.setForeground(Color.DARK_GRAY);
        nameLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        nameLabel.setBounds(100, 90, 100, 20);
        panel.add(nameLabel);

        nameTextField = new JTextField();
        nameTextField.setBounds(260, 90, 150, 20);
        panel.add(nameTextField);

        passwordLabel = new JLabel("Password");
        passwordLabel.setForeground(Color.DARK_GRAY);
        passwordLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        passwordLabel.setBounds(100, 130, 100, 20);
        panel.add(passwordLabel);

        passwordTextField = new JTextField();
        passwordTextField.setBounds(260, 130, 150, 20);
        panel.add(passwordTextField);

        createAccountAsLabel = new JLabel("Create Account As");
        createAccountAsLabel.setForeground(Color.DARK_GRAY);
        createAccountAsLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        createAccountAsLabel.setBounds(100, 170, 140, 20);
        panel.add(createAccountAsLabel);

        meterNoLabel = new JLabel("Meter Number");
        meterNoLabel.setForeground(Color.DARK_GRAY);
        meterNoLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        meterNoLabel.setBounds(100, 210, 100, 20);
        meterNoLabel.setVisible(false);
        panel.add(meterNoLabel);

        meterNoTextField = new JTextField();
        meterNoTextField.setBounds(260, 210, 150, 20);
        meterNoTextField.setVisible(false);
        panel.add(meterNoTextField);

        userChoice = new Choice();
        userChoice.add("Admin");
        userChoice.add("Customer");
        userChoice.setBounds(260, 170, 150, 20);
        panel.add(userChoice);

        userChoice.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent itemEvent) {
                String user = userChoice.getSelectedItem();
                if (user.equals("Customer")) {
                    meterNoLabel.setVisible(true);
                    meterNoTextField.setVisible(true);
                } else {
                    meterNoLabel.setVisible(false);
                    meterNoTextField.setVisible(false);
                }
            }
        });

        createButton = new JButton("Create");
        createButton.setBackground(Color.BLACK);
        createButton.setForeground(Color.WHITE);
        createButton.setBounds(140, 290, 120, 30);
        createButton.addActionListener(this);
        panel.add(createButton);

        backButton = new JButton("Back");
        backButton.setBackground(Color.BLACK);
        backButton.setForeground(Color.WHITE);
        backButton.setBounds(300, 290, 120, 30);
        backButton.addActionListener(this);
        panel.add(backButton);

        ImageIcon imageIcon1 = new ImageIcon(ClassLoader.getSystemResource("icon/signupImage.png"));
        Image image = imageIcon1.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
        ImageIcon imageIcon2 = new ImageIcon(image);
        JLabel imageLabel = new JLabel(imageIcon2);
        imageLabel.setBounds(450, 30, 250, 250);
        panel.add(imageLabel);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == createButton) {
            String userName = usernameTextField.getText();
            String name = nameTextField.getText();
            String password = passwordTextField.getText();
            String userType = userChoice.getSelectedItem();
            String meterNumber = meterNoTextField.getText();

            try {
                Connect connect = new Connect();
                String query = null;
                if (userType.equals("Admin")) {
                    query = "insert into login values('" + meterNumber + "', '" + userName + "', '" + name + "', '" + password + "', '" + userType + "')";
                } else {
                    query = "update login set username = '" + userName + "', name = '" + name + "', password = '" + password + "', user = '" + userType + "' where meter_no = '" + meterNumber + "'";
                }
                connect.statement.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Account Created Successfully");
                this.setVisible(false);
                new Login().setVisible(true);
            } catch (Exception e) {
            }
        } else if (actionEvent.getSource() == backButton) {
            this.setVisible(false);
            new Login().setVisible(true);
        }
    }

    public static void main(String[] args) {
        new Signup().setVisible(true);
    }
}