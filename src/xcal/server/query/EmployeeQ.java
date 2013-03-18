package xcal.server.query;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import xcal.model.*;


public class EmployeeQ
{
	private static DbConnection connection;
	private static Statement statement = null;
	/*
	 * EMPLOYEE CREATION / UPDATE  / DELETE / SELECT / CHECK PASSWORD
	 */
	

	public EmployeeQ(DbConnection connection) {
		this.connection=connection;
		// TODO Auto-generated constructor stub
	}
	
	
	
	

	public static Employee createPerson(String name, String mail, String password) throws SQLException{ 
		synchronized (connection) {
			
		
		String sql = "INSERT INTO Person(email, password, name) VALUES("+mail+","+password+","+name+");";
		statement.executeUpdate(sql);
		return new Employee(name, mail, password);
		}
	}
	
	public static Employee createPerson(Employee emp) throws SQLException{
		return createPerson(emp.getName(),emp.getEmail(),emp.getPassword());
	}
	
	public static void updatePerson(String name, String mail, String password) throws SQLException{
		synchronized (connection) {
		String sql ="UPDATE `Person` SET `password`=["+password+"],`name`=["+name+"] WHERE email='"+mail+"';";
				statement.executeUpdate(sql);
		}
	}
	
	public static void updatePerson(Employee emp) throws SQLException{
		synchronized (connection) {
			updatePerson(emp.getName(),emp.getEmail(),emp.getPassword());
		}
		
	}
	
	public static Employee selectPerson(int EmployeeId) throws SQLException{
		String sql = "SELECT * FROM Person WHERE id='"+EmployeeId+"';";
		 
		Statement stat = connection.getConnection().createStatement();
		ResultSet result=stat.executeQuery(sql);
		Employee e=new Employee(result.getString("name"),result.getString("email"), result.getString("password"));
		   return e;
		
	}
	
	public static Employee selectPersonWithEmail(String mail)
	{
		
		synchronized (connection) {
		String query="select * from Person where email='"+mail+"'";
 	   
 	   try 
 	   {
 		   Statement stat = connection.getConnection().createStatement();
 		   ResultSet result=stat.executeQuery(query);
 		   
 		  if( result.next()){
 		   Employee e=new Employee(result.getString("name"),result.getString("email"), result.getString("password"));
 		   return e;
 		  }
 		  return null;
 	   } 
 	   catch (SQLException e) 
 	   {
			//couldn't get from db
			e.printStackTrace();
 	   }
 	   
 	   return null;
 	   
		}
	}
	
	public static void deletePerson(int EmployeeId){
		synchronized (connection) {
			
			
		}
		
		
	}
	
	public ArrayList<Meeting> getMeetingsFor (int EmployeeId){
		synchronized (connection) {
			
			return null;
		}
		
		
	}
	
	public ArrayList<Appointment> getAppointmentsFor (int EmployeeId){
		synchronized (connection) {
		return null;
		}
	}
	
    private String getPasswordFromEmail (String email) throws SQLException{
    	String sql = "SELECT * FROM Person WHERE email = '" + email + "'";
    	ResultSet resultset = statement.executeQuery(sql);
    	resultset.next();
    	return resultset.getString("password");
    }
	
	public  boolean checkPassword(String email, String password) throws SQLException{
		synchronized (connection) {
			
			if (getPasswordFromEmail(email) == password)
				return true;
			else
		return false;
		
		}
	}
	

}
