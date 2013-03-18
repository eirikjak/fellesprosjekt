/**
 * Room info
 * 
 * Room class holds status about rooms in the system.
 * update status with book&delbook
 */
package xcal.model;

public class Room
{
	private String name;
	private int size;

	
	public Room(){}
	
	public Room(int id, String name2, int capacity) {
		// TODO Auto-generated constructor stub
	}

	public String getName(){return this.name;}
	public int getSize(){return size;}
	
	public void setName(String name){this.name=name;}
	public void setSize(int size){this.size=size;}

}
