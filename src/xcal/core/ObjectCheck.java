package xcal.core;

import xcal.model.*;
import xcal.server.ServerLogic;

public class ObjectCheck 
{
	public static Object handleObject(Object o)
	{
		Object o_return;
		
		if(o instanceof Employee)
		{
			Employee e=(Employee)o;
		}
		else if(o instanceof Authentication)
		{
			
		}
		else if(o instanceof Authentication)
		{
			System.out.println("checking auth..");
			Authentication auth=(Authentication)o;
			if(ServerLogic.login(auth))
				return new Employee();
		}
		
		return null;
	}
}
