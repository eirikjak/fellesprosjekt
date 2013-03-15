package xcal.server.query;

import java.util.ArrayList;

import xcal.model.*;


public class EmployeeQ {
	
	/*
	 * EMPLOYEE CREATION / UPDATE  / DELETE / SELECT / CHECK PASSWORD
	 */
	
	public void createPerson(Person p){
		
	}
	
	public void updatePerson(Person p){
		
	}
	
	public Person selectPerson(int EmployeeId){
		return null;
	}
	
	public Person selectPersonWithEmail(String email){
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
