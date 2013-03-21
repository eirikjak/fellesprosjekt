package xcal.server.query;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
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
		
		synchronized (connection) {
			try {
				String query = "DELETE FROM Appointment WHERE id='" + meeting.getAppId() +"'";
				Statement stat = connection.getConnection().createStatement();
				stat.execute(query);
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
		}
	}
	
	public static void updateStatus(Meeting meeting, int ans, Employee emp){
		synchronized (connection) {
			String query = "UPDATE Invites SET ans ="+ ans +" Where person = '"+ emp.getEmail()+"'";
			Statement stat;
			try {
				stat = connection.getConnection().createStatement();
				System.out.println(stat.executeUpdate(query));
				System.out.println("Q executed");
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
			String query = "SELECT * FROM Invites INNER JOIN Appointment ON (app_id = id) Where (app_id = 1)";
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
				ArrayList[]response = {empList, answerList};
				return response;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return null;
	}
}
