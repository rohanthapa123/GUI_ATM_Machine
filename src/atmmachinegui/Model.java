/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package atmmachinegui;

/**
 *
 * @author RohanThapa
 */
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Model {

    public Connection connection;

    public Model() {
        try {

            connection = DatabaseConnection.getConnection();
            System.out.println("Connected Successfully");
        } catch (SQLException e) {
            System.out.println("Error occured connecting database");
        }
    }

    public boolean AuthenticateUser(String account_number, String pin) {
        String query = "SELECT * from user WHERE account_number = ? AND pin = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, account_number);
            statement.setString(2, pin);

            ResultSet rs = statement.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            System.out.println("Error occured authencating user");
        }
        return false;
    }

    public double getBalance(String account_number) {
        String query = "SELECT balance FROM user WHERE account_number = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, account_number);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getDouble("balance");
            }
        } catch (SQLException e) {
            System.out.println("Error occured getting Balance");
        }
        return 0.0;
    }

    public boolean depositAmount(double balance, String account_number) {
        String query = "UPDATE user SET balance = balance + ? WHERE account_number = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setDouble(1, balance);
            statement.setString(2, account_number);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error depositing amount");
        }
        return false;
    }

    public boolean transferAmount(String account_number, String toTransfer, double amount) {
        try {
            this.withdrawAmount(amount, account_number);
            this.depositAmount(amount, toTransfer);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean withdrawAmount(double balance, String account_number) {
        if (getBalance(account_number) >= balance) {

            String query = "UPDATE user SET balance = balance - ? WHERE account_number = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setDouble(1, balance);
                statement.setString(2, account_number);
                return statement.executeUpdate() > 0;
            } catch (SQLException e) {
                System.out.println("Error withDrawing amount");
            }

        }
        return false;
    }
}
