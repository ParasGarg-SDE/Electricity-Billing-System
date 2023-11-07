package Electricity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Login extends JFrame implements ActionListener {
    JLabel usernameLabel, passwordLabel, personImageLabel, loggingInAsLabel;
    JTextField usernameTextField;
    JPasswordField passwordField;
    JButton loginButton, cancelButton, signupButton;
    Choice choice;

    Login() {
        super("Login Page");
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        usernameLabel = new JLabel("Username");
        usernameLabel.setBounds(300, 20, 100, 20);
        add(usernameLabel);

        passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(300, 60, 100, 20);
        add(passwordLabel);

        usernameTextField = new JTextField(15);
        usernameTextField.setBounds(400, 20, 150, 20);
        add(usernameTextField);

        passwordField = new JPasswordField(15);
        passwordField.setBounds(400, 60, 150, 20);
        add(passwordField);

        loggingInAsLabel = new JLabel("Logging in as");
        loggingInAsLabel.setBounds(300, 100, 100, 20);
        add(loggingInAsLabel);

        choice = new Choice();
        choice.add("Admin");
        choice.add("Customer");
        choice.setBounds(400, 100, 150, 20);
        add(choice);

        ImageIcon imageIcon1 = new ImageIcon(ClassLoader.getSystemResource("icon/login.png"));
        Image loginImage = imageIcon1.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT);
        loginButton = new JButton("Login", new ImageIcon(loginImage));
        loginButton.setBounds(330, 160, 100, 20);
        add(loginButton);

        ImageIcon imageIcon2 = new ImageIcon(ClassLoader.getSystemResource("icon/cancel.jpg"));
        Image cancelImage = imageIcon2.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT);
        cancelButton = new JButton("Cancel", new ImageIcon(cancelImage));
        cancelButton.setBounds(450, 160, 100, 20);
        add(cancelButton);

        ImageIcon imageIcon4 = new ImageIcon(ClassLoader.getSystemResource("icon/signup.png"));
        Image signupImage = imageIcon4.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT);
        signupButton = new JButton("Signup", new ImageIcon(signupImage));
        signupButton.setBounds(380, 200, 130, 20);
        add(signupButton);

        loginButton.addActionListener(this);
        cancelButton.addActionListener(this);
        signupButton.addActionListener(this);

        ImageIcon imageIcon3 = new ImageIcon(ClassLoader.getSystemResource("icon/second.jpg"));
        Image personImage = imageIcon3.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
        ImageIcon icon3 = new ImageIcon(personImage);
        personImageLabel = new JLabel(icon3);
        personImageLabel.setBounds(0, 0, 250, 250);
        add(personImageLabel);

        setLayout(new BorderLayout());
        setSize(640, 300);
        setLocation(600, 300);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == loginButton) {
            try {
                Connect connect = new Connect();
                String username = usernameTextField.getText();
                String password = passwordField.getText();
                String userChoice = choice.getSelectedItem();
                String sqlQuery = "select * from login where username = '" + username + "' and password = '" + password + "' and user = '" + userChoice + "'";
                ResultSet resultSet = connect.statement.executeQuery(sqlQuery);
                if (resultSet.next()) {
                    String meterNumber = resultSet.getString("meter_no");
                    new Project(meterNumber, userChoice).setVisible(true);
                    this.setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid login");
                    usernameTextField.setText("");
                    passwordField.setText("");
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("error: " + e);
            }
        } else if (actionEvent.getSource() == cancelButton) {
            this.setVisible(false);
        } else if (actionEvent.getSource() == signupButton) {
            this.setVisible(false);
            new Signup().setVisible(true);
        }

    }

    public static void main(String[] args) {
        new Login().setVisible(true);
    }
}