package com.night.cache.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

/**
 * @Cacheable 注解在方法上，表示该方法的返回结果是可以缓存的。
 *  也就是说，该方法的返回结果会放在缓存中，以便于以后使用相同的参数调用该方法时，会返回缓存中的值，而不会实际执行该方法。
 * @author night
 */
@RestController
public class RedisCacheController {

    @GetMapping("/demo")
    public String demo(@PathParam("id") Integer id){
        return "success " + id;
    }

    @GetMapping("/getCache")
    public String getCache(@PathParam("id") Integer id) {
        return id.toString();
    }

}
