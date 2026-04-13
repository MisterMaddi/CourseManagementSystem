package com.coursemanagement.model;

import com.coursemanagement.interfaces.Purchasable;
import com.coursemanagement.interfaces.Searchable;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Course entity class
 * Demonstrates: Interfaces, Collections, Encapsulation
 */
public class Course implements Serializable, Purchasable, Searchable {
    private static final long serialVersionUID = 1L;
    
    private int courseId;
    private String courseName;
    private String description;
    private String instructor;
    private double price;
    private boolean isFree;
    private String category;
    private List<Video> videos;
    private List<Quiz> quizzes;
    private int enrolledStudents;
    
    // Constructor
    public Course(int courseId, String courseName, String description, String instructor, 
                  double price, boolean isFree, String category) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.description = description;
        this.instructor = instructor;
        this.price = price;
        this.isFree = isFree;
        this.category = category;
        this.videos = new ArrayList<>();
        this.quizzes = new ArrayList<>();
        this.enrolledStudents = 0;
    }
    
    // Getters and Setters
    public int getCourseId() { return courseId; }
    public void setCourseId(int courseId) { this.courseId = courseId; }
    
    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public String getInstructor() { return instructor; }
    public void setInstructor(String instructor) { this.instructor = instructor; }
    
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    
    public boolean isFree() { return isFree; }
    public void setFree(boolean free) { isFree = free; }
    
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    
    public List<Video> getVideos() { return videos; }
    public void setVideos(List<Video> videos) { this.videos = videos; }
    
    public List<Quiz> getQuizzes() { return quizzes; }
    public void setQuizzes(List<Quiz> quizzes) { this.quizzes = quizzes; }
    
    public int getEnrolledStudents() { return enrolledStudents; }
    public void incrementEnrollment() { this.enrolledStudents++; }
    
    // Interface implementation - Purchasable
    @Override
    public double getPurchasePrice() {
        return price;
    }
    
    @Override
    public boolean canPurchase(double userBalance) {
        return isFree || userBalance >= price;
    }
    
    // Interface implementation - Searchable
    @Override
    public boolean matchesQuery(String query) {
        query = query.toLowerCase();
        return courseName.toLowerCase().contains(query) || 
               description.toLowerCase().contains(query) ||
               category.toLowerCase().contains(query);
    }
    
    // Business methods
    public void addVideo(Video video) {
        videos.add(video);
    }
    
    public void addQuiz(Quiz quiz) {
        quizzes.add(quiz);
    }
    
    public void displayCourseInfo() {
        System.out.println("\n--- Course Details ---");
        System.out.println("ID: " + courseId);
        System.out.println("Name: " + courseName);
        System.out.println("Instructor: " + instructor);
        System.out.println("Category: " + category);
        System.out.println("Price: $" + (isFree ? "FREE" : price));
        System.out.println("Videos: " + videos.size());
        System.out.println("Quizzes: " + quizzes.size());
        System.out.println("Enrolled: " + enrolledStudents);
        System.out.println("Description: " + description);
        System.out.println("--------------------\n");
    }
    
    @Override
    public String toString() {
        return "Course{id=" + courseId + ", name='" + courseName + "', price=" + price + ", free=" + isFree + "}";
    }
}
