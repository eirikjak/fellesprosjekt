/**
 * Person class for fellesprosjekt
 * 
 * Person is abstract, don't want to actually make a person object
 * 
 *
 */

package xcal.model;

abstract class Person 
{
	private String name;
	private String email;

	
	public void setName(String name){this.name=name;}
	public void setEmail(String email){this.email=email;}
	
	public String getName(){return name;}
	public String getEmail(){return email;}
	
	public String toString()
	{
		return "Name "+getName();
	}
	
}
