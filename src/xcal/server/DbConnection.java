package xcal.server;

import java.sql.*;//jconnector
import xcal.server.Room;

	/**
	 * DBConnection lets you connect to a SQL Database
	 * @author Christer
	 *
	 */
	public class DbConnection {

			private Connection connection = null;
			private Statement statement = null;
			private PreparedStatement preparedStatement = null;     
	        private String url, user, password;

	        /**
			 * DbConnection constructor
			 * @param url The url for the desired
			 * @param user Username for the database
			 * @param password Password for the database
	         * @throws Exception 
			 */
	      
	        public static void main (String args []) throws Exception{
	        	/*DbConnection dc = new DbConnection("jdbc:mysql://84.48.49.149/fellesprosjekt", "felles", "felles");
	        	dc.connect();
	        	System.out.println(dc.getPasswordFromEmail("Albert.Gates.220@xcal.com"));
	        	dc.closeConnection();*/
	        	

	        	System.out.println(sqlstr);
	        }
	   
	         
	        public DbConnection(String url, String user, String password){
	        	this.url = url;
	        	this.user = user;
	        	this.password = password;
	        	
	        }
	        
	        /**
	         * Connects to the database requested in the constructor 
	         * @return Returns true if the connection is successful
	         */
			public boolean connect (){
				try{
					Class.forName("com.mysql.jdbc.Driver").newInstance();
					connection = DriverManager.getConnection(url, user, password);
					statement = connection.createStatement();
					if(connection != null){
						return true;
					}
				} catch (Exception e) {
					System.out.println("Connection failed: " + e.getMessage());
				}
				return false; 
			}
			/**
			 * Sets the connection
			 * @param connection The connection you want to set
			 */
	        public void setConnection(Connection connection){
	            this.connection = connection;
	        }
	        /**
	         * Gets this connections
	         * @return Returns this connection
	         */
	        public Connection getConnection(){
	            return this.connection;
	        }
	      
	       
	        
	        /**
	         * Closes the connection to the database
	         */
	        public void closeConnection(){
	        	try{
	        		if (connection != null) 
	        			connection.close();
	        	} catch (SQLException e){
	        		System.out.println("Epic fail: " + e.getMessage());
	        	}
	        }
	      
	        
	        private String getNameFromEmail(String email) throws Exception{
		  	       String sql = "SELECT * FROM Person WHERE email = '" + email + "'"; 
		  	       ResultSet resultSet = statement.executeQuery(sql);
		  	       resultSet.next();
		  	       return resultSet.getString("name"); 
		  	       
	        }
	        
	        private String getPasswordFromEmail (String email) throws SQLException{
	        	String sql = "SELECT * FROM Person WHERE email = '" + email + "'";
	        	ResultSet resultset = statement.executeQuery(sql);
	        	resultset.next();
	        	return resultset.getString("password");
	        }
	        
	        
	        //TODO add parameters for datetime starttime and datetime
	        
	        
	        private Room[] availablebleRooms () throws Exception{
	        	
	        	
	    	    String sqlstr = "SELECT R.name"+
	    	    		"FROM Room R"+
				    	"WHERE R.id NOT IN("+
				    	"SELECT R.id"+
				    	"FROM Room R, Appointment A"+
				    	"WHERE ((('"startDate+"' >= A.start_date) AND '"+endDate+"' <= A.end_date)) "+
				    	"OR (('"+startDate+"' <= A.start_date) AND ('"+endDate+"' >= A.end_date))"+
				    	"OR (('"startDate+"' <= A.start_date) AND ('"+endDate+"' = A.end_date))"+
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
	

