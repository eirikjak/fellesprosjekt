package xcal.server.query;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import xcal.model.Appointment;
import xcal.model.Employee;
import xcal.model.Location;
import xcal.model.Meeting;
import xcal.model.Room;
import xcal.server.ClientThread;

public class MeetingQ {

	private static DbConnection connection;
	
	public MeetingQ(DbConnection connection){
		this.connection = connection;
	}
	
	
	private static String stringForSql(String string){
		return "'" + string + "'";
	}
	
	
	public static  Meeting createMeeting(Meeting meeting,Room room) {
		synchronized (connection) {
			
			Timestamp from = new Timestamp(meeting.getFromTime().getMillis());
			Timestamp to = new Timestamp(meeting.getToTime().getMillis());
			
			DateTimeFormatter format = DateTimeFormat.forPattern("Y-M-d H:m:s");
			String query = "INSERT INTO Appointment ( start_date,end_date,title,description,leader,room)"
					+ " VALUES("+ stringForSql(from.toString()) +"," + stringForSql(to.toString()) + ","+ stringForSql(meeting.getTitle())+ "," + stringForSql(meeting.getDescription()) + "," + stringForSql(meeting.getLeader().getEmail()) + "," + 
			stringForSql(new Integer(room.getID()).toString()) + ")";
			System.out.println(query);
			Statement stat;
			try {
				stat = connection.getConnection().createStatement();
				stat.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
				ResultSet result = stat.getGeneratedKeys();
				if(result.next()){
				meeting.setAppId(result.getInt(1));
				meeting.setRoom(room);
				}
				else
					return null;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}

		}
		return meeting;
	}
	public static void deleteMeeting(Meeting meeting){
		System.out.println(meeting.getAppId());
		synchronized (connection) {
			try {
				
				String query = "DELETE FROM Appointment WHERE id=" + meeting.getAppId();
				Statement stat = connection.getConnection().createStatement();
				stat.executeUpdate(query);
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
		}
	}
	

	public static void updateStatus(Meeting meeting, int ans, Employee emp){
		synchronized (connection) {
			String query = "UPDATE Invites SET ans ="+ ans +"  WHERE (person = '"+ emp.getEmail()+"')  AND (app_id = "+meeting.getAppId()+")";
			Statement stat;
			try {
				stat = connection.getConnection().createStatement();
				
				System.out.println("Q executed" + stat.executeUpdate(query));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	

	public static ArrayList[] getParticipants(Meeting meeting){
		synchronized (connection) {
			
			ArrayList<Employee> empList = new ArrayList();
			ArrayList<Integer> answerList = new ArrayList();
			String query = "SELECT * FROM Invites INNER JOIN Appointment ON (app_id = id) Where (app_id = " +meeting.getAppId()+")";
/*
			System.out.println("APP_ID"+meeting.getAppId());
			//String query = "SELECT * FROM Invites, Appointment Where (app_id ='" + meeting.getAppId()+"') AND app_id ='"+meeting.getAppId()+"'";
			String query="select * from Invites, Appointment where Invites.app_id='"+meeting.getAppId()+"' and Appointment.id='"+meeting.getAppId()+"'";
			*/

			Statement stat;
			try {
				stat = connection.getConnection().createStatement();
				ResultSet result=stat.executeQuery(query);
				Employee l = null;

				while(result.next()){
					l = EmployeeQ.selectPersonWithEmail(result.getString("leader"));
					Employee e = EmployeeQ.selectPersonWithEmail(result.getString("person"));
					if(!empList.contains(e)){
						System.out.println("Legger til employee" + e);
						empList.add(e);
						answerList.add(result.getInt(3));
					}	
				}
				
				if(!empList.contains(l)){
					System.out.println("legger til leder" + l);
					empList.add(l);
					answerList.add(1);
				}

				ArrayList[]resList = {empList, answerList};

				return resList;

				//return empList;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return null;
	}


	public static void update(Meeting meeting) {
		// TODO Auto-generated method stub
		int id = meeting.getAppId();
		Timestamp startDate = new Timestamp(meeting.getFromTime().getMillis());
		Timestamp endDate = new Timestamp(meeting.getToTime().getMillis());
		String descr = meeting.getDescription();
		String email = meeting.getLeader().getEmail();
		int roomid = meeting.getLocationID();
		System.out.println("edit mode" + id);
		
		synchronized (connection) {
   			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
   			Statement statement;
   			System.out.println(id + "APPOINTMENT ID");
   			String query = "UPDATE Appointment SET start_date='"+df.format(startDate)+"', end_date='"+df.format(endDate)+"', description='"+descr+"', leader='"+email+"',  room="+roomid +" WHERE id="+id+"";
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
}
