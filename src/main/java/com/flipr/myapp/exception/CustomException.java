package com.flipr.myapp.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomException extends Exception {
	
	
	private static final long serialVersionUID = 1L;
	private String errorMsg;
	private String timeStamp;
	

}
