package com.courtalon.firstSpringWebForm.beans;

public class Message {
	private String corps;

	public String getCorps() {return corps;}
	public void setCorps(String corps) {this.corps = corps;}
	public Message() {
		System.out.println("construction message");
	}

	
}
