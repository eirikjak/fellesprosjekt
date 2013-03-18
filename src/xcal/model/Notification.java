/**
 * Notification class
 * 
 * check when notification needs to be sent out for meeting/appointment
 */

package xcal.model;

import java.io.Serializable;
import java.sql.Timestamp;

import org.joda.time.DateTime;

public class Notification implements Serializable
{
	private DateTime notification;
	private Appointment app;
	private Employee emp;
	
	public Notification(Appointment a, Employee e, DateTime notificationTime)
	{
		app=a;
		emp=e;
		this.notification = notificationTime;
		
	}
	public Notification(Appointment a, Employee e)
	{
		this(a,e,DateTime.now());
		
	}
	
	
	public void setNotificationTime(DateTime time){
		this.notification = time;
	}
	public Appointment getAppointment(){return app;}
	public Employee getEmployee(){return emp;}
	public DateTime getNotificationTime(){
		return this.notification;
	}

}
