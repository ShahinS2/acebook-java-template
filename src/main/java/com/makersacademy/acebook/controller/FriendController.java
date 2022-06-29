package com.makersacademy.acebook.controller;

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.makersacademy.acebook.model.Authority;
import com.makersacademy.acebook.model.Friends;
import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.repository.AuthoritiesRepository;
import com.makersacademy.acebook.repository.FriendRepository;
import com.makersacademy.acebook.repository.UserRepository;

import java.net.http.HttpClient.Redirect;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.print.attribute.standard.DateTimeAtCompleted;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class FriendController {

  @Autowired
  FriendRepository friendRepository;
  @Autowired
  AuthoritiesRepository authoritiesRepository;
  @Autowired
  UserRepository userRepository;
  
  @PostMapping("/addFriend")
  public RedirectView addUser(@RequestParam("friendId")String friendId) throws NullPointerException{
    
    Authentication loggedIn = SecurityContextHolder.getContext().getAuthentication();
    String currentUserID = loggedIn.getName();
    
    User user = userRepository.findByUsername(currentUserID);
    User user2 = userRepository.findByUsername(friendId);
    saveFriend(user, user2);
    return new RedirectView("/posts");
  }

 
  @GetMapping("/listFriends")
  public ResponseEntity<List<Friends>> getFriends() {
      Authentication loggedIn = SecurityContextHolder.getContext().getAuthentication();
      String currentUserID = loggedIn.getName();
      User user = userRepository.findByUsername(currentUserID);
      List<Friends> myFriends = friendRepository.findByFirstUser(user);
      return new ResponseEntity<List<Friends>>(myFriends, HttpStatus.OK);
  }

  public void saveFriend(User name, User friend2) {
    Friends friend = new Friends();
    User firstuser = name;
    User seconduser = friend2;
    if(name.getID() > friend2.getID()){
         firstuser = friend2;
         seconduser = name;
    }
    if( !(friendRepository.existsByFirstUserAndSecondUser(firstuser,seconduser)) ){
        friend.setCreatedDate(LocalDate.now());
        friend.setFirstUser(firstuser);
        friend.setSecondUser(seconduser);
        System.out.println(firstuser);
        System.out.println(seconduser);
        friendRepository.save(friend);
    }
}

}
