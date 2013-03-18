package xcal.model;

import java.io.Serializable;

public class Location implements Serializable {

	private String name;
	private int id;
	public Location(String name,int id){
		this.name = name;
		this.id = id;
	}
	
	public Location(String name){
		this.name = name;
		this.id = -1;
	}
	public String getName(){
		return this.name;
	}
	public int getID(){
		return this.id;
	}
	
	public void setID(int id){
		this.id = id;
	}
	public void setName(String name){
		this.name  = name;
	}
	
	public String toString(){
		
		return this.name + this.id;
	}
}
