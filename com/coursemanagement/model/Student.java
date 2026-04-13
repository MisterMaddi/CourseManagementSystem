package com.coursemanagement.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Student class - Inherits from User
 * Demonstrates: Inheritance, Collections, Encapsulation
 */
public class Student extends User {
    private static final long serialVersionUID = 1L;
    
    private double walletBalance;
    private List<Integer> enrolledCourseIds; // Collection - ArrayList
    
    // Constructor
    public Student(int userId, String name, String email, String password, double walletBalance) {
        super(userId, name, email, password, "STUDENT");
        this.walletBalance = walletBalance;
        this.enrolledCourseIds = new ArrayList<>();
    }
    
    public double getWalletBalance() { return walletBalance; }
    public void setWalletBalance(double walletBalance) { this.walletBalance = walletBalance; }
    
    public List<Integer> getEnrolledCourseIds() { return enrolledCourseIds; }
    
    // Business methods
    public void addCourse(int courseId) {
        enrolledCourseIds.add(courseId);
    }
    
    public void deductBalance(double amount) {
        this.walletBalance -= amount;
    }
    
    // Implementing abstract method - Polymorphism
    @Override
    public void displayDashboard() {
        System.out.println("\n========== STUDENT DASHBOARD ==========");
        System.out.println("Welcome Student: " + getName());
        System.out.println("Email: " + getEmail());
        System.out.println("Wallet Balance: $" + walletBalance);
        System.out.println("Enrolled Courses: " + enrolledCourseIds.size());
        System.out.println("=======================================\n");
    }
    
    @Override
    public String toString() {
        return "Student{" + super.toString() + ", walletBalance=" + walletBalance + ", enrolledCourses=" + enrolledCourseIds.size() + "}";
    }
}
