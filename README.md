# 📚 Course Management System (Java)

A CLI-based Course Management System developed using Object-Oriented Programming principles. This system enables management of users, courses, enrollments, and payments with a modular layered architecture.

---

## 🧠 Overview
This project simulates a lightweight academic platform where admins can manage courses and students can enroll, access content, and handle payments.

It is designed using core OOP concepts such as encapsulation, inheritance, polymorphism, and abstraction, along with clean architecture principles.

---

## 🚀 Features

### 👤 User Management
- Register and login system
- Role-based access (Admin / Student)

### 📘 Course Management
- Create, update, delete courses
- Attach videos and quizzes
- View and search course catalog

### 💳 Enrollment & Payments
- Enroll in courses
- Wallet-based payment system
- Discount handling logic

### 📊 Data Management
- File-based persistence (CSV / serialized data)
- Export reports (TXT/CSV)
- Backup support

---

## 🏗️ Architecture

The system follows a layered architecture:

- **Presentation Layer** → CLI menus and user interaction  
- **Service Layer** → Business logic (Auth, Course, Enrollment)  
- **Data Access Layer** → File handling and persistence  
- **Utility Layer** → Validation, ID generation, constants  

---

## 🧩 Design Patterns Used

- **Singleton Pattern** → FileService instance management  
- **Factory Pattern** → Dynamic user creation (Admin / Student)  
- **Strategy Pattern** → Flexible payment calculation  

---

## 🛠️ Tech Stack

- Java  
- Object-Oriented Programming  
- File Handling (CSV / Serialization)  
- CLI-based interface  

---

## ▶️ How to Run

### Compile
