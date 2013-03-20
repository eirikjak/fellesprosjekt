package xcal.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Group  implements Serializable{

	private int groupID;
	private String name;
	private ArrayList<Employee> members;
	
	public Group(int id, String name,ArrayList<Employee> members){
		this.groupID = id;
		this.name = name;
		this.members = members;
	}
	
	public Group(){
		
	}

	public void addMember(Employee member){
		members.add(member);
	}
	public void removeMember(Employee member){
		members.remove(member);
	}
	public ArrayList<Employee> getMembers(){
		return this.members;
	}
	public int getGroupID() {
		return groupID;
	}

	public void setGroupID(int groupID) {
		this.groupID = groupID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String toString(){
		return this.name;
	}
	
}
