package com.cerebrum.jsoupdemo.service.impl;

import com.cerebrum.jsoupdemo.model.TV;
import com.cerebrum.jsoupdemo.repository.TvRepository;
import com.cerebrum.jsoupdemo.service.TvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TvServiceImpl implements TvService {

    @Autowired
    private  TvRepository repo;


    @Override
    public List<TV> getAllBy(int page, int limit) {
        return repo.findAll(PageRequest.of(page, limit)).getContent();
    }

    @Override
    public void create(TV tv) {
        repo.save(tv);
    }
}
