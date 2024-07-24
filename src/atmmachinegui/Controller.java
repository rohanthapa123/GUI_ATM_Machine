package atmmachinegui;

import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {

    private View view;
    private Model model;

    public Controller(View v, Model m) {
        this.view = v;
        this.model = m;

        view.getLoginButton().addActionListener(new LoginButtonListener());
        view.getBalanceButton().addActionListener(new BalanceButtonListener());
        view.getDepositButton().addActionListener(new DepositButtonListener());
        view.getWithdrawButton().addActionListener(new WithdrawButtonListener());
        view.getFastCashButton().addActionListener(new FastCashButtonListener());
        view.getTransferButton().addActionListener(new TransferButtonListener());
        view.getCancelButton().addActionListener(new CancelButtonListener());

    }

    private class LoginButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String accountNumber = view.getAccountField().getText();
            String pinNumber = new String(view.getPinField().getPassword());

            if (model.AuthenticateUser(accountNumber, pinNumber)) {
                view.getStatusLabel().setText("Login Successful");
                view.showAtmPanel();
            } else {
                view.getStatusLabel().setText("Invaid Creentials");
            }
        }
    }

    private class CancelButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            view.getAccountField().setText("");
            view.getPinField().setText("");
            view.getStatusLabel().setText("");
            view.showLoginPanel();
        }
    }
    private class BalanceButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String accountNumber = view.getAccountField().getText();
            double balance = model.getBalance(accountNumber);
            JOptionPane.showMessageDialog(view.getFrame(), "Current Balance: " + balance);
        }
    }

    private class DepositButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String accountNumber = view.getAccountField().getText();
            String amountStr = JOptionPane.showInputDialog(view.getFrame(), "Enter Amount to deposit");
            double amt = Double.parseDouble(amountStr);

            if (model.depositAmount(amt, accountNumber)) {
                JOptionPane.showMessageDialog(view.getFrame(), "Deposit Successfully");
            } else {
                JOptionPane.showMessageDialog(view.getFrame(), "Failed to Deposit Amount");
            }

        }
    }

    private class WithdrawButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String accountNumber = view.getAccountField().getText();
            String amount = JOptionPane.showInputDialog(view.getFrame(), "Enter amount you want to withdraw");

            if (amount != null && !amount.trim().isEmpty()) {
                try {
                    double amt = Double.parseDouble(amount);
                    if (model.withdrawAmount(amt, accountNumber)) {
                        JOptionPane.showMessageDialog(view.getFrame(), "Withdrawal Successful!");
                    } else {
                        JOptionPane.showMessageDialog(view.getFrame(), "Insufficient Funds!");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(view.getFrame(), "Invalid amount entered!");
                }
            }

        }
    }

    private class TransferButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String accountNumber = view.getAccountField().getText();
            String toTransfer = JOptionPane.showInputDialog(view.getFrame(), "Enter Account number to transfer amount");
            String transferAmount = JOptionPane.showInputDialog(view.getFrame(), "Enter amount to transfer");
            double amount = Double.parseDouble(transferAmount);
            int result = JOptionPane.showConfirmDialog(view.getFrame(),
                    "Are you sure you want to transfer " + transferAmount + " to Account Number " + toTransfer + "?");

            if (result == JOptionPane.YES_OPTION) {
                if (toTransfer != null && !toTransfer.trim().isEmpty()) {
                    try {
                        if (model.transferAmount(accountNumber, toTransfer , amount)) {
                            JOptionPane.showMessageDialog(view.getFrame(), "Transfer Successful!");
                        } else {
                            JOptionPane.showMessageDialog(view.getFrame(), "Transfer Failed!");
                        }
//                    model.transferAmount(accountNumber , toTransfer , transferAmount);
                    } catch (Exception ex) {
                        System.out.println("Transfered Failed");
                    }
                }
            } else {
                System.out.println("Transfer canceled.");
            }

        }
    }

    private class FastCashButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // Create a JDialog to display Fast Cash options
            JDialog fastCashDialog = new JDialog(view.getFrame(), "Select Amount", true);
            fastCashDialog.setLayout(new GridLayout(4, 2, 10, 10)); // 4 rows, 2 columns with spacing
            fastCashDialog.setSize(300, 300); // Set the size of the dialog

            // Amounts for Fast Cash
            int[] amounts = {500, 1000, 2000, 5000, 10000, 15000, 20000, 25000};

            // Create and add buttons for each amount
            for (int amount : amounts) {
                JButton amountButton = new JButton("Rs. " + amount);
                amountButton.setFont(new Font("Arial", Font.BOLD, 16));
                amountButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        handleFastCash(amount);
                        fastCashDialog.dispose(); // Close the dialog after selection
                    }
                });
                fastCashDialog.add(amountButton);
            }

            // Center the dialog on the screen
            fastCashDialog.setLocationRelativeTo(view.getFrame());
            fastCashDialog.setVisible(true); // Show the dialog
        }

        private void handleFastCash(int amount) {
            String accountNumber = view.getAccountField().getText();
            if (model.withdrawAmount(amount, accountNumber)) {
                JOptionPane.showMessageDialog(view.getFrame(), "Withdrawal of Rs. " + amount + " Successful!");
            } else {
                JOptionPane.showMessageDialog(view.getFrame(), "Insufficient Funds or Failed to Withdraw!");
            }
        }
    }
}
