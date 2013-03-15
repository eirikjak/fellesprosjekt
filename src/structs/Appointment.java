package structs;

import java.io.Serializable;

public class Appointment implements Serializable{

	private int id;
	private datetime startDate, endDate;
	private String description =" ";
	private String leader;
	private int place;
	private int room;
	public int getId() {
		return id;
	}
	public datetime getStartDate() {
		return startDate;
	}
	public datetime getEndDate() {
		return endDate;
	}
	public String getDescription() {
		return description;
	}
	public String getLeader() {
		return leader;
	}
	public int getPlace() {
		return place;
	}
	public int getRoom() {
		return room;
	}
	
	
}
