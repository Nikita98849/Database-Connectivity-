package jdbc_conn;
import java.sql.Connection;
import java.sql.DriverManager;

public class db_connection {
	public static Connection getConnection() {
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			return DriverManager.getConnection("jdbc:mysql://localhost:3306/college","root","nikita");
		}
		catch(Exception e)
		{
			System.out.println("Connection failed :"+e);
			return null;
		}
	}

}
