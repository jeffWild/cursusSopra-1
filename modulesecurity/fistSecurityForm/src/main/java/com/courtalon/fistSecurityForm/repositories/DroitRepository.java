package com.courtalon.fistSecurityForm.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.courtalon.fistSecurityForm.metier.DroitUtilisateur;

public interface DroitRepository 
		extends PagingAndSortingRepository<DroitUtilisateur, Integer> {
	public DroitUtilisateur findByDroit(String droit);
}
