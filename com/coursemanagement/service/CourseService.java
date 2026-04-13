package com.coursemanagement.service;

import com.coursemanagement.model.*;
import com.coursemanagement.exceptions.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Course Management Service
 * Demonstrates: Collections, Exception handling, Business logic
 */
public class CourseService {
    
    private Map<Integer, Course> courseDatabase; // Collection - HashMap
    private int nextCourseId;
    
    public CourseService() {
        this.courseDatabase = new HashMap<>();
        this.nextCourseId = 1;
    }
    
    /**
     * Load courses into system
     */
    public void loadCourses(List<Course> courses) {
        courseDatabase.clear();
        for (Course course : courses) {
            courseDatabase.put(course.getCourseId(), course);
            if (course.getCourseId() >= nextCourseId) {
                nextCourseId = course.getCourseId() + 1;
            }
        }
    }
    
    /**
     * Admin: Create new course
     */
    public Course createCourse(String name, String description, String instructor, 
                              double price, boolean isFree, String category) throws DuplicateCourseException {
        
        // Check for duplicate course name
        for (Course c : courseDatabase.values()) {
            if (c.getCourseName().equalsIgnoreCase(name)) {
                throw new DuplicateCourseException("Course with name '" + name + "' already exists!");
            }
        }
        
        Course newCourse = new Course(nextCourseId++, name, description, instructor, price, isFree, category);
        courseDatabase.put(newCourse.getCourseId(), newCourse);
        System.out.println(" Course created successfully: " + name);
        return newCourse;
    }
    
    /**
     * Admin: Update existing course
     */
    public void updateCourse(int courseId, String name, String description, double price) 
            throws CourseNotFoundException {
        
        if (!courseDatabase.containsKey(courseId)) {
            throw new CourseNotFoundException("Course with ID " + courseId + " not found!");
        }
        
        Course course = courseDatabase.get(courseId);
        course.setCourseName(name);
        course.setDescription(description);
        course.setPrice(price);
        System.out.println(" Course updated successfully!");
    }
    
    /**
     * Admin: Delete course
     */
    public void deleteCourse(int courseId) throws CourseNotFoundException {
        if (!courseDatabase.containsKey(courseId)) {
            throw new CourseNotFoundException("Course with ID " + courseId + " not found!");
        }
        
        Course removed = courseDatabase.remove(courseId);
        System.out.println(" Course deleted: " + removed.getCourseName());
    }
    
    /**
     * Admin: Add video to course
     */
    public void addVideoToCourse(int courseId, Video video) throws CourseNotFoundException {
        Course course = getCourseById(courseId);
        course.addVideo(video);
        System.out.println(" Video added to course: " + video.getTitle());
    }
    
    /**
     * Admin: Add quiz to course
     */
    public void addQuizToCourse(int courseId, Quiz quiz) throws CourseNotFoundException {
        Course course = getCourseById(courseId);
        course.addQuiz(quiz);
        System.out.println(" Quiz added to course: " + quiz.getTitle());
    }
    
    /**
     * Get course by ID
     */
    public Course getCourseById(int courseId) throws CourseNotFoundException {
        if (!courseDatabase.containsKey(courseId)) {
            throw new CourseNotFoundException("Course with ID " + courseId + " not found!");
        }
        return courseDatabase.get(courseId);
    }
    
    /**
     * Get all courses
     */
    public List<Course> getAllCourses() {
        return new ArrayList<>(courseDatabase.values());
    }
    
    /**
     * Search courses by query
     */
    public List<Course> searchCourses(String query) {
        return courseDatabase.values().stream()
                .filter(course -> course.matchesQuery(query))
                .collect(Collectors.toList());
    }
    
    /**
     * Get free courses
     */
    public List<Course> getFreeCourses() {
        return courseDatabase.values().stream()
                .filter(Course::isFree)
                .collect(Collectors.toList());
    }
    
    /**
     * Get courses by category
     */
    public List<Course> getCoursesByCategory(String category) {
        return courseDatabase.values().stream()
                .filter(course -> course.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }
}
