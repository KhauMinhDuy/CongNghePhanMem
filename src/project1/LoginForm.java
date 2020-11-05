package project1;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

public class LoginForm {

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
	EventQueue.invokeLater(() -> {
            try {
                LoginForm window = new LoginForm();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public LoginForm() {
	initialize();

    }

    private void initialize() {
	frame = new JFrame();
	frame.setBounds(100, 100, 436, 345);
	frame.setLocationRelativeTo(null);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setUndecorated(true);
	frame.getContentPane().setLayout(null);

	JPanel panel = new JPanel();
	panel.setBounds(0, 0, 436, 65);
	panel.setBackground(new Color(35, 183, 114));
	panel.setLayout(null);
	frame.getContentPane().add(panel);

	JLabel lblNewLabel = new JLabel("Login");
	lblNewLabel.setForeground(new Color(255, 255, 255));
	lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
	lblNewLabel.setBounds(10, 11, 217, 32);
	panel.add(lblNewLabel);

	exit = new JButton("");
	String[] options = new String[] { "Yes", "No" };
	exit.addActionListener(e -> {
	    int choose = JOptionPane.showOptionDialog(frame, "Ban muon thoat", "Exit", JOptionPane.OK_CANCEL_OPTION,
		    JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
	    if (choose == 0) {
		System.exit(0);
	    }
	});
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
	usernameLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
	usernameLabel.setBounds(21, 48, 102, 33);
	panel_1.add(usernameLabel);

	JLabel passwordLabel = new JLabel("Password");
	passwordLabel.setHorizontalAlignment(SwingConstants.RIGHT);
	passwordLabel.setForeground(new Color(255, 255, 255));
	passwordLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
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
	cancel.addActionListener(e -> {
	    int choose = JOptionPane.showOptionDialog(frame, "Ban muon thoat", "Exit", JOptionPane.OK_CANCEL_OPTION,
		    JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
	    if (choose == 0) {
		System.exit(0);
	    }
	});
	cancel.setBounds(98, 188, 89, 33);
	cancel.setBackground(new Color(255, 102, 0));
	panel_1.add(cancel);

	time = new Timer(1, e -> {
	    arrErr.stream().forEach(e1 -> {
		e1.setVisible(false);
	    });
	});
	time.setInitialDelay(1000);

	login = new JButton("Login");
	login.addActionListener(e -> {
	    int count = 0;
	    for (int i = 0; i < arrField.size(); i++) {
		if (arrField.get(i).getText().equals("")) {
		    count++;
		    arrErr.get(i).setVisible(true);
		}
	    }
	    if (count == 0) {
		try {
		    Connection connection = MyConnect.getInstance();
		    PreparedStatement pre = connection.prepareStatement(
			    "SELECT username, password FROM Account WHERE username = ? and password = ?;");
		    pre.setString(1, textField.getText());
		    pre.setString(2, passwordField.getText());
		    ResultSet resultSet = pre.executeQuery();
		    if (textField.getText().equals("admin") && passwordField.getText().equals("admin")) {
			AdminForm adminForm = new AdminForm();
			adminForm.getFrame().setVisible(true);
			frame.dispose();
		    } else if (resultSet.next()) {
			Contact contact = new Contact(textField.getText());
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
	    count = 0;
	    time.stop();
	});
	login.setBackground(new Color(38, 134, 193));
	login.setBounds(244, 188, 89, 33);
	panel_1.add(login);

	showPass = new JCheckBox("Show Pass");
	showPass.addActionListener(e -> {
	    if (showPass.isSelected()) {
		passwordField.setEchoChar((char) 0);
	    } else {
		passwordField.setEchoChar('*');
	    }
	});
	showPass.setBounds(331, 117, 97, 23);
	panel_1.add(showPass);

	JLabel lblNewLabel_2 = new JLabel("new user ?");
	lblNewLabel_2.setForeground(new Color(255, 255, 255));
	lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
	lblNewLabel_2.setBounds(98, 255, 77, 14);
	panel_1.add(lblNewLabel_2);

	clickcreate = new JLabel(" click here to create user");
	clickcreate.addMouseListener(new MouseAdapter() {
	    @Override
	    public void mouseClicked(MouseEvent e) {
		CreateAccount window = new CreateAccount();
		window.getFrame().setVisible(true);
		frame.dispose();
	    }

	});
	clickcreate.setForeground(new Color(255, 255, 255));
	clickcreate.setFont(new Font("Tahoma", Font.BOLD, 16));
	clickcreate.setBounds(179, 255, 214, 14);
	panel_1.add(clickcreate);

	JLabel userErr = new JLabel("Not Empty");
	userErr.setForeground(new Color(255, 0, 0));
	userErr.setBounds(135, 85, 77, 14);
	panel_1.add(userErr);

	JLabel passErr = new JLabel("Not Empty");
	passErr.setForeground(new Color(255, 0, 0));
	passErr.setBounds(135, 150, 77, 14);
	panel_1.add(passErr);

	arrErr = new ArrayList<>();
	arrErr.add(userErr);
	arrErr.add(passErr);
	arrErr.forEach(e -> {
	    e.setVisible(false);
	});

	arrField = new ArrayList<>();
	arrField.add(textField);
	arrField.add(passwordField);
	arrField.stream().forEach(e -> {
	    e.addKeyListener(new KeyListener() {
		@Override
		public void keyTyped(KeyEvent e) {
		}

		@Override
		public void keyReleased(KeyEvent e) {
		}

		@SuppressWarnings("deprecation")
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
			if (textField.getText().equals("admin") && passwordField.getText().equals("admin")) {
//				JOptionPane.showMessageDialog(frame, "Admin");
			    AdminForm adminForm = new AdminForm();
			    adminForm.getFrame().setVisible(true);
			    frame.dispose();
			} else if (count == 0) {
			    try {
				Connection connection = MyConnect.getInstance();
				PreparedStatement pre = connection.prepareStatement(
					"SELECT username, password FROM Account WHERE username = ? and password = ?;");
				pre.setString(1, textField.getText());
				pre.setString(2, passwordField.getText());
				ResultSet resultSet = pre.executeQuery();
				if (resultSet.next()) {
				    Contact contact = new Contact(textField.getText());
				    contact.getFrame().setVisible(true);
				    frame.dispose();
				} else {
				    JOptionPane.showMessageDialog(null, "Tai Khoan hoac mat khau khong dung");
				}
			    } catch (Exception e2) {
				e2.printStackTrace();
			    }
			}
			count = 0;
			time.stop();
		    }
		}
	    });
	});

    }
}
