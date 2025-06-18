package jdbc_conn;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class AddStudent
{
	public static void main(String arg[])
	{
		try
			(Connection con=db_connection.getConnection();  //db_connection->file name
			Scanner sc=new Scanner(System.in))
			{
				for(int i=1;i<=5;i++)
				{
			   System.out.println("Enter name :");
			   String name=sc.nextLine();
			   System.out.println("Enter email :");
			   String email=sc.nextLine();
			   System.out.println("Enter course :");
			   String cource=sc.nextLine();
			   
			   String query="insert into students(name,email,cource)values(?,?,?)";
			   PreparedStatement pst=con.prepareStatement(query);
			   pst.setString(1, name);
			   pst.setString(2, email);
			   pst.setString(3, cource);
			   
			   int rows=pst.executeUpdate();
			     System.out.println(rows+"Student(s).added");
			}
			}
			 
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
