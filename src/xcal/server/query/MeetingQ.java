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
	
	public static ArrayList getParticipants(Meeting meeting){
		ArrayList<Employee> empList = new ArrayList();
		ArrayList<Integer> answerList = new ArrayList();
		//connection.connection = Connection;
		synchronized (connection) {
			String query = "SELECT * FROM Invites, Appointment Where (app_id =" + meeting.getAppId()+") AND app_id = id";
			Statement stat;
			try {
				stat = connection.getConnection().createStatement();
				stat.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
				ResultSet result = stat.getGeneratedKeys();
				while(result.next()){
					Employee e = EmployeeQ.selectPersonWithEmail(result.getString(2));
					if(!empList.contains(e)){
						empList.add(e);
						answerList.add(result.getInt(3));
					}
					empList.add(EmployeeQ.selectPersonWithEmail(result.getString(8)));
					answerList.add(1);
				}
				ArrayList[]resList = {empList, answerList};
				return empList;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return null;
	}
}
