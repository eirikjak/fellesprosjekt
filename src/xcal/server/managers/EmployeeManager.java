package xcal.server.managers;

import java.io.ObjectInputStream.GetField;

import xcal.client.Status;
import xcal.client.Wrapper;
import xcal.model.Employee;
import xcal.model.Notification;
import xcal.server.query.EmployeeQ;
import xcal.server.query.NotificationQ;

public class EmployeeManager {

	public static Wrapper handle(Employee employee, Status flag){
		switch(flag){
		case SELECT:
			return select(employee);
		case CREATE:
			return create(employee);
		case UPDATE:
			return update(employee);
		case DESTROY:
			return destroy(employee);
		case GET_ALL:
			return getAll(employee);
			
		case CHECK_NOTIFICATION:
			return checkedNotifications(employee);
			
		case LOGOUT:
			System.out.println("LOGOUT");
			return new Wrapper(Status.LOGOUT,null);
		}
		return null;	
	}
	
	private static Wrapper select(Employee employee){
		if(employee.getEmpId() != 0){
			return new Wrapper(Status.SUCCESS, EmployeeQ.selectPerson(employee.getEmpId()));
		}
		else if(employee.getEmail() != null){
			return new Wrapper(Status.SUCCESS, EmployeeQ.selectPersonWithEmail(employee.getEmail()));
		}
		return new Wrapper(Status.ERROR, null);
	}
	
	private static Wrapper create(Employee employee){
		Employee result = EmployeeQ.createPerson(employee);
		if(result != null)
			return new Wrapper(Status.SUCCESS, result);
		return new Wrapper(Status.ERROR,null);
		
	}
	
	private static Wrapper update(Employee employee){
		EmployeeQ.updatePerson(employee);
		return new Wrapper(Status.ERROR, null);
	}
	
	private static Wrapper destroy(Employee employee){
		EmployeeQ.deletePerson(employee.getEmpId());
		return new Wrapper(Status.ERROR, null);
	}
	private static Wrapper getAll(Employee employee){
		return new Wrapper(Status.SUCCESS, EmployeeQ.getAllEmployees());
		
	}
	
	
	/**
	 * check db if any notifications needs to be triggered
	 * 
	 * @param e - employee to check notifications for
	 * @return - notifications for employee if exist, Status.Error otherwise
	 */
	private static Wrapper checkedNotifications(Employee e)
	{
		Notification[] notifications=NotificationQ.getNotificationPerson(e);
		
		if(notifications.length<=0)
			return new Wrapper(Status.ERROR,null);
		return new Wrapper(Status.GET_ALL,notifications);

	}
}
