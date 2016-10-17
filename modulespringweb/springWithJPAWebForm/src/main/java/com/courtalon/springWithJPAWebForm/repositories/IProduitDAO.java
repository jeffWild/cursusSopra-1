package com.courtalon.springWithJPAWebForm.repositories;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.courtalon.springWithJPAWebForm.metier.Produit;

public interface IProduitDAO {

	List<Produit> findAll();
	Produit findByID(int id);
	Produit save(Produit p);
	void remove(int id);

}