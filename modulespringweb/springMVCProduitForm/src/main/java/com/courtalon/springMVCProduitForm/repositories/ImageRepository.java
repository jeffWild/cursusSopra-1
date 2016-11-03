package com.courtalon.springMVCProduitForm.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.courtalon.springMVCProduitForm.metier.Image;



public interface ImageRepository 
		extends PagingAndSortingRepository<Image, Integer>,
				ImageRepositoryCustom {

}
