package api.restaurant.exception;

import java.io.Serializable;

import lombok.Data;

@Data
public class FieldMessage implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String filedName;
	private String message;
	public FieldMessage(String filedName, String message) {
		super();
		this.filedName = filedName;
		this.message = message;
	}
	public FieldMessage() {
		
	}
	
	
}
