package com.courtalon.firstMVCAjaxForm.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.courtalon.firstMVCAjaxForm.metier.Message;

public interface MessageRepository 
		extends PagingAndSortingRepository<Message, Integer> {

	
}
