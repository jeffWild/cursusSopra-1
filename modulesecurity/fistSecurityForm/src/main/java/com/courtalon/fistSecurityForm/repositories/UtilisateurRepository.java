package com.courtalon.fistSecurityForm.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.courtalon.fistSecurityForm.metier.Utilisateur;

public interface UtilisateurRepository extends PagingAndSortingRepository<Utilisateur, Integer> {

	Utilisateur findByName(String name);
}
