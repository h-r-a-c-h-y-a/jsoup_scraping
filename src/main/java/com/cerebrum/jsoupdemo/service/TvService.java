package com.cerebrum.jsoupdemo.service;

import com.cerebrum.jsoupdemo.model.TV;

import java.util.List;

public interface TvService {
    List<TV> getAllBy(int page, int limit);
    void create(TV tv);
}
