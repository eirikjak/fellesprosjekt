/**
 * Employee class for fellesprosjekt
 * 
 * 
 * 
 *
 */


package xcal.model;

import java.io.Serializable;
import java.util.Vector;


public class Employee extends Person implements Serializable
{
	private int empId;
	private String password;
	public int getEmpId() {
		return empId;
	}

	public Employee(String name,String mail, String password)
	{
		setName(name);
		setEmail(mail);
		this.password = password;
	}
	
	public Employee(){}
	
	public String getPassword(){
		return this.password;
	}
	public Appointment createAppointment(String time,Room place){return null;}
	public Meeting createMeeting(String time,Room place, Vector<Employee> participants){return null;}
	
}
