package com.night.controller;

import com.night.bean.dao.UserBean;
import com.night.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
public class DemoController {

    @Autowired
    private UserService userService;

    @GetMapping("/save")
    public void save(@PathParam("name") String name, @PathParam("age") Integer age){
        userService.saveBean(name, age);
    }

    @GetMapping("/update")
    public void update(@PathParam("id") Long id, @PathParam("name") String name, @PathParam("age") Integer age){
        userService.updateBean(id, name, age);
    }

    @GetMapping("/delete")
    public void delete(@PathParam("id") Long id){
        userService.deleteBean(id);
    }

    @GetMapping("/list")
    public ResponseEntity<List<UserBean>> doList(){
        return ResponseEntity.ok(userService.doList());
    }
}
