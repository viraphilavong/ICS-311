import java.sql.Connection;
import java.sql.DriverManager;

public class Driver {

	public static void main (String[] args) {
		//connecting to db
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/ics311?user=root&password=ics311");
			System.out.println("Connection Object Created : " + con);
		} catch (Exception ex) {
			ex.printStackTrace(); 
		}
	}
}
