package com.controller;

import com.model.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestfullController {
    //    http://localhost:8080/boot/user/111
    @RequestMapping("boot/user/{id}")
    public Object user(@PathVariable("id") Integer id) {
        User user = new User();
        user.setId(id);
        user.setName("ceshi");
        user.setAge(18);
        return user;
    }

    //    http://localhost:8080/boot/user/111/xiaoming
    @RequestMapping("boot/user/{id}/{name}")
    public Object user(@PathVariable("id") Integer id, @PathVariable("name") String name) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setAge(18);
        return user;
    }
}
