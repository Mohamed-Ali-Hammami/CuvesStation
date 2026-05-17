package shell.sijoumi.etatcuve;

import java.sql.*;
import javax.swing.*;

public class ConnectionSqlite {
		Connection conn = null;
		
		public static Connection dbConnector() {
			
			try {
				Class.forName("org.sqlite.JDBC");
				//path after jdbc:sqlite
				Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\fakhe\\OneDrive\\Bureau\\Shell\\test.sqlite");
				JOptionPane.showMessageDialog(null, "Connection succsseful");
				return conn;
			} catch (Exception e) {
				
				JOptionPane.showMessageDialog(null, e);
				return null;
			}
					
		}
}
