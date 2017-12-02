package bankatm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ATMFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	
	// Tracks if saving or checking is selected
    int selectedAccount = 0;
    
    public ATMFrame() {
        //JFrame setup
        super("ATM");
        setLocationRelativeTo(null);        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Initializes all the JPanels
        JPanel superPanel = new JPanel();
        JPanel loginPanel = new JPanel();
        JPanel buttons = new JPanel();
        JPanel buttonsRow2 = new JPanel();
        JPanel radios = new JPanel();
        JPanel field =  new JPanel();
        
        // Set JPanel layout
        superPanel.setLayout(new BorderLayout());
        loginPanel.setLayout(new FlowLayout());
        buttons.setLayout(new FlowLayout(FlowLayout.CENTER, 25, 5));
        buttonsRow2.setLayout(new FlowLayout());
        radios.setLayout(new FlowLayout());
        
        
        // Button setup
        JButton loginButton = new JButton("Login");
        JButton withdrawButton = new JButton("Withdraw");
        JButton depositButton = new JButton("Deposit");
        JButton transferButton = new JButton("Transfer To");
        JButton balanceButton = new JButton("View Balance");
        
        JRadioButton savings = new JRadioButton("Savings", true);
        JRadioButton checking = new JRadioButton("Checking");
        ButtonGroup radioButtons = new ButtonGroup();
        radioButtons.add(savings);
        radioButtons.add(checking);
        
        JTextField input = new JTextField();
        input.setPreferredSize(new Dimension(200, 25));
        
        // Add components to the panels
        buttons.add(withdrawButton, BorderLayout.WEST);
        buttons.add(depositButton, BorderLayout.EAST);
        buttonsRow2.add(transferButton);
        buttonsRow2.add(balanceButton);
        
        radios.add(savings);
        radios.add(checking);
        
        field.add(input);
        
        loginPanel.add(loginButton);
        
        // Account class setup
        Account account = new Account(500, 0);
        
        // Login button setup
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                remove(loginPanel);
                getContentPane().add(superPanel, BorderLayout.NORTH);
                getContentPane().add(field, BorderLayout.CENTER);
                setLocationRelativeTo(null);
                pack();
            }
        });
        //Withdraw button setup
        withdrawButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double withdrawalAmount = 0; 
                try {
                    withdrawalAmount = Integer.parseInt(input.getText());
                    String text = account.withdraw(selectedAccount, withdrawalAmount);
                    JOptionPane.showMessageDialog(null, text, "Withdraw", 
                            JOptionPane.INFORMATION_MESSAGE);
                }
                catch (NumberFormatException nfexc) {
                    JOptionPane.showMessageDialog(null, "Input is not a number."
                            + " Please try again", 
                            "Non-numerical Input", JOptionPane.ERROR_MESSAGE);
                }
                catch (InsufficientFundsException ifexc) {
                    JOptionPane.showMessageDialog(null, "Insufficient funds found to complete "
                            + "transaction. Please try again", 
                            "Insufficient Funds", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        //Deposit button setup
        depositButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double depositAmount = 0;
                try {
                    depositAmount = Double.parseDouble(input.getText());
                    String text = account.deposit(selectedAccount, depositAmount);
                    JOptionPane.showMessageDialog(null, text, "Deposit", 
                            JOptionPane.INFORMATION_MESSAGE);
                }
                catch (NumberFormatException nfexc) {
                    JOptionPane.showMessageDialog(null, "Input is not a number."
                            + " Please try again", 
                            "Non-numerical Input", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        //Transfer button setup
        transferButton.addActionListener( new ActionListener() {
            public void actionPerformed (ActionEvent e) {
                double transferAmount = 0;
                
                try {
                    transferAmount = Double.parseDouble(input.getText());
                    String text = account.transfer(selectedAccount, transferAmount);
                    JOptionPane.showMessageDialog(null, text, "Transfer", 
                            JOptionPane.INFORMATION_MESSAGE);
                }
                catch (InsufficientFundsException exc) {
                    JOptionPane.showMessageDialog(null, "Insufficient funds found to complete "
                            + "transaction. Please try again", 
                            "Insufficient Funds", JOptionPane.ERROR_MESSAGE);
                    
                }
                catch (NumberFormatException nfexc) {
                    JOptionPane.showMessageDialog(null, "Input is not a number."
                            + " Please try again", 
                            "Non-numerical Input", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        //Balance button setup, here you can view the balance of the accounts
        balanceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String text = account.displayBalance(selectedAccount);
                JOptionPane.showMessageDialog(null, text, "Balance", 
                            JOptionPane.INFORMATION_MESSAGE);
            }
        }); 
        //Savings radio button setup
        savings.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                selectedAccount = 0;
            }
        });
        //Checking radio button setup
        checking.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                selectedAccount = 1;
            }
        });
        
        // Adding subpanels for the button groups
        superPanel.add(buttons, BorderLayout.NORTH);
        superPanel.add(buttonsRow2, BorderLayout.CENTER);
        superPanel.add(radios, BorderLayout.SOUTH);
        
        getContentPane().add(loginPanel, BorderLayout.NORTH);
        add(field, BorderLayout.SOUTH);
        
        
    }

}