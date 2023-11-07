package Electricity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class NewCustomer extends JFrame implements ActionListener {

    JLabel nameLabel, meterNoLabel, meterLabel, addressLabel, cityLabel, stateLabel, emailLabel, phoneNoLabel, imageLabel;
    JTextField nameTextField, addressTextField, cityTextField, stateTextField, emailTextField, phoneNoTextField;
    JButton nextButton, cancelButton;

    NewCustomer() {
        setLocation(600, 200);
        setSize(700, 500);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);
        panel.setBackground(new Color(173, 216, 230));

        JLabel titleLable = new JLabel("New Customer");
        titleLable.setBounds(180, 10, 200, 26);
        titleLable.setFont(new Font("Tahoma", Font.PLAIN, 24));
        panel.add(titleLable);

        nameLabel = new JLabel("Customer Name");
        nameLabel.setBounds(100, 80, 100, 20);
        nameTextField = new JTextField();
        nameTextField.setBounds(240, 80, 200, 20);
        panel.add(nameLabel);
        panel.add(nameTextField);

        meterNoLabel = new JLabel("Meter No");
        meterNoLabel.setBounds(100, 120, 100, 20);
        meterLabel = new JLabel();
        meterLabel.setBounds(240, 120, 200, 20);
        panel.add(meterNoLabel);
        panel.add(meterLabel);

        addressLabel = new JLabel("Address");
        addressLabel.setBounds(100, 160, 100, 20);
        addressTextField = new JTextField();
        addressTextField.setBounds(240, 160, 200, 20);
        panel.add(addressLabel);
        panel.add(addressTextField);

        cityLabel = new JLabel("City");
        cityLabel.setBounds(100, 200, 100, 20);
        cityTextField = new JTextField();
        cityTextField.setBounds(240, 200, 200, 20);
        panel.add(cityLabel);
        panel.add(cityTextField);

        stateLabel = new JLabel("State");
        stateLabel.setBounds(100, 240, 100, 20);
        stateTextField = new JTextField();
        stateTextField.setBounds(240, 240, 200, 20);
        panel.add(stateLabel);
        panel.add(stateTextField);

        emailLabel = new JLabel("Email");
        emailLabel.setBounds(100, 280, 100, 20);
        emailTextField = new JTextField();
        emailTextField.setBounds(240, 280, 200, 20);
        panel.add(emailLabel);
        panel.add(emailTextField);

        phoneNoLabel = new JLabel("Phone Number");
        phoneNoLabel.setBounds(100, 320, 100, 20);
        phoneNoTextField = new JTextField();
        phoneNoTextField.setBounds(240, 320, 200, 20);
        panel.add(phoneNoLabel);
        panel.add(phoneNoTextField);

        nextButton = new JButton("Next");
        nextButton.setBounds(120, 390, 100, 25);
        nextButton.setBackground(Color.BLACK);
        nextButton.setForeground(Color.WHITE);

        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(250, 390, 100, 25);
        cancelButton.setBackground(Color.BLACK);
        cancelButton.setForeground(Color.WHITE);

        panel.add(nextButton);
        panel.add(cancelButton);
        setLayout(new BorderLayout());
        add(panel, "Center");

        ImageIcon imageIcon1 = new ImageIcon(ClassLoader.getSystemResource("icon/hicon1.jpg"));
        Image image = imageIcon1.getImage().getScaledInstance(150, 300, Image.SCALE_DEFAULT);
        ImageIcon imageIcon2 = new ImageIcon(image);
        imageLabel = new JLabel(imageIcon2);
        add(imageLabel, "West");
        //for changing the color of the whole Frame
        getContentPane().setBackground(Color.WHITE);

        nextButton.addActionListener(this);
        cancelButton.addActionListener(this);

        Random random = new Random();
        long first = (random.nextLong() % 1000000);
        meterLabel.setText("" + Math.abs(first));
    }

    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == nextButton) {
            String name = nameTextField.getText();
            String meter = meterLabel.getText();
            String address = addressTextField.getText();
            String state = stateTextField.getText();
            String city = cityTextField.getText();
            String email = emailTextField.getText();
            String phone = phoneNoTextField.getText();

            String query1 = "insert into customer values('" + name + "','" + meter + "','" + address + "','" + city + "','" + state + "','" + email + "','" + phone + "')";
            String query2 = "insert into login values('" + meter + "', '', '', '', '')";

            try {
                Connect connect = new Connect();
                connect.statement.executeUpdate(query1);
                connect.statement.executeUpdate(query2);
                JOptionPane.showMessageDialog(null, "Customer Details Added Successfully");
                this.setVisible(false);
                new MeterInfo(meter).setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (actionEvent.getSource() == cancelButton) {
            this.setVisible(false);
        }
    }

    public static void main(String[] args) {
        new NewCustomer().setVisible(true);
    }
}