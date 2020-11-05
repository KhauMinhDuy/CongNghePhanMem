//package project1;
//
//import java.awt.Color;
//import java.awt.Font;
//import java.awt.Image;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.InputStream;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//import javax.swing.DefaultComboBoxModel;
//import javax.swing.ImageIcon;
//import javax.swing.JButton;
//import javax.swing.JComboBox;
//import javax.swing.JFileChooser;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JOptionPane;
//import javax.swing.JPanel;
//import javax.swing.JSeparator;
//import javax.swing.JTextArea;
//import javax.swing.JTextField;
//import javax.swing.SwingConstants;
//import javax.swing.border.EmptyBorder;
//import javax.swing.border.EtchedBorder;
//import javax.swing.filechooser.FileNameExtensionFilter;
//
//public class Contact {
//
//    private JFrame frame;
//    private JLabel username;
//    private JLabel avatar;
//    private JTextField firstNameField;
//    private JTextField lastNameField;
//    private JTextField phoneField;
//    private JTextField emailField;
//    private String path;
//    private JButton exitButton;
//    private JTextArea addressTextArea;
//    private JComboBox<String> groupComboBox;
//    private JButton browserButton;
//    private JLabel avatarprofile;
//    private JButton saveButton;
//
//    public JFrame getFrame() {
//	return frame;
//    }
//
//    public Contact(String s) {
//	initialize(s);
//
//    }
//
//    private void initialize(String user) {
//	frame = new JFrame();
//	frame.setBounds(100, 100, 1051, 915);
//	frame.setUndecorated(true);
//	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//	frame.getContentPane().setLayout(null);
//	frame.setLocationRelativeTo(new LoginForm().getFrame());
//
//	JPanel panel = new JPanel();
//	panel.setBounds(0, 0, 1051, 92);
//	panel.setBackground(new Color(35, 183, 114));
//	panel.setLayout(null);
//
//	frame.getContentPane().add(panel);
//
//	avatar = new JLabel("");
//	avatar.setBounds(10, 11, 70, 70);
//	panel.add(avatar);
//
//	username = new JLabel();
//	username.setText(user);
//	username.setForeground(new Color(255, 255, 255));
//	username.setFont(new Font("Tahoma", Font.PLAIN, 18));
//	username.setBounds(86, 36, 110, 25);
//	panel.add(username);
//
//	Image image = null;
//	byte[] img = null;
//	try {
//	    Connection connection = MyConnect.getInstance();
//	    PreparedStatement pre = connection
//		    .prepareStatement("SELECT picture, path FROM Account WHERE username = ?;");
//	    pre.setString(1, user);
//	    ResultSet resultSet = pre.executeQuery();
//	    if (resultSet.next()) {
//		img = resultSet.getBytes("picture");
//		image = new ImageIcon(img).getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
//		avatar.setIcon(new ImageIcon(image));
//		path = resultSet.getString("path");
//	    } else {
//		System.out.println("Fail");
//	    }
//	} catch (SQLException e1) {
//	    System.out.println(e1.getMessage());
//	}
//
//	JLabel lblNewLabel_1 = new JLabel("ConTacts");
//	lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
//	lblNewLabel_1.setForeground(new Color(255, 255, 255));
//	lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 24));
//	lblNewLabel_1.setBounds(350, 30, 310, 40);
//	panel.add(lblNewLabel_1);
//
//	exitButton = new JButton("");
//	String[] options = { "Yes", "No" };
//	exitButton.addActionListener(e -> {
//	    int i = JOptionPane.showOptionDialog(frame, "Ban Muon Quay Lai Login", "Exit", JOptionPane.OK_CANCEL_OPTION,
//		    JOptionPane.WARNING_MESSAGE, null, options, 0);
//	    if (i == 0) {
//		frame.dispose();
//		new LoginForm().getFrame().setVisible(true);
//	    }
//
//	});
//	exitButton.setBorder(new EmptyBorder(0, 0, 0, 0));
//	exitButton.setBackground(new Color(35, 183, 114));
//	exitButton.setIcon(new ImageIcon(Contact.class.getResource("/project1/icons/exit.png")));
//	exitButton.setBounds(1001, 11, 39, 40);
//	panel.add(exitButton);
//
//	JPanel panel_1 = new JPanel();
//	panel_1.setBounds(0, 91, 1051, 398);
//	panel_1.setBackground(new Color(0xEEEEEE));
//	panel_1.setLayout(null);
//	frame.getContentPane().add(panel_1);
//
//	JLabel firstNameLabel = new JLabel("First Name:");
//	firstNameLabel.setBounds(10, 12, 114, 36);
//	panel_1.add(firstNameLabel);
//	firstNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
//	firstNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
//	firstNameLabel.setForeground(new Color(0, 0, 0));
//
//	JLabel lastNameLabel = new JLabel("Last Name:");
//	lastNameLabel.setBounds(10, 74, 114, 36);
//	lastNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
//	lastNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
//	lastNameLabel.setForeground(new Color(0, 0, 0));
//	panel_1.add(lastNameLabel);
//
//	JLabel phoneLabel = new JLabel("Phone:");
//	phoneLabel.setBounds(10, 133, 114, 36);
//	phoneLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
//	phoneLabel.setHorizontalAlignment(SwingConstants.RIGHT);
//	phoneLabel.setForeground(new Color(0, 0, 0));
//	panel_1.add(phoneLabel);
//
//	JLabel emailLabel = new JLabel("Email:");
//	emailLabel.setBounds(10, 199, 114, 36);
//	emailLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
//	emailLabel.setHorizontalAlignment(SwingConstants.RIGHT);
//	emailLabel.setForeground(new Color(0, 0, 0));
//	panel_1.add(emailLabel);
//
//	JLabel groupLabel = new JLabel("Group:");
//	groupLabel.setBounds(10, 264, 114, 36);
//	panel_1.add(groupLabel);
//	groupLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
//	groupLabel.setHorizontalAlignment(SwingConstants.RIGHT);
//	groupLabel.setForeground(new Color(0, 0, 0));
//
//	firstNameField = new JTextField();
//	firstNameField.setBounds(144, 15, 284, 36);
//	firstNameField.setColumns(10);
//	panel_1.add(firstNameField);
//
//	lastNameField = new JTextField();
//	lastNameField.setBounds(144, 74, 284, 36);
//	lastNameField.setColumns(10);
//	panel_1.add(lastNameField);
//
//	phoneField = new JTextField();
//	phoneField.setBounds(144, 133, 284, 36);
//	phoneField.setColumns(10);
//	panel_1.add(phoneField);
//
//	emailField = new JTextField();
//	emailField.setBounds(144, 199, 284, 36);
//	emailField.setColumns(10);
//	panel_1.add(emailField);
//
//	groupComboBox = new JComboBox<>();
//	groupComboBox.setModel(new DefaultComboBoxModel<String>(
//		new String[] { "-- Select --", "Student", "Teacher", "Doctor", "Dev" }));
//	groupComboBox.setSelectedIndex(0);
//	groupComboBox.setBounds(145, 267, 283, 36);
//	panel_1.add(groupComboBox);
//
//	addressTextArea = new JTextArea();
//	addressTextArea.setBounds(655, 218, 270, 93);
//	panel_1.add(addressTextArea);
//
//	try {
//	    Connection connection = MyConnect.getInstance();
//	    PreparedStatement pre = connection.prepareStatement(
//		    "SELECT firstname, lastname, phone, email, nhom, address FROM Account WHERE username = ?;");
//	    pre.setString(1, user);
//	    ResultSet resultSet = pre.executeQuery();
//	    if (resultSet.next()) {
//		firstNameField.setText(resultSet.getString("firstname"));
//		lastNameField.setText(resultSet.getString("lastname"));
//		phoneField.setText(resultSet.getString("phone"));
//		emailField.setText(resultSet.getString("email"));
//		String nhom = resultSet.getString("nhom");
//		if (nhom.equals("-- Select --")) {
//		    groupComboBox.setSelectedItem(0);
//		} else if (nhom.equals("Student")) {
//		    groupComboBox.setSelectedItem(1);
//		} else if (nhom.equals("Teacher")) {
//		    groupComboBox.setSelectedItem(2);
//		} else if (nhom.equals("Doctor")) {
//		    groupComboBox.setSelectedItem(3);
//		} else {
//		    groupComboBox.setSelectedItem(4);
//		}
//		addressTextArea.setText(resultSet.getString("address"));
//	    } else {
//		System.out.println("Fail");
//	    }
//	} catch (SQLException e1) {
//	    e1.printStackTrace();
//	}
//
//	JSeparator separator = new JSeparator();
//	separator.setBounds(479, 11, 7, 300);
//	panel_1.add(separator);
//	separator.setOrientation(SwingConstants.VERTICAL);
//
//	JLabel proPictureLabel = new JLabel("Profile Picture:");
//	proPictureLabel.setBounds(532, 12, 114, 36);
//	proPictureLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
//	panel_1.add(proPictureLabel);
//
//	avatarprofile = new JLabel("");
//	avatarprofile.setBounds(655, 12, 270, 145);
//	avatarprofile.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
//	Image avatarImg = new ImageIcon(img).getImage().getScaledInstance(270, 145, Image.SCALE_SMOOTH);
//	avatarprofile.setIcon(new ImageIcon(avatarImg));
//	panel_1.add(avatarprofile);
//
//	browserButton = new JButton("Browser");
//
//	browserButton.addActionListener(e -> {
//	    JFileChooser fc = new JFileChooser();
//	    fc.setCurrentDirectory(new File(System.getProperty("user.home")));
//
//	    FileNameExtensionFilter fileFilter = new FileNameExtensionFilter("*.Images", "jpg", "png", "gif");
//	    fc.addChoosableFileFilter(fileFilter);
//	    int saveDialog = fc.showSaveDialog(null);
//	    if (saveDialog == JFileChooser.APPROVE_OPTION) {
//		File selectedFile = fc.getSelectedFile();
//		path = selectedFile.getAbsolutePath();
//		Image imagebr = new ImageIcon(path).getImage().getScaledInstance(270, 145, Image.SCALE_DEFAULT);
//		avatarprofile.setIcon(new ImageIcon(imagebr));
//		Image imagebr2 = new ImageIcon(path).getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT);
//		avatar.setIcon(new ImageIcon(imagebr2));
//	    } else if (saveDialog == JFileChooser.CANCEL_OPTION) {
//		System.out.println("No image select");
//	    }
//	});
//	browserButton.setBounds(655, 168, 94, 36);
//	browserButton.setForeground(new Color(0, 0, 0));
//	browserButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
//	panel_1.add(browserButton);
//
//	JLabel addressLabel = new JLabel("Address:");
//	addressLabel.setBounds(532, 209, 114, 36);
//	panel_1.add(addressLabel);
//	addressLabel.setHorizontalAlignment(SwingConstants.RIGHT);
//	addressLabel.setForeground(Color.BLACK);
//	addressLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
//
//	saveButton = new JButton("Save");
//	saveButton.addActionListener(e -> {
//	    try {
//		Connection connection = MyConnect.getInstance();
//		PreparedStatement pre = connection.prepareStatement(
//			"UPDATE Account SET firstname = ?, lastname = ?, picture = ?, phone = ?, email = ?, address = ?, nhom = ? WHERE username = ?;");
//		pre.setString(1, firstNameField.getText());
//		pre.setString(2, lastNameField.getText());
//		InputStream inputStream = new FileInputStream(new File(path));
//		pre.setBlob(3, inputStream);
//		pre.setString(4, phoneField.getText());
//		pre.setString(5, emailField.getText());
//		pre.setString(6, addressTextArea.getText());
//		pre.setString(7, groupComboBox.getSelectedItem().toString());
//		pre.setString(8, user);
//		if (pre.executeUpdate() != 0) {
//		    JOptionPane.showMessageDialog(frame, "Success");
//		} else {
//		    JOptionPane.showMessageDialog(frame, "Fail");
//		}
//	    } catch (SQLException | FileNotFoundException e1) {
//		e1.printStackTrace();
//	    }
//	});
//	saveButton.setBackground(new Color(71, 181, 249));
//	saveButton.setBounds(927, 344, 114, 43);
//	panel_1.add(saveButton);
//
//	JPanel panel_2 = new JPanel();
//	panel_2.setBounds(0, 490, 1051, 425);
//	panel_2.setLayout(null);
//	frame.getContentPane().add(panel_2);
//
//    }
//}

