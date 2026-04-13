package com.coursemanagement.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Quiz class with questions
 * Demonstrates: Nested collections, Encapsulation
 */
public class Quiz implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int quizId;
    private String title;
    private List<String> questions;
    private int totalMarks;
    
    public Quiz(int quizId, String title, int totalMarks) {
        this.quizId = quizId;
        this.title = title;
        this.totalMarks = totalMarks;
        this.questions = new ArrayList<>();
    }
    
    // Getters and Setters
    public int getQuizId() { return quizId; }
    public void setQuizId(int quizId) { this.quizId = quizId; }
    
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    public List<String> getQuestions() { return questions; }
    
    public int getTotalMarks() { return totalMarks; }
    public void setTotalMarks(int totalMarks) { this.totalMarks = totalMarks; }
    
    public void addQuestion(String question) {
        questions.add(question);
    }
    
    @Override
    public String toString() {
        return "Quiz{id=" + quizId + ", title='" + title + "', questions=" + questions.size() + ", marks=" + totalMarks + "}";
    }
}
