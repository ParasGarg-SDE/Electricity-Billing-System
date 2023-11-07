package Electricity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class LastBill extends JFrame implements ActionListener {

    JLabel titleLabel;
    JTextArea textArea, meterTextArea;
    JButton generateBillButton;
    JPanel panel;

    LastBill() {
        setSize(500, 900);
        setLayout(new BorderLayout());

        panel = new JPanel();

        titleLabel = new JLabel("Generate Bill");

        meterTextArea = new JTextArea();

        textArea = new JTextArea(50, 15);
        JScrollPane scrollPane = new JScrollPane(textArea);
        textArea.setFont(new Font("Senserif", Font.ITALIC, 18));

        generateBillButton = new JButton("Generate Bill");

        panel.add(titleLabel);
        panel.add(meterTextArea);
        add(panel, "North");
        add(scrollPane, "Center");
        add(generateBillButton, "South");

        generateBillButton.addActionListener(this);
        setLocation(350, 40);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        try {
            Connect connect = new Connect();
            ResultSet resultSet = connect.statement.executeQuery("select * from customer where meter=" + meterTextArea.getSelectedText());
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
            textArea.append("Details of the Last Bills\n\n\n");

            resultSet = connect.statement.executeQuery("select * from bill where meter=" + meterTextArea.getSelectedText());
            while (resultSet.next()) {
                textArea.append("       " + resultSet.getString("month") + "           " + resultSet.getString("amount") + "\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new LastBill().setVisible(true);
    }
}