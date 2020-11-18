package project1.DAO;

import project1.model.Account;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static project1.DAO.MyConnect.getInstance;

public class AdminFormDAO {

    public void findAll(DefaultTableModel model) {
        try {
            PreparedStatement pre = getInstance().prepareStatement("SELECT username, firstname, lastname, phone, email, address, nhom FROM Account;");
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                model.addRow(new Object[]{rs.getString("username"), rs.getString("firstname"),
                        rs.getString("lastname"), rs.getString("phone"), rs.getString("email"), rs.getString("address"),
                        rs.getString("nhom")});

            }

        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    public void delete(String username, DefaultTableModel model, int row, JFrame frame) {
        try {
            Connection connection = getInstance();
            PreparedStatement prepareStatement = connection.prepareStatement("DELETE Account WHERE username = ?;");
            prepareStatement.setString(1, username);
            int update = prepareStatement.executeUpdate();
            if (update != 0) {
                JOptionPane.showMessageDialog(frame, "Xoa tai khoan thanh cong");
                model.removeRow(row);
            } else {
                JOptionPane.showMessageDialog(frame, "Xoa tai khoan that bai");
            }

        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    public void save(Account account,DefaultTableModel model,int row, JFrame frame) {
        try {
            Connection connection = getInstance();

            PreparedStatement statement = connection.prepareStatement("SELECT username FROM Account WHERE username = ?");
            statement.setString(1, account.getUsername());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                PreparedStatement pre2 = connection.prepareStatement(
                        "UPDATE Account SET firstname = ?, lastname = ?, phone = ?, email = ?, address = ?, nhom = ? WHERE username = ?;");
                pre2.setString(1, account.getFirstname());
                pre2.setString(2, account.getLastName());
                pre2.setString(3, account.getPhone());
                pre2.setString(4, account.getEmail());
                pre2.setString(5, account.getAddress());
                pre2.setString(6, account.getGroup());
                pre2.setString(7, account.getUsername());
                int executeUpdate = pre2.executeUpdate();
                if (executeUpdate != 0) {
                    JOptionPane.showMessageDialog(frame, "Sua thanh cong");
                    model.setValueAt(account.getFirstname(), row, 1);
                    model.setValueAt(account.getLastName(), row, 2);
                    model.setValueAt(account.getPhone(), row, 3);
                    model.setValueAt(account.getEmail(), row, 4);
                    model.setValueAt(account.getAddress(), row, 5);
                    model.setValueAt(account.getGroup(), row, 6);
                } else {
                    JOptionPane.showMessageDialog(frame, "Sua that bai");
                }
            } else {
                PreparedStatement pre = connection.prepareStatement(
                        "INSERT INTO Account(username, password, firstname, lastname, phone, email, address, nhom) VALUES (?,?,?,?,?,?,?,?);");
                pre.setString(1, account.getUsername());
                pre.setString(2, account.getPassword());
                pre.setString(3, account.getFirstname());
                pre.setString(4, account.getLastName());
                pre.setString(5, account.getPhone());
                pre.setString(6, account.getEmail());
                pre.setString(7,account.getAddress());
                pre.setString(8, account.getGroup());
                long executeLargeUpdate = pre.executeLargeUpdate();
                if (executeLargeUpdate != 0) {
                    model.addRow(new Object[]{account.getUsername(), account.getFirstname(), account.getLastName(),
                            account.getPhone(),account.getEmail(), account.getAddress(),
                            account.getGroup()});
                    JOptionPane.showMessageDialog(frame, "Them tai khoan thanh cong");
                } else {
                    JOptionPane.showMessageDialog(frame, "Them tai khoan that bai");
                }
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }
}
