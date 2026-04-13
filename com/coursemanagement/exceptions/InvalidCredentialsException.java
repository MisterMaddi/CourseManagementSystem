package com.coursemanagement.exceptions;

/**
 * Custom exception for invalid login credentials
 * Demonstrates: Exception Handling - Custom exceptions
 */
public class InvalidCredentialsException extends Exception {
    public InvalidCredentialsException(String message) {
        super(message);
    }
}
