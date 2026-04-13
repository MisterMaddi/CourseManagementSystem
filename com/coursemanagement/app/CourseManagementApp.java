package com.coursemanagement.app;

import com.coursemanagement.model.*;
import com.coursemanagement.service.*;
import com.coursemanagement.exceptions.*;
import java.io.IOException;
import java.util.*;

/**
 * Main Course Management Application (Udemy/Coursera Clone)
 * Demonstrates: All OOP concepts + File I/O
 */
public class CourseManagementApp {
    
    private static Scanner scanner = new Scanner(System.in);
    private static FileService fileService = new FileService();
    private static AuthService authService = new AuthService();
    private static CourseService courseService = new CourseService();
    private static EnrollmentService enrollmentService = new EnrollmentService();
    
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║   COURSE MANAGEMENT SYSTEM (CMS)       ║");
        System.out.println("║                                        ║");
        System.out.println("╚════════════════════════════════════════╝\n");
        
        // Load data from files
        loadDataFromFiles();

        // Main application loop
        while (true) {
            try {
                if (authService.getCurrentUser() == null) {
                    showLoginMenu();
                } else {
                    if (authService.isAdmin()) {
                        showAdminMenu();
                    } else if (authService.isStudent()) {
                        showStudentMenu();
                    }
                }
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
    }
    
    // ========== FILE OPERATIONS ==========
    
    private static void loadDataFromFiles() {
        try {
            // Load users
            List<User> users = fileService.readUsersFromFile();
            if (users.isEmpty()) {
                // Create default users if no file exists
                users = createDefaultUsers();
                fileService.writeUsersToFile(users);
            }
            authService.loadUsers(users);
            
            // Load courses
            List<Course> courses = fileService.readCoursesFromFile();
            if (courses.isEmpty()) {
                courses = createDefaultCourses();
                fileService.writeCoursesToFile(courses);
            }
            courseService.loadCourses(courses);
            
            // Load enrollments
            List<Enrollment> enrollments = fileService.readEnrollmentsFromFile();
            enrollmentService.loadEnrollments(enrollments);
            
            System.out.println(" All data loaded successfully!\n");
            
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(" Could not load data from files. Starting fresh...\n");
            // Initialize with default data
            authService.loadUsers(createDefaultUsers());
            courseService.loadCourses(createDefaultCourses());
        }
    }
    
    private static void saveDataToFiles() {
        try {
            fileService.writeUsersToFile(authService.getAllUsers());
            fileService.writeCoursesToFile(courseService.getAllCourses());
            fileService.writeEnrollmentsToFile(enrollmentService.getAllEnrollments());
            System.out.println(" All data saved to files!");
        } catch (IOException e) {
            System.err.println(" Error saving data: " + e.getMessage());
        }
    }
    
    // ========== DEFAULT DATA CREATION ==========
    
    private static List<User> createDefaultUsers() {
        List<User> users = new ArrayList<>();
        
        users.add(new Admin(1, "Admin User", "admin@cms.com", "admin123", "SUPER_ADMIN"));
        
        users.add(new Student(2, "John Doe", "john@student.com", "john123", 500.0));
        users.add(new Student(3, "Jane Smith", "jane@student.com", "jane123", 300.0));
        users.add(new Student(4, "Alice Johnson", "alice@student.com", "alice123", 150.0));

        return users;
    }
    
    private static List<Course> createDefaultCourses() {
        List<Course> courses = new ArrayList<>();
        
        Course course1 = new Course(1, "Java Programming Masterclass", 
            "Complete Java course from beginner to advanced", "Dr. Robert Smith", 
            49.99, false, "Programming");
        course1.addVideo(new Video(1, "Introduction to Java", "https://video1.com", 30, 1));
        course1.addVideo(new Video(2, "OOP Concepts", "https://video2.com", 45, 2));
        course1.addQuiz(new Quiz(1, "Java Basics Quiz", 10));
        courses.add(course1);
        
        Course course2 = new Course(2, "Python for Data Science", 
            "Learn Python and data science libraries", "Prof. Emily Davis", 
            39.99, false, "Data Science");
        course2.addVideo(new Video(3, "Python Basics", "https://video3.com", 25, 1));
        courses.add(course2);
        
        Course course3 = new Course(3, "Web Development Bootcamp", 
            "HTML, CSS, JavaScript and React", "Mike Johnson", 
            59.99, false, "Web Development");
        courses.add(course3);
        
        Course course4 = new Course(4, "Introduction to Git & GitHub", 
            "Version control for beginners", "Sarah Williams", 
            0.0, true, "Programming");
        courses.add(course4);
        
        Course course5 = new Course(5, "DSA in Java", 
            "Data Structures and Algorithms", "Prof. Kumar", 
            0.0, true, "Programming");
        courses.add(course5);
        
        return courses;
    }
    
    // ========== LOGIN MENU ==========
    
    private static void showLoginMenu() {
        System.out.println("\n========== LOGIN MENU ==========");
        System.out.println("1. Login");
        System.out.println("2. Register as Student");
        System.out.println("3. Exit & Save");
        System.out.print("Choose option: ");
        
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline
        
        switch (choice) {
            case 1:
                handleLogin();
                break;
            case 2:
                handleStudentRegistration();
                break;
            case 3:
                saveDataToFiles();
                System.out.println("\n Thank you for using CMS! Goodbye!");
                System.exit(0);
            default:
                System.out.println(" Invalid option!");
        }
    }
    
    private static void handleLogin() {
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        
        try {
            authService.login(email, password);
            authService.getCurrentUser().displayDashboard();
        } catch (InvalidCredentialsException e) {
            System.err.println(" Login failed: " + e.getMessage());
        }
    }
    
    private static void handleStudentRegistration() {
    System.out.print("Name: ");
    String name = scanner.nextLine();
    System.out.print("Email: ");
    String email = scanner.nextLine();
    
    if (authService.getAllUsers().stream()
          .anyMatch(u -> u.getEmail().equalsIgnoreCase(email))) {
        System.out.println("Email already registered! Please login or use another email.");
        return; // stop registration here
    }
    
    System.out.print("Password: ");
    String password = scanner.nextLine();
    System.out.print("Initial Wallet Balance: $");
    double balance = scanner.nextDouble();
    scanner.nextLine();
    
    int newId = authService.getAllUsers().size() + 1;
    Student newStudent = new Student(newId, name, email, password, balance);
    authService.registerUser(newStudent);
    saveDataToFiles();
    
    System.out.println("Registration successful! You can now login.");
}

    
    // ========== ADMIN MENU ==========
    
    private static void showAdminMenu() {
        System.out.println("\n========== ADMIN MENU ==========");
        System.out.println("1. View All Courses");
        System.out.println("2. Create New Course");
        System.out.println("3. Update Course");
        System.out.println("4. Delete Course");
        System.out.println("5. Add Video to Course");
        System.out.println("6. Add Quiz to Course");
        System.out.println("7. View All Users");
        System.out.println("8. View Enrollments & Revenue");
        System.out.println("9. Export Reports");
        System.out.println("10. Logout");
        System.out.print("Choose option: ");
        
        int choice = scanner.nextInt();
        scanner.nextLine();
        
        try {
            switch (choice) {
                case 1: viewAllCourses(); break;
                case 2: createNewCourse(); break;
                case 3: updateCourse(); break;
                case 4: deleteCourse(); break;
                case 5: addVideoToCourse(); break;
                case 6: addQuizToCourse(); break;
                case 7: viewAllUsers(); break;
                case 8: viewEnrollmentsAndRevenue(); break;
                case 9: exportReports(); break;
                case 10: 
                    authService.logout();
                    saveDataToFiles();
                    break;
                default: System.out.println("Invalid option!");
            }
        } catch (Exception e) {
            System.err.println(" Error: " + e.getMessage());
        }
    }
    
    private static void viewAllCourses() {
        List<Course> courses = courseService.getAllCourses();
        System.out.println("\n========== ALL COURSES ==========");
        for (Course course : courses) {
            course.displayCourseInfo();
        }
    }
    
    private static void createNewCourse() throws DuplicateCourseException {
        System.out.print("Course Name: ");
        String name = scanner.nextLine();
        System.out.print("Description: ");
        String description = scanner.nextLine();
        System.out.print("Instructor Name: ");
        String instructor = scanner.nextLine();
        System.out.print("Category: ");
        String category = scanner.nextLine();
        System.out.print("Is Free (true/false): ");
        boolean isFree = scanner.nextBoolean();
        double price = 0;
        if (!isFree) {
            System.out.print("Price: $");
            price = scanner.nextDouble();
        }
        scanner.nextLine();
        
        courseService.createCourse(name, description, instructor, price, isFree, category);
        saveDataToFiles();
    }
    
    private static void updateCourse() throws CourseNotFoundException {
        System.out.print("Enter Course ID to update: ");
        int courseId = scanner.nextInt();
        scanner.nextLine();
        
        System.out.print("New Course Name: ");
        String name = scanner.nextLine();
        System.out.print("New Description: ");
        String description = scanner.nextLine();
        System.out.print("New Price: $");
        double price = scanner.nextDouble();
        scanner.nextLine();
        
        courseService.updateCourse(courseId, name, description, price);
        saveDataToFiles();
    }
    
    private static void deleteCourse() throws CourseNotFoundException {
        System.out.print("Enter Course ID to delete: ");
        int courseId = scanner.nextInt();
        scanner.nextLine();
        
        courseService.deleteCourse(courseId);
        saveDataToFiles();
    }
    
    private static void addVideoToCourse() throws CourseNotFoundException {
        System.out.print("Enter Course ID: ");
        int courseId = scanner.nextInt();
        scanner.nextLine();
        
        System.out.print("Video Title: ");
        String title = scanner.nextLine();
        System.out.print("Video URL: ");
        String url = scanner.nextLine();
        System.out.print("Duration (minutes): ");
        int duration = scanner.nextInt();
        System.out.print("Order Index: ");
        int order = scanner.nextInt();
        scanner.nextLine();
        
        Video video = new Video(new Random().nextInt(1000), title, url, duration, order);
        courseService.addVideoToCourse(courseId, video);
        saveDataToFiles();
    }
    
    private static void addQuizToCourse() throws CourseNotFoundException {
        System.out.print("Enter Course ID: ");
        int courseId = scanner.nextInt();
        scanner.nextLine();
        
        System.out.print("Quiz Title: ");
        String title = scanner.nextLine();
        System.out.print("Total Marks: ");
        int marks = scanner.nextInt();
        scanner.nextLine();
        
        Quiz quiz = new Quiz(new Random().nextInt(1000), title, marks);
        courseService.addQuizToCourse(courseId, quiz);
        saveDataToFiles();
    }
    
    private static void viewAllUsers() {
        System.out.println("\n========== ALL USERS ==========");
        for (User user : authService.getAllUsers()) {
            System.out.println(user);
        }
    }
    
    private static void viewEnrollmentsAndRevenue() {
        List<Enrollment> enrollments = enrollmentService.getAllEnrollments();
        System.out.println("\n========== ENROLLMENTS ==========");
        for (Enrollment e : enrollments) {
            System.out.println(e);
        }
        System.out.println("\nTotal Revenue: $" + enrollmentService.getTotalRevenue());
    }
    
    private static void exportReports() {
        try {
            fileService.exportCoursesToCSV(courseService.getAllCourses(), "courses_report.csv");
            fileService.generateUserReport(authService.getAllUsers(), "users_report.txt");
            System.out.println("Reports exported successfully!");
        } catch (IOException e) {
            System.err.println("Error exporting reports: " + e.getMessage());
        }
    }
    
    // ========== STUDENT MENU ==========
    
    private static void showStudentMenu() {
        System.out.println("\n========== STUDENT MENU ==========");
        System.out.println("1. Browse All Courses");
        System.out.println("2. Browse Free Courses");
        System.out.println("3. Search Courses");
        System.out.println("4. View Course Details");
        System.out.println("5. Enroll in Course");
        System.out.println("6. My Enrolled Courses");
        System.out.println("7. View Wallet Balance");
        System.out.println("8. Logout");
        System.out.print("Choose option: ");
        
        int choice = scanner.nextInt();
        scanner.nextLine();
        
        try {
            switch (choice) {
                case 1: browseAllCourses(); break;
                case 2: browseFreeCourses(); break;
                case 3: searchCourses(); break;
                case 4: viewCourseDetails(); break;
                case 5: enrollInCourse(); break;
                case 6: viewEnrolledCourses(); break;
                case 7: viewWalletBalance(); break;
                case 8: 
                    authService.logout();
                    saveDataToFiles();
                    break;
                default: System.out.println("Invalid option!");
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
    
    private static void browseAllCourses() {
        List<Course> courses = courseService.getAllCourses();
        System.out.println("\n========== ALL COURSES ==========");
        for (Course course : courses) {
            System.out.println(course.getCourseId() + ". " + course.getCourseName() + 
                             " - $" + (course.isFree() ? "FREE" : course.getPrice()) + 
                             " [" + course.getCategory() + "]");
        }
    }
    
    private static void browseFreeCourses() {
        List<Course> freeCourses = courseService.getFreeCourses();
        System.out.println("\n========== FREE COURSES ==========");
        for (Course course : freeCourses) {
            System.out.println(course.getCourseId() + ". " + course.getCourseName() + 
                             " [" + course.getCategory() + "]");
        }
    }
    
    private static void searchCourses() {
        System.out.print("Enter search query: ");
        String query = scanner.nextLine();
        
        List<Course> results = courseService.searchCourses(query);
        System.out.println("\n========== SEARCH RESULTS ==========");
        if (results.isEmpty()) {
            System.out.println("No courses found!");
        } else {
            for (Course course : results) {
                System.out.println(course.getCourseId() + ". " + course.getCourseName());
            }
        }
    }
    
    private static void viewCourseDetails() throws CourseNotFoundException {
        System.out.print("Enter Course ID: ");
        int courseId = scanner.nextInt();
        scanner.nextLine();
        
        Course course = courseService.getCourseById(courseId);
        course.displayCourseInfo();
        
        System.out.println("Videos:");
        for (Video video : course.getVideos()) {
            System.out.println("  - " + video);
        }
        
        System.out.println("Quizzes:");
        for (Quiz quiz : course.getQuizzes()) {
            System.out.println("  - " + quiz);
        }
    }
    
    private static void enrollInCourse() throws CourseNotFoundException, InsufficientBalanceException {
        Student student = (Student) authService.getCurrentUser();
        
        System.out.print("Enter Course ID to enroll: ");
        int courseId = scanner.nextInt();
        scanner.nextLine();
        
        Course course = courseService.getCourseById(courseId);
        enrollmentService.enrollStudentInCourse(student, course);
        saveDataToFiles();
    }
    
    private static void viewEnrolledCourses() {
        Student student = (Student) authService.getCurrentUser();
        List<Enrollment> enrollments = enrollmentService.getEnrollmentsByStudent(student.getUserId());
        
        System.out.println("\n========== MY ENROLLED COURSES ==========");
        for (Enrollment e : enrollments) {
            try {
                Course course = courseService.getCourseById(e.getCourseId());
                System.out.println("- " + course.getCourseName() + " (Paid: $" + e.getPricePaid() + ")");
            } catch (CourseNotFoundException ex) {
                System.err.println("Course not found!");
            }
        }
    }
    
    private static void viewWalletBalance() {
        Student student = (Student) authService.getCurrentUser();
        System.out.println("\nWallet Balance: $" + student.getWalletBalance());
    }
}
