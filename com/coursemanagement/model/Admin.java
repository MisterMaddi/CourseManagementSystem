package com.coursemanagement.model;

/**
 * Admin class - Inherits from User
 * Demonstrates: Inheritance, Polymorphism, Method Overriding
 */
public class Admin extends User {
    private static final long serialVersionUID = 1L;
    
    private String adminLevel; // "SUPER_ADMIN" or "ADMIN"
    
    // Constructor
    public Admin(int userId, String name, String email, String password, String adminLevel) {
        super(userId, name, email, password, "ADMIN");
        this.adminLevel = adminLevel;
    }
    
    public String getAdminLevel() { return adminLevel; }
    public void setAdminLevel(String adminLevel) { this.adminLevel = adminLevel; }
    
    // Implementing abstract method - Polymorphism
    @Override
    public void displayDashboard() {
        System.out.println("\n========== ADMIN DASHBOARD ==========");
        System.out.println("Welcome Admin: " + getName());
        System.out.println("Admin Level: " + adminLevel);
        System.out.println("Email: " + getEmail());
        System.out.println("Privileges: Create, Edit, Delete Courses");
        System.out.println("=====================================\n");
    }
    
    @Override
    public String toString() {
        return "Admin{" + super.toString() + ", adminLevel='" + adminLevel + "'}";
    }
}
