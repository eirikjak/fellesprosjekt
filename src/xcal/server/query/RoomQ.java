package xcal.server.query;

import xcal.model.*;
import java.sql.*;
public class RoomQ {
	
	;

	public void createRoom(int id, String name, int capacity){
		statement = connection.createStatement();
		statement  = "INSERT INTO Room (id, name, capacity) VALUES("+id+", "+name+", "+capacity+");";

	
	}
	
	public void updateRoom(Room room){
		
	}
	
	public Room selectRoom(int romId){
		return null;
	}
	
	

}
