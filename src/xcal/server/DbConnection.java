package xcal.server;

import java.io.IOException;
import java.sql.*;//jconnector

import xcal.model.Appointment;
import xcal.model.Employee;
import xcal.model.Meeting;
import xcal.model.Notification;
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
	        	DbConnection dc = new DbConnection("jdbc:mysql://10.0.0.111/fellesprosjekt", "felles", "felles");
	        	dc.connect();
	        	
	        	//System.out.println(dc.getPasswordFromEmail("Albert.Gates.220@xcal.com"));
	        	
	        	Notification[] not=dc.checkNotification();
	        	
	        	for(int i=0;i<not.length;++i)
	        	{
	        		System.out.println(not[i].getEmployee().getName());
	        	}
	        	
	        	
	        	dc.closeConnection();
	        	
	        	
	        	
	        	
	        	
	        	

	        	//System.out.println(sqlstr);
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
	        
	       /**
	        * check if any notifications need to be executed
	        * 
	        * check with current time with notification time in db
	        * 
	        * @return array of notifications that need to be run
	        */
	       public Notification[] checkNotification()
	       {
	    	  // java.util.Date date=new java.util.Date();
	    	   //Timestamp now=new Timestamp(date.getTime());
	    	   
	    	   //check if current time went passed notifiedAt
	    	   String query="select * from Notification where NOW() >=  notifiedAt";
	    	   
	    	   try 
	    	   {
	    		   
	    		   Statement stat=connection.createStatement();
	    		   ResultSet result=stat.executeQuery(query);
	    		   
	    		   result.last();
	    		   Notification[] notification=new Notification[result.getRow()];
	    		   result.beforeFirst();
	    		   
	    		   int size=0;
	    		   
	    		   while(result.next())
	    		   {


	    			   notification[size]=new Notification
	    			   			(getAppointment(result.getInt("app_id")),
	    			   					getEmployee(result.getString(("person"))));
	    			 
	    			 ++size;
	    		   }
	    			   
	    		   return notification;
				
	    	   } 
	    	   catch (SQLException e) 
	    	   {
				//couldn't get from db
				e.printStackTrace();
	    	   }
	    	   
	    	   return null;
	       }
	       
	       
	       
	       /**
	        * returns an employee from email info
	        * 
	        * @param mail - email to get person from
	        * @return - employee object
			**/
	       public Employee getEmployee(String mail)
	       {
	    	   String query="select * from Person where email='"+mail+"'";
	    	   
	    	   try 
	    	   {
	    		   Statement stat = connection.createStatement();
	    		   ResultSet result=stat.executeQuery(query);
	    		   
	    		   result.next();
	    		   
	    		   Employee e=new Employee(result.getString("name"),result.getString("email"));
	    		   return e;
	    	   } 
	    	   catch (SQLException e) 
	    	   {
				//couldn't get from db
				e.printStackTrace();
	    	   }
	    	   
	    	   return null;
	       }
	       
	       
	       /**
	        * 
	        * @param id - id of appointment/meeting to get from db
	        * @return - appointment/meeting with input id
	        */
	       public Appointment getAppointment(int id)
	       {
	    	   String query="select * from Appointment where id='"+id+"'";
	    	   
	    	   try 
	    	   {
	    		   Statement stat = connection.createStatement();
	    		   ResultSet result=stat.executeQuery(query);
	    		   result.next();

	    		   
	    		   if(result.getString("leader").isEmpty())
	    		   {
	    			   Appointment app=new Appointment();
	    			  //app.setLocation(result.getString("Location"));
	    			   app.setDescription(result.getString("description"));
	    			   //app.setName(result.getString("name"));
	    			   app.setFromTime(result.getTimestamp("start_date"));
	    			   app.setToTime(result.getTimestamp("end_date"));
	    			   return app;
	    		   }
	    		   
	    		   Appointment meeting=new Meeting();
	    		   meeting.setDescription(result.getString("description"));
	    		   meeting.setFromTime(result.getTimestamp("start_date"));
	    		   meeting.setToTime(result.getTimestamp("end_date"));
	    		   
	    		   return meeting;   		   
	    		   

	    	   } 
	    	   catch (SQLException e) 
	    	   {
				//couldn't get from db
				e.printStackTrace();
	    	   }
	    	   
	    	   
	    	   return null;
	       }
	       
	       

	       
	        
	}
	

