package xcal.model;

public class Location {

	private String name;
	private int id;
	public Location(String name,int id){
		this.name = name;
		this.id = id;
	}
	
	public Location(String name){
		this.name = name;
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
}
