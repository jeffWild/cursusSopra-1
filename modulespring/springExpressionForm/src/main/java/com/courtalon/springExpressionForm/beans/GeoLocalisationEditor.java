package com.courtalon.springExpressionForm.beans;

import java.beans.PropertyEditorSupport;

public class GeoLocalisationEditor extends PropertyEditorSupport {

	@Override
	public String getAsText() {
		GeoLocalisation loc = (GeoLocalisation)getValue();
		StringBuilder sb = new StringBuilder();
		
		sb.append(loc.getLongitude())
		  .append(';')
		  .append(loc.getLatitude());
		
		return sb.toString();
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (text == null || text.isEmpty())
			throw new IllegalArgumentException("geolocalisation is null or empty");
		String[] champs = text.split(";");
		if (champs.length != 2)
			throw new IllegalArgumentException("geolocalisation format is invalid");
		GeoLocalisation loc = new GeoLocalisation(
				Double.parseDouble(champs[0]),
				Double.parseDouble(champs[1]));
		setValue(loc);
	}
	
	

}
