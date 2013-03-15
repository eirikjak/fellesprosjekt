/**
 * Notification class
 * 
 * check when notification needs to be sent out for meeting/appointment
 */

package xcal.model;

import java.sql.Timestamp;

public class Notification 
{
	private Timestamp notification;
	
	private Appointment app;
	private Employee emp;
	
	public Notification(Appointment a, Employee e)
	{
		app=a;
		emp=e;
		
	}
	
	
	public Appointment getAppointment(){return app;}
	public Employee getEmployee(){return emp;}

}
