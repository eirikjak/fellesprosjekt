/**
 * Meeting class
 * 
 * Meeting with date,participants and a manager
 * sends out invite when created
 */


package xcal.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Vector;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

public class Meeting extends Appointment implements Serializable
{
	public static final String PROPERTY_ROOM = "room";
	public static final String PROPERTY_EMPLOYEES_ADD = "employeesAdd";
	public static final String PROPERTY_EMPLOYEES_REMOVE ="employeesRemove";
	private ArrayList<Employee> participants;
	private Room room;
	private PropertyChangeSupport pcs;
	

	
	public Meeting(){
		pcs = new PropertyChangeSupport(this);
		participants = new ArrayList<Employee>();
	}
	
	public Meeting(DateTime start,DateTime end,String title,String description,Employee leader,Room room){
		super(start,end,title,description,leader);
		this.room = room;
		
	}

	
	
	@Override
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
		super.addPropertyChangeListener(listener);
		
	};
	public void removeParticipant(Employee emp)
	{
		
		participants.remove(emp);
		pcs.firePropertyChange(PROPERTY_EMPLOYEES_REMOVE, null, emp);
	}
	
	public void addParticipant(Employee emp)
	{
		pcs.firePropertyChange(PROPERTY_EMPLOYEES_ADD, null, emp);
		participants.add(emp);
		
	}
	

	public ArrayList<Employee> getParticipants(){return participants;}
	public Employee getParticipant(int index){return participants.get(index);}
	

	
	public Room getRoom(){
		return room;
		}
	public void setRoom(Room r){
		pcs.firePropertyChange(PROPERTY_ROOM, this.room, r);
		this.room=r;
		System.out.println("setRoom" + room);
		
	}
	
	public boolean validateParticipants(){
		
		return participants.size() > 0;
	}
	
	@Override
	public boolean validateLocation() {
		return room != null;
	};
	@Override
	public boolean validateFields() {
		return super.validateFields() && validateParticipants();
	};
	
	@Override
	 public String toString(){
		System.out.println("tostring");
         return "" + getFromTime() + " " + getToTime() + " " + getTitle() + " " + getDescription()+ " " + getLeader() + " " + room;
 }
	
	
}
