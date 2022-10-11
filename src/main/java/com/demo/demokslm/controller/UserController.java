package com.demo.demokslm.controller;

import com.demo.demokslm.pojo.User;
import com.demo.demokslm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    //get user by id
    @GetMapping(value = "/{id}")
    public User findUser(@PathVariable Integer id){
        User user = userService.findUserById(id);
        //System.out.println(user);
        return user;
    }

    @PostMapping
    public String addUser(@RequestBody User user){
        userService.addUser(user);
        return "Success";
    }

    @PutMapping
    public String adjustUser(@RequestBody User user){
        userService.updateUser(user);
        return "Success";
    }

    @DeleteMapping(value = "/{id}")
    public String deleteUserById(@PathVariable Integer id){
        userService.deleteUserById(id);
        return "Success";
    }

}
