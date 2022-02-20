package com.uit.Service.User;

import com.uit.Model.User;

import java.util.List;

public interface UserService {
    public void insertUser(User newUser);
    public void updateUser(User updatedUser);
    public void deleteUser(String username);
    public boolean checkUsername (String username);
    public List<User> getAllUser();
    public boolean certifyUser(String username, String password);
    public String getRole (String username);
    public User getUser (String username);
}
