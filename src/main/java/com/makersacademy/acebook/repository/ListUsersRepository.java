package com.makersacademy.acebook.repository;

import com.makersacademy.acebook.model.User;
import org.springframework.data.repository.CrudRepository;

public interface ListUsersRepository extends CrudRepository<User, Long>{
  
}
