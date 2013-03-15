package xcal.server.query;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import xcal.model.*;


public class EmployeeQ
{
	private DbConnection connection;
	/*
	 * EMPLOYEE CREATION / UPDATE  / DELETE / SELECT / CHECK PASSWORD
	 */
	

	public EmployeeQ(DbConnection connection) {
		this.connection=connection;
		// TODO Auto-generated constructor stub
	}

	public void createPerson(Employee p){
		
	}
	
	public void updatePerson(Employee p){
		
	}
	
	public Employee selectPerson(int EmployeeId){
		return null;
	}
	
	public Employee selectPersonWithEmail(String mail)
	{
		String query="select * from Person where email='"+mail+"'";
 	   
 	   try 
 	   {
 		   Statement stat = connection.getConnection().createStatement();
 		   ResultSet result=stat.executeQuery(query);
 		   
 		   result.next();
 		   
 		   Employee e=new Employee(result.getString("name"),result.getString("email"));
 		   return e;
 	   } 
 	   catch (SQLException e) 
 	   {
			//couldn't get from db
			e.printStackTrace();
 	   }
 	   
 	   return null;
	}
	
	public void deletePerson(int EmployeeId){
		
	}
	
	public ArrayList<Meeting> getMeetingsFor (int EmployeeId){
		return null;
	}
	
	public ArrayList<Appointment> getAppointmentsFor (int EmployeeId){
		return null;
	}
	
	public boolean checkPassword(String loginInfo){
		return false;
	}
	

}
