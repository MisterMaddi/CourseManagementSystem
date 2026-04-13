package com.coursemanagement.service;

import com.coursemanagement.model.*;
import com.coursemanagement.exceptions.InvalidCredentialsException;
import com.coursemanagement.interfaces.Authenticable;
import java.util.*;

/**
 * Authentication Service
 * Demonstrates: Interface implementation, Exception handling
 */
public class AuthService implements Authenticable {
    
    private Map<String, User> userDatabase; // Collection - HashMap
    private User currentUser;
    
    public AuthService() {
        this.userDatabase = new HashMap<>();
    }
    
    /**
     * Load users into authentication system
     */
    public void loadUsers(List<User> users) {
        userDatabase.clear();
        for (User user : users) {
            userDatabase.put(user.getEmail(), user);
        }
    }
    
    /**
     * Register new user
     */
    public void registerUser(User user) {
        userDatabase.put(user.getEmail(), user);
        System.out.println(" User registered successfully: " + user.getName());
    }
    
    /**
     * Login implementation - Demonstrates exception handling
     */
    @Override
    public boolean login(String email, String password) throws InvalidCredentialsException {
        if (!userDatabase.containsKey(email)) {
            throw new InvalidCredentialsException("Email not found!");
        }
        
        User user = userDatabase.get(email);
        if (!user.getPassword().equals(password)) {
            throw new InvalidCredentialsException("Incorrect password!");
        }
        
        currentUser = user;
        System.out.println(" Login successful! Welcome " + user.getName());
        return true;
    }
    
    @Override
    public void logout() {
        if (currentUser != null) {
            System.out.println(" User " + currentUser.getName() + " logged out successfully!");
            currentUser = null;
        }
    }
    
    public User getCurrentUser() {
        return currentUser;
    }
    
    public boolean isAdmin() {
        return currentUser != null && currentUser instanceof Admin;
    }
    
    public boolean isStudent() {
        return currentUser != null && currentUser instanceof Student;
    }
    
    public List<User> getAllUsers() {
        return new ArrayList<>(userDatabase.values());
    }
}
