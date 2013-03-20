/**
 * Appointment class
 *
 * Simple, holds location&time. usually just personal
 */
package xcal.model;
 
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.sql.Timestamp;

import javax.xml.bind.ValidationEvent;
 
 
import org.joda.time.DateTime;
import org.joda.time.IllegalFieldValueException;

import xcal.client.Client;
 
public class Appointment implements Serializable
{
		public static final String PROPERTY_TO_HOUR = "tohour";
		public static final String PROPERTY_TO_MINUTE = "tominute";
		public static final String PROPERTY_FROM_HOUR = "fromhour";
		public static final String PROPERTY_FROM_MINUTE = "fromminute";
		public static final String PROPERTY_TITLE = "title";
		public static final String PROPERTY_DESCRIPTION = "description";
		public static final String PROPERTY_LOCATION = "location";
		public static final String PROPERTY_NOTIFICATION = "notification";
		public static final String PROPERTY_DAY = "day";
		
        private int appId;
        private PropertyChangeSupport pcs;
        private Location location;
        private DateTime from_time;
        private DateTime to_time;
        private String title;
        private Employee leader;
        private String description;
        private Notification notification;
 
       public Appointment(){
    	   this(new DateTime(0,1,1,0,0), new DateTime(0,1,1,0,0),"","", null, new Location("") );
       }
        
        public Appointment(DateTime start,DateTime end,String title,String description,Employee leader,Location location)
        {
                this.from_time = start;
                this.to_time = end;
                this.title = title;
                this.description = description;
                this.leader = leader;
                this.location = location;
                pcs = new PropertyChangeSupport(this);
                
               
               
        }
        public Appointment(DateTime start,DateTime end,String title,String description,Employee leader)
        {
        	
        		this(start,end,title,description,leader,null);
 
        }
 
 
       public void addPropertyChangeListener(PropertyChangeListener listener){
    	   pcs.addPropertyChangeListener(listener);
       }
       public void removePropertyChangeListener(PropertyChangeListener listener){
    	   pcs.removePropertyChangeListener(listener);
       }
 
        public void setFromTime(DateTime from){
        	
        	setDate(new DateTime(from.getYear(),from.getMonthOfYear(),from.getDayOfMonth(),0,0));
        	setFromHour(from.getHourOfDay());
        	setFromMinute(from.getMinuteOfHour());
        	from_time=from;
        	}
        public void setFromTime(Timestamp from){this.from_time = new DateTime(from);}
        public void setDate(DateTime date){
        	DateTime newTime;
        	int oldYear= from_time.getDayOfYear();
        	int oldMonth = from_time.getMonthOfYear();
        	int oldDay = from_time.getDayOfMonth();
        	try{
        		newTime = new DateTime(date.getYear(), date.getMonthOfYear(), date.getDayOfMonth(), from_time.getHourOfDay(), from_time.getMinuteOfHour());
        	}catch(IllegalFieldValueException e){
        		newTime = from_time;
        	}
        	from_time = newTime;
        	to_time = new DateTime(from_time.getYear(), from_time.getMonthOfYear(), from_time.getDayOfMonth(), to_time.getHourOfDay(), to_time.getMinuteOfHour());
        	
        	pcs.firePropertyChange(PROPERTY_DAY,new DateTime(oldYear, oldMonth, oldDay, 0, 0), new DateTime(from_time.getYear(), from_time.getMonthOfYear(), from_time.getDayOfMonth(), 0, 0));
        	
        	
        }
        public void setFromHour(String hour){
        	try{
        		setFromHour(Integer.parseInt(hour));
        	}catch(NumberFormatException e){
        		setFromHour(0);
        	}
        }
        public void setFromHour(int hour){
        	int old = from_time.getHourOfDay();
        	DateTime newTime;
        	try{
        		newTime = new DateTime(from_time.getYear(), from_time.getMonthOfYear(), from_time.getDayOfMonth(), hour, from_time.getMinuteOfHour());
        	}catch(IllegalFieldValueException e){
        		newTime = new DateTime(from_time.getYear(), from_time.getMonthOfYear(), from_time.getDayOfMonth(), 0, from_time.getMinuteOfHour());
        	}
        	from_time = newTime;
        	pcs.firePropertyChange(PROPERTY_FROM_HOUR, null, from_time.getHourOfDay());
        	
        }
        public void setFromMinute(String minute){
        	try{
        		setFromMinute(Integer.parseInt(minute));
        	}catch(NumberFormatException e){
        		setFromMinute(0);
        	}
        }
        public void setFromMinute(int minute){
        	int old = from_time.getMinuteOfHour();
        	DateTime newTime;
        	try{
        		newTime = new DateTime(from_time.getYear(), from_time.getMonthOfYear(), from_time.getDayOfMonth(), from_time.getHourOfDay(), minute);
        	}catch(IllegalFieldValueException e){
        		newTime = new DateTime(from_time.getYear(), from_time.getMonthOfYear(), from_time.getDayOfMonth(), from_time.getHourOfDay(), 0);
        	}
        	from_time = newTime;
        	pcs.firePropertyChange(PROPERTY_FROM_MINUTE, null, from_time.getMinuteOfHour());
        	
        }
        public void setToTime(DateTime to){
        	setDate(new DateTime(to.getYear(),to.getMonthOfYear(),to.getDayOfMonth(),0,0));
        	setToHour(to.getHourOfDay());
        	setToMinute(to.getMinuteOfHour());
        	to_time=to;
        }
        public void setToTime(Timestamp to){ setToTime(new DateTime(to));}
        public void setToHour(String hour){
        	try{
        		setToHour(Integer.parseInt(hour));
        	}catch(NumberFormatException e){
        		setFromHour(0);
        	}
        }
        public void setToHour(int hour){
        	int old = to_time.getHourOfDay();
        	DateTime newTime;
        	try{
        		newTime = new DateTime(to_time.getYear(), to_time.getMonthOfYear(), to_time.getDayOfMonth(), hour, to_time.getMinuteOfHour());
        	}catch(IllegalFieldValueException e){
        		newTime = new DateTime(to_time.getYear(), to_time.getMonthOfYear(), to_time.getDayOfMonth(), 0, to_time.getMinuteOfHour());
        	}
        	to_time = newTime;
        	pcs.firePropertyChange(PROPERTY_TO_HOUR, null, to_time.getHourOfDay());
        }
        
