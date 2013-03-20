package xcal.model;

import java.io.Serializable;

import org.joda.time.DateTime;

public class Invite implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4106531727169014891L;
	private DateTime Invite;
	private Appointment app;
	private Employee emp;
	
	public Invite(Appointment a, Employee e, DateTime notificationTime)
	{
		app=a;
		emp=e;
		this.Invite = notificationTime;
		
	}
	public Invite(Appointment a, Employee e)
	{
		this(a,e,DateTime.now());
		
	}
	public Invite(){
		
	}
	
	
	public void setNotificationTime(DateTime time){
		this.Invite = time;
	}
	public Appointment getAppointment(){return app;}
	public Employee getEmployee(){return emp;}
	public DateTime getNotificationTime(){
		return this.Invite;
	}
	
	public String toString(){
		return "" + this.app;
	}

}
