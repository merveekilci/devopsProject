package com.sau.myapp2.Controllers;

import com.sau.myapp2.Models.User;
import com.sau.myapp2.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class APIController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String findAllUsers(){
        return userRepository.findAll().toString();
    }

    @GetMapping("/user/{id}")
    public String getUser(@PathVariable Integer id){
        Optional<User> usr = userRepository.findById(id);
        return usr.toString();
    }


    @GetMapping("/user")
    public String getUserReqParam(@RequestParam Integer id){
        Optional<User> usr = userRepository.findById(id);
        return usr.toString();
    }


}
