package Electricity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class About extends JFrame implements ActionListener {

    TextArea aboutProjectTextArea;
    JLabel aboutProjectLabel;
    Font font1, font2, font3;
    JButton exitButton;
    String aboutProject;

    public About() {
        setLayout(null);
        exitButton = new JButton("Exit");
        add(exitButton);
        exitButton.setBounds(180, 430, 120, 20);
        exitButton.addActionListener(this);

        font1 = new Font("RALEWAY", Font.BOLD, 180);
        setFont(font1);

        aboutProject = "                                    About Projects          \n  " + "\nElectricity Billing System is a software-based application " + "developed in Java programming language. The project aims at serving" + "the department of electricity by computerizing the billing system. " + "It mainly focuses on the calculation of Units consumed during the " + "specified time and the money to be paid to electricity offices. " + "This computerized system will make the overall billing system easy, " + "accessible, comfortable and effective for consumers.\n\n";

        aboutProjectTextArea = new TextArea(aboutProject, 10, 40, Scrollbar.VERTICAL);
        aboutProjectTextArea.setEditable(false);
        aboutProjectTextArea.setBounds(20, 100, 450, 300);

        add(aboutProjectTextArea);

        font2 = new Font("RALEWAY", Font.BOLD, 16);
        aboutProjectTextArea.setFont(font2);

        Container contentPane = this.getContentPane();
        aboutProjectTextArea = new TextArea();

        aboutProjectLabel = new JLabel("About Project");
        add(aboutProjectLabel);
        aboutProjectLabel.setBounds(170, 10, 180, 80);
        aboutProjectLabel.setForeground(Color.red);

        font3 = new Font("RALEWAY", Font.BOLD, 20);
        aboutProjectLabel.setFont(font3);

        setBounds(700, 220, 500, 550);
        setLayout(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        dispose();
    }

    public static void main(String[] args) {
        new About().setVisible(true);
    }
}