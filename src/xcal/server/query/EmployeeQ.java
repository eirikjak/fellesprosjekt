package xcal.server.query;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import xcal.model.*;


public class EmployeeQ
{
	private static DbConnection connection;
	/*
	 * EMPLOYEE CREATION / UPDATE  / DELETE / SELECT / CHECK PASSWORD
	 */
	

	public EmployeeQ(DbConnection connection) {
		this.connection=connection;
		// TODO Auto-generated constructor stub
	}

	public static Object createPerson(Employee p){
		return null;
	}
	
	public static void updatePerson(Employee p){
		
	}
	
	public static Employee selectPerson(int EmployeeId){
		return null;
	}
	
	public static Employee selectPersonWithEmail(String mail)
	{
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
	
	public static void deletePerson(int EmployeeId){
		
	}
	
	public ArrayList<Meeting> getMeetingsFor (int EmployeeId){
		return null;
	}
	
	public ArrayList<Appointment> getAppointmentsFor (int EmployeeId){
		return null;
	}
	
	public static boolean checkPassword(String email){
		return false;
	}
	

}
