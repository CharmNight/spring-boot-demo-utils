package com.night.cache.controller;

import com.night.cache.service.CacheService;
import com.night.cache.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

@RestController
public class CacheController {

    @Autowired
    private CacheService cacheService;

    @GetMapping("/getCacheByController")
    public Integer getCacheByController(@PathParam("id") Integer id){
        return RedisUtils.get("cache::" + id);
    }

    @GetMapping("/setCacheByController")
    @Cacheable(cacheNames = "cache", key = "#id")
    public Integer setCacheByController(@PathParam("id") Integer id){
        return id;
    }

    /**
     * 移除缓存
     * @param id
     * @return
     */
    @GetMapping("/removeCache")
    @CacheEvict(cacheNames = "cache", key = "#id")
    public Integer removeCache(@PathParam("id") Integer id){
        return id;
    }

    @GetMapping("/setCacheByFunction")
    public Integer setCacheByFunction(@PathParam("id") Integer id){
        return cacheService.cache(id);
    }

}
