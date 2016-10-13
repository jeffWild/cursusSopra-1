package com.courtalon.springEventConvertForm.beans;

import org.springframework.context.ApplicationEvent;

public class CustomEvent extends ApplicationEvent {
	
	private String message;
	public String getMessage() {return message;}
	public void setMessage(String message) {this.message = message;}


	public CustomEvent(Object source, String message) {
		super(source);
		setMessage(message);
	}
	@Override
	public String toString() {
		return "CustomEvent [message=" + message + "]";
	}

	
}
