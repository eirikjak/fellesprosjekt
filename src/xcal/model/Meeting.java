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
	private ArrayList<Employee> participants;
	private Room room;
	private PropertyChangeSupport pcs;
	

	
	public Meeting(){
		
		this(new DateTime(),new DateTime(),"", "",new Employee(),new ArrayList<Employee>(),new Room());
		
	}
	
	public Meeting(DateTime start,DateTime end,String title,String description,Employee leader,Room room){
		this(new DateTime(),new DateTime(),title, description ,leader,new ArrayList<Employee>(),room);
		
		
	}
	public Meeting(DateTime start,DateTime end,String title,String description,Employee leader, ArrayList<Employee> participants ,Room room){
		super(start,end,title,description,leader);
		this.participants = participants;
		this.room = room;
		pcs = new PropertyChangeSupport(this);
	}

	
	public void setParticipants(ArrayList<Employee> participants){
		this.participants = participants;
	}
	
	public ArrayList<Employee> getParticipants(){
		return participants;
	}
	
	@Override
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
		super.addPropertyChangeListener(listener);
		
	};
	
	public Employee getParticipant(int index){return participants.get(index);}
	public Room getRoom(){
		return room;
		}
	public void setRoom(Room r){
		pcs.firePropertyChange(PROPERTY_ROOM, this.room, r);
		this.room=r;
		
		
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
         return "" + getFromTime() + " " + getToTime() + " " + getTitle() + " " + getDescription()+ " " + getLeader() + " " + room;
 }
	
	
}
