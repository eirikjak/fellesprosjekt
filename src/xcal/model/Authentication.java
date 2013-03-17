package xcal.model;

import java.io.Serializable;

public class Authentication implements Serializable
{
	private String user;
	private String password;

	
	public Authentication(String u,String p)
	{
		user=u;
		password=p;
	}
	

	public String getUser(){return user;}
	public String getPassword(){return password;}
	
	
	

}
