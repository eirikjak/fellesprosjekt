package xcal.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class ParticipantSelectorModel {

	public final static String PROPERTY_EMPLOYEE_ADD  = "employeeAdd";
	public final static String PROPERTY_EMPLOYEE_REMOVE  = "employeeRemove";
	public final static String PROPERTY_GROUP_ADD  = "groupAdd";
	public final static String PROPERTY_GROUP_REMOVE  = "groupRemove";
	private ArrayList<Employee> employees;
	private ArrayList<Group> groups;
	private PropertyChangeSupport pcs;
	
	public ParticipantSelectorModel(){
		pcs = new PropertyChangeSupport(this);
		employees = new ArrayList<Employee>();
		groups = new ArrayList<Group>();
	}
	
	public void addPropertyChangeListener(PropertyChangeListener listener){
		pcs.addPropertyChangeListener(listener);
	}
	
	public void removePropertyChangeListener(PropertyChangeListener listener){
		pcs.removePropertyChangeListener(listener);
	}
	
	public void addGroup(Group group){
		pcs.firePropertyChange(PROPERTY_GROUP_ADD, null, group);
		groups.add(group);
	}
	
	public void removeGroup(Group group){
		pcs.firePropertyChange(PROPERTY_GROUP_REMOVE, null, group);
		groups.remove(group);
	}
	
	public void addEmployee(Employee emp){
		pcs.firePropertyChange(PROPERTY_EMPLOYEE_ADD, null, emp);
		employees.add(emp);
		
	}
	
	public void removeEmployee(Employee emp){
		pcs.firePropertyChange(PROPERTY_EMPLOYEE_REMOVE, null, emp);
		employees.remove(emp);
		
	}
	
	public ArrayList<Employee> getEmployees(){
		return this.employees;
	}
	
	public ArrayList<Group> getGroups(){
		return this.groups;
	}
	public ArrayList<Employee> getParticipantsAndGroups(){
		ArrayList<Employee> result;
		//need to get all the participants in the selected groups
		ArrayList<Employee> unroledGroups = new ArrayList<Employee>();
		
		for(Group group: groups){
			for(Employee groupMember: group.getMembers()){
				boolean duplicate = false;
				for (Employee member:unroledGroups){
					if(member.getEmail().equals(groupMember.getEmail())){
						duplicate = true;
					}
				}
				if(!duplicate){
					unroledGroups.add(groupMember);
				}
			}
		}
		
		result = unroledGroups;
		for(Employee participant: employees){
			boolean duplicate = false;
			for(Employee member: result){
				if(member.getEmail().equals(participant.getEmail())){
					duplicate = true;
				}
			}
			
			if(!duplicate){
				result.add(participant);
			}
		}
		
		return result;
		
		
	}
	
	
}