package project1;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Contact {

    private JFrame frame;
    private JLabel username;
    private JLabel avatar;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField phoneField;
    private JTextField emailField;
    private String path;
    private JButton exitButton;
    private JTextArea addressTextArea;
    private JComboBox<String> groupComboBox;
    private JButton browserButton;
    private JLabel avatarprofile;
    private JButton saveButton;

    public JFrame getFrame() {
	return frame;
    }

    public Contact(String avatarImg) {
	initialize(avatarImg);
    }

    private void initialize(String user) {
	frame = new JFrame();
	frame.setBounds(100, 100, 1051, 489);
	frame.setUndecorated(true);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.getContentPane().setLayout(null);
	frame.setLocationRelativeTo(new LoginForm().getFrame());

	JPanel panel = new JPanel();
	panel.setBounds(0, 0, 1051, 92);
	panel.setBackground(new Color(35, 183, 114));
	panel.setLayout(null);
	frame.getContentPane().add(panel);

	avatar = new JLabel("");
	avatar.setBounds(10, 11, 70, 70);
	panel.add(avatar);

	username = new JLabel();
	username.setText(user);
	username.setForeground(new Color(255, 255, 255));
	username.setFont(new Font("Tahoma", Font.PLAIN, 18));
	username.setBounds(86, 36, 110, 25);
	panel.add(username);

	Image image = null;
	byte[] img = null;
	try {
	    Connection connection = MyConnect.getInstance();
	    PreparedStatement pre = connection
		    .prepareStatement("SELECT picture, path FROM Account WHERE username = ?;");
	    pre.setString(1, user);
	    ResultSet resultSet = pre.executeQuery();
	    if (resultSet.next()) {
		img = resultSet.getBytes("picture");
		image = new ImageIcon(img).getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
		avatar.setIcon(new ImageIcon(image));
		path = resultSet.getString("path");
	    } else {
		System.out.println("Fail");
	    }
	} catch (SQLException e1) {
	    e1.printStackTrace();
	}

	JLabel lblNewLabel_1 = new JLabel("ConTacts");
	lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
	lblNewLabel_1.setForeground(new Color(255, 255, 255));
	lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 24));
	lblNewLabel_1.setBounds(350, 30, 310, 40);
	panel.add(lblNewLabel_1);

	String[] options = { "Yes", "No" };
	exitButton = new JButton("");
	exitButton.addActionListener(e -> {
	    int i = JOptionPane.showOptionDialog(frame, "Ban Muon Quay Lai Login", "Exit", JOptionPane.OK_CANCEL_OPTION,
		    JOptionPane.WARNING_MESSAGE, null, options, 0);
	    if (i == 0) {
		frame.dispose();
		new LoginForm().getFrame().setVisible(true);
	    }

	});
	exitButton.setBorder(new EmptyBorder(0, 0, 0, 0));
	exitButton.setBackground(new Color(35, 183, 114));
	exitButton.setIcon(new ImageIcon(Contact.class.getResource("/project1/icons/exit.png")));
	exitButton.setBounds(1001, 11, 39, 40);
	panel.add(exitButton);

	JPanel panel_1 = new JPanel();
	panel_1.setBounds(0, 91, 1051, 398);
	panel_1.setBackground(new Color(0xEEEEEE));
	panel_1.setLayout(null);
	frame.getContentPane().add(panel_1);

	JLabel firstNameLabel = new JLabel("First Name:");
	firstNameLabel.setBounds(10, 12, 114, 36);
	firstNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
	firstNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
	firstNameLabel.setForeground(new Color(0, 0, 0));
	panel_1.add(firstNameLabel);

	JLabel lastNameLabel = new JLabel("Last Name:");
	lastNameLabel.setBounds(10, 74, 114, 36);
	lastNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
	lastNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
	lastNameLabel.setForeground(new Color(0, 0, 0));
	panel_1.add(lastNameLabel);

	JLabel phoneLabel = new JLabel("Phone:");
	phoneLabel.setBounds(10, 133, 114, 36);
	phoneLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
	phoneLabel.setHorizontalAlignment(SwingConstants.RIGHT);
	phoneLabel.setForeground(new Color(0, 0, 0));
	panel_1.add(phoneLabel);

	JLabel emailLabel = new JLabel("Email:");
	emailLabel.setBounds(10, 199, 114, 36);
	emailLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
	emailLabel.setHorizontalAlignment(SwingConstants.RIGHT);
	emailLabel.setForeground(new Color(0, 0, 0));
	panel_1.add(emailLabel);

	JLabel groupLabel = new JLabel("Group:");
	groupLabel.setBounds(10, 264, 114, 36);
	groupLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
	groupLabel.setHorizontalAlignment(SwingConstants.RIGHT);
	groupLabel.setForeground(new Color(0, 0, 0));
	panel_1.add(groupLabel);

	firstNameField = new JTextField();
	firstNameField.setBounds(144, 15, 284, 36);
	firstNameField.setColumns(10);
	panel_1.add(firstNameField);

	lastNameField = new JTextField();
	lastNameField.setBounds(144, 74, 284, 36);
	lastNameField.setColumns(10);
	panel_1.add(lastNameField);

	phoneField = new JTextField();
	phoneField.setBounds(144, 133, 284, 36);
	panel_1.add(phoneField);
	phoneField.setColumns(10);

	emailField = new JTextField();
	emailField.setBounds(144, 199, 284, 36);
	panel_1.add(emailField);
	emailField.setColumns(10);

	groupComboBox = new JComboBox<>();
	groupComboBox.setModel(new DefaultComboBoxModel<String>(
		new String[] { "-- Select --", "Student", "Teacher", "Doctor", "Dev" }));
