package com.courtalon.firstMVCAjaxForm.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.courtalon.firstMVCAjaxForm.metier.Message;

public interface MessageRepository 
		extends PagingAndSortingRepository<Message, Integer> {

	//Iterable<Message> findByTitreContaining(String titre);
	// Pageable est l'interface implémentée par PageRequest
	Page<Message> findByTitreContaining(String titre, Pageable p);
}
