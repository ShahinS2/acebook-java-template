package com.makersacademy.acebook.repository;

import com.makersacademy.acebook.model.Friends;
import com.makersacademy.acebook.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendRepository extends JpaRepository<Friends,Integer> {

    boolean existsByFirstUserAndSecondUser(User first,User second);

    List<Friends> findByFirstUser(User user);
    List<Friends> findBySecondUser(User user);

}