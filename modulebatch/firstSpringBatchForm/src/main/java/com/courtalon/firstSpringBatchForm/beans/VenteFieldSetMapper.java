package com.courtalon.firstSpringBatchForm.beans;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

public class VenteFieldSetMapper implements FieldSetMapper<Vente> {

	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	
	@Override
	public Vente mapFieldSet(FieldSet champs) throws BindException {
		Date d = new Date();
		try {
			d = dateFormat.parse(champs.readString(4));
		} catch (ParseException e) {e.printStackTrace();}
		return new Vente(champs.readInt(0),
				champs.readBigDecimal(1),
				champs.readInt(2),
				champs.readString(3),
				d);
	}

}
