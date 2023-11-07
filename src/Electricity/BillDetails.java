package Electricity;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;

public class BillDetails extends JFrame {
    JTable billDetailsTable;
    String x[] = {"Meter Number", "Month", "Units", "Total Bill", "Status"};

    String y[][] = new String[40][8];

    BillDetails(String meter) {
        super("Bill Details");
        setSize(700, 650);
        setLocation(600, 150);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        billDetailsTable = new JTable(y, x);

        try {
            Connect connect = new Connect();
            String query = "select * from bill where meter = " + meter;
            ResultSet resultSet = connect.statement.executeQuery(query);
            billDetailsTable.setModel(DbUtils.resultSetToTableModel(resultSet));
        } catch (Exception e) {
            e.printStackTrace();
        }

        JScrollPane scrollPane = new JScrollPane(billDetailsTable);
        scrollPane.setBounds(0, 0, 700, 650);
        add(scrollPane);
    }

    public static void main(String[] args) {
        new BillDetails("").setVisible(true);
    }
}
