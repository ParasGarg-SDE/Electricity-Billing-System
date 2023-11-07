package Electricity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class UpdateInformation extends JFrame implements ActionListener {

    JTextField addressTextField, cityTextField, stateTextField, emailTextField, phoneTextField;
    JLabel titleLabel, nameLabel, nameDisplayLabel, meterNoLabel, meterNoDisplayLabel, addressLabel, cityLabel, stateLabel, emailLabel, phoneLabel;
    JButton updateButton, backButton;
    String meter;

    UpdateInformation(String meter) {
        this.meter = meter;

        setBounds(500, 220, 1050, 450);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        titleLabel = new JLabel("UPDATE CUSTOMER INFORMATION");
        titleLabel.setBounds(110, 0, 400, 30);
        titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        add(titleLabel);

        nameLabel = new JLabel("Name");
        nameLabel.setBounds(30, 70, 100, 20);
        add(nameLabel);

        nameDisplayLabel = new JLabel();
        nameDisplayLabel.setBounds(230, 70, 200, 20);
        add(nameDisplayLabel);

        meterNoLabel = new JLabel("Meter Number");
        meterNoLabel.setBounds(30, 110, 100, 20);
        add(meterNoLabel);

        meterNoDisplayLabel = new JLabel();
        meterNoDisplayLabel.setBounds(230, 110, 200, 20);
        add(meterNoDisplayLabel);

        addressLabel = new JLabel("Address");
        addressLabel.setBounds(30, 150, 100, 20);
        add(addressLabel);

        addressTextField = new JTextField();
        addressTextField.setBounds(230, 150, 200, 20);
        add(addressTextField);

        cityLabel = new JLabel("City");
        cityLabel.setBounds(30, 190, 100, 20);
        add(cityLabel);

        cityTextField = new JTextField();
        cityTextField.setBounds(230, 190, 200, 20);
        add(cityTextField);

        stateLabel = new JLabel("State");
        stateLabel.setBounds(30, 230, 100, 20);
        add(stateLabel);

        stateTextField = new JTextField();
        stateTextField.setBounds(230, 230, 200, 20);
        add(stateTextField);

        emailLabel = new JLabel("Email");
        emailLabel.setBounds(30, 270, 100, 20);
        add(emailLabel);

        emailTextField = new JTextField();
        emailTextField.setBounds(230, 270, 200, 20);
        add(emailTextField);

        phoneLabel = new JLabel("Phone");
        phoneLabel.setBounds(30, 310, 100, 20);
        add(phoneLabel);

        phoneTextField = new JTextField();
        phoneTextField.setBounds(230, 310, 200, 20);
        add(phoneTextField);

        updateButton = new JButton("Update");
        updateButton.setBackground(Color.BLACK);
        updateButton.setForeground(Color.WHITE);
        updateButton.setBounds(70, 360, 100, 25);
        updateButton.addActionListener(this);
        add(updateButton);

        backButton = new JButton("Back");
        backButton.setBackground(Color.BLACK);
        backButton.setForeground(Color.WHITE);
        backButton.setBounds(230, 360, 100, 25);
        backButton.addActionListener(this);
        add(backButton);

        try {
            Connect connect = new Connect();
            ResultSet resultSet = connect.statement.executeQuery("select * from customer where meter = '" + meter + "'");
            while (resultSet.next()) {
                nameDisplayLabel.setText(resultSet.getString(1));
                meterNoDisplayLabel.setText(resultSet.getString(2));
                addressTextField.setText(resultSet.getString(3));
                cityTextField.setText(resultSet.getString(4));
                stateTextField.setText(resultSet.getString(5));
                emailTextField.setText(resultSet.getString(6));
                phoneTextField.setText(resultSet.getString(7));

            }
        } catch (Exception e) {
        }

        ImageIcon imageIcon1 = new ImageIcon(ClassLoader.getSystemResource("icon/update.jpg"));
        Image image = imageIcon1.getImage().getScaledInstance(400, 300, Image.SCALE_DEFAULT);
        ImageIcon imageIcon2 = new ImageIcon(image);
        JLabel imageLabel = new JLabel(imageIcon2);
        imageLabel.setBounds(550, 50, 400, 300);
        add(imageLabel);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == updateButton) {
            String name = nameDisplayLabel.getText();
            String meterNo = meterNoDisplayLabel.getText();
            String address = addressTextField.getText();
            String city = cityTextField.getText();
            String state = stateTextField.getText();
            String email = emailTextField.getText();
            String phone = phoneTextField.getText();

            try {
                Connect connect = new Connect();
                connect.statement.executeUpdate("update customer set address = '" + address + "', city = '" + city + "', state = '" + state + "', email = '" + email + "', phone = '" + phone + "' where meter = '" + meter + "'");
                JOptionPane.showMessageDialog(null, "Details Updated Successfully");
                this.setVisible(false);
            } catch (Exception e) {
            }

        } else if (actionEvent.getSource() == backButton) {
            this.setVisible(false);
        }
    }
}