/**
 * Meeting class
 * 
 * Meeting with date,participants and a manager
 * sends out invite when created
 */


package xcal.model;

import java.util.Vector;

public class Meeting extends Appointment
{
	private Vector<Employee> participants;
	private Employee manager;
	private Room room;
	
	public Meeting(){}
	

	
	public void removeParticipant(Employee e)
	{
		for(int i=0;i<participants.size();++i)//loop thru vector to find invited employee
			if(participants.get(i)==e)
				participants.remove(i);
	}
	
	public void addParticipant(Employee e)
	{
		participants.add(e);
	}
	
	public Vector<Employee> getParticipants(){return participants;}
	public Employee getParticipant(int index){return participants.get(index);}
	
	public Employee getManager(){return manager;}
	public void setManager(Employee e){manager=e;}
	
	public Room getRoom(){return room;}
	public void setRoom(Room r){room=r;}
	
	/**send invite sends invite to all participants
	 * 
	 * write to db that invite sent to employee
	 */
	private void sendInvite()
	{
		
	
	}
	
	
}
