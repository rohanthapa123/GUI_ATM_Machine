
package atmmachinegui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * View class for ATM Machine GUI.
 */
public class View {
    private JFrame frame;
    private JPanel loginPanel;
    private JLabel l1, l2;
    private JTextField accountField;
    private JPasswordField pinField;
    private JButton loginButton;
    private JLabel statusLabel;
    private JPanel atmPanel;
    private JButton balanceButton;
    private JButton depositButton;
    private JButton withdrawButton;
    private JButton fastCashButton;
    private JButton transferButton;
    private JButton cancelButton;

    public View() {
        frame = new JFrame("ATM Machine");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        // Set background image
        JLabel background = new JLabel(new ImageIcon(getClass().getResource("/resources/slider-bg.png")));
        frame.setContentPane(background);
        frame.setLayout(new GridBagLayout());

        // Initialize and configure login panel
        loginPanel = new JPanel();
        loginPanel.setOpaque(false);
        loginPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Font for labels and fields
        Font labelFont = new Font("Arial", Font.BOLD, 24);
        Font fieldFont = new Font("Arial", Font.PLAIN, 18);
        
        // Account Number Label
        l1 = new JLabel("Account Number");
        l1.setFont(labelFont);
        l1.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        loginPanel.add(l1, gbc);

        // Account Number Field
        accountField = new JTextField(15);
        accountField.setFont(fieldFont);
        accountField.setPreferredSize(new Dimension(300, 30));
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        loginPanel.add(accountField, gbc);

        // PIN Label
        l2 = new JLabel("PIN");
        l2.setFont(labelFont);
        l2.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridy = 2;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        loginPanel.add(l2, gbc);

        // PIN Field
        pinField = new JPasswordField(15);
        pinField.setFont(fieldFont);
        pinField.setPreferredSize(new Dimension(300, 30));
        gbc.gridy = 3;
        loginPanel.add(pinField, gbc);

        // Login Button
        loginButton = new JButton("Login");
        gbc.gridy = 4;
        loginPanel.add(loginButton, gbc);

        // Status Label
        statusLabel = new JLabel("");
        gbc.gridy = 5;
        loginPanel.add(statusLabel, gbc);

        // Initialize and configure ATM panel
        atmPanel = new JPanel();
        atmPanel.setOpaque(false);
        atmPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbcAtm = new GridBagConstraints();
        gbcAtm.insets = new Insets(10, 10, 10, 10);
        gbcAtm.fill = GridBagConstraints.BOTH;
        gbcAtm.weightx = 1.0;
        gbcAtm.weighty = 1.0;

        // Font for buttons
        Font buttonFont = new Font("Arial", Font.BOLD, 18);

        // Define and add buttons with large text
        balanceButton = new JButton("Check Balance");
        balanceButton.setFont(buttonFont);
        gbcAtm.gridx = 0;
        gbcAtm.gridy = 0;
        gbcAtm.gridwidth = 1;
        atmPanel.add(balanceButton, gbcAtm);

        depositButton = new JButton("Deposit");
        depositButton.setFont(buttonFont);
        gbcAtm.gridy = 1;
        atmPanel.add(depositButton, gbcAtm);

        withdrawButton = new JButton("Withdraw");
        withdrawButton.setFont(buttonFont);
        gbcAtm.gridy = 2;
        atmPanel.add(withdrawButton, gbcAtm);

        fastCashButton = new JButton("Fast Cash");
        fastCashButton.setFont(buttonFont);
        gbcAtm.gridx = 1;
        gbcAtm.gridy = 0;
        atmPanel.add(fastCashButton, gbcAtm);

        transferButton = new JButton("Transfer");
        transferButton.setFont(buttonFont);
        gbcAtm.gridy = 1;
        atmPanel.add(transferButton, gbcAtm);

        cancelButton = new JButton("Cancel");
        cancelButton.setFont(buttonFont);
        gbcAtm.gridy = 2;
        atmPanel.add(cancelButton, gbcAtm);

        // Add loginPanel initially
        frame.add(loginPanel, new GridBagConstraints());
        frame.setVisible(true);
    }

    public JFrame getFrame() {
        return frame;
    }

    public JPanel getLoginPanel() {
        return loginPanel;
    }

    public JPanel getAtmPanel() {
        return atmPanel;
    }

    public JTextField getAccountField() {
        return accountField;
    }

    public JPasswordField getPinField() {
        return pinField;
    }

    public JLabel getStatusLabel() {
        return statusLabel;
    }

    public JButton getLoginButton() {
        return loginButton;
    }

    public JButton getBalanceButton() {
        return balanceButton;
    }

    public JButton getDepositButton() {
        return depositButton;
    }

    public JButton getWithdrawButton() {
        return withdrawButton;
    }

    public JButton getFastCashButton() {
        return fastCashButton;
    }

    public JButton getTransferButton() {
        return transferButton;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }

    public void showAtmPanel() {
        frame.remove(loginPanel);
        frame.add(atmPanel);
        frame.revalidate();
        frame.repaint();
    }

    public void showLoginPanel() {
        frame.remove(atmPanel);
        frame.add(loginPanel);
        frame.revalidate();
        frame.repaint();
    }
}
