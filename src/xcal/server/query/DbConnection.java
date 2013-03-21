package xcal.server.query;

import java.io.ObjectInputStream.GetField;
import java.io.Serializable;
import java.sql.*;//jconnector
import java.util.ArrayList;

import org.joda.time.DateTime;

import xcal.model.Appointment;
import xcal.model.Employee;

/**
 * DBConnection lets you connect to a SQL Database
 * 
 * @author Christer
 * 
 */
public class DbConnection {

	public Connection connection = null;
	public Statement statement = null;
	public PreparedStatement preparedStatement = null;
	public String url, user, password;

	/**
	 * DbConnection constructor
	 * 
	 * @param url
	 *            The url for the desired
	 * @param user
	 *            Username for the database
	 * @param password
	 *            Password for the database
	 * @throws Exception
	 */

	public static void main(String args[]) {

		// TEST METODE FOR DATABASE
		DbConnection dc = new DbConnection(
				"jdbc:mysql://84.48.49.149/fellesprosjekt", "felles", "felles");
		dc.connect();

		/*
		 * try { //System.out.println(dc.selectRoom(5).length);
		 * 
		 * } catch (SQLException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */

		/*
		 * dc.selectRoom(5); Room[] rooms = dc.selectRoom(5);
		 * System.out.println(rooms);
		 */

		dc.closeConnection();

	}

	public DbConnection(String url, String user, String password) {
		this.url = url;
		this.user = user;
		this.password = password;
		

	}

	/**
	 * Connects to the database requested in the constructor
	 * 
	 * @return Returns true if the connection is successful
	 */
	public boolean connect() {
		try {
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection(url, user, password);
			statement = connection.createStatement();
			if (connection != null) {
				new EmployeeQ(this);
				new LocationQ(this);
				new AppointmentsQ(this);
				new NotificationQ(this);
				new RoomQ(this);
				new MeetingQ(this);
				new GroupQ(this);
				new InviteQ(this);
				return true;
			}
		} catch (Exception e) {
			System.out.println("Connection failed: " + e.getMessage());
		}
		return false;
	}

	/**
	 * Sets the connection
	 * 
	 * @param connection
	 *            The connection you want to set
	 */
	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	/**
	 * Gets this connections
	 * 
	 * @return Returns this connection
	 */
	public Connection getConnection() {
		return this.connection;
	}

	/**
	 * Closes the connection to the database
	 */
	public void closeConnection() {
		try {
			if (connection != null)
				connection.close();
		} catch (SQLException e) {
			System.out.println("Epic fail: " + e.getMessage());
		}
	}

	private String getNameFromEmail(String email) throws Exception {
		String sql = "SELECT * FROM Person WHERE email = '" + email + "'";
		ResultSet resultSet = statement.executeQuery(sql);
		resultSet.next();
		return resultSet.getString("name");

	}

	private String getPasswordFromEmail(String email) throws SQLException {
		String sql = "SELECT * FROM Person WHERE email = '" + email + "'";
		ResultSet resultset = statement.executeQuery(sql);
		resultset.next();
		return resultset.getString("password");
	}

	/*
	 * public void createAppointment(int EmployeeId, DateTime startDate,
	 * DateTime endDate, String description, String email, int roomid) throws
	 * SQLException{ statement = connection.createStatement(); String sql =
	 * "INSERT INTO Appointment (start_date, end_date, description, leader,place, room) VALUES ("
	 * +startDate+","+endDate+","+description+","+email+","+roomid+");";
	 * statement.executeUpdate(sql);
	 * 
	 * } /** no need to return array, since appointments have unique id
	 * 
	 * @throws SQLException *
	 */
	/*
	 * public Appointment[] selectAppointment(int AppointmentId){ String sql =
	 * "SELECT * FROM Room WHERE id ='"+AppointmentId+"'"; ResultSet resultset =
	 * statement.executeQuery(sql); resultset.last(); Appointment []
	 * appointments = new Appointment [resultset.getRow()];
	 * resultset.beforeFirst(); int AppCount = 0; while(resultset.next()){ int
	 * id = resultset.getInt("id"); datetime startDate.GetField("start_date");
	 * datetime endDate.GetField("end_date"); String description =
	 * resultset.getString("description"); String leader =
	 * resultset.getString("leader"); int place = resultset.getInt("place"); int
	 * room = resultset.getInt("room");
	 * 
	 * appointments[AppCount] = new Appointment(Id,startDate,endDate,
	 * description, leader, place, room); AppCount++ ; } return appointments; }
	 */

	// TODO add parameters for datetime starttime and datetime endtime

	/*
	 * public Room[] getAvailableRooms (Timestamp startDate, Timestamp endDate)
	 * throws Exception{
	 * 
	 * 
	 * String sqlstr = "SELECT R.name"+ "FROM Room R"+ "WHERE R.id NOT IN("+
	 * "SELECT R.id"+ "FROM Room R, Appointment A"+
	 * "WHERE ((('"+startDate+"' >= A.start_date) AND '"
	 * +endDate+"' <= A.end_date)) "+
	 * "OR (('"+startDate+"' <= A.start_date) AND ('"
	 * +endDate+"' >= A.end_date))"+
	 * "OR (('"+startDate+"' <= A.start_date) AND ('"
	 * +endDate+"' = A.end_date))"+
	 * "OR (('"+startDate+"' > A.start_date AND '"+endDate
	 * +"' < A.end_date) AND ('"
	 * +endDate+"' >= A.end_date))) AND R.id = A.room)";
	 * 
	 * ResultSet resultset = statement.executeQuery(sqlstr); resultset.last();
	 * Room [] rooms = new Room [resultset.getRow()]; resultset.beforeFirst();
	 * int roomCount = 0; while(resultset.next()){ int id =
	 * resultset.getInt("id"); String name = resultset.getString("name"); int
	 * capacity = resultset.getInt("capacity");
	 * 
	 * rooms[roomCount] = new Room(id, name, capacity); roomCount++ ; } return
	 * rooms; }
	 */

}