//	groupComboBox.setSelectedIndex(0);
	groupComboBox.setBounds(145, 267, 283, 36);
	panel_1.add(groupComboBox);

	addressTextArea = new JTextArea();
	addressTextArea.setBounds(655, 218, 270, 93);
	panel_1.add(addressTextArea);

	try {
	    Connection connection = MyConnect.getInstance();
	    PreparedStatement pre = connection.prepareStatement(
		    "SELECT firstname, lastname, phone, email, nhom, address FROM Account WHERE username = ?;");
	    pre.setString(1, user);
	    ResultSet resultSet = pre.executeQuery();
	    String nhom = "";
	    if (resultSet.next()) {
		firstNameField.setText(resultSet.getString("firstname"));
		lastNameField.setText(resultSet.getString("lastname"));
		phoneField.setText(resultSet.getString("phone"));
		emailField.setText(resultSet.getString("email"));
		nhom = resultSet.getString("nhom");
		addressTextArea.setText(resultSet.getString("address"));
	    } else {
		System.out.println("Fail");
	    }

	    if (nhom == null) {
		groupComboBox.setSelectedIndex(0);
	    } else {
		switch (nhom) {
		case "Student":
		    groupComboBox.setSelectedIndex(1);
		    break;
		case "Teacher":
		    groupComboBox.setSelectedIndex(2);
		    break;
		case "Doctor":
		    groupComboBox.setSelectedIndex(3);
		    break;
		case "Dev":
		    groupComboBox.setSelectedIndex(4);
		    break;
		}
	    }
	} catch (SQLException e1) {
	    e1.printStackTrace();
	}

	JSeparator separator = new JSeparator();
	separator.setBounds(479, 11, 7, 300);
	panel_1.add(separator);
	separator.setOrientation(SwingConstants.VERTICAL);

	JLabel proPictureLabel = new JLabel("Change Picture:");
	proPictureLabel.setBounds(513, 12, 133, 36);
	panel_1.add(proPictureLabel);
	proPictureLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));

	avatarprofile = new JLabel("");
	avatarprofile.setBounds(655, 12, 270, 145);
	panel_1.add(avatarprofile);
	avatarprofile.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
	Image avatarImg = new ImageIcon(img).getImage().getScaledInstance(270, 145, Image.SCALE_SMOOTH);
	avatarprofile.setIcon(new ImageIcon(avatarImg));

	browserButton = new JButton("Browser");
	browserButton.addActionListener(e -> {
	    JFileChooser fc = new JFileChooser();
	    fc.setCurrentDirectory(new File(System.getProperty("user.home")));

	    FileNameExtensionFilter fileFilter = new FileNameExtensionFilter("*.Images", "jpg", "png", "gif");
	    fc.addChoosableFileFilter(fileFilter);
	    int saveDialog = fc.showSaveDialog(null);
	    if (saveDialog == JFileChooser.APPROVE_OPTION) {
		File selectedFile = fc.getSelectedFile();
		path = selectedFile.getAbsolutePath();
		Image imagebr = new ImageIcon(path).getImage().getScaledInstance(270, 145, Image.SCALE_DEFAULT);
		avatarprofile.setIcon(new ImageIcon(imagebr));
		Image imagebr2 = new ImageIcon(path).getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT);
		avatar.setIcon(new ImageIcon(imagebr2));
	    } else if (saveDialog == JFileChooser.CANCEL_OPTION) {
		System.out.println("No image select");
	    }
	});
	browserButton.setBounds(655, 168, 94, 36);
	browserButton.setForeground(new Color(0, 0, 0));
	browserButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
	panel_1.add(browserButton);

	JLabel addressLabel = new JLabel("Address:");
	addressLabel.setBounds(532, 209, 114, 36);
	panel_1.add(addressLabel);
	addressLabel.setHorizontalAlignment(SwingConstants.RIGHT);
	addressLabel.setForeground(Color.BLACK);
	addressLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));

	saveButton = new JButton("Save");
	saveButton.addActionListener(e -> {
	    try {
		Connection connection = MyConnect.getInstance();
		PreparedStatement pre = connection.prepareStatement(
			"UPDATE Account SET firstname = ?, lastname = ?, picture = ?, phone = ?, email = ?, address = ?, nhom = ? WHERE username = ?;");
		pre.setString(1, firstNameField.getText());
		pre.setString(2, lastNameField.getText());
		InputStream inputStream = new FileInputStream(new File(path));
		pre.setBlob(3, inputStream);
		pre.setString(4, phoneField.getText());
		pre.setString(5, emailField.getText());
		pre.setString(6, addressTextArea.getText());
		pre.setString(7, groupComboBox.getSelectedItem().toString());
		pre.setString(8, user);
		if (pre.executeUpdate() != 0) {
		    JOptionPane.showMessageDialog(frame, "Success");
		} else {
		    JOptionPane.showMessageDialog(frame, "Fail");
		}
	    } catch (SQLException | FileNotFoundException e1) {
		e1.printStackTrace();
	    }
	});
	saveButton.setBackground(new Color(71, 181, 249));
	saveButton.setBounds(927, 344, 114, 43);
	panel_1.add(saveButton);

    }
}
