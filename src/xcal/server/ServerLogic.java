package xcal.server;

import xcal.model.Authentication;
import xcal.model.Employee;
import xcal.server.query.EmployeeQ;

public class ServerLogic 
{
	
	
	public static boolean login(Authentication auth)
	{
		
		Employee employee = EmployeeQ.selectPersonWithEmail(auth.getUser());
		
		if(employee != null && employee.getPassword().equals(auth.getPassword())){
			return true;
		}
		return false;
	}

}
