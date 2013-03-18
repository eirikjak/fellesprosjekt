/**
 * Appointment class
 * 
 * Simple, holds location&time. usually just personal
 */
package xcal.model;

import java.io.Serializable;
import java.sql.Timestamp;


import org.joda.time.DateTime;

public class Appointment implements Serializable
{
	private int appId;
	
<<<<<<< HEAD
	private int locId;
	private String location;
	private Timestamp from_time;
	private Timestamp to_time;
=======
	private Location location;
	private DateTime from_time;
	private DateTime to_time;
	private String title;
	private Employee leader;
>>>>>>> 68959c5730b07a7ebd670b42854786212e58820e
	private String name;
	private String description;

	
	public Appointment(DateTime start,DateTime end,String title,String description,Employee leader,Location location)
	{
		this.from_time = start;
		this.to_time = end;
		this.title = title;
		this.description = description;
		this.leader = leader;
		
		
	}

	
<<<<<<< HEAD
<<<<<<< HEAD
	public void setFromTime(DateTime from){from_time=from;}
	public void setToTime(DateTime to){to_time=to;}
=======
	public void setFromTime(Timestamp from){from_time=from;}
	public void setToTime(Timestamp to){to_time=to;}
>>>>>>> d465fe07bb1a0a7b178e2fde21c72fcaa275a506
	public void setLocation(String location){this.location=location;}
=======
	
	public Appointment(){};
	

	public void setFromTime(DateTime from){from_time=from;}
	public void setFromTime(Timestamp from){this.from_time = new DateTime(from);}
	public void setToTime(DateTime to){to_time=to;}
	public void setToTime(Timestamp to){ this.to_time = new DateTime(to);}
		
	public void setLocation(Location location){this.location=location;}

>>>>>>> 68959c5730b07a7ebd670b42854786212e58820e
	public void setName(String name){this.name=name;}
	public void setDescription(String desc){this.description=desc;}
	public void setTitle(String title){this.title = title;}
	
<<<<<<< HEAD
<<<<<<< HEAD
	public DateTime getFromTime(){return from_time;}
	public DateTime getToTime(){return to_time;}
=======
	public Timestamp getFromTime(){return from_time;}
	public Timestamp getToTime(){return to_time;}
>>>>>>> d465fe07bb1a0a7b178e2fde21c72fcaa275a506
	public String getLocation(){return location;}
=======

	public int getLocationID(){return this.location.getID();};
	public DateTime getFromTime(){return from_time;}
	public DateTime getToTime(){return to_time;}
	public String getLocationName(){return location.getName();}
	public Employee getLeader(){
		return this.leader;
	}
>>>>>>> 68959c5730b07a7ebd670b42854786212e58820e
	public String getName(){return name;}
	public String getDescription(){return description;}
	public String getTitle(){
		return this.title;
	}
	public int getAppId() {
		return appId;
	}

	public String toString(){
		return "" + from_time + " " + to_time + " " + title + " " + description + " " + leader + " " + location;
	}
	
	
}
