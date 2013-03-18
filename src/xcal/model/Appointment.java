/**
 * Appointment class
 * 
 * Simple, holds location&time. usually just personal
 */
package xcal.model;

import java.sql.Timestamp;

public class Appointment
{
	private int appId;
	
	private int locId;
	private String location;
	private Timestamp from_time;
	private Timestamp to_time;
	private String name;
	private String description;
	
	public Appointment()
	{
		location=null;
	}
	

	
<<<<<<< HEAD
	public void setFromTime(DateTime from){from_time=from;}
	public void setToTime(DateTime to){to_time=to;}
=======
	public void setFromTime(Timestamp from){from_time=from;}
	public void setToTime(Timestamp to){to_time=to;}
>>>>>>> d465fe07bb1a0a7b178e2fde21c72fcaa275a506
	public void setLocation(String location){this.location=location;}
	public void setName(String name){this.name=name;}
	public void setDescription(String desc){this.description=desc;}

	
<<<<<<< HEAD
	public DateTime getFromTime(){return from_time;}
	public DateTime getToTime(){return to_time;}
=======
	public Timestamp getFromTime(){return from_time;}
	public Timestamp getToTime(){return to_time;}
>>>>>>> d465fe07bb1a0a7b178e2fde21c72fcaa275a506
	public String getLocation(){return location;}
	public String getName(){return name;}
	public String getDescription(){return description;}
	public int getAppId() {
		return appId;
	}

	
	
}
