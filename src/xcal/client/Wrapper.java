package xcal.client;

import java.io.Serializable;

public class Wrapper implements Serializable {
	private Status flag;
	private Object content;
	
	public Wrapper(Status s, Object o){
		this.flag = s;
		this.content = o;
	}
	
	public Status getFlag(){
		return this.flag;
	}
	
	public Object getContent(){
		return this.content;
	}
	
	
}
