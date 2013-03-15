package xcal.server;

import xcal.model.Authentication;

public class ServerLogic 
{
	
	public static boolean login(Authentication auth)
	{
		if(auth.getUser().equals("jonas") && auth.getPassword().equals("123"))
			return true;
		return false;
	}

}
