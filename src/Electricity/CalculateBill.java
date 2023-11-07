package Electricity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;

public class CalculateBill extends JFrame implements ActionListener {

    JLabel calculateElectricityBillLabel, meterNoLabel, nameLabel, nameDisplayLabel, addressLabel, addressDisplayLabel, unitsConsumedLabel, monthLabel;
    JTextField textField;
    Choice meterChoice, monthChoice;
    JButton submitButton, cancelButton;
    JPanel panel;

    CalculateBill() {
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(173, 216, 230));

        calculateElectricityBillLabel = new JLabel("Calculate Electricity Bill");
        calculateElectricityBillLabel.setBounds(30, 10, 400, 30);

        meterNoLabel = new JLabel("Meter No");
        meterNoLabel.setBounds(60, 70, 100, 30);

        nameLabel = new JLabel("Name");
        nameLabel.setBounds(60, 120, 100, 30);

        addressLabel = new JLabel("Address");
        addressLabel.setBounds(60, 170, 100, 30);

        unitsConsumedLabel = new JLabel("Units Consumed");
        unitsConsumedLabel.setBounds(60, 220, 100, 30);

        monthLabel = new JLabel("Month");
        monthLabel.setBounds(60, 270, 100, 20);

        meterChoice = new Choice();
        meterChoice.setBounds(200, 70, 180, 20);
        try {
            Connect connect = new Connect();
            ResultSet resultSet = connect.statement.executeQuery("select * from customer");
            while (resultSet.next()) {
                meterChoice.add(resultSet.getString("meter"));
            }
        } catch (Exception e) {
        }

        nameDisplayLabel = new JLabel();
        nameDisplayLabel.setBounds(200, 120, 180, 20);
        panel.add(nameDisplayLabel);

        addressDisplayLabel = new JLabel();
        addressDisplayLabel.setBounds(200, 170, 180, 20);
        panel.add(addressDisplayLabel);

        try {
            Connect connect = new Connect();
            ResultSet resultSet = connect.statement.executeQuery("select * from customer where meter = '" + meterChoice.getSelectedItem() + "'");
            while (resultSet.next()) {
                nameDisplayLabel.setText(resultSet.getString("name"));
                addressDisplayLabel.setText(resultSet.getString("address"));
            }
        } catch (Exception e) {
        }
        meterChoice.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent itemEvent) {
                try {
                    Connect connect = new Connect();
                    ResultSet resultSet = connect.statement.executeQuery("select * from customer where meter = '" + meterChoice.getSelectedItem() + "'");
                    while (resultSet.next()) {
                        nameDisplayLabel.setText(resultSet.getString("name"));
                        addressDisplayLabel.setText(resultSet.getString("address"));
                    }
                } catch (Exception e) {
                }
            }
        });

        textField = new JTextField();
        textField.setBounds(200, 220, 180, 20);

        monthChoice = new Choice();
        monthChoice.setBounds(200, 270, 180, 20);
        monthChoice.add("January");
        monthChoice.add("February");
        monthChoice.add("March");
        monthChoice.add("April");
        monthChoice.add("May");
        monthChoice.add("June");
        monthChoice.add("July");
        monthChoice.add("August");
        monthChoice.add("September");
        monthChoice.add("October");
        monthChoice.add("November");
        monthChoice.add("December");

        submitButton = new JButton("Submit");
        submitButton.setBounds(100, 350, 100, 25);
        submitButton.setBackground(Color.BLACK);
        submitButton.setForeground(Color.WHITE);

        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(230, 350, 100, 25);
        cancelButton.setBackground(Color.BLACK);
        cancelButton.setForeground(Color.WHITE);

        ImageIcon imageIcon1 = new ImageIcon(ClassLoader.getSystemResource("icon/hicon2.jpg"));
        Image image = imageIcon1.getImage().getScaledInstance(180, 270, Image.SCALE_DEFAULT);
        ImageIcon imageIcon2 = new ImageIcon(image);
        JLabel imageLabel = new JLabel(imageIcon2);

        calculateElectricityBillLabel.setFont(new Font("Senserif", Font.PLAIN, 26));
        //Move the label to center
        calculateElectricityBillLabel.setHorizontalAlignment(JLabel.CENTER);

        panel.add(calculateElectricityBillLabel);
        panel.add(meterNoLabel);
        panel.add(nameLabel);
        panel.add(addressLabel);
        panel.add(meterChoice);
        panel.add(monthLabel);
        panel.add(monthChoice);
        panel.add(unitsConsumedLabel);
        panel.add(textField);
        panel.add(submitButton);
        panel.add(cancelButton);

        setLayout(new BorderLayout(30, 30));
        add(panel, "Center");
        add(imageLabel, "West");

        submitButton.addActionListener(this);
        cancelButton.addActionListener(this);

        getContentPane().setBackground(Color.WHITE);
        setSize(750, 500);
        setLocation(550, 220);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == submitButton) {
            String meter_no = meterChoice.getSelectedItem();
            String units = textField.getText();
            String month = monthChoice.getSelectedItem();

            int units_consumed = Integer.parseInt(units);

            int total_bill = 0;
            try {
                Connect connect = new Connect();
                ResultSet resultSet = connect.statement.executeQuery("select * from tax");
                while (resultSet.next()) {
                    total_bill = units_consumed * Integer.parseInt(resultSet.getString("cost_per_unit"));
                    total_bill += Integer.parseInt(resultSet.getString("meter_rent"));
                    total_bill += Integer.parseInt(resultSet.getString("service_charge"));
                    total_bill += Integer.parseInt(resultSet.getString("service_tax"));
                    total_bill += Integer.parseInt(resultSet.getString("swacch_bharat_cess"));
                    total_bill += Integer.parseInt(resultSet.getString("fixed_tax"));
                }
            } catch (Exception e) {
            }

            String query = "insert into bill values('" + meter_no + "','" + month + "','" + units + "','" + total_bill + "', 'Not Paid')";
            try {
                Connect connect = new Connect();
                connect.statement.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Customer Bill Updated Successfully");
                this.setVisible(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (actionEvent.getSource() == cancelButton) {
            this.setVisible(false);
        }
    }

    public static void main(String[] args) {
        new CalculateBill().setVisible(true);
    }
}