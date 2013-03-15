package structs;

import java.io.Serializable;

public class Room implements Serializable{
	private int id;
	private String name;
	private int capacity = 0;

	public Room(int id, String name, int capacity){
		
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getCapacity() {
		return capacity;
	}

}
