package com.demo.demokslm.controller;

import com.demo.demokslm.pojo.ResponseResult;
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
    public ResponseResult<User> findUser(@PathVariable Integer id){
        User user = userService.findUserById(id);
        return ResponseResult.success(user);
    }

    @PostMapping
    public ResponseResult<String> addUser(@RequestBody User user){
        userService.addUser(user);
        return ResponseResult.success("Successful add one User");
    }

    @PutMapping
    public ResponseResult<String> adjustUser(@RequestBody User user){
        userService.updateUser(user);
        return ResponseResult.success("Successful adjust one User");
    }

    @DeleteMapping(value = "/{id}")
    public ResponseResult<String> deleteUserById(@PathVariable Integer id){
        userService.deleteUserById(id);
        return ResponseResult.success("Successful delete one User");
    }

}
