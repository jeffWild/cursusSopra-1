package com.courtalon.springMVCProduitForm.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.courtalon.springMVCProduitForm.metier.Produit;

public interface ProduitRepository extends PagingAndSortingRepository<Produit, Integer> {
	
	Page<Produit> findByNomContaining(String nom, Pageable p);
	Page<Produit> findByPrixGreaterThanEqual(double prix, Pageable p);
	Page<Produit> findByNomContainingAndPrixGreaterThanEqual(String nom, double prix, Pageable p);
	
}
