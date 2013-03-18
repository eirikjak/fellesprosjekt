/**
 * Person class for fellesprosjekt
 * 
 * Person is abstract, don't want to actually make a person object
 * 
 *
 */

package xcal.model;

<<<<<<< HEAD
import java.io.Serializable;

abstract class Person implements Serializable 
=======
public abstract class Person 
>>>>>>> 45cd177ad536bc371fd221c99f03e5636a10215c
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
