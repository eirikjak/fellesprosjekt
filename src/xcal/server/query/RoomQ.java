package xcal.server.query;

import xcal.model.*;
import java.sql.*;
import java.util.ArrayList;
import structs.*;

import structs.Room;
public class RoomQ {
	
	public Connection connection = null;
	public Statement statement = null;;

	/*public void createRoom(int id, String name, int capacity){
		statement = connection.createStatement();
		statement  = "INSERT INTO Room (id, name, capacity) VALUES("+id+", "+name+", "+capacity+");";

	
	}*/
    public void createRoom(int id, String name, int capacity) throws SQLException{
    	synchronized (connection) {
    	
		statement = connection.createStatement();
		String sql  = "INSERT INTO Room (id, name, capacity) VALUES("+id+", "+name+", "+capacity+");";
		statement.executeUpdate(sql);
		
    	}
	
	}
    
	public void updateRoom(int id, String name, int capacity) throws SQLException{
		synchronized (connection) {
				String sql = "UPDATE Room "+
								"SET name='"+name+"',"+
								"capacity='"+capacity+
										"WHERE id= "+ id;
				statement.executeUpdate(sql);
		}
	}
	
	public  Room[] selectRoom(int id) throws SQLException{
		synchronized (connection) {
		String sql = "SELECT * FROM Room WHERE id ='"+id+"'";
	    ResultSet resultset = statement.executeQuery(sql);
	   resultset.last();
	    Room [] rooms = new Room [resultset.getRow()];
	    resultset.beforeFirst();
	    int roomCount = 0;
	    while(resultset.next()){
	    	String name = resultset.getString("name");
	    	int capacity = resultset.getInt("capacity");
	    	
	    	rooms[roomCount] = new Room(id, name, capacity); 
	    	roomCount++	;
	    }
		
	    return rooms;
		}
	}
	
	   public static Room[] getAvailableRooms (Timestamp startDate, Timestamp endDate) throws Exception{
		   synchronized (connection) {
       	
   	    String sqlstr = "SELECT R.name"+
   	    		"FROM Room R"+
			    	"WHERE R.id NOT IN("+
			    	"SELECT R.id"+
			    	"FROM Room R, Appointment A"+
			    	"WHERE ((('"+startDate+"' >= A.start_date) AND '"+endDate+"' <= A.end_date)) "+
			    	"OR (('"+startDate+"' <= A.start_date) AND ('"+endDate+"' >= A.end_date))"+
			    	"OR (('"+startDate+"' <= A.start_date) AND ('"+endDate+"' = A.end_date))"+
			    	"OR (('"+startDate+"' > A.start_date AND '"+endDate+"' < A.end_date) AND ('"+endDate+"' >= A.end_date))) AND R.id = A.room)";
			       
   	    ResultSet resultset = statement.executeQuery(sqlstr);
   	    resultset.last();
   	    Room [] rooms = new Room [resultset.getRow()];
   	    resultset.beforeFirst();
   	    int roomCount = 0;
   	    while(resultset.next()){
   	    	int id = resultset.getInt("id");
   	    	String name = resultset.getString("name");
   	    	int capacity = resultset.getInt("capacity");
   	    	
   	    	rooms[roomCount] = new Room(id, name, capacity); 
   	    	roomCount++	;
   	    }
		   
   	    return rooms;
		   }
      }
	
	public void updateRoom(Room room){
		synchronized (connection) {
			
			
		}
	}
	
	/*public Room selectRoom(int romId){
		return null;
	}*/

	public static ArrayList<Room> findAvailableRooms() {
		synchronized (connection) {
		return null;
		}
	}
	
	

}
