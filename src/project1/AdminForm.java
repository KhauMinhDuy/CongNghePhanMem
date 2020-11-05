package project1;

import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class AdminForm {

    private JFrame frame;
    private JButton exitButton;
    private JTable table;
    private static DefaultTableModel model;
    private JTextField username;
    private JPasswordField password;
    private JTextField firstname;
    private JTextField lastname;
    private JTextField phone;
    private JTextField email;
    private JTextField address;
    private JComboBox<String> group;
    private int row;

    public JFrame getFrame() {
	return frame;
    }

    public AdminForm() {
	initialize();
    }

    private void initialize() {
	frame = new JFrame("User Manager");
	frame.setResizable(false);
	frame.setBounds(100, 100, 1692, 772);
	frame.setLocationRelativeTo(null);
	frame.setUndecorated(true);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.getContentPane().setLayout(null);

	JPanel panel = new JPanel();
	panel.setBackground(new Color(35, 183, 114));
	panel.setBounds(0, 0, 1692, 85);
	frame.getContentPane().add(panel);
	panel.setLayout(null);

	exitButton = new JButton("");
	exitButton.setBackground(Color.cyan);
	String[] options = new String[] { "Yes", "No" };
	exitButton.addActionListener(e -> {
	    int choose = JOptionPane.showOptionDialog(frame, "Ban muon thoat", "Exit", JOptionPane.OK_CANCEL_OPTION,
		    JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
	    if (choose == 0) {
		System.exit(0);
	    }
	});
	exitButton.setIcon(new ImageIcon(AdminForm.class.getResource("/project1/icons/exit.png")));
	exitButton.setBounds(1647, 10, 35, 35);
	panel.add(exitButton);

	JLabel lblNewLabel = new JLabel("User Manager");
	lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
	lblNewLabel.setForeground(new Color(255, 255, 255));
	lblNewLabel.setBounds(10, 10, 174, 44);
	panel.add(lblNewLabel);

	JScrollPane scrollPane = new JScrollPane();
	scrollPane.setBounds(10, 98, 1270, 590);
	frame.getContentPane().add(scrollPane);

	table = new JTable();
	table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	table.setModel(new DefaultTableModel(new Object[][] {},
		new String[] { "Username", "FirstName", "LastName", "Phone", "Email", "Address", "Group" }) {
	    private static final long serialVersionUID = 1L;
	    boolean[] columnEditables = new boolean[] { false, false, false, false, false, false, false };

	    public boolean isCellEditable(int row, int column) {
		return columnEditables[column];
	    }
	});
	table.getColumnModel().getColumn(0).setResizable(false);
	table.getColumnModel().getColumn(1).setResizable(false);
	table.getColumnModel().getColumn(2).setResizable(false);
	table.getColumnModel().getColumn(3).setResizable(false);
	table.getColumnModel().getColumn(4).setResizable(false);
	table.getColumnModel().getColumn(5).setResizable(false);
	table.getColumnModel().getColumn(6).setResizable(false);
	scrollPane.setViewportView(table);
	model = (DefaultTableModel) table.getModel();
	try {
	    Connection connection = MyConnect.getInstance();
	    PreparedStatement pre = connection.prepareStatement("SELECT * FROM Account;");
	    ResultSet rs = pre.executeQuery();
	    while (rs.next()) {
		model.addRow(new Object[] { rs.getString("username"), rs.getString("firstname"),
			rs.getString("lastname"), rs.getString("phone"), rs.getString("email"), rs.getString("address"),
			rs.getString("nhom") });

	    }

	} catch (SQLException e1) {
	    e1.printStackTrace();
	}

	JButton xoaButton = new JButton("Xoa");
	xoaButton.setBounds(308, 699, 90, 40);
	frame.getContentPane().add(xoaButton);

	JButton suaButton = new JButton("Sua");
	suaButton.setBounds(209, 699, 90, 40);
	frame.getContentPane().add(suaButton);

	JButton themButton = new JButton("Them");
	themButton.setBounds(110, 699, 90, 40);
	frame.getContentPane().add(themButton);

	JButton btnLogin = new JButton("Login");
	btnLogin.addActionListener(e -> {
	    int choose = JOptionPane.showOptionDialog(frame, "Ban muon quay lai login", "Exit",
		    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
	    if (choose == 0) {
		LoginForm loginForm = new LoginForm();
		loginForm.getFrame().setVisible(true);
		frame.dispose();
	    }

	});
	btnLogin.setBounds(10, 700, 90, 40);
	frame.getContentPane().add(btnLogin);

	JPanel panel_1 = new JPanel();
	panel_1.setBounds(1290, 96, 392, 595);
	frame.getContentPane().add(panel_1);
	panel_1.setLayout(null);

	JLabel lblNewLabel_1 = new JLabel("UserName");
	lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
	lblNewLabel_1.setBounds(0, 10, 100, 30);
	panel_1.add(lblNewLabel_1);

	username = new JTextField();
	username.setBounds(110, 10, 272, 30);
	username.setColumns(10);
	username.setEnabled(false);
	panel_1.add(username);

	JLabel lblNewLabel_1_1 = new JLabel("Password");
	lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
	lblNewLabel_1_1.setBounds(0, 55, 100, 30);
	panel_1.add(lblNewLabel_1_1);

	password = new JPasswordField();
	password.setColumns(10);
	password.setBounds(110, 55, 272, 30);
	password.setEnabled(false);
	panel_1.add(password);

	JLabel lblNewLabel_1_2 = new JLabel("FirstName");
	lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.RIGHT);
	lblNewLabel_1_2.setBounds(0, 96, 100, 30);
	panel_1.add(lblNewLabel_1_2);

	firstname = new JTextField();
	firstname.setColumns(10);
	firstname.setBounds(110, 96, 272, 30);
	panel_1.add(firstname);

	JLabel lblNewLabel_1_3 = new JLabel("LastName");
	lblNewLabel_1_3.setHorizontalAlignment(SwingConstants.RIGHT);
	lblNewLabel_1_3.setBounds(0, 137, 100, 30);
	panel_1.add(lblNewLabel_1_3);

	lastname = new JTextField();
	lastname.setColumns(10);
	lastname.setBounds(110, 137, 272, 30);
	panel_1.add(lastname);

	JLabel lblNewLabel_1_4 = new JLabel("Phone");
	lblNewLabel_1_4.setHorizontalAlignment(SwingConstants.RIGHT);
	lblNewLabel_1_4.setBounds(0, 182, 100, 30);
	panel_1.add(lblNewLabel_1_4);

	phone = new JTextField();
	phone.setColumns(10);
	phone.setBounds(110, 182, 272, 30);
	panel_1.add(phone);

	JLabel lblNewLabel_1_5 = new JLabel("Email");
	lblNewLabel_1_5.setHorizontalAlignment(SwingConstants.RIGHT);
	lblNewLabel_1_5.setBounds(0, 223, 100, 30);
	panel_1.add(lblNewLabel_1_5);

	email = new JTextField();
	email.setColumns(10);
	email.setBounds(110, 223, 272, 30);
	panel_1.add(email);

	JLabel lblNewLabel_1_6 = new JLabel("Address");
	lblNewLabel_1_6.setHorizontalAlignment(SwingConstants.RIGHT);
	lblNewLabel_1_6.setBounds(0, 264, 100, 30);
	panel_1.add(lblNewLabel_1_6);

	address = new JTextField();
	address.setColumns(10);
	address.setBounds(110, 264, 272, 30);
	panel_1.add(address);

	JLabel lblNewLabel_1_7 = new JLabel("Group");
	lblNewLabel_1_7.setHorizontalAlignment(SwingConstants.RIGHT);
	lblNewLabel_1_7.setBounds(0, 305, 100, 30);
	panel_1.add(lblNewLabel_1_7);

	group = new JComboBox<>();
	group.setModel(new DefaultComboBoxModel<String>(new String[] { "Dev", "Teacher", "Student", "Doctor" }));
	group.setBounds(110, 305, 272, 30);
	panel_1.add(group);

	JButton save = new JButton("Save");
	save.setBounds(293, 346, 89, 36);
	panel_1.add(save);

	ListSelectionModel selectionModel = table.getSelectionModel();
	selectionModel.addListSelectionListener(e -> {
	    username.setEnabled(false);
	    password.setEnabled(false);
	    firstname.setEnabled(false);
	    lastname.setEnabled(false);
	    phone.setEnabled(false);
	    email.setEnabled(false);
	    address.setEnabled(false);
	    group.setEnabled(false);
	    row = table.getSelectedRow();
	    System.out.println(row);
	    if (row >= 0) {
		username.setText((String) model.getValueAt(row, 0));
		firstname.setText((String) model.getValueAt(row, 1));
		lastname.setText((String) model.getValueAt(row, 2));
		phone.setText((String) model.getValueAt(row, 3));
		email.setText((String) model.getValueAt(row, 4));
		address.setText((String) model.getValueAt(row, 5));
		group.setSelectedItem(model.getValueAt(row, 6));
	    }
	});

	themButton.addActionListener(e -> {
	    username.setEnabled(true);
	    username.setText("");
	    password.setEnabled(true);
	    password.setText("");
	    firstname.setEnabled(true);
	    firstname.setText("");
	    lastname.setEnabled(true);
	    lastname.setText("");
	    phone.setEnabled(true);
	    phone.setText("");
	    email.setEnabled(true);
	    email.setText("");
	    address.setEnabled(true);
	    address.setText("");
	    group.setEnabled(true);
	    group.setSelectedIndex(0);
	});

	suaButton.addActionListener(e -> {
	    firstname.setEnabled(true);
	    lastname.setEnabled(true);
	    phone.setEnabled(true);
	    email.setEnabled(true);
	    address.setEnabled(true);
	    group.setEnabled(true);
	    group.setSelectedIndex(0);
	});

	xoaButton.addActionListener(e -> {
	    String username = (String) model.getValueAt(row, 0);
	    try {
		Connection connection = MyConnect.getInstance();
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

	});
	save.addActionListener(e -> {
	    try {
		Connection connection = MyConnect.getInstance();

		PreparedStatement statement = connection
			.prepareStatement("SELECT username FROM Account WHERE username = ?");
		statement.setString(1, username.getText());
		ResultSet resultSet = statement.executeQuery();
		if (resultSet.next()) {
		    PreparedStatement pre2 = connection.prepareStatement(
			    "UPDATE Account SET firstname = ?, lastname = ?, phone = ?, email = ?, address = ?, nhom = ? WHERE username = ?;");
		    pre2.setString(1, firstname.getText());
		    pre2.setString(2, lastname.getText());
		    pre2.setString(3, phone.getText());
		    pre2.setString(4, email.getText());
		    pre2.setString(5, address.getText());
		    pre2.setString(6, group.getSelectedItem().toString());
		    pre2.setString(7, username.getText());
		    int executeUpdate = pre2.executeUpdate();
		    if (executeUpdate != 0) {
			JOptionPane.showMessageDialog(frame, "Sua thanh cong");
			model.setValueAt(firstname.getText(), row, 1);
			model.setValueAt(lastname.getText(), row, 2);
			model.setValueAt(phone.getText(), row, 3);
			model.setValueAt(email.getText(), row, 4);
			model.setValueAt(address.getText(), row, 5);
			model.setValueAt(group.getSelectedItem().toString(), row, 6);
		    } else {
			JOptionPane.showMessageDialog(frame, "Sua that bai");
		    }
		} else {
		    PreparedStatement pre = connection.prepareStatement(
			    "INSERT INTO Account(username, password, firstname, lastname, phone, email, address, nhom) VALUES (?,?,?,?,?,?,?,?);");
		    pre.setString(1, username.getText());
		    pre.setString(2, password.getText());
		    pre.setString(3, firstname.getText());
		    pre.setString(4, lastname.getText());
		    pre.setString(5, phone.getText());
		    pre.setString(6, email.getText());
		    pre.setString(7, address.getText());
		    pre.setString(8, group.getSelectedItem().toString());
		    long executeLargeUpdate = pre.executeLargeUpdate();
		    if (executeLargeUpdate != 0) {
			model.addRow(new Object[] { username.getText(), firstname.getText(), lastname.getText(),
				phone.getText(), email.getText(), address.getText(),
				group.getSelectedItem().toString() });
			JOptionPane.showMessageDialog(frame, "Them tai khoan thanh cong");
		    } else {
			JOptionPane.showMessageDialog(frame, "Them tai khoan that bai");
		    }
		}
	    } catch (SQLException e1) {
		e1.printStackTrace();
	    }
	});

    }
}
