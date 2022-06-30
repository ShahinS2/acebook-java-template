package com.makersacademy.acebook.model;

import javax.persistence.*;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.Data;

import java.util.List;
import java.util.Set;

import static java.lang.Boolean.TRUE;

@Data
@Entity
@Table(name = "USERS")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private boolean enabled;
    private String photo;

    private PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public User() {
        this.enabled = TRUE;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = passwordEncoder().encode(password);;
        this.enabled = TRUE;
    }

    public User(String username, String password, boolean enabled) {
        this.username = username;
        this.password = passwordEncoder().encode(password);
        this.enabled = enabled;
    }

  /*   public Long getID(){
        return id;
    }*/

    // public List<User> getFriends(){

    //     Authentication loggedIn = SecurityContextHolder.getContext().getAuthentication();
    //     User currentUser = userRepository.findUserByEmail(currentUserDto.getEmail());
    //     List<Friends> friendsByFirstUser = friendRepository.findByFirstUser(currentUser);
    //     List<Friends> friendsBySecondUser = friendRepository.findBySecondUser(currentUser);
    //     List<User> friendUsers = new ArrayList<>();

    //     /*
    //         suppose there are 3 users with id 1,2,3.
    //         if user1 add user2 as friend database record will be first user = user1 second user = user2
    //         if user3 add user2 as friend database record will be first user = user2 second user = user3
    //         it is because of lexicographical order
    //         while calling get friends of user 2 we need to check as a both first user and the second user
    //      */
    //     for (Friends friend : friendsByFirstUser) {
    //         friendUsers.add(userRepository.findUserById(friend.getSecondUser().getId()));
    //     }
    //     for (Friends friend : friendsBySecondUser) {
    //         friendUsers.add(userRepository.findUserById(friend.getFirstUser().getId()));
    //     }
    //     return friendUsers;

    // }

    public String getUsername() { return this.username; }
    public String getPassword() { return this.password; }
    public void setUsername(String username) { this.username = username; }
    public void setPhotoSrc(String photo){ this.photo = photo;}
    public Long getID(){ return this.id;}
    @Transient
    public String getPhotosImagePath() {
        if (photo == null || id == null) return null;
      return "image/" + id + "/" + photo;

    }
    public void setPassword(String password) { this.password = passwordEncoder().encode(password);; }

}
