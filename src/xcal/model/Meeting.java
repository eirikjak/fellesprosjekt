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
		room=r;
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
	
	
	/**send invite sends invite to all participants
	 * 
	 * write to db that invite sent to employee
	 */
	private void sendInvite()
	{
		
	
	}
	
	
}
