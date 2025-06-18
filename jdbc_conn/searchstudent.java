package jdbc_conn;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;
import java.sql.ResultSet;

public class searchstudent {
	public static void main(String arg[])
	{
		try
			(Connection con=db_connection.getConnection();  //db_connection->file name
			Scanner sc=new Scanner(System.in))
			{
				for(int i=1;i<=5;i++)
				 {
					   System.out.println("\nEnter ID :");
					   String id=sc.nextLine();
					   String query="select * from students where id=?";
					   PreparedStatement pst=con.prepareStatement(query);
					   pst.setString(1, id);
					   ResultSet rs=pst.executeQuery();
			   
					   while(rs.next())
					   {
						   System.out.println("Name :"+rs.getString("name"));
						   System.out.println("Email :"+rs.getString("email"));
						   System.out.println("Cource :"+rs.getString("cource"));
			           }
				  }
			 }  
				
		 catch(Exception e)
		   {
			    e.printStackTrace();
			}
				
	}	   

}
