package Electricity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class Project extends JFrame implements ActionListener {

    String meterNumber;

    public Project(String meterNumber, String userChoice) {
        super("Electricity Billing System");
        this.meterNumber = meterNumber;
        setSize(1920, 1030);

        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/elect1.jpg"));
        Image image = imageIcon.getImage().getScaledInstance(1900, 950, Image.SCALE_DEFAULT);
        JLabel imageLabel = new JLabel(new ImageIcon(image));
        add(imageLabel);

        //First Column
        JMenuBar menuBar = new JMenuBar();
        JMenu master = new JMenu("Master");

        JMenuItem newCustomer = new JMenuItem("New Customer");
        JMenuItem customerDetails = new JMenuItem("Customer Details");
        JMenuItem depositDetails = new JMenuItem("Deposit Details");
        JMenuItem calculateBill = new JMenuItem("Calculate Bill");

        master.setForeground(Color.BLUE);

        //JMenu - Customer Details
        newCustomer.setFont(new Font("monospaced", Font.PLAIN, 12));
        ImageIcon imageIcon1 = new ImageIcon(ClassLoader.getSystemResource("icon/icon1.png"));
        Image image1 = imageIcon1.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        newCustomer.setIcon(new ImageIcon(image1));
        newCustomer.setMnemonic('D');
        newCustomer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
        newCustomer.setBackground(Color.WHITE);

        //JMenu - Meter Details
        customerDetails.setFont(new Font("monospaced", Font.PLAIN, 12));
        ImageIcon imageIcon2 = new ImageIcon(ClassLoader.getSystemResource("icon/icon2.png"));
        Image image2 = imageIcon2.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        customerDetails.setIcon(new ImageIcon(image2));
        customerDetails.setMnemonic('M');
        customerDetails.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, ActionEvent.CTRL_MASK));
        customerDetails.setBackground(Color.WHITE);

        //JMenu - Deposit Details
        depositDetails.setFont(new Font("monospaced", Font.PLAIN, 12));
        ImageIcon imageIcon3 = new ImageIcon(ClassLoader.getSystemResource("icon/icon3.png"));
        Image image3 = imageIcon3.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        depositDetails.setIcon(new ImageIcon(image3));
        depositDetails.setMnemonic('N');
        depositDetails.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        depositDetails.setBackground(Color.WHITE);

        //JMenu -  Calculate Bill
        calculateBill.setFont(new Font("monospaced", Font.PLAIN, 12));
        ImageIcon imageIcon5 = new ImageIcon(ClassLoader.getSystemResource("icon/icon5.png"));
        Image image5 = imageIcon5.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        calculateBill.setIcon(new ImageIcon(image5));
        calculateBill.setMnemonic('B');
        calculateBill.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, ActionEvent.CTRL_MASK));
        calculateBill.setBackground(Color.WHITE);

        newCustomer.addActionListener(this);
        customerDetails.addActionListener(this);
        depositDetails.addActionListener(this);
        calculateBill.addActionListener(this);

        //Second Column
        JMenu info = new JMenu("Information");
        JMenuItem updateInfo = new JMenuItem("Update Information");
        JMenuItem viewInfo = new JMenuItem("View Information");

        info.setBackground(Color.RED);

        //Pay Bill
        updateInfo.setFont(new Font("monospaced", Font.PLAIN, 12));
        ImageIcon imageIcon4_1 = new ImageIcon(ClassLoader.getSystemResource("icon/icon4.png"));
        Image image4_1 = imageIcon4_1.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        updateInfo.setIcon(new ImageIcon(image4_1));
        updateInfo.setMnemonic('P');
        updateInfo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
        updateInfo.setBackground(Color.WHITE);

        //Last Bill
        viewInfo.setFont(new Font("monospaced", Font.PLAIN, 12));
        ImageIcon imageIcon4_2 = new ImageIcon(ClassLoader.getSystemResource("icon/icon6.png"));
        Image image4_2 = imageIcon4_2.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        viewInfo.setIcon(new ImageIcon(image4_2));
        viewInfo.setMnemonic('L');
        viewInfo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.CTRL_MASK));
        viewInfo.setBackground(Color.WHITE);

        updateInfo.addActionListener(this);
        viewInfo.addActionListener(this);

        //------------------------------------------------------------------

        //second column
        JMenu user = new JMenu("User");
        JMenuItem payBill = new JMenuItem("Pay Bill");
        JMenuItem billDetails = new JMenuItem("Bill Details");

        user.setForeground(Color.RED);

        //Pay Bill
        payBill.setFont(new Font("monospaced", Font.PLAIN, 12));
        ImageIcon imageIcon4 = new ImageIcon(ClassLoader.getSystemResource("icon/icon4.png"));
        Image image4 = imageIcon4.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        payBill.setIcon(new ImageIcon(image4));
        payBill.setMnemonic('P');
        payBill.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
        payBill.setBackground(Color.WHITE);

        //Last Bill
        billDetails.setFont(new Font("monospaced", Font.PLAIN, 12));
        ImageIcon imageIcon6 = new ImageIcon(ClassLoader.getSystemResource("icon/icon6.png"));
        Image image6 = imageIcon6.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        billDetails.setIcon(new ImageIcon(image6));
        billDetails.setMnemonic('L');
        billDetails.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.CTRL_MASK));
        billDetails.setBackground(Color.WHITE);

        payBill.addActionListener(this);
        billDetails.addActionListener(this);

        //---------------------------------------------------------------------------
        //Third Column
        JMenu report = new JMenu("Report");
        JMenuItem generateBill = new JMenuItem("Generate Bill");

        report.setForeground(Color.BLUE);

        generateBill.setFont(new Font("monospaced", Font.PLAIN, 12));
        ImageIcon imageIcon7 = new ImageIcon(ClassLoader.getSystemResource("icon/icon7.png"));
        Image image7 = imageIcon7.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        generateBill.setIcon(new ImageIcon(image7));
        generateBill.setMnemonic('R');
        generateBill.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));
        generateBill.setBackground(Color.WHITE);

        generateBill.addActionListener(this);

        //--------------------------------------------------------------------
        //Fourth Column
        JMenu utility = new JMenu("Utility");
        JMenuItem notepad = new JMenuItem("Notepad");
        JMenuItem calculator = new JMenuItem("Calculator");
        JMenuItem webBrowser = new JMenuItem("Web Browser");

        utility.setForeground(Color.RED);

        //Notepad
        notepad.setFont(new Font("monospaced", Font.PLAIN, 12));
        ImageIcon imageIcon8 = new ImageIcon(ClassLoader.getSystemResource("icon/icon12.png"));
        Image image8 = imageIcon8.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        notepad.setIcon(new ImageIcon(image8));
        notepad.setMnemonic('N');
        notepad.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
        notepad.setBackground(Color.WHITE);

        //Calculator
        calculator.setFont(new Font("monospaced", Font.PLAIN, 12));
        ImageIcon imageIcon9 = new ImageIcon(ClassLoader.getSystemResource("icon/icon9.png"));
        Image image9 = imageIcon9.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        calculator.setIcon(new ImageIcon(image9));
        calculator.setMnemonic('X');
        calculator.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
        calculator.setBackground(Color.WHITE);

        //Web Browser
        webBrowser.setFont(new Font("monospaced", Font.PLAIN, 12));
        ImageIcon imageIcon10 = new ImageIcon(ClassLoader.getSystemResource("icon/icon10.png"));
        Image image10 = imageIcon10.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        webBrowser.setIcon(new ImageIcon(image10));
        webBrowser.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, ActionEvent.CTRL_MASK));
        webBrowser.setBackground(Color.WHITE);

        notepad.addActionListener(this);
        calculator.addActionListener(this);
        webBrowser.addActionListener(this);

        //--------------------------------------------------------------------------------

        //Fifth Column
        JMenu exit = new JMenu("Logout");
        JMenuItem logout = new JMenuItem("Logout");
        exit.setForeground(Color.BLUE);

        //Logout
        logout.setFont(new Font("monospaced", Font.PLAIN, 12));
        ImageIcon imageIcon11 = new ImageIcon(ClassLoader.getSystemResource("icon/icon11.png"));
        Image image11 = imageIcon11.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        logout.setIcon(new ImageIcon(image11));
        logout.setMnemonic('Z');
        logout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
        logout.setBackground(Color.WHITE);

        logout.addActionListener(this);

        //----------------------------------------------------------------------

        master.add(newCustomer);
        master.add(customerDetails);
        master.add(depositDetails);
        master.add(calculateBill);

        info.add(updateInfo);
        info.add(viewInfo);

        user.add(payBill);
        user.add(billDetails);

        report.add(generateBill);

        utility.add(notepad);
        utility.add(calculator);
        utility.add(webBrowser);

        exit.add(logout);

        if (userChoice.equalsIgnoreCase("Admin")) {
            menuBar.add(master);
        } else {
            menuBar.add(info);
            menuBar.add(user);
            menuBar.add(report);
        }
        menuBar.add(utility);
        menuBar.add(exit);

        setJMenuBar(menuBar);

        setFont(new Font("Senserif", Font.BOLD, 16));
        setLayout(new FlowLayout());
        setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String action = actionEvent.getActionCommand();
        if (action.equalsIgnoreCase("Customer Details")) {
            new CustomerDetails().setVisible(true);
        } else if (action.equalsIgnoreCase("New Customer")) {
            new NewCustomer().setVisible(true);
        } else if (action.equalsIgnoreCase("Calculate Bill")) {
            new CalculateBill().setVisible(true);
        } else if (action.equalsIgnoreCase("Pay Bill")) {
            new PayBill(meterNumber).setVisible(true);
        } else if (action.equalsIgnoreCase("Notepad")) {
            try {
                Runtime.getRuntime().exec("notepad.exe");
            } catch (Exception e) {
            }
        } else if (action.equalsIgnoreCase("Calculator")) {
            try {
                Runtime.getRuntime().exec("calc.exe");
            } catch (Exception e) {
            }
        } else if (action.equalsIgnoreCase("Web Browser")) {
            try {
                Runtime.getRuntime().exec("C:\\Program Files (x86)\\Microsoft\\Edge Dev\\Application\\msedge.exe");
            } catch (Exception e) {
            }
        } else if (action.equalsIgnoreCase("Logout")) {
            this.setVisible(false);
            new Login().setVisible(true);
        } else if (action.equalsIgnoreCase("Generate Bill")) {
            new GenerateBill(meterNumber).setVisible(true);
        } else if (action.equalsIgnoreCase("Deposit Details")) {
            new DepositDetails().setVisible(true);
        } else if (action.equalsIgnoreCase("View Information")) {
            new ViewInformation(meterNumber).setVisible(true);
        } else if (action.equalsIgnoreCase("Update Information")) {
            new UpdateInformation(meterNumber).setVisible(true);
        } else if (action.equalsIgnoreCase("Bill Details")) {
            new BillDetails(meterNumber).setVisible(true);
        }
    }

    public static void main(String[] args) {
        new Project("", "").setVisible(true);
    }
}