        public void setToMinute(String minute){
        	try{
        		setToMinute(Integer.parseInt(minute));
        	}catch(NumberFormatException e){
        		setToMinute(0);
        	}
        }
        public void setToMinute(int minute){
        	int old = to_time.getMinuteOfHour();
        	DateTime newTime;
        	
        	try{
        		newTime = new DateTime(to_time.getYear(), to_time.getMonthOfYear(), to_time.getDayOfMonth(), to_time.getHourOfDay(), minute);
        	}catch(IllegalFieldValueException e){
        		newTime = new DateTime(to_time.getYear(), to_time.getMonthOfYear(), to_time.getDayOfMonth(), to_time.getHourOfDay(), 0);
        	}
        	to_time = newTime;
        	pcs.firePropertyChange(PROPERTY_TO_MINUTE, null, to_time.getMinuteOfHour());
        }
        	
        
        public void setNotification(Notification notification){
        	
        	this.notification = notification;
        	};
        public void setLocation(Location location){
        	pcs.firePropertyChange(PROPERTY_LOCATION, this.location, location);
        	this.location=location;
        	}
      
        public void setDescription(String desc){
        	pcs.firePropertyChange(PROPERTY_DESCRIPTION, this.description, desc);
        	this.description=desc;
        	}
        public void setTitle(String title){
        	pcs.firePropertyChange(PROPERTY_TITLE, this.title, title);
        	this.title = title;
        }
        public void setAppId(int id){this.appId = id;}
 
        public int getLocationID(){return this.location.getID();};
        public DateTime getFromTime(){return from_time;}
        public DateTime getToTime(){return to_time;}
        public String getLocationName(){return location.getName();}
        public Employee getLeader(){ return this.leader; }
    
        public String getDescription(){return description;}
        public String getTitle(){ return this.title; }
        public int getAppId() { return appId;}
        public Notification getNotification(){return this.notification;};
 
        public boolean validateDate(){
        	
        	DateTime today = DateTime.now();
			if(!today.isBefore(from_time)){
				return false;
			}
			if(!from_time.isBefore(to_time)){
				
				return false;
			}
				
        	return true;
        }
        
        public boolean validateDescription(){
        	return description.length() > 0;
        }
        
        public boolean validateLocation(){
        	return location.getName().length() > 0;
        }
       
        public boolean validateTitle(){
        	return this.title.length() > 0;
        }
        public boolean validateFields(){
        	return validateDate() && validateDescription() && validateLocation() && validateTitle();
        }
        public String toString(){
                return "" + from_time + " " + to_time + " " + title + " " + description + " " + leader + " " + location;
        }
        
        
        
       
       
}