package Electricity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;

public class PayBill extends JFrame implements ActionListener {

    JLabel titleLabel, meterNoLabel, meterNoDisplayLabel, nameLabel, nameDisplayLabel, monthLabel, unitsLabel, unitsDisplayLabel, totalBillLabel, totalBillDisplayLabel, statusLabel, statusDisplayLabel;
    Choice monthChoice;
    Button payButton, backButton;
    String meter;

    PayBill(String meter) {
        this.meter = meter;
        setLayout(null);
        setBounds(550, 220, 900, 600);

        titleLabel = new JLabel("Electricity Bill");
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
        titleLabel.setBounds(120, 5, 400, 30);
        add(titleLabel);

        meterNoLabel = new JLabel("Meter No");
        meterNoLabel.setBounds(35, 80, 200, 20);
        add(meterNoLabel);

        meterNoDisplayLabel = new JLabel();
        meterNoDisplayLabel.setBounds(300, 80, 200, 20);
        add(meterNoDisplayLabel);

        nameLabel = new JLabel("Name");
        nameLabel.setBounds(35, 140, 200, 20);
        add(nameLabel);

        nameDisplayLabel = new JLabel();
        nameDisplayLabel.setBounds(300, 140, 200, 20);
        add(nameDisplayLabel);

        monthLabel = new JLabel("Month");
        monthLabel.setBounds(35, 200, 200, 20);
        add(monthLabel);

        monthChoice = new Choice();
        monthChoice.setBounds(300, 200, 200, 20);
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
        add(monthChoice);

        unitsLabel = new JLabel("Units");
        unitsLabel.setBounds(35, 260, 200, 20);
        add(unitsLabel);

        unitsDisplayLabel = new JLabel();
        unitsDisplayLabel.setBounds(300, 260, 200, 20);
        add(unitsDisplayLabel);

        totalBillLabel = new JLabel("Total Bill");
        totalBillLabel.setBounds(35, 320, 200, 20);
        add(totalBillLabel);

        totalBillDisplayLabel = new JLabel();
        totalBillDisplayLabel.setBounds(300, 320, 200, 20);
        add(totalBillDisplayLabel);

        statusLabel = new JLabel("Status");
        statusLabel.setBounds(35, 380, 200, 20);
        add(statusLabel);

        statusDisplayLabel = new JLabel();
        statusDisplayLabel.setBounds(300, 380, 200, 20);
        statusDisplayLabel.setForeground(Color.RED);
        add(statusDisplayLabel);

        try {
            Connect connect = new Connect();
            ResultSet resultSet = connect.statement.executeQuery("select * from customer where meter = '" + meter + "'");
            while (resultSet.next()) {
                meterNoDisplayLabel.setText(resultSet.getString("meter"));
                nameDisplayLabel.setText(resultSet.getString("name"));
            }

            resultSet = connect.statement.executeQuery("select * from bill where meter = '" + meter + "' AND month = 'January' ");
            while (resultSet.next()) {
                unitsDisplayLabel.setText(resultSet.getString("units"));
                totalBillDisplayLabel.setText(resultSet.getString("total_bill"));
                statusDisplayLabel.setText(resultSet.getString("status"));
            }
        } catch (Exception e) {
        }

        monthChoice.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent itemEvent) {
                try {
                    Connect connect = new Connect();
                    ResultSet resultSet = connect.statement.executeQuery("select * from bill where meter = '" + meter + "' AND month = '" + monthChoice.getSelectedItem() + "'");
                    while (resultSet.next()) {
                        unitsDisplayLabel.setText(resultSet.getString("units"));
                        totalBillDisplayLabel.setText(resultSet.getString("total_bill"));
                        statusDisplayLabel.setText(resultSet.getString("status"));
                    }
                } catch (Exception e) {
                }
            }
        });

        payButton = new Button("Pay");
        payButton.setBounds(100, 460, 100, 20);
        add(payButton);
        payButton.setBackground(Color.BLACK);
        payButton.setForeground(Color.WHITE);

        backButton = new Button("Back");
        backButton.setBounds(230, 460, 100, 25);
        add(backButton);
        backButton.setBackground(Color.BLACK);
        backButton.setForeground(Color.WHITE);

        ImageIcon imageIcon1 = new ImageIcon(ClassLoader.getSystemResource("icon/bill.png"));
        Image image = imageIcon1.getImage().getScaledInstance(600, 300, Image.SCALE_DEFAULT);
        ImageIcon imageIcon2 = new ImageIcon(image);
        JLabel imageLabel = new JLabel(imageIcon2);
        imageLabel.setBounds(400, 120, 600, 300);
        add(imageLabel);

        payButton.addActionListener(this);
        backButton.addActionListener(this);

        getContentPane().setBackground(Color.WHITE);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == payButton) {
            try {
                Connect connect = new Connect();
                connect.statement.executeUpdate("update bill set status = 'Paid' where meter = '" + meter + "' AND month = '" + monthChoice.getSelectedItem() + "'");
            } catch (Exception e) {
            }
            this.setVisible(false);
            new Paytm(meter).setVisible(true);
        } else if (actionEvent.getSource() == backButton) {
            this.setVisible(false);
        }
    }

    public static void main(String[] args) {
        new PayBill("").setVisible(true);
    }
}