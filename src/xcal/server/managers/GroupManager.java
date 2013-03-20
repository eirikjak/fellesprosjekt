package xcal.server.managers;

import xcal.client.Status;
import xcal.client.Wrapper;
import xcal.model.Group;
import xcal.server.query.GroupQ;

public class GroupManager {

	public static Wrapper handle(Group group, Status flag){
		
		switch (flag) {
		case GET_ALL:
			return getAll(group);
		}
		
		return null;
	}
	
	public static Wrapper getAll(Group group){
		return new Wrapper(Status.SUCCESS, GroupQ.getAllGroups());
	}
}
