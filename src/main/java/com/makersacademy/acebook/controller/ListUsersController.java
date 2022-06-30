package com.makersacademy.acebook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;
import com.makersacademy.acebook.repository.UserRepository;

import com.makersacademy.acebook.model.User;

@Controller
public class ListUsersController {

  @Autowired
    UserRepository userRepository;
  
  @GetMapping("/listUsers")
  public String user(Model model){
    Iterable<User> users;
    Authentication loggedIn = SecurityContextHolder.getContext().getAuthentication();
    User user = userRepository.findByUsername(loggedIn.getName());
    // List<User> lsUsers = UserRepository.User(user);
    model.addAttribute("user", user);
    return "/users/listUsers";
  }  
}
