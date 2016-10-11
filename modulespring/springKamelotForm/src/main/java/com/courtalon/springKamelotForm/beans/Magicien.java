package com.courtalon.springKamelotForm.beans;

import java.util.List;
import java.util.Random;

public class Magicien {
	
	private List<String> actions;
	private List<String> sujets;
	private Random rd;
	
	public List<String> getActions() {return actions;}
	public void setActions(List<String> actions) {this.actions = actions;}
	public List<String> getSujets() {return sujets;}
	public void setSujets(List<String> sujets) {this.sujets = sujets;}

	public Magicien() {
		this.rd = new Random();
	}
	
	public IQuete fournirQuete() {
		QueteEpique q = new QueteEpique(
				actions.get(rd.nextInt(actions.size())) 
				+ " " 
				+ sujets.get(rd.nextInt(sujets.size())),
				rd.nextDouble());
		return q;
	}
	
	
}
