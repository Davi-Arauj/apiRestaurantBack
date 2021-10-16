package api.restaurant.service.exception;

import java.io.Serializable;

import lombok.Data;

@Data
public class StandarError implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer status;
	private String msg;
	private Long timeStamp;
	
	public StandarError(Integer status, String msg, Long timeStamp) {
		super();
		this.status = status;
		this.msg = msg;
		this.timeStamp = timeStamp;
	}
}
