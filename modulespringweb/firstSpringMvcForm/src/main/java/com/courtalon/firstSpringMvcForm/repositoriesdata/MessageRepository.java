package com.courtalon.firstSpringMvcForm.repositoriesdata;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.courtalon.firstSpringMvcForm.metier.Message;

public interface MessageRepository extends PagingAndSortingRepository<Message, Integer> {

	
}
