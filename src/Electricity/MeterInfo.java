package Electricity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MeterInfo extends JFrame implements ActionListener {

    JLabel meterNoLabel, meterLabel, meterLocationLabel, meterTypeLabel, phaseCodeLabel, billTypeLabel, daysLabel, thirtyDaysLabel, noteLabel, defaultNoteLabel, imageLabel;
    Choice meterLocationChoice, meterTypeChoice, phaseCodeChoice, billTypeChoice;
    JButton submitButton, cancelButton;

    MeterInfo(String meter) {
        setLocation(600, 200);
        setSize(700, 500);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(173, 216, 230));

        JLabel titleLabel = new JLabel("Meter Information");
        titleLabel.setBounds(180, 10, 200, 26);
        titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
        panel.add(titleLabel);

        meterNoLabel = new JLabel("Meter Number");
        meterNoLabel.setBounds(100, 80, 100, 20);
        meterLabel = new JLabel(meter);
        meterLabel.setBounds(240, 80, 200, 20);
        panel.add(meterNoLabel);
        panel.add(meterLabel);

        meterLocationLabel = new JLabel("Meter Location");
        meterLocationLabel.setBounds(100, 120, 100, 20);

        meterLocationChoice = new Choice();
        meterLocationChoice.add("Outside");
        meterLocationChoice.add("Inside");
        meterLocationChoice.setBounds(240, 120, 200, 20);
        panel.add(meterLocationLabel);
        panel.add(meterLocationChoice);

        meterTypeLabel = new JLabel();
        meterTypeLabel.setBounds(100, 160, 100, 20);
        meterTypeChoice = new Choice();
        meterTypeChoice.add("Electric Meter");
        meterTypeChoice.add("Solar Meter");
        meterTypeChoice.add("Smart Meter");
        meterTypeChoice.setBounds(240, 160, 200, 20);
        panel.add(meterTypeLabel);
        panel.add(meterLocationChoice);

        phaseCodeLabel = new JLabel("Phase Code");
        phaseCodeLabel.setBounds(100, 200, 100, 20);
        phaseCodeChoice = new Choice();
        phaseCodeChoice.add("011");
        phaseCodeChoice.add("022");
        phaseCodeChoice.add("033");
        phaseCodeChoice.add("044");
        phaseCodeChoice.add("055");
        phaseCodeChoice.add("066");
        phaseCodeChoice.add("077");
        phaseCodeChoice.add("088");
        phaseCodeChoice.add("099");
        phaseCodeChoice.setBounds(240, 200, 200, 20);
        panel.add(phaseCodeLabel);
        panel.add(phaseCodeChoice);

        billTypeLabel = new JLabel("Bill Type");
        billTypeLabel.setBounds(100, 240, 100, 20);
        billTypeChoice = new Choice();
        billTypeChoice.add("Normal");
        billTypeChoice.add("Industrial");
        billTypeChoice.setBounds(240, 240, 200, 20);
        panel.add(billTypeLabel);
        panel.add(billTypeChoice);

        daysLabel = new JLabel("Days");
        daysLabel.setBounds(100, 280, 100, 20);
        thirtyDaysLabel = new JLabel("30 Days");
        thirtyDaysLabel.setBounds(240, 280, 200, 20);
        panel.add(daysLabel);
        panel.add(thirtyDaysLabel);

        noteLabel = new JLabel("Note");
        noteLabel.setBounds(100, 320, 100, 20);
        defaultNoteLabel = new JLabel("By Default Bill is calculated for 30 days only");
        defaultNoteLabel.setBounds(240, 320, 300, 20);
        panel.add(noteLabel);
        panel.add(defaultNoteLabel);

        submitButton = new JButton("Submit");
        submitButton.setBounds(120, 390, 100, 25);
        submitButton.setBackground(Color.BLACK);
        submitButton.setForeground(Color.WHITE);

        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(250, 390, 100, 25);
        cancelButton.setBackground(Color.BLACK);
        cancelButton.setForeground(Color.WHITE);

        panel.add(submitButton);
        panel.add(cancelButton);

        add(panel, "Center");

        ImageIcon imageIcon1 = new ImageIcon(ClassLoader.getSystemResource("icon/hicon1.jpg"));
        Image image = imageIcon1.getImage().getScaledInstance(150, 300, Image.SCALE_DEFAULT);
        ImageIcon imageIcon2 = new ImageIcon(image);
        imageLabel = new JLabel(imageIcon2);

        add(imageLabel, "West");
        //for changing the color of the whole Frame
        getContentPane().setBackground(Color.WHITE);

        submitButton.addActionListener(this);
        cancelButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == submitButton) {
            String meter_number = meterLabel.getText();
            String meter_location = meterLocationChoice.getSelectedItem();
            String meter_type = meterTypeChoice.getSelectedItem();
            String phase_code = phaseCodeChoice.getSelectedItem();
            String bill_type = billTypeChoice.getSelectedItem();
            String days = "30";

            String query1 = "insert into meter_info values('" + meter_number + "','" + meter_location + "','" + meter_type + "','" + phase_code + "','" + bill_type + "','" + days + "')";
            try {
                Connect connect = new Connect();
                connect.statement.executeUpdate(query1);
                JOptionPane.showMessageDialog(null, "Meter Info Added Successfully");
                this.setVisible(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (actionEvent.getSource() == cancelButton) {
            this.setVisible(false);
        }
    }

    public static void main(String[] args) {
        new MeterInfo("").setVisible(true);
    }
}