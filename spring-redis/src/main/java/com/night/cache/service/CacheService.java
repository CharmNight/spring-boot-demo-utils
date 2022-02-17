package com.night.cache.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class CacheService {

    @Cacheable(cacheNames = "cache::fun", key = "#data")
    public Integer cache(Integer data){
        return data + 1;
    }
}
