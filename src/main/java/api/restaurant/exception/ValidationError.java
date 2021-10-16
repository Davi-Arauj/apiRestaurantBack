package api.restaurant.exception;

import java.util.ArrayList;
import java.util.List;

import api.restaurant.service.exception.StandarError;

public class ValidationError extends StandarError {
	private static final long serialVersionUID = 1L;
	
	public ValidationError(Integer status, String msg, Long timeStamp) {
		super(status, msg, timeStamp);
		
	}
	
	private List<FieldMessage> errors = new ArrayList<>();
	
	public List<FieldMessage> getErrors(){
		return errors;
	}
	
	public void addErrors(String fieldName, String msg) {
		errors.add(new FieldMessage(fieldName,msg));
	}
	

}
