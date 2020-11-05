package project1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class MyConnect {

    private static Connection instance;

    private MyConnect() {
    }

    public static Connection getInstance() {
	if (instance == null) {
	    synchronized (MyConnect.class) {
		if (instance == null) {
		    try {
			instance = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=project1;",
				"cnpm", "cnpm");
		    } catch (SQLException e) {
                        JOptionPane.showMessageDialog(null, "Khong ket noi duoc CSDL");
			e.printStackTrace();
		    }
		}
	    }
	}
	return instance;
    }

//    public static void main(String[] args) {
//	Connection connection = MyConnect.getInstance();
//	try {
//	    if (connection.isValid(2)) {
//		System.out.println("Connect");
//	    } else {
//		System.out.println("Not connect");
//	    }
//	} catch (SQLException e) {
//	    e.printStackTrace();
//	}
//    }
}
