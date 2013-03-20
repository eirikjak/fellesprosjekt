package xcal.server.query;

import java.awt.Component;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import xcal.model.*;


public class EmployeeQ
{
	private static  DbConnection connection;
	private static Statement statement = null;
	/*
	 * EMPLOYEE CREATION / UPDATE  / DELETE / SELECT / CHECK PASSWORD
	 */
	

	public EmployeeQ(DbConnection connection) {
		System.out.println("constructor");
		this.connection=connection;
	}
	
	
	
	

	public static Employee createPerson(String name, String mail, String password){
		synchronized (connection) {
			
		
		String sql = "INSERT INTO Person(email, password, name) VALUES("+mail+","+password+","+name+");";
		try {
			statement.executeUpdate(sql);
			return new Employee(name, mail, password);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		}
	}
	
	public static Employee createPerson(Employee emp){
		return createPerson(emp.getName(),emp.getEmail(),emp.getPassword());
	}
	
	public static void updatePerson(String name, String mail, String password){
		synchronized (connection) {
			String sql ="UPDATE `Person` SET `password`=["+password+"],`name`=["+name+"] WHERE email='"+mail+"';";
			try {
				statement.executeUpdate(sql);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void updatePerson(Employee emp){
		
			updatePerson(emp.getName(),emp.getEmail(),emp.getPassword());
		
		
	}
	
	public static Employee selectPerson(int EmployeeId){
		
		synchronized (connection) {
			
		
			try {
				String sql = "SELECT * FROM Person WHERE id='"+EmployeeId+"';";
				 
				Statement stat = connection.getConnection().createStatement();
				ResultSet result;
			
				result = stat.executeQuery(sql);
				Employee e=new Employee(result.getString("name"),result.getString("email"), result.getString("password"));
				return e;
			} catch (SQLException e1) {
				e1.printStackTrace();
				return null;
	
			}
		
		}
	}
	
	public static Employee selectPersonWithEmail(String mail)
	{
		
		System.out.println(connection);
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
	
    private String getPasswordFromEmail (String email){
    	
    	synchronized (connection) {
    		try {
		
    	String sql = "SELECT * FROM Person WHERE email = '" + email + "'";
    	ResultSet resultset = statement.executeQuery(sql);
		
		if(resultset.next())
		return resultset.getString("password");
		else
			return null;
		} catch (SQLException e) {	
			e.printStackTrace();
			return null;
		}
    	
    	
    	}
    }
	
	public  boolean checkPassword(String email, String password){
		
			
			if (getPasswordFromEmail(email) == password)
				return true;
			else
		return false;
		
		
	}

	public static ArrayList<Employee> getAllEmployees() {
		
		synchronized (connection) {
			String query="select * from Person";
			ArrayList<Employee> empArray = new ArrayList();
			Employee[] empList = {};
			System.out.println("heii");
	 	   try 
	 	   {
	 		   Statement stat = connection.getConnection().createStatement();
	 		   ResultSet result=stat.executeQuery(query);
	 		   while(result.next()){
	 			   empArray.add(new Employee(result.getString("name"),result.getString("email"), result.getString("password")));
	 		   }
	 	   }
	 	  catch (SQLException e) 
	 	   {
				//couldn't get from db
				e.printStackTrace();
	 	   }
	 	   
	 	   
	//	Employee[] empList ={new Employee("Nemanja", "nemanja@xcal.com","123"),new Employee("Nemanja1", "nemanja1@xcal.com","1123"),new Employee("Nemanja11", "nemanja2@xcal.com","1223")};
		//System.out.println(empList);
	 	//System.out.println(empArray);
//	 	empList = (Employee[]) empArray.toArray();
		return empArray;
		}
	}
	

}
