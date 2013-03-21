package xcal.model;

import java.io.Serializable;

import org.joda.time.DateTime;

public class Invite implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4106531727169014891L;
	private Meeting meeting;
	private Employee emp;
	private int answer = -1;
	
	public Invite(Meeting a, Employee e)
	{
		meeting=a;
		emp=e;
		
	}
	
	
	public Invite(){};
	public void setAnswer(int answer){
		this.answer = answer;
	}
	
	public int getAnswer(){
		return this.answer;
	}
	public Invite(Employee e){
		this(new Meeting(), e);
	}
	
	

	public Meeting getMeeting(){return meeting;}
	public Employee getEmployee(){return emp;}
	public void setEmployee(Employee employee){
		this.emp = employee;
	}
	
	public String toString(){
		return "" + this.meeting;
	}

}
