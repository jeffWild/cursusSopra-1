package com.courtalon.superGallerie.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.courtalon.superGallerie.metier.Tag;

public interface TagRepository extends PagingAndSortingRepository<Tag, Integer> {

}
