package Electricity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class ViewInformation extends JFrame implements ActionListener {

    JLabel titleLabel, nameLabel, nameDisplayLabel, meterNoLabel, meterNoDisplayLabel, addressLabel, addressDisplayLabel, cityLabel, cityDisplayLabel, stateLabel, stateDisplayLabel, emailLabel, emailDisplayLabel, phoneLabel, phoneDisplayLabel;
    JButton backButton;

    ViewInformation(String meter) {
        setBounds(600, 250, 850, 650);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        titleLabel = new JLabel("VIEW CUSTOMER INFORMATION");
        titleLabel.setBounds(250, 0, 500, 40);
        titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        add(titleLabel);

        nameLabel = new JLabel("Name");
        nameLabel.setBounds(70, 80, 100, 20);
        add(nameLabel);

        nameDisplayLabel = new JLabel();
        nameDisplayLabel.setBounds(250, 80, 100, 20);
        add(nameDisplayLabel);

        meterNoLabel = new JLabel("Meter Number");
        meterNoLabel.setBounds(70, 140, 100, 20);
        add(meterNoLabel);

        meterNoDisplayLabel = new JLabel();
        meterNoDisplayLabel.setBounds(250, 140, 100, 20);
        add(meterNoDisplayLabel);

        addressLabel = new JLabel("Address");
        addressLabel.setBounds(70, 200, 100, 20);
        add(addressLabel);

        addressDisplayLabel = new JLabel();
        addressDisplayLabel.setBounds(250, 200, 100, 20);
        add(addressDisplayLabel);

        cityLabel = new JLabel("City");
        cityLabel.setBounds(70, 260, 100, 20);
        add(cityLabel);

        cityDisplayLabel = new JLabel();
        cityDisplayLabel.setBounds(250, 260, 100, 20);
        add(cityDisplayLabel);

        stateLabel = new JLabel("State");
        stateLabel.setBounds(500, 80, 100, 20);
        add(stateLabel);

        stateDisplayLabel = new JLabel();
        stateDisplayLabel.setBounds(650, 80, 100, 20);
        add(stateDisplayLabel);

        emailLabel = new JLabel("Email");
        emailLabel.setBounds(500, 140, 100, 20);
        add(emailLabel);

        emailDisplayLabel = new JLabel();
        emailDisplayLabel.setBounds(650, 140, 150, 20);
        add(emailDisplayLabel);

        phoneLabel = new JLabel("Phone");
        phoneLabel.setBounds(500, 200, 100, 20);
        add(phoneLabel);

        phoneDisplayLabel = new JLabel();
        phoneDisplayLabel.setBounds(650, 200, 100, 20);
        add(phoneDisplayLabel);

        try {
            Connect connect = new Connect();
            ResultSet resultSet = connect.statement.executeQuery("select * from customer where meter = '" + meter + "'");
            while (resultSet.next()) {
                nameDisplayLabel.setText(resultSet.getString(1));
                meterNoDisplayLabel.setText(resultSet.getString(2));
                addressDisplayLabel.setText(resultSet.getString(3));
                cityDisplayLabel.setText(resultSet.getString(4));
                stateDisplayLabel.setText(resultSet.getString(5));
                emailDisplayLabel.setText(resultSet.getString(6));
                phoneDisplayLabel.setText(resultSet.getString(7));
            }
        } catch (Exception e) {
        }

        backButton = new JButton("Back");
        backButton.setBackground(Color.BLACK);
        backButton.setForeground(Color.WHITE);
        backButton.setBounds(350, 340, 100, 25);
        backButton.addActionListener(this);
        add(backButton);

        ImageIcon imageIcon1 = new ImageIcon(ClassLoader.getSystemResource("icon/viewcustomer.jpg"));
        Image image = imageIcon1.getImage().getScaledInstance(600, 300, Image.SCALE_DEFAULT);
        ImageIcon imageIcon2 = new ImageIcon(image);
        JLabel imageLabel = new JLabel(imageIcon2);
        imageLabel.setBounds(20, 350, 600, 300);
        add(imageLabel);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        this.setVisible(false);
    }

    public static void main(String[] args) {
        new ViewInformation("").setVisible(true);
    }
}