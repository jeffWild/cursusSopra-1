package com.courtalon.springAcademyForm.beans;

import java.util.List;
import java.util.Random;

public class Luthier {

	private List<String> noms;
	private Random rd;
	
	
	public List<String> getNoms() {return noms;}
	public void setNoms(List<String> noms) {this.noms = noms;}
	
	public Luthier() {
		rd = new Random();
	}
	
	public IInstrument fabriqueViolon() {
		Violon v = new Violon(noms.get(rd.nextInt(noms.size())));
		return v;
	}
	
	
	
}
