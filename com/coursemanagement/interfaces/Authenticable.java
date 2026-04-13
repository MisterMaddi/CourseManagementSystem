package com.coursemanagement.interfaces;

import com.coursemanagement.exceptions.InvalidCredentialsException;

/**
 * Interface for authentication
 * Demonstrates: Interface concept
 */
public interface Authenticable {
    boolean login(String email, String password) throws InvalidCredentialsException;
    void logout();
}
