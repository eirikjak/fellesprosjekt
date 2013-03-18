package xcal.core;

import java.io.Serializable;

public class KeyValuePair implements Serializable
{
	private Object key;
	private Object value;
	public KeyValuePair(Object key, Object value){
		this.key = key;
		this.value = value;
	}
	
	public Object getKey(){
		return this.key;
	}
	public Object getValue(){
		return this.value;
	}
}


