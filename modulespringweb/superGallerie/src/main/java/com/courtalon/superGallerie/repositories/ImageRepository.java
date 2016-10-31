package com.courtalon.superGallerie.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.courtalon.superGallerie.metier.Image;

public interface ImageRepository extends PagingAndSortingRepository<Image, Integer> {

}
