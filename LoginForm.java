package project1;

import project1.DAO.LoginDAO;
import project1.DAO.MyConnect;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;

import static javax.swing.JFrame.*;

public class LoginForm {

    public static final String TAHOMA = "Tahoma";
    private JFrame frame;
    private JTextField textField;
    private JPasswordField passwordField;
    private ArrayList<JLabel> arrErr;
    private ArrayList<JTextField> arrField;
    private JButton exit;
    private JButton cancel;
    private JButton login;
    private Timer time;
    private JCheckBox showPass;
    private JLabel clickcreate;
    private JLabel userErr;
    private JLabel passErr;

    public JFrame getFrame() {
        return frame;
    }

    public static void main(String[] args) {
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        SwingUtilities.invokeLater(() -> new LoginForm().frame.setVisible(true));
    }

    public LoginForm() {
        initialize();
        addEvent();
    }


    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 436, 345);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setUndecorated(true);
        frame.getContentPane().setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 436, 65);
        panel.setBackground(new Color(35, 183, 114));
        panel.setLayout(null);
        frame.getContentPane().add(panel);

        JLabel lblNewLabel = new JLabel("Login");
        lblNewLabel.setForeground(new Color(255, 255, 255));
        lblNewLabel.setFont(new Font(TAHOMA, Font.BOLD, 24));
        lblNewLabel.setBounds(10, 11, 217, 32);
        panel.add(lblNewLabel);

        exit = new JButton("");

        exit.setBackground(Color.cyan);
        exit.setIcon(new ImageIcon(LoginForm.class.getResource("/project1/icons/exit.png")));
        exit.setBounds(395, 11, 29, 32);
        panel.add(exit);

        JPanel panel_1 = new JPanel();
        panel_1.setBackground(new Color(47, 65, 83));
        panel_1.setBounds(0, 65, 436, 280);
        panel_1.setLayout(null);
        frame.getContentPane().add(panel_1);

        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        usernameLabel.setForeground(new Color(255, 255, 255));
        usernameLabel.setFont(new Font(TAHOMA, Font.PLAIN, 18));
        usernameLabel.setBounds(21, 48, 102, 33);
        panel_1.add(usernameLabel);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        passwordLabel.setForeground(new Color(255, 255, 255));
        passwordLabel.setFont(new Font(TAHOMA, Font.PLAIN, 18));
        passwordLabel.setBounds(21, 112, 102, 33);
        panel_1.add(passwordLabel);

        textField = new JTextField();
        textField.setBounds(133, 48, 181, 33);
        textField.setColumns(10);
        panel_1.add(textField);

        passwordField = new JPasswordField();
        passwordField.setBounds(133, 112, 181, 33);
        panel_1.add(passwordField);

        cancel = new JButton("Cancel");

        cancel.setBounds(98, 188, 89, 33);
        cancel.setBackground(new Color(255, 102, 0));
        panel_1.add(cancel);


        login = new JButton("Login");
        login.setBackground(new Color(38, 134, 193));
        login.setBounds(244, 188, 89, 33);
        panel_1.add(login);

        showPass = new JCheckBox("Show Pass");
        showPass.setBounds(331, 117, 97, 23);
        panel_1.add(showPass);

        JLabel lblNewLabel_2 = new JLabel("new user ?");
        lblNewLabel_2.setForeground(new Color(255, 255, 255));
        lblNewLabel_2.setFont(new Font(TAHOMA, Font.PLAIN, 16));
        lblNewLabel_2.setBounds(98, 255, 77, 14);
        panel_1.add(lblNewLabel_2);

        clickcreate = new JLabel(" click here to create user");
        clickcreate.setForeground(new Color(255, 255, 255));
        clickcreate.setFont(new Font(TAHOMA, Font.BOLD, 16));
        clickcreate.setBounds(179, 255, 214, 14);
        panel_1.add(clickcreate);

        userErr = new JLabel("Not Empty");
        userErr.setForeground(new Color(255, 0, 0));
        userErr.setBounds(135, 85, 77, 14);
        panel_1.add(userErr);

        passErr = new JLabel("Not Empty");
        passErr.setForeground(new Color(255, 0, 0));
        passErr.setBounds(135, 150, 77, 14);
        panel_1.add(passErr);
    }

    private void addEvent() {
        arrErr = new ArrayList<>();
        arrErr.add(userErr);
        arrErr.add(passErr);
        arrErr.forEach(e -> {
            e.setVisible(false);
        });

        showPass.addActionListener(e -> {
            if (showPass.isSelected()) {
                passwordField.setEchoChar((char) 0);
            } else {
                passwordField.setEchoChar('*');
            }
        });

        time = new Timer(1, e -> {
            arrErr.forEach(e1 -> {
                e1.setVisible(false);
            });
        });
        time.setInitialDelay(1000);

        clickcreate.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                CreateAccount window = new CreateAccount();
                window.getFrame().setVisible(true);
                frame.dispose();
            }

        });

        String[] options = new String[]{"Yes", "No"};
        exit.addActionListener(e -> {
            int choose = JOptionPane.showOptionDialog(frame, "Ban muon thoat", "Exit", JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
            if (choose == 0) {
                System.exit(0);
            }
        });

        cancel.addActionListener(e -> {
            int choose = JOptionPane.showOptionDialog(frame, "Ban muon thoat", "Exit", JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
            if (choose == 0) {
                System.exit(0);
            }
        });

        login.addActionListener(e -> {
            int count = 0;
            for (int i = 0; i < arrField.size(); i++) {
                if (arrField.get(i).getText().equals("")) {
                    count++;
                    arrErr.get(i).setVisible(true);
                }
            }
            if (count == 0) {
                LoginDAO loginDAO = new LoginDAO();
                String username = textField.getText();
                String password = passwordField.getText();
                loginDAO.validate(username, password, frame);
            }
            count = 0;
            time.stop();
        });

        arrField = new ArrayList<>();
        arrField.add(textField);
        arrField.add(passwordField);
        arrField.forEach(e -> {
            e.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    time.start();
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        int count = 0;
                        for (int i = 0; i < arrField.size(); i++) {
                            if (arrField.get(i).getText().equals("")) {
                                count++;
                                arrErr.get(i).setVisible(true);
                            }
                        }
                        if (count == 0) {
                            LoginDAO loginDAO = new LoginDAO();
                            String username = textField.getText();
                            String password = passwordField.getText();
                            loginDAO.validate(username, password, frame);
                        }
                        count = 0;
                        time.stop();
                    }
                }
            });
        });
    }
}
