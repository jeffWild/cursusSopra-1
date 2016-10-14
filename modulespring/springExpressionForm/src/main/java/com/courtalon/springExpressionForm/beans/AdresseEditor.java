package com.courtalon.springExpressionForm.beans;

import java.beans.PropertyEditorSupport;

public class AdresseEditor extends PropertyEditorSupport {

	@Override
	public String getAsText() {
		System.out.println("conversion de adresse vers texte");
		// nous récupérons le bean a convertir en texte
		Adresse a = (Adresse)getValue();
		StringBuilder sb = new StringBuilder();
		sb.append(a.getRue())
		  .append(';')
		  .append(a.getVille())
		  .append(';')
		  .append(a.getCodePostal())
		  .append(';')
		  .append(a.getPays());
		// et nous renvoyons cette adresse sous forme de texte
		return sb.toString();
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (text == null || text.isEmpty())
			throw new IllegalArgumentException("not a correct adress(null or empty)");
		// découpage via le separateur ';'
		String[] champs = text.split(";");
		if (champs.length != 4)
			throw new IllegalArgumentException("not a correct adress(invalid format)");
		System.out.println("conversion de texte vers adresse");
		// conversion en objet adresse
		setValue(new Adresse(champs[0], champs[1], champs[2], champs[3]));
	}

	
}
