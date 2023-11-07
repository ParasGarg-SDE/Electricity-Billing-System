package Electricity;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class CustomerDetails extends JFrame implements ActionListener {

    JTable table;
    JButton printButton;
    String x[] = {"Customer Name", "Meter Number", "Address", "City", "State", "Email", "Phone"};
    String y[][] = new String[40][8];
    int i = 0, j = 0;

    CustomerDetails() {
        super("Customer Details");
        setSize(1200, 650);
        setLocation(400, 150);

        try {
            Connect connect = new Connect();
            String query = "select * from customer";
            ResultSet resultSet = connect.statement.executeQuery(query);
            while (resultSet.next()) {
                y[i][j++] = resultSet.getString("name");
                y[i][j++] = resultSet.getString("meter");
                y[i][j++] = resultSet.getString("address");
                y[i][j++] = resultSet.getString("city");
                y[i][j++] = resultSet.getString("state");
                y[i][j++] = resultSet.getString("email");
                y[i][j++] = resultSet.getString("phone");
                i++;
                j = 0;
            }
            table = new JTable(y, x);
        } catch (Exception e) {
            e.printStackTrace();
        }

        printButton = new JButton("Print");
        add(printButton, "South");
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);
        printButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        try {
            table.print();
        } catch (Exception e) {
        }
    }

    public static void main(String[] args) {
        new CustomerDetails().setVisible(true);
    }
}