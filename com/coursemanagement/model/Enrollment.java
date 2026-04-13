package com.coursemanagement.model;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Enrollment entity linking Student and Course
 * Demonstrates: Composition, Encapsulation
 */
public class Enrollment implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int enrollmentId;
    private int studentId;
    private int courseId;
    private LocalDateTime enrollmentDate;
    private double pricePaid;
    private boolean completed;
    
    public Enrollment(int enrollmentId, int studentId, int courseId, double pricePaid) {
        this.enrollmentId = enrollmentId;
        this.studentId = studentId;
        this.courseId = courseId;
        this.enrollmentDate = LocalDateTime.now();
        this.pricePaid = pricePaid;
        this.completed = false;
    }
    
    // Getters and Setters
    public int getEnrollmentId() { return enrollmentId; }
    public int getStudentId() { return studentId; }
    public int getCourseId() { return courseId; }
    public LocalDateTime getEnrollmentDate() { return enrollmentDate; }
    public double getPricePaid() { return pricePaid; }
    public boolean isCompleted() { return completed; }
    public void setCompleted(boolean completed) { this.completed = completed; }
    
    @Override
    public String toString() {
        return "Enrollment{id=" + enrollmentId + ", studentId=" + studentId + ", courseId=" + courseId + ", paid=$" + pricePaid + "}";
    }
}
