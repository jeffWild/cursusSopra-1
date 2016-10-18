package com.courtalon.firstSpringMvcForm.repositories;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.courtalon.firstSpringMvcForm.metier.Message;

public interface IMessageDAO {

	List<Message> findAll();

	// readOnly permettra a hibernate d'optimiser un peu mieux ses requettes
	// attention, a utiliser uniquement si vous etes SUR que la requete ne
	// modifie pas la base
	Message findByID(int id);

	Message save(Message m);

	boolean remove(int id);

}