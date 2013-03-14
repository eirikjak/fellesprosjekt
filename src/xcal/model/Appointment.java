/**
 * Appointment class
 * 
 * Simple, holds location&time. usually just personal
 */
package xcal.model;

import java.sql.Timestamp;

public class Appointment
{
	private String location;
	private Timestamp from_time;
	private Timestamp to_time;
	private String name;
	private String description;
	private Timestamp notification;
	
	public Appointment()
	{
		location=null;
	}
	
	
	
	public void setFromTime(Timestamp from){from_time=from;}
	public void setToTime(Timestamp to){to_time=to;}
	
	public void setLocation(String location){this.location=location;}
	
	public Timestamp getFromTime(){return from_time;}
	public Timestamp getToTime(){return to_time;}
	
	public String getLocation(){return location;}

	
	
}
