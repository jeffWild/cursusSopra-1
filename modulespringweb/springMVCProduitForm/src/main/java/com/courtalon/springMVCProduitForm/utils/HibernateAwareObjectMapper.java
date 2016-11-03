package com.courtalon.springMVCProduitForm.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;

public class HibernateAwareObjectMapper extends ObjectMapper {

	private static final long serialVersionUID = 1L;
	
	public HibernateAwareObjectMapper() {
		// fournir a jackson un module hibernate4
		// ce qui lui permettra de détecter les collections lazy-loader
		registerModule(new Hibernate4Module());
	}
	
}
