package com.coursemanagement.service;

import com.coursemanagement.model.*;
import java.io.*;
import java.util.*;

/**
 * File Service for reading and writing data to files
 * Demonstrates: FILE I/O Operations, Serialization, Exception Handling
 */
public class FileService {
    
    // File paths
    private static final String USERS_FILE = "users.dat";
    private static final String COURSES_FILE = "courses.dat";
    private static final String ENROLLMENTS_FILE = "enrollments.dat";
    
    // ========== USER FILE OPERATIONS ==========
    
    /**
     * Write users to file using Serialization
     */
    public void writeUsersToFile(List<User> users) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(USERS_FILE))) {
            oos.writeObject(users);
            System.out.println(" Users data saved to file successfully!");
        }
    }
    
    /**
     * Read users from file using Deserialization
     */
    @SuppressWarnings("unchecked")
    public List<User> readUsersFromFile() throws IOException, ClassNotFoundException {
        File file = new File(USERS_FILE);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(USERS_FILE))) {
            List<User> users = (List<User>) ois.readObject();
            System.out.println(" Users data loaded from file successfully!");
            return users;
        }
    }
    
    // ========== COURSE FILE OPERATIONS ==========
    
    /**
     * Write courses to file using Serialization
     */
    public void writeCoursesToFile(List<Course> courses) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(COURSES_FILE))) {
            oos.writeObject(courses);
            System.out.println(" Courses data saved to file successfully!");
        }
    }
    
    /**
     * Read courses from file using Deserialization
     */
    @SuppressWarnings("unchecked")
    public List<Course> readCoursesFromFile() throws IOException, ClassNotFoundException {
        File file = new File(COURSES_FILE);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(COURSES_FILE))) {
            List<Course> courses = (List<Course>) ois.readObject();
            System.out.println(" Courses data loaded from file successfully!");
            return courses;
        }
    }
    
    // ========== ENROLLMENT FILE OPERATIONS ==========
    
    /**
     * Write enrollments to file using Serialization
     */
    public void writeEnrollmentsToFile(List<Enrollment> enrollments) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ENROLLMENTS_FILE))) {
            oos.writeObject(enrollments);
            System.out.println(" Enrollments data saved to file successfully!");
        }
    }
    
    /**
     * Read enrollments from file using Deserialization
     */
    @SuppressWarnings("unchecked")
    public List<Enrollment> readEnrollmentsFromFile() throws IOException, ClassNotFoundException {
        File file = new File(ENROLLMENTS_FILE);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ENROLLMENTS_FILE))) {
            List<Enrollment> enrollments = (List<Enrollment>) ois.readObject();
            System.out.println(" Enrollments data loaded from file successfully!");
            return enrollments;
        }
    }
    
    // ========== TEXT FILE OPERATIONS (Alternative approach) ==========
    
    /**
     * Export courses to CSV text file
     */
    public void exportCoursesToCSV(List<Course> courses, String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write("CourseID,CourseName,Instructor,Price,IsFree,Category,EnrolledStudents\n");
            for (Course course : courses) {
                writer.write(String.format("%d,%s,%s,%.2f,%b,%s,%d\n",
                    course.getCourseId(),
                    course.getCourseName(),
                    course.getInstructor(),
                    course.getPrice(),
                    course.isFree(),
                    course.getCategory(),
                    course.getEnrolledStudents()));
            }
            System.out.println("✓ Courses exported to CSV: " + filename);
        }
    }
    
    /**
     * Generate user report to text file
     */
    public void generateUserReport(List<User> users, String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write("========== USER REPORT ==========\n\n");
            writer.write("Total Users: " + users.size() + "\n\n");
            
            int adminCount = 0, studentCount = 0;
            for (User user : users) {
                if (user instanceof Admin) adminCount++;
                if (user instanceof Student) studentCount++;
                
                writer.write(user.toString() + "\n");
            }
            
            writer.write("\n========== SUMMARY ==========\n");
            writer.write("Admins: " + adminCount + "\n");
            writer.write("Students: " + studentCount + "\n");
            
            System.out.println(" User report generated: " + filename);
        }
    }
}
