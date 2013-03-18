/**
 * Appointment class
 * 
 * Simple, holds location&time. usually just personal
 */
package xcal.model;

import java.sql.Timestamp;

import org.joda.time.DateTime;

public class Appointment
{
	private int appId;
	
	private String location;
	private DateTime from_time;
	private DateTime to_time;
	private String name;
	private String description;
	private String title;
	private Employee leader;
	
	public Appointment(DateTime start,DateTime end,String title,String description,Employee leader,String place)
	{
		this.from_time = start;
		this.to_time = end;
		this.title = title;
		this.description = description;
		this.leader = leader;
		this.location = place;
		
		
	}
	

	
	public void setFromTime(DateTime from){from_time=from;}
	public void setToTime(DateTime to){to_time=to;}
	public void setLocation(int location){this.location=location;}
	public void setName(String name){this.name=name;}
	public void setDescription(String desc){this.description=desc;}

	
	public DateTime getFromTime(){return from_time;}
	public DateTime getToTime(){return to_time;}
	public int getLocation(){return location;}
	public String getName(){return name;}
	public String getDescription(){return description;}
	public int getAppId() {
		return appId;
	}
	public Employee getLeader(){return leader;}

	
	
}
