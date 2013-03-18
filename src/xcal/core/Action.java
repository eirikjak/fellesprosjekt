package xcal.core;

import java.io.Serializable;
import java.util.HashMap;

import javax.management.ImmutableDescriptor;

public class Action implements Serializable {

	private HashMap<Object, Object> properties;
	
	public Action(KeyValuePair ...pairs){
		
		for(int i = 0; i < pairs.length; i++){
			KeyValuePair pair = pairs[i];
			properties.put(pair.getKey(), pair.getValue());
		}
		
	}
}
	
