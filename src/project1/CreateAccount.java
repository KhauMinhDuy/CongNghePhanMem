package project1;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EtchedBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

public class CreateAccount {

    private JFrame frame;

    public JFrame getFrame() {
	return frame;
    }

    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField usernameField;
    private JPasswordField passField;
    private JPasswordField rePassField;
    private ArrayList<JLabel> arrErr;
    private ArrayList<JTextField> arrField;
    private String image = "";// "images/face.png";
    private String path = "";
    private JButton exitButton;
    private JCheckBox chckbxNewCheckBox;
    private JButton cancel;

    public CreateAccount() {
	initialize();
	addEvent();
    }

    private void addEvent() {
	String[] options = { "Yes", "No" };
	exitButton.addActionListener(e -> {
	    int choose = JOptionPane.showOptionDialog(frame, "Ban muon thoat", "Exit", JOptionPane.OK_CANCEL_OPTION,
		    JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
	    if (choose == 0) {
		System.exit(0);
	    }

	});

	cancel.addActionListener(e -> {
	    int choose = JOptionPane.showOptionDialog(null, "Ban muon quay lai", "Exit", 1, 1, null, options,
		    options[0]);
	    if (choose == 0) {
		LoginForm loginForm = new LoginForm();
		loginForm.getFrame().setVisible(true);
		frame.dispose();
	    }
	});

	chckbxNewCheckBox.addActionListener(e -> {
	    if (chckbxNewCheckBox.isSelected()) {
		passField.setEchoChar((char) 0);
	    } else {
		passField.setEchoChar('*');
	    }
	});
    }

    private void initialize() {
	frame = new JFrame();
	frame.setResizable(false);
	frame.setBounds(100, 100, 569, 827);
	frame.setLocationRelativeTo(null);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setUndecorated(true);
	frame.getContentPane().setLayout(null);

	JPanel panelTop = new JPanel();
	panelTop.setBounds(0, 0, 569, 70);
	panelTop.setBackground(new Color(35, 183, 114));
	panelTop.setLayout(null);
	frame.getContentPane().add(panelTop);

	exitButton = new JButton();
	exitButton.setIcon(new ImageIcon(CreateAccount.class.getResource("/project1/icons/exit.png")));
	exitButton.setFont(new Font("Tohama", Font.BOLD, 20));
	exitButton.setBackground(Color.cyan);
	exitButton.setForeground(Color.RED);
	exitButton.setBounds(520, 10, 40, 40);
	panelTop.add(exitButton);

	JLabel lblNewLabel = new JLabel("Create New Account");
	lblNewLabel.setForeground(new Color(255, 255, 255));
	lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
	lblNewLabel.setBounds(10, 10, 250, 30);
	panelTop.add(lblNewLabel);

	JPanel panelCenter = new JPanel();
	panelCenter.setBackground(new Color(43, 60, 83));
	panelCenter.setBounds(0, 70, 569, 757);
	panelCenter.setLayout(null);
	frame.getContentPane().add(panelCenter);

	JLabel firstName = new JLabel("First Name:");
	firstName.setHorizontalAlignment(SwingConstants.RIGHT);
	firstName.setFont(new Font("Tahoma", Font.PLAIN, 16));
	firstName.setForeground(new Color(255, 255, 255));
	firstName.setBounds(23, 40, 121, 35);
	panelCenter.add(firstName);

	JLabel lastName = new JLabel("Last Name:");
	lastName.setHorizontalAlignment(SwingConstants.RIGHT);
	lastName.setForeground(new Color(255, 255, 255));
	lastName.setFont(new Font("Tahoma", Font.PLAIN, 16));
	lastName.setBounds(23, 120, 121, 35);
	panelCenter.add(lastName);

	JLabel username = new JLabel("Username:");
	username.setHorizontalAlignment(SwingConstants.RIGHT);
	username.setForeground(new Color(255, 255, 255));
	username.setFont(new Font("Tahoma", Font.PLAIN, 16));
	username.setBounds(23, 200, 121, 35);
	panelCenter.add(username);

	JLabel password = new JLabel("Password:");
	password.setHorizontalAlignment(SwingConstants.RIGHT);
	password.setForeground(new Color(255, 255, 255));
	password.setFont(new Font("Tahoma", Font.PLAIN, 16));
	password.setBounds(23, 280, 121, 35);
	panelCenter.add(password);

	JLabel rePassword = new JLabel("Retype Password:");
	rePassword.setHorizontalAlignment(SwingConstants.RIGHT);
	rePassword.setForeground(new Color(255, 255, 255));
	rePassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
	rePassword.setBounds(10, 360, 134, 35);
	panelCenter.add(rePassword);

	JLabel picture = new JLabel("Picture:");
	picture.setHorizontalAlignment(SwingConstants.RIGHT);
	picture.setForeground(new Color(255, 255, 255));
	picture.setFont(new Font("Tahoma", Font.PLAIN, 16));
	picture.setBounds(23, 440, 121, 35);
	panelCenter.add(picture);

	firstNameField = new JTextField();
	firstNameField.setBounds(168, 42, 272, 35);
	panelCenter.add(firstNameField);
	firstNameField.setColumns(10);

	lastNameField = new JTextField();
	lastNameField.setColumns(10);
	lastNameField.setBounds(168, 122, 272, 35);
	panelCenter.add(lastNameField);

	usernameField = new JTextField();
	usernameField.setColumns(10);
	usernameField.setBounds(168, 202, 272, 35);
	panelCenter.add(usernameField);

	passField = new JPasswordField();
	passField.setColumns(10);
	passField.setBounds(168, 282, 272, 35);
	panelCenter.add(passField);

	chckbxNewCheckBox = new JCheckBox("Show Pass");
	chckbxNewCheckBox.setBounds(460, 288, 97, 23);
	panelCenter.add(chckbxNewCheckBox);

	rePassField = new JPasswordField();
	rePassField.setColumns(10);
	rePassField.setBounds(168, 362, 272, 35);
	panelCenter.add(rePassField);

	JCheckBox chckbxShowPass = new JCheckBox("Show Pass");
	chckbxShowPass.setBounds(460, 368, 97, 23);
	panelCenter.add(chckbxShowPass);
	chckbxShowPass.addActionListener(e -> {
	    if (chckbxShowPass.isSelected()) {
		rePassField.setEchoChar((char) 0);
	    } else {
		rePassField.setEchoChar('*');
	    }
	});

	cancel = new JButton("Cancel");
	cancel.setBackground(new Color(255, 102, 0));
	cancel.setForeground(new Color(255, 255, 255));
	cancel.setFont(new Font("Dialog", Font.BOLD, 18));
	cancel.setBounds(127, 637, 112, 40);
	panelCenter.add(cancel);

	Timer time = new Timer(1, e -> {
	    arrErr.stream().forEach(e1 -> {
		e1.setVisible(false);
	    });
	});

	time.setInitialDelay(1000);

	JLabel lblNewLabel_1 = new JLabel("already have an account? ");
	lblNewLabel_1.setForeground(new Color(255, 255, 255));
	lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
	lblNewLabel_1.setBounds(146, 721, 162, 14);
	panelCenter.add(lblNewLabel_1);

	JLabel clickLogin = new JLabel("click here to login");
	clickLogin.addMouseListener(new MouseAdapter() {
	    @Override
	    public void mouseClicked(MouseEvent e) {
		LoginForm form = new LoginForm();
		form.getFrame().setVisible(true);
		frame.dispose();
	    }
	});
	clickLogin.setForeground(Color.WHITE);
	clickLogin.setFont(new Font("Tahoma", Font.BOLD, 14));
	clickLogin.setBounds(306, 721, 162, 14);
	panelCenter.add(clickLogin);

	JLabel avatar = new JLabel("");
	avatar.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
	avatar.setBounds(168, 440, 270, 145);
	panelCenter.add(avatar);

	JButton browser = new JButton("Browser");
	browser.addActionListener(e -> {
	    JFileChooser fc = new JFileChooser();
	    fc.setCurrentDirectory(new File(System.getProperty("user.home")));

	    FileNameExtensionFilter fileFilter = new FileNameExtensionFilter("*.Images", "jpg", "png", "gif");
	    fc.addChoosableFileFilter(fileFilter);
	    int saveDialog = fc.showSaveDialog(null);
	    if (saveDialog == JFileChooser.APPROVE_OPTION) {
		File selectedFile = fc.getSelectedFile();
		path = selectedFile.getAbsolutePath();
		System.out.println(path);
		Image imagelabel = new ImageIcon(path).getImage().getScaledInstance(270, 145, Image.SCALE_DEFAULT);
		avatar.setIcon(new ImageIcon(imagelabel));
	    } else if (saveDialog == JFileChooser.CANCEL_OPTION) {
		System.out.println("No image select");
	    }
	});
	browser.setBounds(459, 449, 100, 35);
	panelCenter.add(browser);

	JLabel FiNameErr = new JLabel("Not Empty");
	FiNameErr.setForeground(new Color(255, 0, 0));
	FiNameErr.setBounds(170, 75, 270, 14);
	panelCenter.add(FiNameErr);

	JLabel laNameErr = new JLabel("Not Empty");
	laNameErr.setForeground(Color.RED);
	laNameErr.setBounds(170, 155, 270, 14);
	panelCenter.add(laNameErr);

	JLabel userErr = new JLabel("Not Empty");
	userErr.setForeground(Color.RED);
	userErr.setBounds(170, 235, 272, 14);
	panelCenter.add(userErr);

	JLabel passErr = new JLabel("Not Empty");
	passErr.setForeground(Color.RED);
	passErr.setBounds(170, 315, 272, 14);
	panelCenter.add(passErr);

	JLabel rePassErr = new JLabel("Not Empty");
	rePassErr.setForeground(Color.RED);
	rePassErr.setBounds(170, 395, 272, 14);
	panelCenter.add(rePassErr);

	JButton create = new JButton("Create");
	create.addActionListener(e -> {
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
			    "INSERT INTO Account(username, password, firstname, lastname, picture, path) VALUES (?,?,?,?,?,?);");
		    pre.setString(1, usernameField.getText());
		    pre.setString(2, passField.getText());
		    pre.setString(3, firstNameField.getText());
		    pre.setString(4, lastNameField.getText());

		    PreparedStatement select = connection
			    .prepareStatement("SELECT username FROM Account WHERE username = ?;");
		    select.setString(1, usernameField.getText());
		    ResultSet resultSet = select.executeQuery();
		    if (resultSet.next()) {
			JOptionPane.showMessageDialog(null, "Username Exist");
		    } else {
			if (!passField.getText().equals(rePassField.getText())) {
			    JOptionPane.showMessageDialog(null, "Confirm pass fail");
			} else if (path.equals("")) {
			    JOptionPane.showMessageDialog(null, "Picture Empty");
			} else {
			    InputStream img = new FileInputStream(new File(path));
			    pre.setBlob(5, img);
			    pre.setString(6, path);
			    if (pre.executeUpdate() != 0) {
				JOptionPane.showMessageDialog(null, "Create Account Success");
			    } else {
				JOptionPane.showMessageDialog(null, "Create Account Fail");
			    }
			}
		    }

		} catch (Exception e2) {
		    e2.printStackTrace();
		}
	    }
	    count = 0;
	    time.stop();

	});
	create.setBackground(new Color(38, 134, 193));
	create.setForeground(new Color(255, 255, 255));
	create.setFont(new Font("Dialog", Font.BOLD, 18));
	create.setBounds(335, 637, 112, 40);
	panelCenter.add(create);

	arrField = new ArrayList<>();
	arrField.add(firstNameField);
	arrField.add(lastNameField);
	arrField.add(usernameField);
	arrField.add(passField);
	arrField.add(rePassField);
	arrField.stream().forEach(e -> {
	    e.addKeyListener(new KeyListener() {

		@Override
		public void keyTyped(KeyEvent e) {

		}

		@Override
		public void keyReleased(KeyEvent e) {

		}

		@Override
		public void keyPressed(KeyEvent e) {
		    time.start();
		}
	    });
	});

	arrErr = new ArrayList<>();
	arrErr.add(FiNameErr);
	arrErr.add(laNameErr);
	arrErr.add(userErr);
	arrErr.add(passErr);
	arrErr.add(rePassErr);

	arrErr.stream().forEach(e -> {
	    e.setVisible(false);
	});

    }
}
