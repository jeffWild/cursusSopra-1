package com.courtalon.springAOPForm.beans;

public class TextUtils implements ITextUtils {
	
	/* (non-Javadoc)
	 * @see com.courtalon.springAOPForm.beans.ITextUtils#inversion(java.lang.String)
	 */
	@Override
	public String inversion(String chaine) {
		System.out.println("dans la methode inversion");
		if (chaine.length() > 100)
			throw new RuntimeException("chaine trop longue");
		StringBuilder sb = new StringBuilder(chaine.length());
		// je copie la chaine de sa fin a son début
		for (int i = chaine.length() - 1; i >= 0; i--) {
			sb.append(chaine.charAt(i));
		}
		return sb.toString();
	}
	
	/* (non-Javadoc)
	 * @see com.courtalon.springAOPForm.beans.ITextUtils#compteVoyelle(java.lang.String)
	 */
	@Override
	public int compteVoyelle(String chaine) {
		String voyelles = "aeiouy";
		int compteur = 0;
		for (int i = 0; i < chaine.length(); i++) {
			char c = chaine.charAt(i);
			// est ce que c est dans me chaine de voyelles ? oui ? c'est une voyelle
			if (voyelles.indexOf(c) != -1)
				compteur++;
		}
		return compteur;
	}
	
	
	/*
	 * 
	 * exemple d'utilisation du StringBuilder
	 *  pour les operations "lourdes" sur les chaines de caracteres
	 *  en effet, la classe String, bien que très pratique, n'est pas
	 * adapté de par son immutabilité (pour des raisons de performances)
	 * 
	public String testConcatenation(int compteur) {
		String buffer = "";
		for (int i = 0; i < compteur; i++) {
			buffer += "to";
			if (i % 10000 == 0)
				System.out.println("iteration no " + i);
		}
		return buffer;
	}

	public String testConcatenation2(int compteur) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < compteur; i++) {
			sb.append("to");
			if (i % 10000 == 0)
				System.out.println("iteration no " + i);
		}
		return sb.toString();
	}*/
}
