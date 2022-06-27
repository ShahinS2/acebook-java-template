package com.makersacademy.acebook.controller;

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.makersacademy.acebook.model.Authority;
import com.makersacademy.acebook.model.Friends;
import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.repository.AuthoritiesRepository;
import com.makersacademy.acebook.repository.FriendRepository;
import com.makersacademy.acebook.repository.UserRepository;

import java.sql.Date;
import java.util.List;

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
  
  @GetMapping("addFriend")
  public ResponseEntity<?> addUser(@RequestParam("friendId")String friendId) throws NullPointerException{
  
    Authentication loggedIn = SecurityContextHolder.getContext().getAuthentication();
    String currentUser = loggedIn.getName();
      User user = userRepository.findById(id).orElseThrow();
      Friends second_friend = new Friends().getSecondUser();
      return ResponseEntity.ok("Friend added successfully");
  }


  @GetMapping("listFriends")
  public ResponseEntity<List<User>> getFriends() {
      List<User> myFriends = friendService.getFriends();
      return new ResponseEntity<List<User>>(myFriends, HttpStatus.OK);
  }

  public void saveFriend(String name, Long id) throws NullPointerException{

    Friends friend = new Friends();
    User user1 = userRepository.findByUsername(name);
    User user2 = userRepository.findById(id).orElseThrow();
    User firstuser = user1;
    User seconduser = user2;
    if(user1.getID() > user2.getID()){
         firstuser = user2;
         seconduser = user1;
    }
    if( !(friendRepository.existsByFirstUserAndSecondUser(firstuser,seconduser)) ){
        friend.setCreatedDate(new Date(id));
        friend.setFirstUser(firstuser);
        friend.setSecondUser(seconduser);
        friendRepository.save(friend);
    }
}

}
