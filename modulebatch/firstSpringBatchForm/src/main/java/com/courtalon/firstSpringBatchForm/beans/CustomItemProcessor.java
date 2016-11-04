package com.courtalon.firstSpringBatchForm.beans;

import org.springframework.batch.item.ItemProcessor;

public class CustomItemProcessor implements ItemProcessor<Vente, Vente> {

	@Override
	public Vente process(Vente item) throws Exception {
		System.out.println("processing item : " + item.getNom()
							+ ", " + item.getDate());
		return item;
	}

	
}
