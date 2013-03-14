/**
 * Person class for fellesprosjekt
 * 
 * Person is abstract, don't want to actually make a person object
 * 
 *
 */

package xcal.model;

public abstract class Person 
{
	private String name;
	private String email;
	private String dateOfBirth;
	
	public void setName(String name){this.name=name;}
	public void setEmail(String email){this.email=email;}
	public void setDateOfBirth(String date){this.dateOfBirth=date;}
	
	public String getName(){return name;}
	public String getEmail(){return email;}
	public String dateOfBirth(){return dateOfBirth;}
	
	public String toString()
	{
		return "Name "+getName();
	}
	
}
