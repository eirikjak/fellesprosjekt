/**
 * Room info
 * 
 * Room class holds status about rooms in the system.
 * update status with book&delbook
 */
package xcal.model;

import java.io.Serializable;

public class Room implements Serializable
{
	private String name;
	private int size;
	private int id;
	
	public Room(){}
	
	public Room(int id, String name ,int capacity) {
		this.id = id;
		this.name = name;
		this.size = capacity;
	}

	public String getName(){return this.name;}
	public int getSize(){return size;}
	public int getID(){
		return this.id;
	}
	
	public void setName(String name){this.name=name;}
	public void setSize(int size){this.size=size;}

	public String toString(){
		return name;
	}
}
