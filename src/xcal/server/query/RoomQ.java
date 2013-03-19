package xcal.server.query;

import xcal.model.*;
import java.sql.*;
import java.util.ArrayList;

import org.joda.time.DateTime;


public class RoomQ {
	
	private static DbConnection connection = null;

	/*public void createRoom(int id, String name, int capacity){
		statement = connection.createStatement();
		statement  = "INSERT INTO Room (id, name, capacity) VALUES("+id+", "+name+", "+capacity+");";

	
	}*/
	
	public RoomQ(DbConnection connection){
		this.connection = connection;
	}
    public void createRoom(int id, String name, int capacity) throws SQLException{
    	synchronized (connection) {
    	
    	Statement statement = connection.getConnection().createStatement();
		statement = connection.getConnection().createStatement();
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
				Statement statement = connection.getConnection().createStatement();
				statement.executeUpdate(sql);
		}
	}
	
	public  Room[] selectRoom(int id) throws SQLException{
		synchronized (connection) {
		String sql = "SELECT * FROM Room WHERE id ='"+id+"'";
		Statement statement = connection.getConnection().createStatement();
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
	
	   public static Room[] getAvailableRooms (DateTime  start, DateTime end){
		   synchronized (connection) {
			   try{
				   Timestamp startDate = new Timestamp(start.getMillis());
				   Timestamp endDate = new Timestamp(end.getMillis());
				   
			   	    String sqlstr = "SELECT R.name"+
			   	    		"FROM Room R "+
						    	"WHERE R.id NOT IN("+
						    	"SELECT R.id"+
						    	"FROM Room R, Appointment A"+
						    	"WHERE ((('"+startDate+"' >= A.start_date) AND '"+endDate+"' <= A.end_date)) "+
						    	"OR (('"+startDate+"' <= A.start_date) AND ('"+endDate+"' >= A.end_date))"+
						    	"OR (('"+startDate+"' <= A.start_date) AND ('"+endDate+"' = A.end_date))"+
						    	"OR (('"+startDate+"' > A.start_date AND '"+endDate+"' < A.end_date) AND ('"+endDate+"' >= A.end_date))) AND R.id = A.room)";
			   	    System.out.println(sqlstr);
			   		Statement statement = connection.getConnection().createStatement();
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
			   }catch(SQLException e){
				   e.printStackTrace();
				   return null;
				   
			   }
		   }
      }
	
	public void updateRoom(Room room){
		synchronized (connection) {
			
			
		}
	}
	
	/*public Room selectRoom(int romId){
		return null;
	}*/

	public ArrayList<Room> findAvailableRooms() {
		synchronized (connection) {
		return null;
		}
	}
	
	

}
