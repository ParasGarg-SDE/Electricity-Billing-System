package Electricity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class GenerateBill extends JFrame implements ActionListener {

    JLabel titleLabel, meterLabel;
    JTextArea textArea;
    JButton generateBillButton;
    Choice monthChoice;
    JPanel panel;
    String meter;

    GenerateBill(String meter) {
        this.meter = meter;
        setSize(500, 900);
        setLayout(new BorderLayout());

        panel = new JPanel();

        titleLabel = new JLabel("Generate Bill");

        meterLabel = new JLabel(meter);

        monthChoice = new Choice();
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

        textArea = new JTextArea(50, 15);
        textArea.setText("\n\n\t------- Click on the -------\n\t Generate Bill Button to get\n\tthe bill of the Selected Month\n\n");
        JScrollPane scrollPane = new JScrollPane(textArea);
        textArea.setFont(new Font("Senserif", Font.ITALIC, 18));

        generateBillButton = new JButton("Generate Bill");
        panel.add(titleLabel);
        panel.add(meterLabel);
        panel.add(monthChoice);
        add(panel, "North");
        add(scrollPane, "Center");
        add(generateBillButton, "South");

        generateBillButton.addActionListener(this);
        setLocation(750, 100);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        try {
            Connect connect = new Connect();
            String month = monthChoice.getSelectedItem();
            textArea.setText("\tReliance Power Limited\nELECTRICITY BILL FOR THE MONTH OF " + month + " ,2021\n\n\n");

            ResultSet resultSet = connect.statement.executeQuery("select * from customer where meter=" + meter);
            if (resultSet.next()) {
                textArea.append("\n    Customer Name:" + resultSet.getString("name"));
                textArea.append("\n    Meter Number:  " + resultSet.getString("meter"));
                textArea.append("\n    Address:            " + resultSet.getString("address"));
                textArea.append("\n    State:                 " + resultSet.getString("state"));
                textArea.append("\n    City:                   " + resultSet.getString("city"));
                textArea.append("\n    Email:                " + resultSet.getString("email"));
                textArea.append("\n    Phone Number  " + resultSet.getString("phone"));
                textArea.append("\n-------------------------------------------------------------");
                textArea.append("\n");
            }

            resultSet = connect.statement.executeQuery("select * from meter_info where meter_number = " + meter);
            if (resultSet.next()) {
                textArea.append("\n    Meter Location:" + resultSet.getString("meter_location"));
                textArea.append("\n    Meter Type:      " + resultSet.getString("meter_type"));
                textArea.append("\n    Phase Code:    " + resultSet.getString("phase_code"));
                textArea.append("\n    Bill Type:         " + resultSet.getString("bill_type"));
                textArea.append("\n    Days:               " + resultSet.getString("days"));
                textArea.append("\n");
            }

            resultSet = connect.statement.executeQuery("select * from tax");
            if (resultSet.next()) {
                textArea.append("---------------------------------------------------------------");
                textArea.append("\n\n");
                textArea.append("\n Cost per Unit:             resultSet " + resultSet.getString("cost_per_unit"));
                textArea.append("\n Meter Rent:                resultSet " + resultSet.getString("meter_rent"));
                textArea.append("\n Service Charge:            resultSet " + resultSet.getString("service_charge"));
                textArea.append("\n Service Tax:               resultSet " + resultSet.getString("service_tax"));
                textArea.append("\n Swacch Bharat Cess:        resultSet " + resultSet.getString("swacch_bharat_cess"));
                textArea.append("\n Fixed Tax:                 resultSet " + resultSet.getString("fixed_tax"));
                textArea.append("\n");
            }

            resultSet = connect.statement.executeQuery("select * from bill where meter=" + meter + " AND month = '" + monthChoice.getSelectedItem() + "'");
            if (resultSet.next()) {
                textArea.append("\n    Current Month :\t" + resultSet.getString("month"));
                textArea.append("\n    Units Consumed:\t" + resultSet.getString("units"));
                textArea.append("\n    Total Charges :\t" + resultSet.getString("total_bill"));
                textArea.append("\n---------------------------------------------------------------");
                textArea.append("\n    TOTAL PAYABLE :\t" + resultSet.getString("total_bill"));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new GenerateBill("").setVisible(true);
    }
}