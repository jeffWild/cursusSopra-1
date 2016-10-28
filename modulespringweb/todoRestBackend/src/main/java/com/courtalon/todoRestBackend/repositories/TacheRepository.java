package com.courtalon.todoRestBackend.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.courtalon.todoRestBackend.metier.Tache;

public interface TacheRepository extends PagingAndSortingRepository<Tache, Integer> {

}
