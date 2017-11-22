package com.infoshare.web.services.persistence.user;

import com.infoshare.web.model.user.User;

import java.util.List;

public interface IUserService {


  void  add(User user);

  User get(long userId);

  List<User> getUserByEmail(String userEmail);

  void update(User user);

  List<User> getAllUsers();

  public void addDefaultAdminUser();



}
