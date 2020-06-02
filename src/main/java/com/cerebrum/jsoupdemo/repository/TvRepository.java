package com.cerebrum.jsoupdemo.repository;

import com.cerebrum.jsoupdemo.model.TV;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface TvRepository extends PagingAndSortingRepository<TV, Long> {

   Page<TV> findAll(Pageable pageable);
}
