package xcal.server.query;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import xcal.model.Employee;
import xcal.model.Group;

public class GroupQ {

	private AppointmentsQ appointments_query;
	private EmployeeQ employee_query;
	private static DbConnection connection;
	
	
	
	public GroupQ(DbConnection connection) 
	{
		this.connection=connection;
		

	}
	
	
	public static ArrayList<Group> getAllGroups(){
		synchronized (connection) {
				String query="select G.grp_id, G.name as grp_name, P.email, P.name from Groupe G, Person P, PersonMemberOfGroup PM where G.grp_id = PM.id_group AND PM.email_person = P.email";
				HashMap<Integer, Group> groups = new HashMap<Integer, Group>();
		 	   try 
		 	   {
		 		   Statement stat = connection.getConnection().createStatement();
		 		   ResultSet result=stat.executeQuery(query);
		 		   while(result.next()){
		 			  int id = result.getInt("grp_id");
		 			  Group group = groups.get(id);
		 			  String name = result.getString("grp_name");
		 			  if(group == null){
		 				  group = new Group(id, name, new ArrayList<Employee>());
		 				  groups.put(id, group);
		 			  }
		 			  String userMail = result.getString("email");
		 			  String userName = result.getString("name");
		 			  group.addMember(new Employee(userName, userMail, ""));
		 			  
		 		   }
		 	   }
		 	  catch (SQLException e) 
		 	   {
					//couldn't get from db
					e.printStackTrace();
		 	   }
	 	  return new ArrayList<Group>(groups.values());
		}
		
	}
		
}
