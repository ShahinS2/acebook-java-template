package com.makersacademy.acebook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.makersacademy.acebook.repository.UserRepository;

import com.makersacademy.acebook.model.User;

@Controller
public class ListUsersController {

  @Autowired
    UserRepository userRepository;
  
  @GetMapping("/listUsers")
  public String user(Model model){
    Iterable<User> users = userRepository.findAll();
    model.addAttribute("users", users);
    return "/users/listUsers";
  }  
}
