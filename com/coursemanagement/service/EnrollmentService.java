package com.coursemanagement.service;

import com.coursemanagement.model.*;
import com.coursemanagement.exceptions.*;
import java.util.*;

/**
 * Enrollment Service for student course purchases
 * Demonstrates: Business logic, Exception handling, Collections
 */
public class EnrollmentService {
    
    private List<Enrollment> enrollments;
    private int nextEnrollmentId;
    
    public EnrollmentService() {
        this.enrollments = new ArrayList<>();
        this.nextEnrollmentId = 1;
    }
    
    /**
     * Load enrollments into system
     */
    public void loadEnrollments(List<Enrollment> enrollmentList) {
        this.enrollments = enrollmentList;
        for (Enrollment e : enrollments) {
            if (e.getEnrollmentId() >= nextEnrollmentId) {
                nextEnrollmentId = e.getEnrollmentId() + 1;
            }
        }
    }
    
    /**
     * Student: Enroll in course (purchase or free)
     */
    public Enrollment enrollStudentInCourse(Student student, Course course) 
            throws InsufficientBalanceException {
        
        // Check if already enrolled
        if (isStudentEnrolled(student.getUserId(), course.getCourseId())) {
            System.out.println(" Already enrolled in this course!");
            return null;
        }
        
        double price = course.isFree() ? 0.0 : course.getPrice();
        
        // Check if student can purchase
        if (!course.canPurchase(student.getWalletBalance())) {
            throw new InsufficientBalanceException(
                "Insufficient balance! Required: $" + price + ", Available: $" + student.getWalletBalance()
            );
        }
        
        // Process enrollment
        if (!course.isFree()) {
            student.deductBalance(price);
        }
        
        Enrollment enrollment = new Enrollment(nextEnrollmentId++, student.getUserId(), course.getCourseId(), price);
        enrollments.add(enrollment);
        student.addCourse(course.getCourseId());
        course.incrementEnrollment();
        
        System.out.println(" Successfully enrolled in: " + course.getCourseName() + 
                          (course.isFree() ? " (FREE)" : " (Paid: $" + price + ")"));
        
        return enrollment;
    }
    
    /**
     * Check if student is enrolled in course
     */
    public boolean isStudentEnrolled(int studentId, int courseId) {
        return enrollments.stream()
                .anyMatch(e -> e.getStudentId() == studentId && e.getCourseId() == courseId);
    }
    
    /**
     * Get enrollments by student
     */
    public List<Enrollment> getEnrollmentsByStudent(int studentId) {
        List<Enrollment> studentEnrollments = new ArrayList<>();
        for (Enrollment e : enrollments) {
            if (e.getStudentId() == studentId) {
                studentEnrollments.add(e);
            }
        }
        return studentEnrollments;
    }
    
    /**
     * Get all enrollments
     */
    public List<Enrollment> getAllEnrollments() {
        return new ArrayList<>(enrollments);
    }
    
    /**
     * Get total revenue
     */
    public double getTotalRevenue() {
        double total = 0;
        for (Enrollment e : enrollments) {
            total += e.getPricePaid();
        }
        return total;
    }
}
