package xcal.model;

public class Authentication 
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
