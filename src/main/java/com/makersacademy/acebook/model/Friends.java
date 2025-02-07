package com.makersacademy.acebook.model;

import java.sql.Date;
import java.time.LocalDate;

import javax.annotation.Generated;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.makersacademy.acebook.repository.FriendRepository;
import lombok.Data;

@Data
@Entity
@Table(name = "friends")
public class Friends {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "created_date")
  private Date created_date;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "first_user_id", referencedColumnName = "id")
  User firstUser;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "second_user_id", referencedColumnName = "id")
  User secondUser;

  public Friends() {
  }

  public long getId(){
    return id;
  }

  public void setId(long id){
    this.id = id;
  }

  public Date getCreatedDate() {
    return created_date;
  }

  public void setCreatedDate(LocalDate localDate) {
    this.created_date = Date.valueOf(localDate.now());
}

public User getFirstUser() {
    return firstUser;
}

public void setFirstUser(User firstUser) {
    this.firstUser = firstUser;
}

public User getSecondUser() {
    return secondUser;
}

public void setSecondUser(User secondUser) {
    this.secondUser = secondUser;
}

}
