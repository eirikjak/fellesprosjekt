package xcal.server.managers;

import xcal.client.Status;
import xcal.client.Wrapper;
import xcal.model.Authentication;
import xcal.model.Employee;
import xcal.server.ServerLogic;
import xcal.server.query.EmployeeQ;

public class AuthenticationManager {


	public static Wrapper handle(Authentication authentication, Status flag){
		if(flag == Status.LOGIN){
			return login(authentication);
		}
		return null;
	}
	
	private static Wrapper login(Authentication authentication){
		if(ServerLogic.login(authentication)){			
			return new Wrapper(Status.SUCCESS, EmployeeQ.selectPersonWithEmail(authentication.getUser()));
		}
		else{
			return new Wrapper(Status.ERROR,new Employee());
		}
	}

}
