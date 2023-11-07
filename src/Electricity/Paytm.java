package Electricity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Paytm extends JFrame implements ActionListener {

    String meter;
    JButton backButton;

    Paytm(String meter) {
        this.meter = meter;
        JEditorPane editorPane = new JEditorPane();
        editorPane.setEditable(false);

        backButton = new JButton("Back");
        backButton.setBackground(Color.BLACK);
        backButton.setForeground(Color.WHITE);
        backButton.setBounds(500, 20, 120, 25);
        backButton.addActionListener(this);
        editorPane.add(backButton);

        try {
            editorPane.setPage("https://paytm.com/electricity-bill-payment");
        } catch (Exception e) {
            editorPane.setContentType("text/html");
            editorPane.setText("<html>Could not load</html>");
        }

        JScrollPane scrollPane = new JScrollPane(editorPane);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().add(scrollPane);
        setPreferredSize(new Dimension(800, 600));
        setSize(800, 800);
        setLocation(250, 120);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        this.setVisible(false);
        new PayBill(meter).setVisible(true);
    }

    public static void main(String[] args) {
        new Paytm("").setVisible(true);
    }
}