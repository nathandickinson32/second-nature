package com.techelevator.dao;

import com.techelevator.model.UserFolder.RegisterUserDto;
import com.techelevator.model.UserFolder.User;
import com.techelevator.model.UserFolder.UserInformation;

import java.util.List;

public interface UserDao {

    List<User> getUsers();

    User getUserById(int id);

    User getUserByUsername(String username);

    User createUser(RegisterUserDto user);

     int getUserIdByUsername(String username);

     List<UserInformation> getAllUsersInformation(int userId);
}
