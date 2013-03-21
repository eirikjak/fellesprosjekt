package xcal.server.managers;

import xcal.client.Status;
import xcal.client.Wrapper;
import xcal.model.Authentication;
import xcal.model.Employee;
import xcal.model.Group;
import xcal.model.Meeting;
import xcal.model.Notification;
import xcal.server.ServerLogic;
import xcal.server.query.EmployeeQ;
import xcal.server.query.NotificationQ;


public class NotificationManager 
{
	public static Wrapper handle(Notification notification, Status flag)
	{
		switch(flag)
		{
			case DESTROY:
				return deleteNotification(notification);
			case UPDATE:
				return updateNotification(notification);
			
			
		}
		
		return null;
		
	}
	
	/**
	 * delete given notification 
	 * @param notification - notification to delete
	 * @return - success if notification found in db, error otherwise
	 */
	public static Wrapper deleteNotification(Notification notification)
	{
		if(notification.getEmployee().getEmail()!=null && notification.getAppointment().getAppId()>=0)
		{
			NotificationQ.deleteNotification(notification.getEmployee(), notification.getAppointment());
			return new Wrapper(Status.SUCCESS,null);
		}
		return new Wrapper(Status.ERROR,null);
			
	}
	

	/**
	 * updates notification time
	 * 
	 * @param notification - notification to update
	 * @return - success if notification with emp&app actually exist, error otherwise
	 */
	public static Wrapper updateNotification(Notification notification)
	{
		if(notification.getEmployee().getEmail()!=null && notification.getAppointment().getAppId()>=0)
		{
			NotificationQ.updateNotification(notification);
			return new Wrapper(Status.SUCCESS,null);
		}
		return new Wrapper(Status.ERROR,null);
			
	}
	
	
	

}
