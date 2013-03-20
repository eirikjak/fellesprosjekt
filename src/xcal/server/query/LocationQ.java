package xcal.server.query;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import xcal.model.Employee;
import xcal.model.Location;

public class LocationQ {

	private static DbConnection connection;
	
	public static void main(String args[]) {

		// TEST METODE FOR DATABASE
		DbConnection dc = new DbConnection(
				"jdbc:mysql://84.48.49.149/fellesprosjekt", "felles", "felles");
		dc.connect();

		new LocationQ(dc);
		LocationQ.createLocation("testloc");
		dc.closeConnection();

	}
	
	
	public LocationQ(DbConnection connection){
		this.connection = connection;
	}
	
	public static Location createLocation(String description){
		synchronized (connection) {
			String query = "INSERT INTO Place (description) VALUES(" + "'" + description + "'" + ")";
	 	   try 
	 	   {
	 		   Statement stat = connection.getConnection().createStatement();
	 		   stat.executeUpdate(query,Statement.RETURN_GENERATED_KEYS);
	 		   ResultSet result = stat.getGeneratedKeys();
	 		   if(result.next())
	 			   return new Location(description, result.getInt(1));
	 		   else
	 			   return null;
	 	   }catch(SQLException e){
	 		   e.printStackTrace();
	 		   return null;
	 	   }
		}

	}
	
	public static String getPlaceName(int loc){
		synchronized (connection) {
			
			
			try {
				String sql = "SELECT description FROM Place WHERE id='"+loc+"';";
				 
				Statement stat = connection.getConnection().createStatement();
				ResultSet result;
			
				result = stat.executeQuery(sql);
				result.next();
				String place = result.getString("description");
				return place;
			} catch (SQLException e1) {
				e1.printStackTrace();
				return null;
			}
		
		}
	}
	
	public static void deleteLocation(Location loc){
		synchronized (connection) {
			try {
				String query = "DELETE FROM Place WHERE id='" + loc.getID() +"'";
				Statement stat = connection.getConnection().createStatement();
				stat.execute(query);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
	}
}