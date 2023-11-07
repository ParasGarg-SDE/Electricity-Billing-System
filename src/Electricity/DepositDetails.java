package Electricity;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class DepositDetails extends JFrame implements ActionListener {

    JTable depositDetailsTable;
    JButton searchButton, printButton;
    JLabel sortByMeterNoLabel, sortByMonthLabel;
    Choice meterChoice, monthChoice;
    String x[] = {"Meter Number", "Month", "Units", "Total bill", "Status"};
    String y[][] = new String[40][8];
    int i = 0, j = 0;

    DepositDetails() {
        super("Deposit Details");
        setSize(700, 750);
        setLocation(600, 150);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        sortByMeterNoLabel = new JLabel("Sort by Meter Number");
        sortByMeterNoLabel.setBounds(20, 20, 150, 20);
        add(sortByMeterNoLabel);

        meterChoice = new Choice();

        sortByMonthLabel = new JLabel("Sort By Month");
        sortByMonthLabel.setBounds(400, 20, 100, 20);
        add(sortByMonthLabel);

        monthChoice = new Choice();

        depositDetailsTable = new JTable(y, x);

        try {
            Connect connect = new Connect();
            String query1 = "select * from bill";
            ResultSet resultSet = connect.statement.executeQuery(query1);

            depositDetailsTable.setModel(DbUtils.resultSetToTableModel(resultSet));

            String query2 = "select * from customer";
            resultSet = connect.statement.executeQuery(query2);
            while (resultSet.next()) {
                meterChoice.add(resultSet.getString("meter"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        meterChoice.setBounds(180, 20, 150, 20);
        add(meterChoice);

        monthChoice.setBounds(520, 20, 150, 20);
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

        searchButton = new JButton("Search");
        searchButton.setBounds(20, 70, 80, 20);
        searchButton.addActionListener(this);
        add(searchButton);

        printButton = new JButton("Print");
        printButton.setBounds(120, 70, 80, 20);
        printButton.addActionListener(this);
        add(printButton);

        JScrollPane scrollPane = new JScrollPane(depositDetailsTable);
        scrollPane.setBounds(0, 100, 700, 650);
        add(scrollPane);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == searchButton) {
            String query = "select * from bill where meter = '" + meterChoice.getSelectedItem() + "' AND month = '" + monthChoice.getSelectedItem() + "'";
            try {
                Connect connect = new Connect();
                ResultSet resultSet = connect.statement.executeQuery(query);
                depositDetailsTable.setModel(DbUtils.resultSetToTableModel(resultSet));
            } catch (Exception e) {
            }
        } else if (actionEvent.getSource() == printButton) {
            try {
                depositDetailsTable.print();
            } catch (Exception e) {
            }

        }
    }

    public static void main(String[] args) {
        new DepositDetails().setVisible(true);
    }
}