package xcal.server.query;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Vector;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import xcal.model.*;

public class AppointmentsQ
{
	public  static Statement statement = null;
	private static DbConnection connection;
	/*
	 * CREATE / CHANGE / DELETE / UPDATE APPOINTMENTS
	 */
	
	public AppointmentsQ(DbConnection connection) {
		this.connection=connection;
		
		// TODO Auto-generated constructor stub
	}


	private static String stringForSql(String string){
		return "'" + string + "'";
	}


	public static  Appointment createAppointment(Appointment app,Location loc) {
		synchronized (connection) {
			
		DateTimeFormatter format = DateTimeFormat.forPattern("Y-M-d H:m:s");
		String query = "INSERT INTO Appointment ( start_date,end_date,title,description,leader,place)"
				+ " VALUES("+ stringForSql(format.print(app.getFromTime())) +"," + stringForSql(format.print(app.getToTime())) + ","
				+ stringForSql(app.getTitle())+ "," + stringForSql(app.getDescription()) + "," + stringForSql(app.getLeader().getEmail()) + "," + stringForSql(new Integer(loc.getID()).toString()) + ")";
		System.out.println(query);
		Statement stat;
		try {
			stat = connection.getConnection().createStatement();
			stat.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
			ResultSet result = stat.getGeneratedKeys();
			if(result.next()){
			app.setAppId(result.getInt(1));
			app.setLocation(loc);
			}
			else
				return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

		}
		
		
		return app;
	

	}
	public static void deleteAppointment(Appointment app){
		
		synchronized (connection) {
			try {
				String query = "DELETE FROM Appointment WHERE id=" + app.getAppId() +"";
				Statement stat = connection.getConnection().createStatement();
				stat.executeUpdate(query);
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
		}
	}
	
	public static ArrayList<Appointment> selectAppointmentsForPersonFromDate (DateTime startDate, DateTime endDate, String Email) {
		ArrayList<Appointment> appList = new ArrayList();
		Timestamp fromDate = new Timestamp(startDate.getMillis());
		//System.out.println();
		Timestamp toDate = new Timestamp(endDate.getMillis());
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//System.out.println(df.format(fromDate) + "FFJJFJ");
		synchronized (connection) {
			String sqlstr = "SELECT * "+
                    "FROM Appointment A "+ "INNER JOIN Person P  ON (A.leader = P.email)" +
                    "WHERE A.id IN "+
                            "(SELECT id "+
                            "FROM Appointment "+
                            "WHERE leader = '"+Email+"' "+
                     
                           "UNION "+
                     
                            "SELECT app_id "+
                            "FROM Invites "+
                            "WHERE person = '"+Email+"') AND (A.start_date >= '"+df.format(fromDate)+"' AND A.start_date <= '"+df.format(toDate)+"') ORDER BY A.start_date ASC";
			ResultSet resultSet = null;
			
			try {
				Statement statement = connection.getConnection().createStatement();
				resultSet = statement.executeQuery(sqlstr);
			//	System.out.println(resultSet);
				while(resultSet.next()){
					
					if(resultSet.getString("place") != null)//appointment doesn't contain room
		  		   	{
						Appointment app=new Appointment();
						//app.setLocation(result.getString("Location"));
						app.setTitle(resultSet.getString("title"));
		  			   	app.setDescription(resultSet.getString("description"));
		  			   	Employee l = new Employee(resultSet.getString("name"), resultSet.getString("email"),"");
		  			   	app.setLeader(l);
		  			   	app.setFromTime(resultSet.getTimestamp("start_date"));
		  			   	app.setToTime(resultSet.getTimestamp("end_date"));
		  			   	app.setAppId(resultSet.getInt("id"));
		  			   	app.setLocation(new Location(LocationQ.getPlaceName(Integer.valueOf(resultSet.getString("place"))),Integer.valueOf(resultSet.getString("place"))));
		  			   	appList.add(app);
		  		   }
					else{
						Appointment meeting=new Meeting();
						meeting.setAppId(resultSet.getInt("id"));
						meeting.setTitle(resultSet.getString("title"));
						meeting.setDescription(resultSet.getString("description"));
						Employee l = new Employee(resultSet.getString("name"), resultSet.getString("email"),"");
		  			   	meeting.setLeader(l);
						meeting.setFromTime(resultSet.getTimestamp("start_date"));
						meeting.setToTime(resultSet.getTimestamp("end_date"));
						Location loc = new Location(LocationQ.getRoomName(Integer.valueOf(resultSet.getString("room"))),Integer.valueOf(resultSet.getString("room")));
						meeting.setLocation(loc);
						appList.add(meeting);
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
        	return appList;
        	
  		   
  		     		   
  		   

  	   } 
		
		}
	
	
	public Appointment selectAppointmentsFromDate (Timestamp startDate, Timestamp endDate) throws SQLException{
		synchronized (connection) {
			String sql = "select * from Appontment where date >= ["+startDate+"] and end_date <= ["+endDate+"];";
			ResultSet resultSet = statement.executeQuery(sql);
        	resultSet.last();
        	
        	if(resultSet.getString("room").isEmpty())//appointment doesn't contain room
  		   {
  			   Appointment app=new Appointment();
  			  //app.setLocation(result.getString("Location"));
  			   app.setDescription(resultSet.getString("description"));
  			   //app.setName(result.getString("name"));
  			   app.setFromTime(resultSet.getTimestamp("start_date"));
  			   app.setToTime(resultSet.getTimestamp("end_date"));
  			   app.setAppId(resultSet.getInt("id"));
  			   return app;
  		   }
  		   
  		   Appointment meeting=new Meeting();
  		   meeting.setDescription(resultSet.getString("description"));
  		   meeting.setFromTime(resultSet.getTimestamp("start_date"));
  		   meeting.setToTime(resultSet.getTimestamp("end_date"));
  		   meeting.setAppId(resultSet.getInt("id"));
  		   
  		   return meeting;   		   
  		   

  	   } 
			
	}
	
	
	
	/**
	 * get appointment from db
	 * 
	 * @param AppointmentId - id to select from db
	 * @return appointment selected from id
	 */
	public static  Appointment selectAppointment(int AppointmentId)
	{
		synchronized (connection) {
		String query="select * from Appointment where id='"+AppointmentId+"'";
 	   
 	   try 
 	   {
 		   Statement stat = connection.getConnection().createStatement();
 		   ResultSet result=stat.executeQuery(query);
 		   result.next();

 		   
 		   if(result.getString("room")==null)//appointment doesn't contain room
 		   {
 			   System.out.println("APPOINTMENT");
 			   Appointment app=new Appointment();
 			   app.setAppId(result.getInt("id"));
 			   app.setTitle(result.getString("title"));
 			   app.setDescription(result.getString("description"));
 			   app.setFromTime(result.getTimestamp("start_date"));
 			   app.setToTime(result.getTimestamp("end_date"));
 			   app.setLocation(new Location(LocationQ.getPlaceName(result.getInt("place")),
 					   						result.getInt(("place"))));
 			   app.setLeader(EmployeeQ.selectPersonWithEmail(result.getString("leader")));
 			   //app.setFromTime(result.getTimestamp("start_date"));
 			  // app.setToTime(result.getTimestamp("end_date"));
 			   return app;
 		   }
 		   
 		   Meeting meeting=new Meeting();
 		   meeting.setAppId(result.getInt("id"));
 		   meeting.setTitle(result.getString("title"));
 		   meeting.setDescription(result.getString("description"));
 		   
 		   meeting.setFromTime(result.getTimestamp("start_date"));
 		   meeting.setToTime(result.getTimestamp("end_date"));
 			   
 		   meeting.setLeader(EmployeeQ.selectPersonWithEmail(result.getString("leader")));
 		   meeting.setRoom(RoomQ.selectRoom(result.getInt("room")));
 		   
 		   return meeting;
 		   
 		  /* Appointment meeting=new Meeting();
 		   meeting.setDescription(result.getString("description"));
 		   meeting.setFromTime(result.getTimestamp("start_date"));
 		   meeting.setToTime(result.getTimestamp("end_date"));
 		   
 		   return meeting;  */
 		   

 	   } 
 	   catch (SQLException e) 
 	   {
			//couldn't get from db
			e.printStackTrace();
 	   }
 	   
		}
 	   return null;
	}
	
	
   	public static void updateAppointment(int id, Timestamp startDate, Timestamp endDate, String description, String email,int place ) {
   		synchronized (connection) {
   			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
   			Statement statement;
   			System.out.println(id + "APPOINTMENT ID");
   			String query = "UPDATE Appointment SET start_date='"+df.format(startDate)+"', end_date='"+df.format(endDate)+"', description='"+description+"', leader='"+email+"',  place="+place +" WHERE id="+id+"";
   			try {
				statement = connection.getConnection().createStatement();
				statement.executeUpdate(query);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		/*String sql = "UPDATE Appointment "+
						"SET start_date='"+df.format(startDate)+"',"+
						"end_date='"+df.format(endDate)+"',"+
						"description='"+description+"',"+
						"leader='"+email+"',"+
						"place="+place+" "+
						//"room='"+room+
								"WHERE id= "+ AppointmentId;*/
   			
   		}
   	}
	
	public void createMeeting(Timestamp from_time,Timestamp to_time, String name, String Description, Employee leader, int room) throws SQLException{
		synchronized (connection) {
			synchronized (connection) {
				DateTimeFormatter format = DateTimeFormat.forPattern("Y-M-d H:m:s");
	
				String sql = "INSERT INTO Appointment ('start_date','end_date','title','description','leader','room') VALUES ("+from_time+","+to_time+","+Description+","+leader+","+room+");";
				statement.executeUpdate(sql);
				
				
					
				}
			
		
		}
	}
	
	public static Meeting selectMeeting(int appId){
		synchronized (connection) {
			
			
		return null;
		}
		
	}
	
	public void updateMeeting(int AppointmentId, Timestamp startDate, Timestamp endDate, String description, String email,int room) throws SQLException{
		synchronized (connection) {
			
			String sql = "UPDATE Appointment "+
					"SET start_date='"+startDate+"',"+
					"end_date='"+endDate+"',"+
					"description='"+description+"',"+
					"leader='"+email+"',"+
					"'room='"+room+
							"WHERE id= "+ AppointmentId;
	statement.executeUpdate(sql);
			
			
		}
		
	}
	
	public static void deleteEvent(int id){
		synchronized (connection) {
			
		}
		
	}
	
	public boolean isMeeting(int id){
		synchronized (connection) {
		return false;
		}
	}
	
	public void removePersonFromMeetign (int app_id, int EmployeeId){
		synchronized (connection) {
			
		}
		
	}
	
	private void sendInvites(ArrayList<Employee> participants, int app_id){
		synchronized (connection) {
			
		}
	}
	
	public boolean getAnswer(int app_id, int EmployeeId){
		
		synchronized (connection) {
		return false;
		
		}
	}

	
	
}
