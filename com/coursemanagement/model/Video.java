package com.coursemanagement.model;

import java.io.Serializable;

/**
 * Video content class
 * Demonstrates: Encapsulation, Serialization
 */
public class Video implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int videoId;
    private String title;
    private String videoUrl;
    private int durationMinutes;
    private int orderIndex;
    
    public Video(int videoId, String title, String videoUrl, int durationMinutes, int orderIndex) {
        this.videoId = videoId;
        this.title = title;
        this.videoUrl = videoUrl;
        this.durationMinutes = durationMinutes;
        this.orderIndex = orderIndex;
    }
    
    // Getters and Setters
    public int getVideoId() { return videoId; }
    public void setVideoId(int videoId) { this.videoId = videoId; }
    
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    public String getVideoUrl() { return videoUrl; }
    public void setVideoUrl(String videoUrl) { this.videoUrl = videoUrl; }
    
    public int getDurationMinutes() { return durationMinutes; }
    public void setDurationMinutes(int durationMinutes) { this.durationMinutes = durationMinutes; }
    
    public int getOrderIndex() { return orderIndex; }
    public void setOrderIndex(int orderIndex) { this.orderIndex = orderIndex; }
    
    @Override
    public String toString() {
        return "Video{id=" + videoId + ", title='" + title + "', duration=" + durationMinutes + "min}";
    }
}

