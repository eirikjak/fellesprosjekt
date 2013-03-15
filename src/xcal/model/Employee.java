/**
 * Employee class for fellesprosjekt
 * 
 * 
 * 
 *
 */


package xcal.model;

import java.util.Vector;


public class Employee extends Person
{
	
	
	public Employee(String name,String mail)
	{
		setName(name);
		setEmail(mail);
	}
	
	public Employee(){}
	
	public Appointment createAppointment(String time,Room place){return null;}
	public Meeting createMeeting(String time,Room place, Vector<Employee> participants){return null;}
	
}
