package project1.DAO;

import project1.AdminForm;
import project1.Contact;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginDAO {

    public void validate(String username, String password, JFrame frame) {
        try {
            Connection connection = MyConnect.getInstance();
            PreparedStatement pre = connection.prepareStatement(
                    "SELECT username, password FROM Account WHERE username = ? and password = ?;");
            pre.setString(1, username);
            pre.setString(2, password);
            ResultSet resultSet = pre.executeQuery();
            if (username.equals("admin") && password.equals("admin")) {
                AdminForm adminForm = new AdminForm();
                adminForm.getFrame().setVisible(true);
                frame.dispose();
            } else if (resultSet.next()) {
                Contact contact = new Contact(username);
                contact.getFrame().setVisible(true);
                frame.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Tai khoan hoac mat khau khong chinh xac");
            }
        } catch (Exception e2) {
            System.out.println(e2.getMessage());
            e2.printStackTrace();
        }
    }
}
