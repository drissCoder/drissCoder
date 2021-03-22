package com.example.dash.service.utils.response;

import org.springframework.stereotype.Service;

@Service
public class ValidationResponse implements IValidationResponse {

	private String message;
	
	private int numberOfRows;
	
	private int numberOfActions;
    
	
	public ValidationResponse() {
		super();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getNumberOfRows() {
		return numberOfRows;
	}

	public void setNumberOfRows(int numberOfRows) {
		this.numberOfRows = numberOfRows;
	}
    
	public int getNumberOfActions() {
		return numberOfActions;
	}

	public void setNumberOfActions(int numberOfActions) {
		this.numberOfActions = numberOfActions;
	}
	
}

