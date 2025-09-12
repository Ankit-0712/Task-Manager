package com.example.taskmanager.service;

import com.example.taskmanager.model.User;
import com.example.taskmanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // create a new user
    public User createUser(User user){
        return userRepository.save(user);
    }

    //update existing user
    public User updateUser(Long id, User updatedUser){
        return userRepository.findById(id).map(user->{
            user.setFirstName(updatedUser.getFirstName());
            user.setLastName(updatedUser.getLastName());
            user.setEmail(updatedUser.getEmail());
            user.setPassword(updatedUser.getPassword());
            user.setTimezone(updatedUser.getTimezone());
            user.setIsActive(updatedUser.getIsActive());
            return userRepository.save(user);
        }).orElseThrow(()-> new RuntimeException("User not found with id" + id));
    }


    //Get All users
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    //get user by id
    public User getUserById(Long id){
        return userRepository.findById(id)
                .orElseThrow(()->new RuntimeException("user not Found with id"));
    }

    //delete user by id
    public void deleteUser(Long id){
        if(!userRepository.existsById(id)){
            throw new RuntimeException("User not found with id" + id);
        }

        userRepository.deleteById(id);

    }

}
